package Query.Execution;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 6/13/17.
 */
public class RangePath {
    public enum Direction{FROM, TO, ALL}

    private List<String> neighbors = new ArrayList<>();
    private List<String> relations = new ArrayList<>();
    private Direction direction;


    public List<String> getNeighbors() {
        return neighbors;
    }

    public List<String> getRelations() {
        return relations;
    }

    public Direction getDirection() {
        return direction;
    }

    public RangePath(Direction direction){
        this.direction = direction;
    }

    public RangePath(Direction direction, String startNode){
        this.direction = direction;
        neighbors.add(startNode);
    }

    public String backNode(){
        assert neighbors.size() > 0;
        return neighbors.get(neighbors.size() - 1);
    }

    public RangePath(RangePath other){
        neighbors.addAll(other.getNeighbors());
        relations.addAll(other.getRelations());
        this.direction = other.getDirection();

    }

    public void put(String nextNode, String nextEdge){
        neighbors.add(nextNode);
        relations.add(nextEdge);
    }

    @Override
    public String toString(){
        if(neighbors.size() == 0){
            return "";
        }
        StringBuilder str = new StringBuilder("(" + neighbors.get(0) + ")");
        for(int i = 1 , size = neighbors.size() ; i < size ; i++){
            str.append(direction.equals(Direction.TO) ? "<" : "").append("-[").append(relations.get(i - 1)).append("]-");
            str.append(direction.equals(Direction.FROM) ? ">" : "");
            str.append("(").append(neighbors.get(i)).append(")");
        }
        return str.toString();
    }
}
