package DataImport;

import Utility.DBUtil;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by liuche on 6/5/17.
 */
public class DBHandler {
    private DBUtil util;

    public DBHandler(DBUtil util){
        this.util = util;
    }

    public void insertObject(String gid, Map<String, Object> values) throws SQLException {
        for(String property : values.keySet()){
            String value = values.get(property).toString();
            String sql = "INSERT INTO P_" + property + "(gid, value) VALUES (\"";
            sql += gid + "\", \"" + value;
            sql += "\");\n";
            util.batchExecute(sql);
        }
    }
    public void finish() throws SQLException {
        util.dumpBatch();
    }


    public void insertObjectType(String gid, String type) throws SQLException {
        String sql = "INSERT INTO ObjectType(gid, type) VALUES (" +
                "\"" + gid + "\"," +
                "\"" + type + "\");\n";
        util.batchExecute(sql);

    }



    public void insertLabel(String gid, List<String> labels) throws SQLException {

        for(String label : labels){
            String sql = "INSERT INTO nodeLabel(gid, label) VALUES(" +
                    "\"" + gid + "\"," +
                    "\"" + label + "\"" +
                    ");\n";
            util.batchExecute(sql);
        }
    }


    public void insertEdge(Map<String, String> item) throws SQLException {
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


    public boolean checkExist(Map<String, Object> item) throws SQLException {
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

    public String getUniqueGidBy(String prop, String value) {
        try {
            String sql = "SELECT gid FROM P_" + prop + " WHERE VALUE = \"" + value + "\";\n";
            return util.getStringFromSQL(sql);
        }catch (SQLException e){
            return "";
        }
    }

    public List<Integer> getGidBy(String property, String value) throws SQLException {
        String sql = "SELECT gid FROM P_" + property + " WHERE VALUE = \"" + value + "\";\n";
        List<String> resStringList = util.getListFromSQL(sql);
        List<Integer> result = new ArrayList<>();
        for(String str : resStringList){
            result.add(Integer.valueOf(str));
        }
        return result;
    }

    public boolean checkExist(String prop, String value) throws SQLException {
        String statement = "SELECT COUNT(*) FROM P_" + prop + " WHERE value = \"" + value + "\";";
        return util.getIntegerFromSQL(statement) != 0;
    }


    public Integer getGidByAll(Map<String, String> properties, boolean isNode){
        return 0;
    }

    public Map<Set<String>,String> getNodeLabelType()  throws SQLException {
        String statement = "SELECT * FROM typeLabel;";
        Map<String, List<String>> res = util.getMapTableFromSQL(statement);
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
