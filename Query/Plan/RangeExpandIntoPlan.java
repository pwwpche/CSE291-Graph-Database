package Query.Plan;

import Query.Engine.QueryIndexer;
import Query.Entities.PlanTable;
import Query.Entities.RelationEdge;
import Utility.Constraint;
import Utility.Pair;
import Utility.QueryConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/30/17.
 */
public class RangeExpandIntoPlan extends Plan {
    private RelationEdge edge;
    private QueryConstraints cons;
    private Pair<Integer, Integer> range;
    private Integer cost;

    private List<String> getLabels(String variable, PlanTable table) {

        List<String> result = new ArrayList<>();
        for (Plan plan : table.plans.toList()) {
            if (plan instanceof ScanByLabelPlan) {
                if (((ScanByLabelPlan) plan).variable.equals(variable)) {
                    result.addAll(((ScanByLabelPlan) plan).labels);
                }
            } else if (plan instanceof FilterConstraintPlan && plan.getVariable().equals(variable)) {
                Constraint constraint = ((FilterConstraintPlan) plan).getConstraint();
                if (constraint.name.equals("nodeLabels")) {
                    List<String> labels = (List<String>) constraint.value.val;
                    result.addAll(labels);
                }
            }
        }
        return result;
    }

    public RangeExpandIntoPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.cons = constraints;
        this.estimatedSize = table.estimatedSize;

        List<String> labels1 = getLabels(edge.start, table);
        List<String> labels2 = getLabels(edge.end, table);
        List<String> relations = new ArrayList<>();

        for (Constraint constraint : constraints.getConstraints()) {
            if (constraint.name.equals("rel_type")) {
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            } else if (constraint.name.equals("range")) {
                assert constraint.value.type.equals("Pair");
                Pair<Integer, Integer> range = (Pair<Integer, Integer>) constraint.value.val;
                this.range = range;
            }
        }

        // Size estimation for (a:A:B)-[r:R1|R2]->(b:D:E)
        Integer outgoingSize = 0, incomingSize = 0;
        if (!edge.direction.equals("<--")) {
            int currentSize = 0;
            for (String relation : relations) {
                int minEdges = Integer.MAX_VALUE;
                for (String label : labels1) {
                    int edges = indexer.getOutingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            outgoingSize = currentSize;
            currentSize = 0;
            for (String relation : relations) {
                int minEdges = Integer.MAX_VALUE;
                for (String label : labels2) {
                    int edges = indexer.getIncomingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            outgoingSize = (outgoingSize < currentSize) ? outgoingSize : currentSize;
        }

        if (!edge.direction.equals("-->")) {
            int currentSize = 0;
            for (String relation : relations) {
                int minEdges = Integer.MAX_VALUE;
                for (String label : labels1) {
                    int edges = indexer.getIncomingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            incomingSize = currentSize;
            currentSize = 0;
            for (String relation : relations) {
                int minEdges = Integer.MAX_VALUE;
                for (String label : labels2) {
                    int edges = indexer.getOutingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            incomingSize = (incomingSize < currentSize) ? incomingSize : currentSize;
        }
        int cost = 0;
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
        this.estimatedSize = this.estimatedSize < cost ? this.estimatedSize : cost;


        return;

    }

    @Override
    public void applyTo(PlanTable table) {
        super.applyTo(table);
        table.relations.add(edge.name);
        if (range.getV1() > 0) {
            for (int i = 1; i <= range.getV1(); i++) {
                table.cost += this.estimatedSize;
            }
        }
        table.estimatedSize = estimatedSize;
        table.plans.add(this);
    }

    @Override
    public String getParams() {
        return "-[" + edge.name + (cons.getConstraints().size() == 0 ? "" : ":" + cons.toString()) + "]-(" + edge.end + ")";
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
