package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Entity.Constraint;
import Entity.QueryConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/29/17.
 *
 */
public class ExpandAllPlan extends Plan {
    private RelationEdge edge;
    private String fromNode;
    private String toNode;
    private QueryConstraints relationConstraints;
    private QueryConstraints nodeConstraints;


    public ExpandAllPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints nodeCons, QueryConstraints edgeCons, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        this.relationConstraints = edgeCons;
        this.nodeConstraints = nodeCons;
        fromNode = table.nodes.contains(edge.start) ? edge.start : edge.end;
        toNode = table.nodes.contains(edge.start) ? edge.end : edge.start;
//        this.edge.start = fromNode;
//        this.edge.end = toNode;
        List<String> usedLabels = new ArrayList<>();
        List<String> usedRelations = new ArrayList<>();
        for (Plan plan : table.plans.toList()) {
            if (plan instanceof ScanByLabelPlan) {
                if (((ScanByLabelPlan) plan).variable.equals(fromNode)) {
                    usedLabels.addAll(((ScanByLabelPlan) plan).labels);
                }
            }
        }

        for (Constraint constraint : edgeCons.getConstraints()) {
            if (constraint.name.equals("rel_type")) {
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                usedRelations.addAll(types);
            }
            //TODO: Relation with string property is not implemented.
        }
        if (usedLabels.size() == 0 && usedRelations.size() == 0) {
            int minExpand = indexer.getAvgEdgesOfNode();
            this.estimatedSize = (int) (this.estimatedSize * 1.0 * minExpand);
        } else {
            if (usedLabels.size() == 0) {
                double size = 0;
                for (String relationLabel : usedRelations) {
                    size += indexer.getRelationsWithLabel(relationLabel);
                }
                double minExpand = indexer.getAvgEdgesOfNode() < size
                        ? indexer.getAvgEdgesOfNode() : size;
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

    public QueryConstraints getRelationConstraint(){
        return this.relationConstraints;
    }

    public void setNodeConstraints(QueryConstraints cons){
        this.nodeConstraints = cons;
    }

    public QueryConstraints getNodeConstraints(){
        return this.nodeConstraints;
    }

    @Override
    public void applyTo(PlanTable table) {
        table.relations.add(edge.name);
        table.nodes.add(toNode);
        table.cost += this.estimatedSize;
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
        super.applyTo(table);
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
        return "ExpandAll";
    }

    public RelationEdge getRelationEdge() {
        return this.edge;
    }

    public String getExpandedNode() {
        return toNode;
    }
}
