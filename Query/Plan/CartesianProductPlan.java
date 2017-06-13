package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.PlanTree;

/**
 * Created by liuche on 5/29/17.
 */
public class CartesianProductPlan extends Plan {
    PlanTable table1, table2;
    public CartesianProductPlan(QueryIndexer queryIndexer, PlanTable table1, PlanTable table2) {
        super(queryIndexer);
        this.table1 = table1;
        this.table2 = table2;
        this.estimatedSize = table1.estimatedSize * table2.estimatedSize;

    }

    @Override
    public void applyTo(PlanTable table) {
        assert table.nodes.equals(table2.nodes);
        table.nodes.addAll(table1.nodes);
        table.relations.addAll(table1.relations);
        table.estimatedSize = this.estimatedSize;
        table.plans = PlanTree.Combine(table1.plans, table2.plans, this);
        table.cost += this.estimatedSize;
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return super.getParams();
    }

    @Override
    public String getVariable() {
        String result = String.join(", ", table1.nodes);
        result += " -- ";
        result += String.join(", ", table2.nodes);
        return result;
    }

    @Override
    public String getName() {
        return "CartesianProduct";
    }
}
