package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Utility.Constraint;
import Utility.QueryConstraints;

import java.util.List;

/**
 * Created by liuche on 5/29/17.
 *
 */
public class ExpandIntoPlan extends Plan {
    private RelationEdge edge;

    public ExpandIntoPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        double validEdges = Double.MAX_VALUE;

        // Size estimation for (a:A:B)-[]->()
        for(Plan plan : table.plans){
            if(plan instanceof ScanByLabelPlan){
                if(validEdges == Double.MAX_VALUE){
                    validEdges = 0;
                }
                if(((ScanByLabelPlan) plan).variable.equals(edge.start)){
                    List<String> labels = ((ScanByLabelPlan) plan).labels;
                    for(String label : labels){
                        int outingEdge = indexer.getOutgoingOfLabel(label);
                        validEdges = validEdges > outingEdge ? outingEdge : validEdges;
                    }
                }

            }
        }
        if(validEdges != Double.MAX_VALUE){
            double edgeRatio = validEdges * 1.0 / indexer.getNumberOfNode();
            this.estimatedSize = (int)(this.estimatedSize * 1.0 * edgeRatio);
        }

        // Size estimation for (a)-[r:R1|R2]->()
        if(validEdges == Double.MAX_VALUE){
            // TODO: Improve size estimation of edge expansion
            for(Constraint constraint : constraints.getConstraints()){
                if(constraint.name.equals("rel_type")){
                    assert constraint.value.type.contains("List");
                    List<String> types = (List<String>) constraint.value.val;
                    if(validEdges == Double.MAX_VALUE){
                        validEdges = 0;
                    }
                    for(String type : types){
                        validEdges += indexer.getRelationsWithLabel(type);
                    }
                }
                //TODO: Relation with string property is not implemented.
            }
            if(validEdges !=  Double.MAX_VALUE){
                double edgeRatio = validEdges * 1.0 / indexer.getNumberOfRelations();
                this.estimatedSize = (int)(this.estimatedSize * 1.0 * edgeRatio);
            }

        }



    }

    @Override
    public void applyTo(PlanTable table) {
        table.relations.add(edge.name);
        table.cost += table.estimatedSize;
        table.estimatedSize = this.estimatedSize;
        table.plans.add(this);
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return "-[" + edge.name + "]-(" + edge.end + ")";
    }

    @Override
    public String getVariable() {
        return edge.start;
    }

    @Override
    public String getName() {
        return "ExpandInto";
    }

    public RelationEdge getRelationEdge(){
        return this.edge;
    }
}
