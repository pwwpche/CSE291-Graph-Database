package Query.Engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by liuche on 5/29/17.
 */
public class QueryIndexer {
    Connection conn;

    Map<String, Integer> labelRelation = new HashMap<>();
    Map<String, Integer> labelNodes = new HashMap<>();
    Map<String, Integer> propertyCountOfNodes = new HashMap<>();
    Map<String, Integer> nodeLabelIncoming = new HashMap<>();
    Map<String, Integer> nodeLabelOutgoing = new HashMap<>();
    Map<String, Map<String, Integer>> nodeRelationInEdgeCount = new HashMap<>();
    Map<String, Map<String, Integer>> nodeRelationOutEdgeCount = new HashMap<>();

    Integer numberOfNodes = 0, numberOfRelations = 0;

    private Integer getIntegerFromSQL(String statement){
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

    private List<String> getListFromSQL(String statement){
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

    private Map<String, Integer> getMapFromSQL(String statement){
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


    public QueryIndexer(Connection conn) {
        this.conn = conn;

        // Get number of nodes and relations
        String statement = "SELECT COUNT(*) FROM person;";
        this.numberOfNodes = getIntegerFromSQL(statement);
        statement = "SELECT COUNT(*) FROM movie;";
        this.numberOfNodes += getIntegerFromSQL(statement);

        statement = "SELECT COUNT(*) FROM Edge;";
        this.numberOfRelations = getIntegerFromSQL(statement);

        // Get number of nodes with same label
        statement = "select label, COUNT(*) from NodeLabel GROUP BY (label);";
        labelNodes = getMapFromSQL(statement);

        // Get number of relations with same label
        statement = "SELECT rel_type, COUNT(*) from Edge GROUP BY rel_type;";
        labelRelation = getMapFromSQL(statement);

        ArrayList<String> node1Fields = new ArrayList<>(Arrays.asList(
                "birthday", "birthplace", "deg", "name", "lastModified",
                "id", "biography", "version", "profileImageUrl"
        ));

        for(String field : node1Fields){
            statement = "SELECT COUNT(distinct " + field + ") FROM Person;";
            Integer counts = getIntegerFromSQL(statement);
            Integer prevCount = propertyCountOfNodes.getOrDefault(field, 0);
            propertyCountOfNodes.put(field, prevCount + counts);
        }


        ArrayList<String> node2Fields = new ArrayList<>(Arrays.asList(
                "studio", "releaseDate", "imdbId", "runtime", "description",
                "language", "title", "version", "trailer", "imageUrl", "genre",
                "tagline", "lastModified", "id", "homepage"
        ));

        for(String field : node2Fields){
            statement = "SELECT COUNT(distinct " + field + ") FROM Movie;";
            Integer counts = getIntegerFromSQL(statement);
            Integer prevCount = propertyCountOfNodes.getOrDefault(field, 0);
            propertyCountOfNodes.put(field, prevCount + counts);
        }

        statement = "SELECT label, COUNT(DISTINCT eid) from (Edge e LEFT JOIN NodeLabel n ON e.pid = n.pid) GROUP BY (label);";
        nodeLabelOutgoing = getMapFromSQL(statement);

        statement = "SELECT label, COUNT(DISTINCT eid) from (Edge e LEFT JOIN NodeLabel n ON e.mid = n.mid) GROUP BY (label);";
        nodeLabelIncoming = getMapFromSQL(statement);

        for(String nodeLabel : labelNodes.keySet()){
            nodeRelationOutEdgeCount.put(nodeLabel, new HashMap<>());
            for(String relationLabel : labelRelation.keySet()){
                statement =
                        "SELECT COUNT(*)\n" +
                        "from Edge LEFT JOIN Person ON Edge.pid = Person.id " +
                        "  LEFT JOIN NodeLabel ON Person.id = NodeLabel.pid " +
                        "WHERE label = \"" + nodeLabel + "\" AND rel_type = \"" + relationLabel + "\"";
                Integer edges = getIntegerFromSQL(statement);
                nodeRelationOutEdgeCount.get(nodeLabel).put(relationLabel, edges);
            }
        }

        for(String nodeLabel : labelNodes.keySet()){
            nodeRelationInEdgeCount.put(nodeLabel, new HashMap<>());
            for(String relationLabel : labelRelation.keySet()){
                statement =
                        "SELECT COUNT(*)\n" +
                                "from Edge LEFT JOIN Movie ON Edge.mid = Movie.id " +
                                "  LEFT JOIN NodeLabel ON Movie.id = NodeLabel.mid " +
                                "WHERE label = \"" + nodeLabel + "\" AND rel_type = \"" + relationLabel + "\"";
                Integer edges = getIntegerFromSQL(statement);
                nodeRelationInEdgeCount.get(nodeLabel).put(relationLabel, edges);
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
