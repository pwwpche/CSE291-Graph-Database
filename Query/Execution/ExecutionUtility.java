package Query.Execution;

import Utility.DBUtil;

import java.util.*;

import static DataImport.FileParser.RELATION_PREFIX;

/**
 * Created by liuche on 6/6/17.
 */
public class ExecutionUtility {
    private DBUtil dbUtil;
    
    public ExecutionUtility(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }

    public void startRecording(){
        this.dbUtil.startRecording();
    }

    public List<String> getHistory(){
        return this.dbUtil.stopAndReturn();
    }

    public List<String> getAllNodes(){
        String statement = "SELECT gid FROM ObjectType WHERE type != \"0\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getNodeByLabel(String label){
        String statement = "SELECT gid FROM NodeLabel WHERE label = \"" + label + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getNodeByLabel(List<String> labels){
        Set<String> nodes = new HashSet<>();
        if(labels.size() > 0){
            nodes = new HashSet<>(getNodeByLabel(labels.get(0)));
            for(int i = 1, size = labels.size() ; i < size ; i++){
                nodes.retainAll(getNodeByLabel(labels.get(i)));
            }
        }
        return new ArrayList<>(nodes);
    }

    public List<String> getNodeGidBy(String field, String value){
        String statement = "SELECT gid FROM P_" + field + "WHERE " + field + " = \"" + value + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeByProperty(String field, String value){
        String statement = "SELECT gid FROM P_" + RELATION_PREFIX + field + " WHERE value = \"" + value + "\";";
        List<String> gids = dbUtil.getListFromSQL(statement);
        List<String> result = new ArrayList<>();
        for(String gid : gids){
            statement = "SELECT eid FROM Edge WHERE relation = \"" + gid + "\";";
            result.addAll(dbUtil.getListFromSQL(statement));
        }
        return result;
    }



    public List<String> getEdgeIdByFromGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node1 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeIdByToGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node2 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeByLabel(String label){
        String statement = "SELECT eid FROM Edge WHERE rel_type = \"" + label + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getAllEdges(){
        String statement = "SELECT eid FROM Edge;";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeByLabel(List<String> labels){
        Set<String> edges = new HashSet<>();
        if(labels.size() > 0){
            edges = new HashSet<>(getNodeByLabel(labels.get(0)));
            for(int i = 1, size = labels.size() ; i < size ; i++){
                edges.addAll(getNodeByLabel(labels.get(i)));
            }
            return new ArrayList<>(edges);
        }else{
            return getAllEdges();
        }

    }

    public Map<String, String> expandEdge(String eid){
        String statement = "SELECT * FROM edge WHERE eid = \""  + eid + "\";";
        return dbUtil.getObjectFromSQL(statement);
    }

    public String getPropertyByGid(String field, String gid){
        String statement = "SELECT gid FROM P_" + field + "WHERE gid = \"" + gid + "\";";
        return dbUtil.getStringFromSQL(statement);
    }

    public Map<String, String> expandObject(Integer gid){
        String statement = "SELECT type FROM ObjectType WHERE gid = \"" + gid.toString() + "\";";
        Integer nodeType = dbUtil.getIntegerFromSQL(statement);
        statement = "SELECT name FROM typeProperty WHERE id = \"" + nodeType.toString() + "\";";
        List<String> properties = dbUtil.getListFromSQL(statement);
        Map<String, String> result = new HashMap<>();
        for(String property : properties){
            if(nodeType == 0){
                statement = "SELECT value FROM P_" + RELATION_PREFIX + property + " WHERE gid = \"" + gid.toString() + "\";";
            }else{
                statement = "SELECT value FROM P_" + property + " WHERE gid = \"" + gid.toString() + "\";";
            }
            String val = dbUtil.getStringFromSQL(statement);
            result.put(property, val);
        }
        return result;
    }

}
