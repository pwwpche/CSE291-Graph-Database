package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Utility.QueryConstraints;

/**
 * Created by liuche on 5/31/17.
 */
public class RangeExpandAllPlan extends Plan {
    private RelationEdge edge;
    private String fromNode;
    private String toNode;
    private QueryConstraints cons;
    public RangeExpandAllPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.estimatedSize = table.estimatedSize;
        this.cons = constraints;
        fromNode = table.nodes.contains(edge.start) ? edge.start : edge.end;
        toNode = table.nodes.contains(edge.start) ? edge.end : edge.start;
        //TODO: NOT FINISHED YET.
        assert false;
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
        return "-[" + edge.name + "]-(" + toNode + ")";
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
