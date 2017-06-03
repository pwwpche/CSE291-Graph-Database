package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Utility.QueryConstraints;

import java.util.List;

/**
 * Created by liuche on 5/30/17.
 */
public class RangeExpandIntoPlan extends Plan {
    private RelationEdge edge;
    private QueryConstraints cons;
    public RangeExpandIntoPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.cons = constraints;
        this.estimatedSize = table.estimatedSize;

        boolean foundNode = false;
        double validNodes = 0;

        // Size estimation for (a:A:B)-[]->()
        for(Plan plan : table.plans){
            if(plan instanceof ScanByLabelPlan){
                if(((ScanByLabelPlan) plan).variable.equals(edge.start)){
                    foundNode = true;
                    List<String> labels = ((ScanByLabelPlan) plan).labels;
                    for(String label : labels){
                        int nodeWithLabel = indexer.getNodesWithLabel(label);
                        validNodes = validNodes > nodeWithLabel ? nodeWithLabel : validNodes;
                    }
                }
            }
        }
        if(!foundNode){
            double edgeRatio = validNodes * 1.0 / indexer.getNumberOfNode();
            this.estimatedSize = (int)(this.estimatedSize * 1.0 * edgeRatio);
        }

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
        return "RangeExpandInto";
    }
}
