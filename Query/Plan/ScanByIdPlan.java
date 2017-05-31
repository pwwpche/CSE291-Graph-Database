package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 */
public class ScanByIdPlan extends Plan {
    String variable;

    public ScanByIdPlan(QueryIndexer queryIndexer, String node) {
        super(queryIndexer);
        this.variable = node;
        this.estimatedSize = 1;
    }


    @Override
    public void applyTo(PlanTable table) {
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return "";
    }

    @Override
    public String getName() {
        return "ScanByIdPlan";
    }
}
