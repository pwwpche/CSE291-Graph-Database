package Query.Plan;

import Query.QueryIndexer;

/**
 * Created by liuche on 5/29/17.
 */
public class CartesianProductPlan extends Plan {
    PlanTable from, to;
    public CartesianProductPlan(QueryIndexer queryIndexer, PlanTable from, PlanTable to) {
        super(queryIndexer);
        this.from = from;
        this.to = to;
        this.estimatedSize = from.estimatedSize * to.estimatedSize;

    }

    @Override
    public void applyTo(PlanTable table) {
        assert table.equals(this.to);
        table.nodes.addAll(from.nodes);
        table.relations.addAll(from.relations);
        table.estimatedSize = this.estimatedSize;
        table.plans.addAll(from.plans);
        table.plans.add(this);
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return super.getParams();
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
        return "CartesianProduct";
    }
}
