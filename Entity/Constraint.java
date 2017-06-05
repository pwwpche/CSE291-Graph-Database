package Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/23/17.
 */


public class Constraint {
    // TODO: Switch to Enum instead of string
    public enum TYPES{
        REL_TYPE, RANGE, PROPERTY,
        LABEL,

    }


    public String name;
    public String equality;
    public Value value;

    public Constraint(String name, Value value){
        this.name = name;
        this.equality = "==";
        this.value = value;
    }

    public Constraint(String name, String relation, Value value){
        this.name = name;
        this.equality = relation;
        this.value = value;
    }

    @Override
    public String toString() {
        switch (name){
            case "label": case "rel_type":
                if(equality.equals("==")){
                    return  ":" + value.val.toString();
                }else{
                    return "label != " + value.val.toString();
                }
            case "nodeLabels":
                List<String> stringList = new ArrayList<>();
                assert value.type.equals("List");
                List<String> valList = (List<String>) value.val;
                valList.forEach(val -> stringList.add(val.toString()));
                return ":" + String.join(":", stringList);
            case "range":
                assert value.type.equals("Pair");
                Pair<Integer, Integer> pair = (Pair<Integer, Integer>)value.val;
                return "*" +
                        (pair.getV0().equals(0) ? "" : pair.getV0().toString()) +
                        (pair.getV1().equals(Integer.MAX_VALUE) ? "" : "..") +
                        (pair.getV1().equals(Integer.MAX_VALUE) ? "" : pair.getV1().toString());
            default:
                return name + "" + equality + "" + value.val.toString();
        }

    }
}
