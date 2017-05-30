package Utility;

/**
 * Created by liuche on 5/23/17.
 */


public class Constraint {
    public enum ConstraintTypes{
        REL_TYPE, RANGE, PROPERTY,
        LABEL,

    }


    public String name;
    public String equality;
    public Value value;

    public Constraint(String name, Value value){
        this.name = name;
        this.equality = "=";
        this.value = value;
    }

    public Constraint(String name, String relation, Value value){
        this.name = name;
        this.equality = relation;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + " " + equality + " " + value.val.toString();
    }
}
