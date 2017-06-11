package Query.Execution;

import Utility.DBUtil;

import java.util.*;
import java.util.stream.StreamSupport;


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
        return this.dbUtil.stopAndReturn();
    }

    public List<String> getAllNodes() {
        String statement = "SELECT gid FROM ObjectType WHERE type != \"0\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getNodeByLabel(String label) {
        String statement = "SELECT gid FROM NodeLabel WHERE label = \"" + label + "\";";
        return dbUtil.getListFromSQL(statement);
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
        String statement = "SELECT gid FROM P_" + field + "WHERE " + field + " = \"" + value + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeByProperty(String field, String value) {
        String statement = "SELECT gid FROM P_" + field + " WHERE value = \"" + value + "\";";
        List<String> gids = dbUtil.getListFromSQL(statement);
        List<String> result = new ArrayList<>();
        for (String gid : gids) {
            statement = "SELECT eid FROM Edge WHERE relation = \"" + gid + "\";";
            result.addAll(dbUtil.getListFromSQL(statement));
        }
        return result;
    }

    private String convertType(String type) {
        switch (type) {
            case "from":
                return "node1";
            case "to":
                return "node2";
            case "name":
                return "eid";
            case "type":
                return "node1";
            default:
                return "";
        }
    }

    public List<List<String>> getEdgesBy(List<String> retItems, Map<String, String> constraint, List<String> labels) {
        assert retItems.size() > 0;
        String statement = "SELECT ";

        List<String> statItems = new ArrayList<>();
        retItems.forEach(prop -> statItems.add(convertType(prop)));
        statement += String.join(", ", retItems);
        statement += " FROM Edge WHERE ";

        for (String prop : constraint.keySet()) {
            statement += convertType(prop) + " = \"" + constraint.get(prop) + "\" AND ";
        }

        if (labels.size() > 0) {
            statement += " (rel_type = ";
            for (int i = 0; i < labels.size() - 1; i++) {
                statement += "\"" + labels.get(i) + "\" OR rel_type = ";
            }
            statement += "\"" + labels.get(labels.size() - 1) + "\")";
        }
        statement += ";";
        Map<String, List<String>> table = dbUtil.getTableFromSQL(statement);
        List<List<String>> res = new ArrayList<>();
        for (int i = 0, size = table.get(statItems.get(0)).size(); i < size; i++) {
            List<String> row = new ArrayList<>();
            for (String retItem : statItems) {
                row.add(table.get(retItem).get(i));
            }
            res.add(row);
        }
        return res;
    }

    public List<String> getEdgeByStart(String node1) {
        String statement = "SELECT eid FROM Edge WHERE node1 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgeByEnd(String node1) {
        String statement = "SELECT eid FROM Edge WHERE node2 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getEdgesByLabel(String label) {
        String statement = "SELECT eid FROM Edge WHERE rel_type = \"" + label + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    public List<String> getAllEdges() {
        String statement = "SELECT eid FROM Edge;";
        return dbUtil.getListFromSQL(statement);
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
        return dbUtil.getObjectFromSQL(statement);
    }

    public boolean exists(String gid, String prop, String value) {
        String statement = "SELECT COUNT(*) FROM P_" + prop + " WHERE gid = \"" + gid + "\" AND value = \"" + value + "\";";
        return dbUtil.getIntegerFromSQL(statement) != 0;
    }

    public String getPropertyByGid(String field, String gid) {
        String statement = "SELECT gid FROM P_" + field + "WHERE gid = \"" + gid + "\";";
        return dbUtil.getStringFromSQL(statement);
    }

    public Map<String, String> expandObject(Integer gid) {
        String statement = "SELECT type FROM ObjectType WHERE gid = \"" + gid.toString() + "\";";
        Integer nodeType = dbUtil.getIntegerFromSQL(statement);
        statement = "SELECT name FROM typeProperty WHERE id = \"" + nodeType.toString() + "\";";
        List<String> properties = dbUtil.getListFromSQL(statement);
        Map<String, String> result = new HashMap<>();
        for (String property : properties) {
            statement = "SELECT value FROM P_" + property + " WHERE gid = \"" + gid.toString() + "\";";
            String val = dbUtil.getStringFromSQL(statement);
            result.put(property, val);
        }
        return result;
    }


    public boolean check(String gid, Map<String, String> props) {
        String base = "SELECT COUNT(*) FROM P_";
        return false;
    }
}
