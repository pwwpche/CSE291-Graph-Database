package Query.Plan;

import Query.QueryIndexer;
import Query.RelationEdge;
import Utility.Constraint;
import Utility.QueryConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/29/17.
 */
public class ExpandAllPlan extends Plan {
    RelationEdge edge;
    String fromNode;
    String toNode;
    public ExpandAllPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        fromNode = table.nodes.contains(edge.start) ? edge.start : edge.end;
        toNode = table.nodes.contains(edge.start) ? edge.end : edge.start;
        List<String> usedLabels = new ArrayList<>();
        List<String> usedRelations = new ArrayList<>();
        for( Plan plan : table.plans){
            if (plan instanceof ScanByLabelPlan) {
                if(((ScanByLabelPlan) plan).variable.equals(fromNode)){
                    usedLabels.addAll(((ScanByLabelPlan) plan).labels);
                }
            }
        }

        // TODO: Improve size estimation of edge expansion
        for(Constraint constraint : constraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                usedRelations.addAll(types);
            }
            //TODO: Relation with string property is not implemented.
        }
        if(usedLabels.size() == 0 && usedRelations.size() == 0){
            this.estimatedSize = (int) (this.estimatedSize * 1.0 * (indexer.getNumberOfRelations()) / indexer.getNumberOfNode());
        }else{
            if(usedLabels.size() == 0){
                //TODO: Add seperate cases for incoming and outgoing edges.
                double size = 0;
                for(String relationLabel : usedRelations){
                    size += indexer.getRelationsWithLabel(relationLabel);
                }
                size = size / (indexer.getNumberOfRelations() * 1.0);
                this.estimatedSize = (int)(this.estimatedSize * 1.0 * size);
            }else{
                if(edge.direction.equals("--")){
                    edge.direction = "<-->";
                }
                double minEdgesOfLabel = Integer.MAX_VALUE;
                for(String nodeLabel : usedLabels){
                    double totEdges = 0;
                    if(edge.direction.contains("->")){
                        totEdges +=  indexer.getOutgoingOfLabel(nodeLabel) * 1.0 / indexer.getNodesWithLabel(nodeLabel);
                    }
                    if(edge.direction.contains("<-")){
                        totEdges +=  indexer.getIncomingOfLabel(nodeLabel) * 1.0 / indexer.getNodesWithLabel(nodeLabel);
                    }
                    minEdgesOfLabel = minEdgesOfLabel > totEdges ? totEdges : minEdgesOfLabel;
                }
                this.estimatedSize = (int)(this.estimatedSize * 1.0 * minEdgesOfLabel);
            }
        }
    }

    @Override
    public void applyTo(PlanTable table) {
        table.relations.add(edge.name);
        table.nodes.add(toNode);
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
        return fromNode;
    }

    @Override
    public String getName() {
        return "ExpandAllPlan";
    }

    public RelationEdge getRelationEdge(){
        return this.edge;
    }
}
