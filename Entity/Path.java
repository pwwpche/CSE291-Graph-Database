package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/28/17.
 */
public class Path {

    public List<String> nodes = new ArrayList<>();
    public List<String> relations = new ArrayList<>();
    public List<String> direction = new ArrayList<>();

    public Path(){}

    public Path(String startNode){
        nodes.add(startNode);
    }
    public void add(String node, String relation, String direction){
        this.nodes.add(node);
        this.relations.add(relation);
        this.direction.add(direction);
    }
}
