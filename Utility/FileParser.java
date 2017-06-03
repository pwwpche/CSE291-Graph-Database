package Utility;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuche on 6/1/17.
 * Assumptions:
 * <p>
 * 1. Property with the same name should have similar meanings.
 * "node1.description" and "node2.description" should be similar.
 * <p>
 * 2. Edge(relation) only have one label.
 * <p>
 * 3. Grammar in this file do not have syntax error.
 * <p>
 * 4. Objects with "ID" field are considered a node, and ID should be
 * unique with in the same type (nodes with same labels) of nodes.
 * <p>
 * Objects without "ID" filed are considered relation objects.
 */
public class FileParser {
    private String fileName = "";
    private Map<String, String> keyType = new HashMap<>();
    private Map<String, String> keyReference = new HashMap<>();

    private Integer globalId = 0;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }

    private Integer getUniqueGlobalId() {
        return globalId++;
    }

    private void getMetadata() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        //Read file schema
        String schemaLine = br.readLine().replaceAll("[\"{} ]", "");
        String objects[] = schemaLine.split(",");
        Map<String, String> valToKey = new HashMap<>();

        // Columns type
        for (String s : objects) {
            assert s.contains(":");
            String keyValPair[] = s.split(":");
            String objName = keyValPair[0];
            String objVal = keyValPair[1];
            String valType = objVal.matches("[A-Za-z0-9]+") ? "Object" : (
                    objVal.contains("(") ? "List" : "Other"
            );
            assert !valType.equals("Other");
            keyType.put(objName, valType);
            valToKey.put(objVal, objName);
        }

        // NOTICE:
        // type of "rel_type" is STRING!!!
        keyType.put("rel_type", "String");

        // Relation between columns
        for (String val : valToKey.keySet()) {
            if (val.contains("(")) {
                String variable = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
                assert valToKey.containsKey(variable);
                String key = valToKey.get(variable);
                keyReference.put(valToKey.get(val), key);
            }
        }

        br.close();
    }

    private Map<String, Object> lineToJsonMap(String line) {
        line = replaceQuote(line);
        JSONObject object = new JSONObject(line);
        Map<String, Object> map = JsonParser.toMap(object);
        return map;
    }

    private String replaceQuote(String str) {

        str = str.replaceAll("\"\"", "\"");
        int startIdx = 0, endIdx = 0;
        Pattern pattern = Pattern.compile("\"[A-Za-z0-9_]+\":");
        Matcher matcher = pattern.matcher(str);
        String result = "";
        while (matcher.find()) {
            endIdx = matcher.start();
            String replaced = str.substring(startIdx, endIdx);
            String kept = str.substring(matcher.start(), matcher.end());
            if (replaced.startsWith("\"") && replaced.endsWith(",") && replaced.length() > 3) {
                replaced = "\"" + replaced.substring(1, replaced.lastIndexOf("\"")).replace("\"", "`") + replaced.substring(replaced.lastIndexOf("\""));
            }
            result += replaced + kept;
            startIdx = matcher.end();
        }
        result += str.substring(startIdx);
        result = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
        return result;
    }

    /*
    *   {
    *       "node1" : {"name"->"VARCHAR(255)", "id"->"VARCHAR(100)", "description"->"VARCHAR(1000)", ...},
    *       "node2" : {"name", "id", "imdbid", ...}
    *
    *   }
    *
    *
    * */

    private Map<String, List<Pair<String, String>>> getObjectProperties(String line) throws IOException {
        Map<String, List<Pair<String, String>>> keyProperties = new HashMap<>();
        Map<String, Object> map = lineToJsonMap(line);
        for (String key : map.keySet()) {
            if (keyType.get(key).equals("Object")) {
                Map<String, Object> objectMap = (Map<String, Object>) map.get(key);
                List<String> propertyList = new ArrayList<>(objectMap.keySet());
                List<Pair<String, String>> typeList = new ArrayList<>();
                propertyList.forEach(prop -> typeList.add(
                        new Pair<>(prop, objectMap.get(prop).toString().length() > 20 ? "VARCHAR(1000)" : "VARCHAR(255)")
                ));
                keyProperties.put(key, typeList);
            }
        }
        return keyProperties;
    }


    private List<String> generateSQLTableSchema(Map<String, List<Pair<String, String>>> keyProperties) {
        List<String> result = new ArrayList<>();

        // Create table for edges
        List<String> edgeSchema = new ArrayList<>();
        List<String> referenceSchema = new ArrayList<>();
        List<String> indexSchema = new ArrayList<>();
        edgeSchema.add("CREATE TABLE Edge(");
        edgeSchema.add("gid VARCHAR(64),");
        for (String tableName : keyType.keySet()) {
            if (keyType.get(tableName).equals("Object")) {
                edgeSchema.add("id" + tableName + " VARCHAR(255),");
                referenceSchema.add("FOREIGN KEY (id" + tableName +
                        ") REFERENCES " + tableName + "(gid),");
                indexSchema.add("CREATE INDEX idx_Edge_id" + tableName +
                        " ON Edge(id" + tableName + ");"
                );
            }
        }
        edgeSchema.addAll(referenceSchema);
        edgeSchema.add("PRIMARY KEY (gid)");
        edgeSchema.addAll(indexSchema);
        result.add(String.join("\n", edgeSchema));


        for (String tableName : keyType.keySet()) {
            List<String> schemaStr = new ArrayList<>();
            schemaStr.add("CREATE TABLE " + tableName + " (");
            if (keyType.get(tableName).equals("List")) {      // Label table
                schemaStr.add("gid VARCHAR(64),");
                schemaStr.add("label VARCHAR(255), ");
                schemaStr.add("FOREIGN KEY (gid) REFERENCES " + keyReference.get(tableName) + "(gid)");
                schemaStr.add(");");
                schemaStr.add("CREATE INDEX idx_" + tableName + "_gid ON " + tableName + "(gid);");
                schemaStr.add("CREATE INDEX idx_" + tableName + "_gid ON " + tableName + "(gid);");
            } else if (keyType.get(tableName).equals("Object")) {
                boolean hasId = false;
                schemaStr.add("gid VARCHAR(64),");
                List<Pair<String, String>> properties = keyProperties.get(tableName);
                for (Pair<String, String> property : properties) {
                    hasId = hasId || (property.getV0().equals("id"));
                    schemaStr.add(property.getV0() + " " + property.getV1() + ",");
                }
                if (hasId) {
                    schemaStr.add("KEY (id),");
                }
                schemaStr.add("PRIMARY KEY (gid)");
                schemaStr.add(");");
                if (hasId) {
                    schemaStr.add("CREATE INDEX idx_" + tableName + "_id ON " + tableName + "(id)");
                }
                schemaStr.add("CREATE INDEX idx_" + tableName + "_gid ON " + tableName + "(gid)");
            } else {
                assert false;
            }
            result.add(String.join("\n", schemaStr));
        }
        return result;
    }

    /*
    * {"name", "id", "birthplace", "duration", "studio"...}
    * */
    private Set<String> getAllProperties(String line) {

        Set<String> res = new HashSet<>();
        Map<String, Object> map = lineToJsonMap(line);
        for (String key : map.keySet()) {
            if (keyType.get(key).equals("Object")) {
                Map<String, Object> objectMap = (Map<String, Object>) map.get(key);
                res.addAll(objectMap.keySet());
            }
        }
        return res;
    }

    /*
    *   {
    *   "node1Label" : {"Person", "Actor"}
    *   "rel_type" : {"ACTS_IN", "OTHER_IN}
    *   }
    * */
    private Map<String, Set<String>> getNodeLabelSet(String line) {
        Map<String, Object> jsonMap = lineToJsonMap(line);
        Map<String, Set<String>> res = new HashMap<>();
        for (String key : jsonMap.keySet()) {
            if (keyType.get(key).equals("List") && (jsonMap.get(key) instanceof List)) {
                List<String> labelList = (List<String>) (jsonMap.get(key));
                res.put(key, new HashSet<>(labelList));
            }
        }
        return res;
    }

    private String constructMetaSchema() {
        String predefinedTableSchema = "" +
                "DROP TABLE IF EXISTS typeProperty;\n" +
                "DROP TABLE IF EXISTS nodeLabel;\n" +
                "DROP TABLE IF EXISTS relLabel;\n" +
                "CREATE TABLE typeProperty(\n" +
                "  id VARCHAR(64),\n" +
                "  name VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX idx_typeProperty_id ON typeProperty(id);\n" +
                "\n" +
                "CREATE TABLE nodeLabel(\n" +
                "  id VARCHAR(64),\n" +
                "  label VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX idx_nodeLabel_id ON nodeLabel(id);\n";

        String edgeTableSchema = predefinedTableSchema +
                "CREATE TABLE Edge(\n" +
                "  eid int AUTO_INCREMENT,";
        for (String key : keyType.keySet()) {
            edgeTableSchema += key + " VARCHAR(255),\n";
        }
        edgeTableSchema +=
                "  PRIMARY KEY (eid)\n" +
                        ");\n" +
                        "CREATE INDEX idx_Edge_eid ON Edge(eid);\n";
        for (String key : keyType.keySet()) {
            edgeTableSchema += "CREATE INDEX idx_Edge_" + key + " ON Edge(" + key + ");\n";
        }
        return edgeTableSchema;
    }

    public void run() throws IOException {

        getMetadata();


        // Construct the set including all properties occurred,
        // and map from label to type id and property set.

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        String line;
        Set<String> globalPropertiesSet = new HashSet<>();

        int typeId = 0;
        Map<Set<String>, Integer> nodelabelToType = new HashMap<>();
        Map<Integer, Set<String>> typeToProperties = new HashMap<>();
        Map<String, String> propertyToSQLType = new HashMap<>();

        while ((line = br.readLine()) != null) {

            // Get all properties of this line, including all nodes and relations
            Set<String> propertySet = getAllProperties(line);
            globalPropertiesSet.addAll(propertySet);

            // Get all labels of objects in this line
            Map<String, Set<String>> labelToSet = getNodeLabelSet(line);
            Map<String, List<Pair<String, String>>> propSQLType = getObjectProperties(line);
            for (String key : propSQLType.keySet()) {
                if (keyType.get(key).equals("Object")) {
                    propSQLType.get(key).forEach(
                            kvPair -> propertyToSQLType.put(kvPair.getV0(), kvPair.getV1())
                    );
                }
            }

            for (String key : labelToSet.keySet()) {
                // Add currently not occurred label to type map.
                Set<String> labelSet = labelToSet.get(key);
                String nodeName = keyReference.get(key);
                Set<String> typePropSet = new HashSet<>();
                propSQLType.get(nodeName).forEach(kvPair -> typePropSet.add(kvPair.getV0()));
                Integer typeIndex = nodelabelToType.getOrDefault(labelSet, -1);
                if (typeIndex.equals(-1)) {
                    typeIndex = typeId++;
                    nodelabelToType.put(labelSet, typeIndex);
                }

                typeToProperties.put(typeIndex, typePropSet);
            }
        }
        br.close();


        String sqlSchema = constructMetaSchema();

        // For each property, create a table for it.
        /*
            DROP TABLE IF EXISTS P_profileImageUrl;
            CREATE TABLE P_profileImageUrl(
            gid VARCHAR(64),
            value VARCHAR(1000),
            PRIMARY KEY (gid)
            );
            CREATE INDEX idx_p_profileImageUrl_gid ON P_profileImageUrl(gid);
        */
        for (String prop : globalPropertiesSet) {
            String typeTableSchema =
                    "\nDROP TABLE IF EXISTS P_" + prop + ";\n" +
                            "CREATE TABLE P_" + prop + "(\n" +
                            "gid VARCHAR(64),\n" +
                            "value " + propertyToSQLType.get(prop) + ",\n";
            typeTableSchema +=
                    "PRIMARY KEY (gid)\n" +
                            ");\n" +
                            "CREATE INDEX idx_p_" + prop + "_gid ON P_" + prop + "(gid);\n";
            sqlSchema += typeTableSchema;
        }


        // Construct Type Table
        /*
            INSERT INTO typeProperty(id, name) VALUES （ "2", "deg");
            INSERT INTO typeProperty(id, name) VALUES （ "2", "name");
            INSERT INTO typeProperty(id, name) VALUES （ "2", "lastModified");
        */

        for (Integer key : typeToProperties.keySet()) {
            String baseSql = "INSERT INTO typeProperty(id, name) VALUES (\""
                    + key.toString() + "\", ";
            for (String prop : typeToProperties.get(key)) {
                String propSql = baseSql + "\"" + prop + "\");\n";
                sqlSchema += propSql;
            }
        }

        // Scan for each node and insert it into database.
        Map<String, Set<String>> usedIDs = new HashMap<>();
        for (String key : keyType.keySet()) {
            if (keyType.get(key).equals("Object")){
                usedIDs.put(key, new HashSet<>());
            }
        }
        br = new BufferedReader(new FileReader(fileName));
        br.readLine();
        while ((line = br.readLine()) != null) {
            Map<String, Object> lineObject = lineToJsonMap(line);

            // Process nodes first.
            for (String type : lineObject.keySet()) {
                if (!keyType.get(type).equals("Object")) {
                    continue;
                }
                Map<String, Object> item = (Map<String, Object>) lineObject.get(type);
                // A node object
                if (item.keySet().contains("id")) {
                    //Node1 or node2?
                    String id = item.get("id").toString();
                    if(!usedIDs.get(type).contains(id)){
                        Integer gid = getUniqueGlobalId();

                    }
                } else {

                }

            }
        }

        System.out.println(sqlSchema);

    }
}
