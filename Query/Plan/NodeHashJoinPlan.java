package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Utility.Equality;

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
        //this.estimatedSize = from.estimatedSize > to.estimatedSize ? from.estimatedSize : to.estimatedSize;
        this.estimatedSize = from.estimatedSize * to.estimatedSize;
    }

    @Override
    public void applyTo(PlanTable table) {
        table.nodes.addAll(from.nodes);
        table.relations.addAll(from.relations);
        table.estimatedSize = this.estimatedSize;
        table.plans.addAll(from.plans);
        table.plans.add(this);
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
        return "NodeHashJoinPlan";
    }

    public List<Equality> getEquality(){
        return this.eqlist;
    }
}
