package Query.Execution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 6/13/17.
 */
public class RangePath {
    public List<String> neighbors = new ArrayList<>();
    public List<String> relations = new ArrayList<>();


    public RangePath(){}

    public RangePath(String startNode){
        neighbors.add(startNode);
    }

    public String backNode(){
        assert neighbors.size() > 0;
        return neighbors.get(neighbors.size() - 1);
    }

    public RangePath(RangePath other){
        neighbors.addAll(other.neighbors);
        relations.addAll(other.relations);
    }

    public void put(String nextNode, String nextEdge){
        neighbors.add(nextNode);
        relations.add(nextEdge);
    }
}
