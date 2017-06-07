package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 * This plan is discarded.
 */
public class ScanByIdPlan extends Plan {

    public ScanByIdPlan(QueryIndexer queryIndexer, String node) {
        super(queryIndexer);
        this.variable = node;
        this.estimatedSize = 1;
        this.variable = node;
    }


    @Override
    public void applyTo(PlanTable table) {
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.cost += 1;
        table.plans.add(this);
    }


    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getName() {
        return "ScanById";
    }
}
