package Entity;

import javax.management.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 5/23/17.
 */
public class QueryConstraints {
    private List<Constraint> cons = new ArrayList<>();

    public QueryConstraints(){

    }

    public QueryConstraints(QueryConstraints other){
        this.cons.addAll(other.getConstraints());
    }

    public void add(Constraint constraint){
        cons.add(constraint);
    }

    public void add(String name, Value val){
        cons.add(new Constraint(name, val));
    }

    public void add(String name, String relation, Value val){
        cons.add(new Constraint(name, relation, val));
    }

    public List<Constraint> getConstraints(){
        return cons;
    }

    @Override
    public String toString() {
        List<String> consStr = new ArrayList<>();
        cons.forEach(constraint -> consStr.add(constraint.toString()));
        return String.join("", consStr);
    }


    public List<String> getNodeLabels(){
        List<String> nodeLabels = new ArrayList<>();
        for(Constraint constraint : cons){
            if(constraint.name.equals("nodeLabels")){
                List<String> labels = (List<String>) constraint.value.val;
                nodeLabels.addAll(labels);
            }
        }
        return nodeLabels;
    }

    public Map<String, String> getNodeProperties(){
        Map<String, String> nodeProperties = new HashMap<>();
        for(Constraint constraint : cons){
            if(!constraint.name.equals("nodeLabels")){
                String key = constraint.name;
                String value = constraint.value.val.toString();
                nodeProperties.put(key, value);
            }
        }
        return nodeProperties;
    }

    public List<String> getEdgeLabels(){
        List<String> relationLabels = new ArrayList<>();
        for(Constraint constraint : cons){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relationLabels.addAll(types);
            }
        }
        return relationLabels;
    }

    public Pair<Integer, Integer> getEdgeRange(){
        for(Constraint constraint : cons){
            if(constraint.name.equals("range")){
                assert constraint.value.type.equals("Pair");
                Pair<Integer, Integer> range = (Pair<Integer, Integer>) constraint.value.val;
                assert range.getV0() <= range.getV1();
                return range;
            }
        }
        assert false;
        return Pair.mkPair(0, 0);
    }
}
