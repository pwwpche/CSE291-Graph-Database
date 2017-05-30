package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 5/21/17.
 */
public class GraphNode {
    public int ID;
    public String table;
    public List<String> NodeLabel = new ArrayList<>();
    public Map<String, String> content = new HashMap<>();



    @Override
    public int hashCode() {
        return table.hashCode() + new Integer(ID).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof GraphNode){
            GraphNode other =  ((GraphNode) obj);
            return other.ID == this.ID && other.table.equals(this.table);
        }
        return false;
    }
}
