package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/30/17.
 */
public class ValueRangeExpandPlan extends Plan {

    public ValueRangeExpandPlan(QueryIndexer queryIndexer) {
        super(queryIndexer);
    }

    @Override
    public void applyTo(PlanTable table) {
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return super.getParams();
    }

    @Override
    public String getVariable() {
        return super.getVariable();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
