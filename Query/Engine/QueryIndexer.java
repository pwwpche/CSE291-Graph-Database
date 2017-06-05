package Query.Engine;

import Utility.DBSchema;
import Utility.DBUtil;
import Utility.FileParser;

import java.sql.Connection;
import java.util.*;

/**
 * Created by liuche on 5/29/17.
 *
 * Requirements:
 * Schema of the imported CSV file should be
 * "{node1: n, node1Label: labels(n), relationship: r, rel_type: type(r), node2:m, node2Label: labels(m)}"
 *
 */
public class QueryIndexer {
    Connection conn;

    private Map<String, Integer> labelRelation = new HashMap<>();
    private Map<String, Integer> labelNodes = new HashMap<>();
    private Map<String, Integer> propertyCountOfNodes = new HashMap<>();
    private Map<String, Integer> nodeLabelIncoming = new HashMap<>();
    private Map<String, Integer> nodeLabelOutgoing = new HashMap<>();
    private Map<String, Map<String, Integer>> nodeRelationInEdgeCount = new HashMap<>();
    private Map<String, Map<String, Integer>> nodeRelationOutEdgeCount = new HashMap<>();

    private Integer numberOfNodes = 0, numberOfRelations = 0;
    private DBUtil dbUtil;

    public QueryIndexer(Connection conn) {
        this.conn = conn;
        this.dbUtil = new DBUtil(conn);

        // Get number of nodes and relations
        String statement = "SELECT COUNT(*) FROM ObjectType WHERE type != \"0\";";
        this.numberOfNodes = dbUtil.getIntegerFromSQL(statement);

        statement = "SELECT COUNT(*) FROM Edge;";
        this.numberOfRelations = dbUtil.getIntegerFromSQL(statement);

        // Get number of nodes with same label
        statement = "select label, COUNT(*) from NodeLabel GROUP BY (label);";
        labelNodes = dbUtil.getMapFromSQL(statement);

        // Get number of relations with same label
        statement = "SELECT rel_type, COUNT(*) from Edge GROUP BY rel_type;";
        labelRelation = dbUtil.getMapFromSQL(statement);

        // Get number of distinct values of each property in nodes.
        statement = "SELECT DISTINCT(name) FROM typeProperty WHERE id > 0";
        List<String> nodeFields = dbUtil.getListFromSQL(statement);

        for(String field : nodeFields){
            statement = "SELECT COUNT(distinct value) FROM P_" + field + ";";
            Integer counts = dbUtil.getIntegerFromSQL(statement);
            Integer prevCount = propertyCountOfNodes.getOrDefault(field, 0);
            propertyCountOfNodes.put(field, prevCount + counts);
        }

        // Get number of edges that comes out of nodes with same label.
        statement = "SELECT label, COUNT(DISTINCT eid) from (Edge e LEFT JOIN NodeLabel n ON e.node1 = n.gid) GROUP BY (label);";
        nodeLabelOutgoing = dbUtil.getMapFromSQL(statement);

        // Get number of edges that goes into nodes with same label.
        statement = "SELECT label, COUNT(DISTINCT eid) from (Edge e LEFT JOIN NodeLabel n ON e.node2 = n.gid) GROUP BY (label);";
        nodeLabelIncoming = dbUtil.getMapFromSQL(statement);


        for(String label : labelNodes.keySet()){
            nodeRelationOutEdgeCount.put(label, new HashMap<>());
            for(String relationLabel : labelRelation.keySet()){
                statement =
                        "SELECT COUNT(*)\n" +
                        "from Edge LEFT JOIN NodeLabel ON Edge.node1 = NodeLabel.gid " +
                        "WHERE label = \"" + label + "\" AND rel_type = \"" + relationLabel + "\"";
                Integer edges = dbUtil.getIntegerFromSQL(statement);
                nodeRelationOutEdgeCount.get(label).put(relationLabel, edges);
            }
        }

        for(String label : labelNodes.keySet()){
            nodeRelationInEdgeCount.put(label, new HashMap<>());
            for(String relationLabel : labelRelation.keySet()){
                statement =
                        "SELECT COUNT(*)\n" +
                                "from Edge LEFT JOIN NodeLabel ON Edge.node2 = NodeLabel.gid " +
                                "WHERE label = \"" + label + "\" AND rel_type = \"" + relationLabel + "\"";
                Integer edges = dbUtil.getIntegerFromSQL(statement);
                nodeRelationInEdgeCount.get(label).put(relationLabel, edges);
            }
        }

    }

    public int getIncomingOfNodeRelation(String nodeLabel, String relationLabel){
        if(relationLabel.equals("")){
            return nodeLabel.equals("") ? numberOfRelations : nodeLabelIncoming.get(nodeLabel);
        }
        if(nodeLabel.equals("")){
            return labelRelation.get(relationLabel);
        }
        return nodeRelationInEdgeCount.get(nodeLabel).get(relationLabel);

    }

    public int getOutingOfNodeRelation(String nodeLabel, String relationLabel){
        if(relationLabel.equals("")){
            return nodeLabel.equals("") ? numberOfRelations : nodeLabelOutgoing.get(nodeLabel);
        }
        if(nodeLabel.equals("")){
            return labelRelation.get(relationLabel);
        }
        return nodeRelationOutEdgeCount.get(nodeLabel).get(relationLabel);

    }

    public int getNodesWithLabel(String label){
        return labelNodes.getOrDefault(label, -1);
    }

    public int getNodesWithProperty(String property){
        return propertyCountOfNodes.getOrDefault(property, -1);
    }

    public int getIncomingOfLabel(String label){
        return nodeLabelIncoming.getOrDefault(label, 0);
    }

    public int getOutgoingOfLabel(String label){
        return nodeLabelOutgoing.getOrDefault(label, 0);
    }

    public int getRelationsWithLabel(String label){
        return labelRelation.getOrDefault(label, -1);
    }

    public int getNumberOfNode(){
        return this.numberOfNodes;
    }

    public int getNumberOfRelations(){
        return this.numberOfRelations;
    }
}
