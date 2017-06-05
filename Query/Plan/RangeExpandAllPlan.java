package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Entity.Constraint;
import Entity.Pair;
import Entity.QueryConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/31/17.
 */
public class RangeExpandAllPlan extends Plan {
    private RelationEdge edge;
    private String fromNode;
    private String toNode;
    private QueryConstraints cons;
    private Pair<Integer, Integer> range;



    public RangeExpandAllPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        this.cons = constraints;
        fromNode = table.nodes.contains(edge.start) ? edge.start : edge.end;
        toNode = table.nodes.contains(edge.start) ? edge.end : edge.start;

        //TODO: NOT FINISHED YET.
        List<String> usedLabels = new ArrayList<>();
        List<String> usedRelations = new ArrayList<>();
        for( Plan plan : table.plans.toList()){
            if (plan instanceof ScanByLabelPlan) {
                if(((ScanByLabelPlan) plan).variable.equals(fromNode)){
                    usedLabels.addAll(((ScanByLabelPlan) plan).labels);
                }
            }
        }



        for(Constraint constraint : constraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                usedRelations.addAll(types);
            }else if (constraint.name.equals("range")){
                assert constraint.value.type.equals("Pair");
                Pair<Integer, Integer> range = (Pair<Integer, Integer>) constraint.value.val;
                this.range = range;
            }
            //TODO: Relation with string property is not implemented.
        }

        assert range.getV0() <= range.getV1();
        if(range.getV1() == 0){
            this.estimatedSize = table.estimatedSize;
            return ;
        }

        for(int iteration = 1 ; iteration <= range.getV1() ; iteration++){
            if(iteration == 1){
                // First iteration is the same as expandAll.
                if(usedLabels.size() == 0 && usedRelations.size() == 0){
                    int minExpand = indexer.getNumberOfNode() < indexer.getNumberOfRelations()
                            ? indexer.getNumberOfNode()
                            : indexer.getNumberOfRelations();
                    this.estimatedSize = (int) (this.estimatedSize * 1.0 * minExpand);
                }else{
                    if (usedLabels.size() == 0 && usedRelations.size() == 0) {
                        int minExpand = indexer.getNumberOfNode() < indexer.getNumberOfRelations()
                                ? indexer.getNumberOfNode()
                                : indexer.getNumberOfRelations();
                        this.estimatedSize = (int) (this.estimatedSize * 1.0 * minExpand);
                    } else {
                        if (usedLabels.size() == 0) {
                            double size = 0;
                            for (String relationLabel : usedRelations) {
                                size += indexer.getRelationsWithLabel(relationLabel);
                            }
                            double minExpand = indexer.getNumberOfNode() < size
                                    ? indexer.getNumberOfNode() : size;
                            this.estimatedSize = (int) (this.estimatedSize * 1.0 * minExpand);
                        } else {

                            double minEdgesOfLabel = 0;
                            for (String relation : usedRelations) {
                                int cost = 0;
                                int outgoingSize = Integer.MAX_VALUE, incomingSize = Integer.MAX_VALUE;

                                for (String nodeLabel : usedLabels) {
                                    if (!edge.direction.equals("<--")) {
                                        int edges = indexer.getOutingOfNodeRelation(nodeLabel, relation);
                                        outgoingSize = outgoingSize < edges ? outgoingSize : edges;
                                    }
                                    if (!edge.direction.equals("-->")) {
                                        int edges = indexer.getIncomingOfNodeRelation(nodeLabel, relation);
                                        incomingSize = incomingSize < edges ? incomingSize : edges;
                                    }
                                }
                                switch (edge.direction) {
                                    case "-->":
                                        cost = outgoingSize;
                                        break;
                                    case "<--":
                                        cost = incomingSize;
                                        break;
                                    case "<-->":
                                        cost = (incomingSize < outgoingSize) ? incomingSize : outgoingSize;
                                        break;
                                    case "--":
                                        cost = (incomingSize > outgoingSize) ? incomingSize : outgoingSize;
                                        break;
                                    default:
                                        break;
                                }
                                minEdgesOfLabel += cost;
                            }
                            this.estimatedSize = (int) (this.estimatedSize * 1.0 * minEdgesOfLabel);
                        }
                    }
                }
            }else{
                //Starting from second iteration, only expand by relations.
                double edges = 0;
                for(String relation : usedRelations){
                    edges += indexer.getRelationsWithLabel(relation);
                }
                edges = (usedRelations.size() == 0) ? indexer.getNumberOfRelations() : edges;
                this.estimatedSize = (int)(this.estimatedSize * 1.0 * edges);
            }
        }

    }

    @Override
    public void applyTo(PlanTable table) {
        super.applyTo(table);
        table.relations.add(edge.name);
        table.nodes.add(toNode);
        table.cost += this.estimatedSize;
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return "-[" + edge.name + (cons.getConstraints().size() == 0 ? "" : ":" + cons.toString()) + "]-(" + toNode + ")";
    }

    @Override
    public String getVariable() {
        return fromNode;
    }

    @Override
    public String getName() {
        return "RangeExpandAll";
    }

    public String getExpandedNode(){
        return toNode;
    }
}
