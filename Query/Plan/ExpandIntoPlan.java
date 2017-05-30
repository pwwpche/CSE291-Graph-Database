package Query.Plan;

import Query.QueryIndexer;
import Query.RelationEdge;
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
        double validEdges = Integer.MAX_VALUE;
        // TODO: Improve size estimation of edge expansion
        for(Constraint constraint : constraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                if(validEdges == Integer.MAX_VALUE){
                    validEdges = 0;
                }
                for(String type : types){
                    validEdges += indexer.getRelationsWithLabel(type);
                }
            }
            //TODO: Relation with string property is not implemented.
        }
        if(validEdges !=  Integer.MAX_VALUE){
            this.estimatedSize = (int)(this.estimatedSize * 1.0 * (validEdges * 1.0 / indexer.getNumberOfRelations()));
        }
    }

    @Override
    public void applyTo(PlanTable table) {
        table.relations.add(edge.name);
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
        super.applyTo(table);
    }

    @Override
    public String getParams() {
        return edge.name;
    }

    @Override
    public String getVariable() {
        return edge.start;
    }

    @Override
    public String getName() {
        return "ExpandIntoPlan";
    }

    public RelationEdge getRelationEdge(){
        return this.edge;
    }
}
