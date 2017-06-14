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
    private QueryConstraints relationConstraints;
    private Pair<Integer, Integer> range;
    private QueryConstraints nodeConstraints;


    public RangeExpandAllPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints nodeCons, QueryConstraints edgeCons, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        this.relationConstraints = edgeCons;
        this.nodeConstraints = nodeCons;
        fromNode = table.nodes.contains(edge.start) ? edge.start : edge.end;
        toNode = table.nodes.contains(edge.start) ? edge.end : edge.start;

        //TODO: NOT FINISHED YET.
        List<String> startNodeLabel = new ArrayList<>();
        List<String> usedRelations = edgeCons.getEdgeLabels();

        for( Plan plan : table.plans.toList()){
            if (plan instanceof ScanByLabelPlan) {
                if(((ScanByLabelPlan) plan).variable.equals(fromNode)){
                    startNodeLabel.addAll(((ScanByLabelPlan) plan).labels);
                }
            }
        }
        this.range = edgeCons.getEdgeRange();

        if(range.getV1() == 0){
            this.estimatedSize = table.estimatedSize;
            return ;
        }

        for(int iteration = 1 ; iteration <= range.getV1() ; iteration++){
            if(iteration == 1){
                // First iteration is the same as expandAll.
                if(startNodeLabel.size() == 0 && usedRelations.size() == 0){
                    this.estimatedSize = (int) (this.estimatedSize * 1.0 * indexer.getAvgEdgesOfNode());
                }else{
                    if (startNodeLabel.size() == 0) {
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

                            for (String nodeLabel : startNodeLabel) {
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
                                case "<-->":case "--":
                                    cost = (incomingSize < outgoingSize) ? incomingSize : outgoingSize;
                                    break;
                                default:
                                    break;
                            }
                            minEdgesOfLabel += cost;
                        }
                        this.estimatedSize = (int) (this.estimatedSize * 1.0 * minEdgesOfLabel);
                    }
                }

            }else{
                //Starting table1 second iteration, only expand by relations.
                double edges = 0;
                for(String relation : usedRelations){
                    edges += indexer.getRelationsWithLabel(relation);
                }
                edges = (usedRelations.size() == 0) ? indexer.getNumberOfRelations() : edges;
                this.estimatedSize = (int)(this.estimatedSize * 1.0 * edges);
            }
        }

    }
    public QueryConstraints getRelationConstraint(){
        return relationConstraints;
    }

    public QueryConstraints getNodeConstraints(){
        return this.nodeConstraints;
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
        return "-[" + edge.name + (relationConstraints.getConstraints().size() == 0 ? "" : ":" + relationConstraints.toString()) + "]-(" + toNode + ")";
    }

    @Override
    public String getVariable() {
        return fromNode;
    }

    @Override
    public String getName() {
        return "RangeExpandAll";
    }

    public RelationEdge getRelationEdge() {
        return this.edge;
    }

    public String getExpandedNode() {
        return toNode;
    }

    public Pair<Integer, Integer> getRange(){
        return range;
    }
}
