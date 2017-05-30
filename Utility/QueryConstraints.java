package Utility;

import Utility.Constraint;
import Utility.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/23/17.
 */
public class QueryConstraints {
    private List<Constraint> cons = new ArrayList<>();

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
}
