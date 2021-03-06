package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

/**
 * Created by liuche on 5/29/17.
 */
public class AllNodeScanPlan extends Plan {

    public AllNodeScanPlan(QueryIndexer indexer, String node){
        super(indexer);
        this.variable = node;
        this.estimatedSize = indexer.getNumberOfNode();
    }

    @Override
    public void applyTo(PlanTable table){
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.cost += this.estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getName(){
        return "AllNodeScan";
    }
}
