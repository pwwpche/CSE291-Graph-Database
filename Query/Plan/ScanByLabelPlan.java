package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/29/17.
 */
public class ScanByLabelPlan extends Plan {
    List<String> labels = new ArrayList<>();
    public ScanByLabelPlan(QueryIndexer queryIndexer, String node, List<String> labels) {
        super(queryIndexer);
        this.variable = node;
        this.labels = labels;
        Integer size = queryIndexer.getNumberOfNode();
        for(String label : labels){
            size /= queryIndexer.getNodesWithLabel(label);
        }
        estimatedSize = size;
    }


    @Override
    public void applyTo(PlanTable table) {
        table.nodes.add(variable);
        table.estimatedSize = estimatedSize;
        table.cost += estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        String res = ":" + String.join(":", labels);
        return res;
    }

    public List<String> getLabels(){
        return labels;
    }

    @Override
    public String getVariable(){
        return super.getVariable();
    }

    @Override
    public String getName() {
        return "ScanByLabelPlan";
    }
}
