package DAO;

/**
 * Created by liuche on 5/21/17.
 */
public class GraphEdge {
    String name;
    String type;
    Integer startNodeID;
    Integer endNodeID;
    public GraphEdge(String name, String type, int startNodeID, int endNodeID){
        this.name = name;
        this.type = type;
        this.startNodeID = startNodeID;
        this.endNodeID = endNodeID;
    }
}
