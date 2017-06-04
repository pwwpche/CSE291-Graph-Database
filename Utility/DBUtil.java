package Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/2/17.
 *
 */
public class DBUtil {
    private Connection conn;

    public DBUtil(Connection connection){
        this.conn = connection;
    }

    public void executeSQL(String statement){
        try {
            if(statement.trim().length() > 0){
                PreparedStatement preparedStatement = conn.prepareStatement(statement);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSQL(List<String> statements){
        try {
            if(statements.size() == 0){
                return ;
            }

            for(String stt : statements){
                if(stt.trim().length() > 0){
                    PreparedStatement preparedStatement = conn.prepareStatement(stt);
                    preparedStatement.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Integer getIntegerFromSQL(String statement){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getStringFromSQL(String statement){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            return result.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    public List<String> getListFromSQL(String statement){
        List<String> resList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String str = result.getString(1);
                resList.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Map<String, Integer> getMapFromSQL(String statement){
        Map<String, Integer> resMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String str = result.getString(1);
                Integer integer = result.getInt(2);
                resMap.put(str, integer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resMap;
    }


    public void insertObject(Integer gid, Map<String, Object> values){
        String id = gid.toString();
        for(String property : values.keySet()){
            String sql = "INSERT INTO P_" + property + "(gid, value) VALUES (\"";
            sql += id + "\", \"" + values.get(property).toString();
            sql += "\");\n";
            executeSQL(sql);
        }

    }

    public void insertObjectType(String gid, String type){
        String sql = "INSERT INTO ObjectType(gid, type) VALUES (" +
                "\"" + gid + "\"," +
                "\"" + type + "\");\n";
        executeSQL(sql);
    }

    public void insertObject(Integer gid, Map<String, Object> values, String tablePrefix){
        String id = gid.toString();
        for(String property : values.keySet()){
            String sql = "INSERT INTO P_" + tablePrefix + property + "(gid, value) VALUES (\"";
            sql += id + "\", \"" ;
            sql += values.get(property).toString() +  "\");\n";
            executeSQL(sql);
        }
    }


    public void insertLabel(String gid, List<String> labels){

        for(String label : labels){
            String sql = "INSERT INTO nodeLabel(gid, label) VALUES(" +
                    "\"" + gid + "\"," +
                    "\"" + label + "\"" +
                    ");\n";
            executeSQL(sql);
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
        executeSQL(sql);
    }


    public boolean checkExist(Map<String, Object> item){
        boolean result;
        for(String prop : item.keySet()){
            String value = item.get(prop).toString();
            String sql = "SELECT COUNT(*) FROM P_" + prop + " WHERE value = \"" + value + "\";\n";
            result = getIntegerFromSQL(sql) > 0;
            if(!result){
                return false;
            }
        }
        return true;
    }

    public Integer getGidBy(String prop, String value){
        String sql = "SELECT gid FROM P_" + prop + " WHERE VALUE = \"" + value + "\";\n";
        return getIntegerFromSQL(sql);
    }

    public List<Integer> getGidBy(String property, String value, boolean isNode){
        String tablePrefix = isNode ? "" : FileParser.RELATION_PREFIX;
        String sql = "SELECT gid FROM P_" + tablePrefix + property + " WHERE VALUE = \"" + value + "\";\n";
        List<String> resStringList = getListFromSQL(sql);
        List<Integer> result = new ArrayList<>();
        for(String str : resStringList){
            result.add(Integer.valueOf(str));
        }
        return result;
    }

    public Map<String, String> expandObject(Integer gid){
        String statement = "SELECT type FROM ObjectType WHERE gid = \"" + gid.toString() + "\";";
        Integer nodeType = getIntegerFromSQL(statement);
        statement = "SELECT name FROM typeProperty WHERE id = \"" + nodeType.toString() + "\";";
        List<String> properties = getListFromSQL(statement);
        Map<String, String> result = new HashMap<>();
        for(String property : properties){
            if(nodeType == 0){
                statement = "SELECT value FROM P_" + FileParser.RELATION_PREFIX + property + " WHERE gid = \"" + gid.toString() + "\";";
            }else{
                statement = "SELECT value FROM P_" + property + " WHERE gid = \"" + gid.toString() + "\";";
            }
            String val = getStringFromSQL(statement);
            result.put(property, val);
        }
        return result;
    }
}
