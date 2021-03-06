package Query.Entities;

import Query.Plan.Plan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liuche on 5/29/17.
 */
public class PlanTable {
    public Set<String> nodes = new HashSet<>();
    public PlanTree plans = new PlanTree();
    public Set<String> relations = new HashSet<>();
    public double estimatedSize = 0;
    public double cost = 0;

    public PlanTable(){
        nodes = new HashSet<>();
        relations = new HashSet<>();
        estimatedSize = 0;
        cost = 0;
    }

    public PlanTable(PlanTable table){

        this.nodes.addAll(table.nodes);
        this.plans.copyAll(table.plans);
        this.relations.addAll(table.relations);
        this.estimatedSize = table.estimatedSize;
        this.cost = table.cost;
    }
}
