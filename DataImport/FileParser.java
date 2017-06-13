package DataImport;


import Utility.DBUtil;
import Entity.Pair;
import com.sangupta.bloomfilter.impl.InMemoryBloomFilter;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

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
 * Objects without "ID" filed are considered relation objects.
 * <p>
 * 5. Relation objects are empty
 * <p>
 * Additional requirement is in QueryIndexer.
 *
 * 6. Use UTF8_GENERAL_CI on text field
 *    ALTER TABLE test.P_text MODIFY COLUMN value TEXT
 *    CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL;
 */
public class FileParser {
    private String fileName = "";
    private Map<String, String> keyType = new HashMap<>();
    private Map<String, String> keyReference = new HashMap<>();

    // Data structures only used in run()
    private Map<String, Set<String>> typeToProperties = new HashMap<>();
    private Set<String> globalProperties = new HashSet<>();
    private Map<Set<String>, String> nodelabelToType = new HashMap<>();
    private Map<String, String> propertyToSQLType = new HashMap<>();
    private Integer globalId = 0;
    private DBUtil dbUtil;
    private DBHandler handler;


    public FileParser(String fileName, Connection conn) {
        this.fileName = fileName;
        this.dbUtil = new DBUtil(conn);
        this.handler = new DBHandler(dbUtil);
    }

    private Integer getUniqueGlobalId() {
        int result = globalId;
        globalId++;
        return result;
    }

    private void getMetadata() throws IOException {

        //Read file schema
        String lineMeta = "{node1: n, node1Label: labels(n), relationship: r, rel_type: type(r), node2:m, node2Label: labels(m)}";
        String schemaLine = lineMeta.replaceAll("[\"{} ]", "");
        String objects[] = schemaLine.split(",");
        Map<String, String> valToKey = new HashMap<>();

        // Columns type
        for (String s : objects) {
            assert s.contains(":");
            String keyValPair[] = s.split(":");
            String objName = keyValPair[0];
            String objVal = keyValPair[1];
            String valType = objVal.matches("[A-Za-z0-9]+") ? "object" : (
                    objVal.contains("(") ? "list" : "string"
            );
            valType = ((objName.toLowerCase().contains("node")) ? "node_" : "relation_") + valType;
            keyType.put(objName, valType);
            valToKey.put(objVal, objName);
        }

        // NOTICE:
        // type of "rel_type" is STRING!!!
        keyType.put("rel_type", "string");

        // Relation among columns
        for (String val : valToKey.keySet()) {
            if (val.contains("(")) {
                String variable = val.substring(val.indexOf("(") + 1, val.indexOf(")"));
                assert valToKey.containsKey(variable);
                String key = valToKey.get(variable);
                keyReference.put(valToKey.get(val), key);
            }
        }

    }

    private Map<String, Object> lineToJsonMap(String line) {
        //line = replaceQuote(line);
        JSONObject object = new JSONObject(line);

        Map<String, Object> objectMap = JsonParser.toMap(object);
        List<Map<String, Object>> rowList = (List<Map<String, Object>>) objectMap.get("row");
        List<Map<String, String>> metaList = (List<Map<String, String>>) objectMap.get("meta");
        Map<String, Object> result = rowList.get(0);
        List<String> ids = new ArrayList<>();
        for (int i = 0, size = metaList.size(); i < size; i++) {
            if (JSONObject.NULL.equals(metaList.get(i))) {
                continue;
            }
            Map<String, String> metaMap = metaList.get(i);
            if (metaMap.containsKey("id") && "node".equals(metaMap.get("type"))) {
                ids.add(metaMap.get("id"));
            }
        }
        assert result.get("node1") instanceof Map;
        ((Map<String, String>) (result.get("node1"))).put("id", ids.get(0));
        ((Map<String, String>) (result.get("node2"))).put("id", ids.get(1));
        return result;
    }

