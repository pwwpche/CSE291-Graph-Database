package DataImport;

import Utility.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/5/17.
 */
public class DBHandler {
    private DBUtil util;

    public DBHandler(DBUtil util){
        this.util = util;
    }

    public void insertObject(Integer gid, Map<String, Object> values){
        String id = gid.toString();
        for(String property : values.keySet()){
            String sql = "INSERT INTO P_" + property + "(gid, value) VALUES (\"";
            sql += id + "\", \"" + values.get(property).toString();
            sql += "\");\n";
            util.executeSQL(sql);
        }

    }

    public void insertObjectType(String gid, String type){
        String sql = "INSERT INTO ObjectType(gid, type) VALUES (" +
                "\"" + gid + "\"," +
                "\"" + type + "\");\n";
        util.executeSQL(sql);
    }

    public void insertObject(Integer gid, Map<String, Object> values, String tablePrefix){
        String id = gid.toString();
        for(String property : values.keySet()){
            String sql = "INSERT INTO P_" + tablePrefix + property + "(gid, value) VALUES (\"";
            sql += id + "\", \"" ;
            sql += values.get(property).toString() +  "\");\n";
            util.executeSQL(sql);
        }
    }


    public void insertLabel(String gid, List<String> labels){

        for(String label : labels){
            String sql = "INSERT INTO nodeLabel(gid, label) VALUES(" +
                    "\"" + gid + "\"," +
                    "\"" + label + "\"" +
                    ");\n";
            util.executeSQL(sql);
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
        util.executeSQL(sql);
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

    public Integer getGidBy(String prop, String value){
        String sql = "SELECT gid FROM P_" + prop + " WHERE VALUE = \"" + value + "\";\n";
        return util.getIntegerFromSQL(sql);
    }

    public List<Integer> getGidBy(String property, String value, boolean isNode){
        String tablePrefix = isNode ? "" : FileParser.RELATION_PREFIX;
        String sql = "SELECT gid FROM P_" + tablePrefix + property + " WHERE VALUE = \"" + value + "\";\n";
        List<String> resStringList = util.getListFromSQL(sql);
        List<Integer> result = new ArrayList<>();
        for(String str : resStringList){
            result.add(Integer.valueOf(str));
        }
        return result;
    }

}
