package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.PlanTree;
import Entity.Equality;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/29/17.
 *
 */
public class NodeHashJoinPlan extends Plan {
    private PlanTable from, to;
    private List<Equality> eqlist;

    public NodeHashJoinPlan(QueryIndexer queryIndexer, PlanTable from, PlanTable to, List<Equality> equalities) {
        super(queryIndexer);
        this.from = from;
        this.to = to;
        this.eqlist = equalities;

        //TODO: This size estimation is wrong.
        //this.estimatedSize = table1.estimatedSize > table2.estimatedSize ? table1.estimatedSize : table2.estimatedSize;
        this.estimatedSize = from.estimatedSize > to.estimatedSize ? from.estimatedSize : to.estimatedSize;
    }

    @Override
    public void applyTo(PlanTable table) {
        table.nodes.addAll(from.nodes);
        table.relations.addAll(from.relations);
        table.estimatedSize = this.estimatedSize;
        table.cost += this.estimatedSize;
        table.plans = PlanTree.Combine(from.plans, to.plans, this);
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        List<String> eqStrList = new ArrayList<>();
        for(Equality equality : eqlist){
            eqStrList.add(equality.toString());
        }
        return String.join(", ", eqStrList);
    }

    @Override
    public String getVariable() {
        String result = String.join(", ", from.nodes);
        result += " -- ";
        result += String.join(", ", to.nodes);
        return result;
    }

    @Override
    public String getName() {
        return "NodeHashJoin";
    }

    public List<Equality> getEquality(){
        return this.eqlist;
    }
}
