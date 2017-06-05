package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 */
public class ScanByPropertyPlan extends Plan {
    String property;
    String value;
    public ScanByPropertyPlan(QueryIndexer queryIndexer, String node, String property, String val) {
        super(queryIndexer);
        this.variable = node;
        this.property = property;
        this.value = val;
        this.estimatedSize = indexer.getNumberOfNode() / indexer.getNodesWithProperty(property);
    }

    @Override
    public void applyTo(PlanTable table) {
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.cost += this.estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return property + "=" + value;
    }

    @Override
    public String getName() {
        return "ScanByProperty";
    }
}
