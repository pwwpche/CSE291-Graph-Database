package Utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/2/17.
 */
public class DBUtil {
    private Connection conn;
    private List<String> executeHistory = new ArrayList<>();
    private boolean recording = false;

    public DBUtil(Connection connection) {
        this.conn = connection;
    }

    public void startRecording() {
        executeHistory.clear();
        this.recording = true;
    }

    public List<String> stopAndReturn() {
        List<String> result = new ArrayList<>(executeHistory);
        this.recording = false;
        return result;
    }

    private List<String> batchStat = new ArrayList<>();

    public void batchExecute(String statement) throws SQLException {
        if (batchStat.size() < 1000) {
            batchStat.add(statement);
        } else {
            dumpBatch();
        }
    }

    public void dumpBatch() throws SQLException {
        Statement sqlStat = conn.createStatement();
        for (String query : batchStat) {
            if (query.trim().length() > 0) {
                sqlStat.addBatch(query);
            }
        }
        try{
            sqlStat.executeBatch();
        }catch (SQLException e){
            try {
                BufferedWriter errWriter = new BufferedWriter(new FileWriter("error.txt"));
                errWriter.append(e.toString()).append("\n");
                errWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        sqlStat.close();
        batchStat.clear();
    }

    public void executeSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }

        if (statement.trim().length() > 0) {
            PreparedStatement preparedStatement = conn.prepareStatement(statement);
            preparedStatement.execute();
            preparedStatement.close();
        }

    }

    public void executeSQL(List<String> statements) throws SQLException {
        if (recording) {
            executeHistory.addAll(statements);
        }
        if (statements.size() == 0) {
            return;
        }
        for (String stt : statements) {
            if (stt.trim().length() > 0) {
                PreparedStatement preparedStatement = conn.prepareStatement(stt);
                preparedStatement.execute();
                preparedStatement.close();
            }
        }
    }


    public Integer getIntegerFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        Integer res = result.getInt(1);
        result.close();
        preparedStatement.close();
        return res;

    }

    public String getStringFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        result.next();
        String res = result.getString(1);
        result.close();
        preparedStatement.close();
        return res;

    }


    public List<String> getListFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        List<String> resList = new ArrayList<>();

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String str = result.getString(1);
            resList.add(str);
        }
        result.close();
        preparedStatement.close();

        return resList;
    }

    public Map<String, Integer> getMapFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        Map<String, Integer> resMap = new HashMap<>();

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String str = result.getString(1);
            Integer integer = result.getInt(2);
            resMap.put(str, integer);
        }
        result.close();
        preparedStatement.close();

        return resMap;
    }

    public Map<String, String> getObjectFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        Map<String, String> map = new HashMap<>();

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();

        if (result.next()) {
            for (int i = 1, size = metaData.getColumnCount(); i <= size; i++) {
                String key = metaData.getColumnName(i);
                String value = result.getString(i);
                map.put(key, value);
            }
        }
        result.close();
        preparedStatement.close();

        return map;
    }

    public Map<String, List<String>> getMapTableFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        Map<String, List<String>> map = new HashMap<>();

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();
        for (int i = 1, size = metaData.getColumnCount(); i <= size; i++) {
            String key = metaData.getColumnName(i);
            map.put(key, new ArrayList<>());
        }
        while (result.next()) {
            for (int i = 1, size = metaData.getColumnCount(); i <= size; i++) {
                String key = metaData.getColumnName(i);
                map.get(key).add(result.getString(i));
            }
        }
        result.close();
        preparedStatement.close();

        return map;
    }

    public List<List<String>> getListTableFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        List<List<String>> res = new ArrayList<>();
        int lineSize = result.getMetaData().getColumnCount();
        while(result.next()){
            List<String> row = new ArrayList<>();
            for(int i = 1 ; i <= lineSize ; i++){
                row.add(result.getString(i));
            }
            res.add(row);
        }
        result.close();
        preparedStatement.close();

        return res;
    }


    public List<Map<String, String>> getObjectListFromSQL(String statement) throws SQLException {
        if (recording) {
            executeHistory.add(statement);
        }
        List<Map<String, String>> res = new ArrayList<>();

        PreparedStatement preparedStatement = conn.prepareStatement(statement);
        ResultSet result = preparedStatement.executeQuery();
        ResultSetMetaData metaData = result.getMetaData();

        while (result.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 1, size = metaData.getColumnCount(); i <= size; i++) {
                String key = metaData.getColumnName(i);
                String value = result.getString(i);
                map.put(key, value);
            }
            res.add(map);
        }
        result.close();
        preparedStatement.close();
        return res;
    }

}
