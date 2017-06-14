package Query.Execution;

import Utility.DBUtil;

import java.sql.SQLException;
import java.util.*;


/**
 * Created by liuche on 6/6/17.
 */
public class ExecutionUtility {
    private DBUtil dbUtil;

    public ExecutionUtility(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public void startRecording() {
        this.dbUtil.startRecording();
    }

    public List<String> getHistory() {
        List<String> newHistory = new ArrayList<>();
        newHistory.addAll( this.dbUtil.stopAndReturn());
        return newHistory;
    }

    public List<String> getAllNodes() {
        String statement = "SELECT gid FROM ObjectType WHERE type != \"0\";";
        try {
            return dbUtil.getListFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getNodeByLabel(String label) {
        String statement = "SELECT gid FROM NodeLabel WHERE label = \"" + label + "\";";
        try {
            return dbUtil.getListFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getNodeByLabel(List<String> labels) {
        Set<String> nodes = new HashSet<>();
        if (labels.size() > 0) {
            nodes = new HashSet<>(getNodeByLabel(labels.get(0)));
            for (int i = 1, size = labels.size(); i < size; i++) {
                nodes.retainAll(getNodeByLabel(labels.get(i)));
            }
        }
        return new ArrayList<>(nodes);
    }


    public List<String> getNodeGidBy(String field, String value) {
        String statement = "SELECT gid FROM P_" + field + " WHERE value = \"" + value + "\";";
        try {
            return dbUtil.getListFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String convEdgeField(String type) {
        switch (type) {
            case "from":
                return "node1";
            case "to":
                return "node2";
            case "name":
                return "eid";
            case "type":
                return "rel_type";
            default:
                return type;
        }
    }

    /*
    *       Input: ["to", "name"], {"from", "ID=123"}, ["Mentioned", "HashTagUsedBy"]
    *       Output:
    *       [
    *           ["to1", "edge1"],
    *           ["to2", "edge2"],
    *           ["to3", "edge3"]...
    *       ]
    * */
    public List<List<String>> getEdgesBy(List<String> retItems, Map<String, String> condition, List<String> labels) {
        assert retItems.size() > 0;
        String statement = "SELECT ";

        List<String> statItems = new ArrayList<>();
        retItems.forEach(prop -> statItems.add(convEdgeField(prop)));
        statement += String.join(", ", statItems);
        statement += " FROM Edge WHERE ";

        for (String prop : condition.keySet()) {
            statement += convEdgeField(prop) + " = \"" + condition.get(prop) + "\" AND ";
        }

        if (labels.size() > 0) {
            statement += " (rel_type = ";
            for (int i = 0; i < labels.size() - 1; i++) {
                statement += "\"" + labels.get(i) + "\" OR rel_type = ";
            }
            statement += "\"" + labels.get(labels.size() - 1) + "\")";
        }else{
            statement = statement.substring(0, statement.lastIndexOf("AND"));
        }
        statement += ";";

        try{
            return dbUtil.getListTableFromSQL(statement);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
//        Map<String, List<String>> table = null;
//        try {
//            table = dbUtil.getMapTableFromSQL(statement);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        List<List<String>> res = new ArrayList<>();
//        for (int i = 0, size = table.get(statItems.get(0)).size(); i < size; i++) {
//            List<String> row = new ArrayList<>();
//            for (String retItem : statItems) {
//                row.add(table.get(retItem).get(i));
//            }
//            res.add(row);
//        }
//        return res;
    }

    public List<List<String>> getEdgeEndByStart(String node1, List<String> relTypes) {
        StringBuilder statement = new StringBuilder("SELECT eid, node2 FROM Edge WHERE node1 = \"" + node1 + "\"");
        if (relTypes.size() > 0) {
            statement.append(" AND (rel_type = ");
            for (int i = 0; i < relTypes.size() - 1; i++) {
                statement.append("\"").append(relTypes.get(i)).append("\" OR rel_type = ");
            }
            statement.append("\"").append(relTypes.get(relTypes.size() - 1)).append("\")");
        }
        statement.append(";");
        try {
            return dbUtil.getListTableFromSQL(statement.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<List<String>> getEdgeStartByEnd(String node2, List<String> relTypes) {
        String statement = "SELECT eid, node1 FROM Edge WHERE node2 = \"" + node2 + "\"";
        if (relTypes.size() > 0) {
            statement += " AND (rel_type = ";
            for (int i = 0; i < relTypes.size() - 1; i++) {
                statement += "\"" + relTypes.get(i) + "\" OR rel_type = ";
            }
            statement += "\"" + relTypes.get(relTypes.size() - 1) + "\")";
        }
        statement += ";";
        try {
            return dbUtil.getListTableFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getAllEdges() {
        String statement = "SELECT eid FROM Edge;";
        try {
            return dbUtil.getListFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getEdgesByLabel(List<String> labels) {
        Set<String> edges = new HashSet<>();
        if (labels.size() > 0) {
            edges = new HashSet<>(getNodeByLabel(labels.get(0)));
            for (int i = 1, size = labels.size(); i < size; i++) {
                edges.addAll(getNodeByLabel(labels.get(i)));
            }
            return new ArrayList<>(edges);
        } else {
            return getAllEdges();
        }

    }

    public Map<String, String> expandEdge(String eid) {
        String statement = "SELECT * FROM edge WHERE eid = \"" + eid + "\";";
        try {
            Map<String, String> obj = dbUtil.getObjectFromSQL(statement);
            Map<String, String> res = new HashMap<>();
            obj.forEach((k, v) -> res.put(convEdgeField(k), v));
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkNode(String gid, Map<String, String> props, List<String> labels) {
        try {
            String base = "SELECT COUNT(*) FROM P_";
            for(String prop : props.keySet()){
                String statement = base + prop + " WHERE gid = \"" + gid + "\" AND value = \"" + props.get(prop) + "\";";
                if(dbUtil.getIntegerFromSQL(statement) == 0){
                    return false;
                }
            }
            base = "SELECT COUNT(*) FROM NodeLabel WHERE gid = \"" + gid + "\" AND value = ";
            for (String label : labels) {
                String statement = base +"\"" + label + "\";";
                if(dbUtil.getIntegerFromSQL(statement) == 0){
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String getPropertyByGid(String field, String gid) {
        String statement = "SELECT value FROM P_" + field + " WHERE gid = \"" + gid + "\";";
        try {
            return dbUtil.getStringFromSQL(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, String> expandObject(String gid) {
        try {
            String statement = "SELECT type FROM ObjectType WHERE gid = \"" + gid + "\";";
            Integer nodeType = dbUtil.getIntegerFromSQL(statement);
            statement = "SELECT name FROM typeProperty WHERE id = \"" + nodeType.toString() + "\";";
            List<String> properties = dbUtil.getListFromSQL(statement);
            Map<String, String> result = new HashMap<>();
            for (String property : properties) {
                statement = "SELECT value FROM P_" + property + " WHERE gid = \"" + gid + "\";";
                String val = dbUtil.getStringFromSQL(statement);
                result.put(property, val);
            }
            return result;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
