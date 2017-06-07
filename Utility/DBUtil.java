package Utility;

import java.sql.*;
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
    private List<String> executeHistory = new ArrayList<>();
    private boolean recording = false;
    public DBUtil(Connection connection){
        this.conn = connection;
    }

    public void startRecording(){
        executeHistory.clear();
        this.recording = true;
    }

    public List<String> stopAndReturn(){
        List<String> result = new ArrayList<>(executeHistory);
        this.recording = false;
        return result;
    }

    public void executeSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        try {
            if(statement.trim().length() > 0){
                PreparedStatement preparedStatement = conn.prepareStatement(statement);
                preparedStatement.execute();
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSQL(List<String> statements){
        if(recording){
            executeHistory.addAll(statements);
        }
        try {
            if(statements.size() == 0){
                return ;
            }
            for(String stt : statements){
                if(stt.trim().length() > 0){
                    PreparedStatement preparedStatement = conn.prepareStatement(stt);
                    preparedStatement.execute();
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Integer getIntegerFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            Integer res =  result.getInt(1);
            result.close();
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getStringFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String res = result.getString(1);
            result.close();
            preparedStatement.close();
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }


    public List<String> getListFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        List<String> resList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String str = result.getString(1);
                resList.add(str);
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Map<String, Integer> getMapFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        Map<String, Integer> resMap = new HashMap<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                String str = result.getString(1);
                Integer integer = result.getInt(2);
                resMap.put(str, integer);
            }
            result.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resMap;
    }

    public Map<String, String> getObjectFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        Map<String, String> map = new HashMap<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            if(result.next()){
                for(int i = 1, size = metaData.getColumnCount(); i <= size ; i++){
                    String key = metaData.getColumnName(i);
                    String value = result.getString(i);
                    map.put(key, value);
                }
            }
            result.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<Map<String, String>> getObjectListFromSQL(String statement){
        if(recording){
            executeHistory.add(statement);
        }
        List<Map<String, String>> res = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            ResultSet result = preparedStatement.executeQuery();
            ResultSetMetaData metaData = result.getMetaData();

            while(result.next()){
                Map<String, String> map = new HashMap<>();
                for(int i = 1, size = metaData.getColumnCount(); i <= size ; i++){
                    String key = metaData.getColumnName(i);
                    String value = result.getString(i);
                    map.put(key, value);
                }
                res.add(map);
            }
            result.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

}
