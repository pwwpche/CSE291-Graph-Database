package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 * This plan is discarded.
 */
public class ScanByIdPlan extends Plan {
    private String id;
    public ScanByIdPlan(QueryIndexer queryIndexer, String node, String id) {
        super(queryIndexer);
        this.variable = node;
        this.estimatedSize = 1;
        this.variable = node;
        this.id = id;
    }


    @Override
    public void applyTo(PlanTable table) {
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.cost += 1;
        table.plans.add(this);
    }

    public String getId(){
        return this.id;
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
