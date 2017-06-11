package DataImport;

import Utility.DBUtil;

import java.nio.charset.Charset;
import java.util.*;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by liuche on 6/5/17.
 */
public class DBHandler {
    private DBUtil util;

    public DBHandler(DBUtil util){
        this.util = util;
    }

    public void insertObject(String gid, Map<String, Object> values){
        for(String property : values.keySet()){
            String value = values.get(property).toString();
            String sql = "INSERT INTO P_" + property + "(gid, value) VALUES (\"";
            sql += gid + "\", \"" + value;
            sql += "\");\n";
            util.batchExecute(sql);
        }
    }
    public void finish(){
        util.dumpBatch();
    }


    public void insertObjectType(String gid, String type){
        String sql = "INSERT INTO ObjectType(gid, type) VALUES (" +
                "\"" + gid + "\"," +
                "\"" + type + "\");\n";
        util.batchExecute(sql);
    }



    public void insertLabel(String gid, List<String> labels){

        for(String label : labels){
            String sql = "INSERT INTO nodeLabel(gid, label) VALUES(" +
                    "\"" + gid + "\"," +
                    "\"" + label + "\"" +
                    ");\n";
            util.batchExecute(sql);
        }
    }


    public void insertEdge(Map<String, String> item){
        List<String> keySets = new ArrayList<>(item.keySet());
        String sql = "INSERT INTO Edge(" + String.join(",", keySets) + ") VALUES(";
        for(String key : keySets){
            String value = item.get(key);
            sql += "\"" + value + "\"" + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ");\n";
        util.batchExecute(sql);
    }


    public boolean checkExist(Map<String, Object> item){
        boolean result;
        for(String prop : item.keySet()){
            String value = item.get(prop).toString();
            String sql = "SELECT COUNT(*) FROM P_" + prop + " WHERE value = \"" + value + "\";\n";
            result = util.getIntegerFromSQL(sql) > 0;
            if(!result){
                return false;
            }
        }
        return true;
    }

    public String getUniqueGidBy(String prop, String value){
        String sql = "SELECT gid FROM P_" + prop + " WHERE VALUE = \"" + value + "\";\n";
        return util.getStringFromSQL(sql);
    }

    public List<Integer> getGidBy(String property, String value){
        String sql = "SELECT gid FROM P_" + property + " WHERE VALUE = \"" + value + "\";\n";
        List<String> resStringList = util.getListFromSQL(sql);
        List<Integer> result = new ArrayList<>();
        for(String str : resStringList){
            result.add(Integer.valueOf(str));
        }
        return result;
    }

    public boolean checkExist(String prop, String value){
        String statement = "SELECT COUNT(*) FROM P_" + prop + " WHERE value = \"" + value + "\";";
        return util.getIntegerFromSQL(statement) != 0;
    }


    public Integer getGidByAll(Map<String, String> properties, boolean isNode){
        return 0;
    }

    public Map<Set<String>,String> getNodeLabelType() {
        String statement = "SELECT * FROM typeLabel;";
        Map<String, List<String>> res = util.getTableFromSQL(statement);
        List<String> ids = res.get("id");
        List<String> labels = res.get("label");

        Map<String, Set<String> > typeLabel = new HashMap<>();
        for(int i = 0 , size = ids.size() ; i < size ; i++){
            String id = ids.get(i), label = labels.get(i);
            if(!typeLabel.containsKey(id)){
                typeLabel.put(id, new HashSet<>());
            }
            typeLabel.get(id).add(label);
        }
        Map<Set<String>, String> result = new HashMap<>();
        typeLabel.keySet().forEach(key -> result.put(typeLabel.get(key), key));
        return result;
    }
}