    private String replaceQuote(String str) {

        str = str.replaceAll("\"\"", "\"");
        int startIdx = 0, endIdx;
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

    private Map<String, List<Pair<String, String>>> getNodePropSQL(Map<String, Object> map) throws IOException {
        Map<String, List<Pair<String, String>>> keyProperties = new HashMap<>();
        for (String key : map.keySet()) {
            if (keyType.get(key).contains("object") && keyType.get(key).contains("node")) {
                Map<String, Object> objectMap = (Map<String, Object>) map.get(key);
                List<String> propertyList = new ArrayList<>(objectMap.keySet());
                List<Pair<String, String>> typeList = new ArrayList<>();
                propertyList.forEach(prop -> typeList.add(
                        new Pair<>(prop, "TEXT")
                ));
                keyProperties.put(key, typeList);
            }
        }
        return keyProperties;
    }


    /*
    *  -> {"name", "id", "birthplace", "duration", "studio"...},
    * */

    private Set<String> getAllProperties(Map<String, Object> map) {
        Set<String> res = new HashSet<>();
        for (String key : map.keySet()) {
            if (keyType.get(key).contains("object") && keyType.get(key).contains("node")) {
                Map<String, Object> objectMap = (Map<String, Object>) map.get(key);
                res.addAll(objectMap.keySet());
            }
        }
        return res;
    }

    /*
    *   {
    *   "node1Label" : {"Person", "Actor"}
    *   }
    * */
    private Map<String, Set<String>> getNodeLabelSet(Map<String, Object> map) {
        Map<String, Set<String>> res = new HashMap<>();
        for (String key : map.keySet()) {
            if (keyType.get(key).contains("list") && (map.get(key) instanceof List)) {
                List<String> labelList = (List<String>) (map.get(key));
                res.put(key, new HashSet<>(labelList));
            }
        }
        return res;
    }

    private List<String> constructMetaSchema() {
        String predefinedTableSchema = "" +
                "DROP TABLE IF EXISTS typeProperty;\n" +
                "DROP TABLE IF EXISTS typeLabel;\n" +
                "DROP TABLE IF EXISTS nodeLabel;\n" +
                "DROP TABLE IF EXISTS Edge;\n" +
                "DROP TABLE IF EXISTS ObjectType;\n" +
                "CREATE TABLE ObjectType(\n" +
                "  gid VARCHAR(64),\n" +
                "  type VARCHAR(100),\n" +
                " PRIMARY KEY (gid)" +
                ");\n" +
                "Create INDEX idx_nodetype_gid ON ObjectType(gid)\n;" +
                "\n" +
                "CREATE TABLE typeProperty(\n" +
                "  id VARCHAR(64),\n" +
                "  name VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX idx_typeProperty_id ON typeProperty(id);\n" +
                "\n" +
                "CREATE TABLE typeLabel(\n" +
                "  id VARCHAR(64),\n" +
                "  label VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX idx_typeLabel_id ON typeLabel(id);\n" +
                "\n" +
                "CREATE TABLE nodeLabel(\n" +
                "  gid VARCHAR(64),\n" +
                "  label VARCHAR(255)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX idx_nodeLabel_id ON nodeLabel(gid);\n";

        String edgeTableSchema = predefinedTableSchema +
                "CREATE TABLE Edge(\n" +
                "  eid int AUTO_INCREMENT,\n";
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
        List<String> ret = Arrays.asList(edgeTableSchema.split(";"));
        List<String> result = new ArrayList<>();
        for (String s : ret) {
            if(s.trim().length() > 0){
                result.add(s + ";\n");
            }

        }
        return result;
    }

    public void run() throws IOException, SQLException {
        System.out.println("Creating tables...");
        getMetadata();
        MyBufferedReader br = new MyBufferedReader(fileName);

        // Construct the set including all properties occurred,
        // and map from label to type id and property set.
        // Distinguish node property from edge property.

        String line;
        if(false) {
            // All relations are of type 0.
            // Typeid of nodes starts from 1.
            int typeId = 1;
            int count0 = 0;

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tables.sql"));

            while ((line = br.readLine()) != null) {
                if (count0++ % 10000 == 0) {
                    System.out.println(count0);
                }
                // Get all properties of this line, including all nodes and relations
                Map<String, Object> lineMap = lineToJsonMap(line);
                Set<String> nodeProperties = getAllProperties(lineMap);

                globalProperties.addAll(nodeProperties);

                // Get all labels of objects in this line
                Map<String, Set<String>> labelToSet = getNodeLabelSet(lineMap);
                Map<String, List<Pair<String, String>>> propSQLType = getNodePropSQL(lineMap);

                for (String key : propSQLType.keySet()) {
                    propSQLType.get(key).forEach(
                            kvPair -> propertyToSQLType.put(kvPair.getV0(), kvPair.getV1())
                    );
                }


                for (String key : labelToSet.keySet()) {
                    // Add currently not occurred label to type map.
                    Set<String> labelSet = labelToSet.get(key);
                    String nodeName = keyReference.get(key);
                    Set<String> typePropSet = new HashSet<>();
                    propSQLType.get(nodeName).forEach(kvPair -> typePropSet.add(kvPair.getV0()));
                    String typeIndex = nodelabelToType.getOrDefault(labelSet, "");
                    if (typeIndex.equals("")) {
                        typeId++;
                        typeIndex = Integer.toString(typeId);
                        nodelabelToType.put(labelSet, typeIndex);
                    }

                    typeToProperties.put(typeIndex, typePropSet);
                }
            }
            br.close();

            List<String> sqlSchema = constructMetaSchema();
            dbUtil.executeSQL(sqlSchema);
            for(String str : sqlSchema){
                bufferedWriter.write(str);
            }
            sqlSchema = new ArrayList<>();

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

            for (String prop : globalProperties) {
                sqlSchema.add("\nDROP TABLE IF EXISTS P_" + prop + ";\n");
                sqlSchema.add("CREATE TABLE P_" + prop + "(\n" +
                        "gid VARCHAR(64),\n" +
                        "value " + propertyToSQLType.get(prop) + ",\n" +
                        "PRIMARY KEY (gid)\n" +
                        ");\n");
                sqlSchema.add("CREATE INDEX idx_p_" + prop + "_gid ON P_" + prop + "(gid);\n");
            }


            // Construct Type Table
        /*
            INSERT INTO typeProperty(id, name) VALUES （ "2", "deg");
            INSERT INTO typeProperty(id, name) VALUES （ "2", "name");
            INSERT INTO typeProperty(id, name) VALUES （ "2", "lastModified");
        */

            for (String key : typeToProperties.keySet()) {
                String baseSql = "INSERT INTO typeProperty(id, name) VALUES (\""
                        + key.toString() + "\", ";
                for (String prop : typeToProperties.get(key)) {
                    String propSql = baseSql + "\"" + prop + "\");\n";
                    sqlSchema.add(propSql);
                }
            }

            // Construct Type Label Table
        /*
            INSERT INTO typeLabel(id, label) VALUES （ "2", "actor");
            INSERT INTO typeLabel(id, label) VALUES （ "2", "person");
            INSERT INTO typeLabel(id, label) VALUES （ "1", "tweet");
        */
            for (Set<String> set : nodelabelToType.keySet()) {
                String baseSql = "INSERT INTO typeLabel(id, label) VALUES (\""
                        + nodelabelToType.get(set) + "\", ";
                for (String label : set) {
                    String propSql = baseSql + "\"" + label + "\");\n";
                    sqlSchema.add(propSql);
                }
            }

            dbUtil.executeSQL(sqlSchema);
            System.out.println("Tables created.");
            for(String str : sqlSchema){
                bufferedWriter.write(str);
            }
            bufferedWriter.close();
        }else{
           nodelabelToType = handler.getNodeLabelType();
        }


        System.out.println("Importing data...");
        br = new MyBufferedReader(fileName);

        // Scan for each node and insert it into database.
        Map<String, String> usedNodeIds = new HashMap<>();
        InMemoryBloomFilter<String> filter = new InMemoryBloomFilter<>(7000000, 0.01);

        int count = 0;
        while ((line = br.readLine()) != null) {
            if (count % 1000 == 0) {
                System.out.println(count);
            }
            count++;
            Map<String, Object> lineObject = lineToJsonMap(line);
            Map<String, String> lineGidType = new HashMap<>();
            Map<String, Integer> newGid = new HashMap<>();

            // Process nodes first.
            for (String type : lineObject.keySet()) {
                if (!keyType.get(type).contains("object") || !keyType.get(type).contains("node")) {
                    continue;
                }
                Map<String, Object> item = (Map<String, Object>) lineObject.get(type);

                if (item.size() == 0) {
                    //An empty object
                    //Only relation object could be empty.
                    assert !type.contains("node");
                    lineGidType.put(type, "");
                    continue;
                }

                if (item.containsKey("id")) {
                    String id = item.get("id").toString();

                    if(item.containsKey("text")){
                        // For tweets, use BloomFilter to find if it check.
                        if(filter.contains(id)) {
                            dbUtil.dumpBatch();
                            String gid = handler.getUniqueGidBy("id", id);
                            if (!("".equals(gid))) {
                                lineGidType.put(type, gid);
                                continue;
                            }
                        }
                        String value = item.get("text").toString();
                        byte[] ptext = value.getBytes(ISO_8859_1);
                        value = new String(ptext, UTF_8);
                        item.put("text", value);

                        String gid = getUniqueGlobalId().toString();
                        handler.insertObject(gid, item);
                        lineGidType.put(type, gid);
                        newGid.put(type, Integer.valueOf(gid));
                    }else{
                        if (usedNodeIds.containsKey(id)) {
                            String gid = usedNodeIds.get(id);
                            lineGidType.put(type, gid);
                        } else {
                            Integer gid = getUniqueGlobalId();
                            handler.insertObject(gid.toString(), item);
                            usedNodeIds.put(id, gid.toString());
                            lineGidType.put(type, gid.toString());
                            newGid.put(type, gid);
                        }
                    }

                }
            }

            //Then process labels
            for (String type : lineObject.keySet()) {
                if (!keyType.get(type).contains("list")) {
                    continue;
                }
                List<String> items = (List<String>) lineObject.get(type);
                String referredType = keyReference.get(type);
                String typeIndex = nodelabelToType.get(new HashSet<>(items));
                lineGidType.put(type, typeIndex);

                if (newGid.containsKey(referredType)) {
                    String gid = lineGidType.get(referredType);
                    handler.insertLabel(gid, items);
                    handler.insertObjectType(gid, typeIndex);
                }
            }


            // Finally insert edge
            Map<String, String> edgeObject = new HashMap<>();
            for (String type : lineObject.keySet()) {
                if (keyType.get(type).contains("list") || keyType.get(type).contains("object")) {
                    edgeObject.put(type, lineGidType.get(type));
                } else {
                    edgeObject.put(type, lineObject.get(type).toString());
                }
            }
            handler.insertEdge(edgeObject);
        }
        handler.finish();
        System.out.println("Importing complete.");

    }

}
