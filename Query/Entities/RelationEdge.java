package Query.Entities;

/**
 * Created by liuche on 5/30/17.
 */
 public class RelationEdge{
    public String name, start, end, direction;
    public boolean used;
    public RelationEdge(String name, String start, String end, String direction){
        this.name = name;
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.used = false;

    }

    @Override
    public String toString() {
        return name;
    }
}