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
public class ExpandIntoPlan extends Plan {
    private RelationEdge edge;
    private QueryConstraints cons;

    private List<String> getLabels(String variable, PlanTable table){
        List<String> result = new ArrayList<>();
        for(Plan plan : table.plans.toList()){
            if(plan instanceof ScanByLabelPlan){
                if(((ScanByLabelPlan) plan).variable.equals(variable)){
                    result.addAll(((ScanByLabelPlan) plan).labels);
                }
            }
            if(plan instanceof FilterConstraintPlan){
                if(plan.getVariable().equals(variable)){
                    Constraint constraint = ((FilterConstraintPlan) plan).getConstraint();
                    if(constraint.name.equals("nodeLabels")){
                        List<String> labels = (List<String>) constraint.value.val;
                        result.addAll(labels);
                    }
                }
            }
        }
        return result;
    }
    public ExpandIntoPlan(QueryIndexer queryIndexer, RelationEdge edge, QueryConstraints constraints, PlanTable table) {
        super(queryIndexer);
        this.edge = edge;
        this.cons = constraints;
        this.estimatedSize = table.estimatedSize;

        List<String> labels1 = getLabels(edge.start, table);
        List<String> labels2 = getLabels(edge.end, table);
        List<String> relations = new ArrayList<>();

        for(Constraint constraint : constraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }

        }

        // Size estimation for (a:A:B)-[r:R1|R2]->(b:D:E)
        Integer outgoingSize = 0, incomingSize = 0;
        if(!edge.direction.equals("<--")){      //Directions: -->, --, <-->
            int currentSize = 0;
            for(String relation : relations){
                int minEdges = Integer.MAX_VALUE;
                for(String label : labels1){
                    int edges = indexer.getOutingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            outgoingSize = currentSize; currentSize = 0;
            for(String relation : relations){
                int minEdges = Integer.MAX_VALUE;
                for(String label : labels2){
                    int edges = indexer.getIncomingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            outgoingSize = (outgoingSize < currentSize) ? outgoingSize : currentSize;
        }

        if(!edge.direction.equals("-->")){      //Directions: <--, --, <-->
            int currentSize = 0;
            for(String relation : relations){
                int minEdges = Integer.MAX_VALUE;
                for(String label : labels1){
                    int edges = indexer.getIncomingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            incomingSize = currentSize; currentSize = 0;
            for(String relation : relations){
                int minEdges = Integer.MAX_VALUE;
                for(String label : labels2){
                    int edges = indexer.getOutingOfNodeRelation(label, relation);
                    minEdges = (minEdges < edges) ? minEdges : edges;
                }
                currentSize += minEdges;
            }
            incomingSize = (incomingSize < currentSize) ? incomingSize : currentSize;
        }
        int cost = 0;
        switch (edge.direction){
            case "-->" :
                cost = outgoingSize;
                break;
            case "<--" :
                cost = incomingSize;
                break;
            case "<-->" : case "--" :
                cost = incomingSize + outgoingSize;
                break;
            default:
                break;

        }

        this.estimatedSize = this.estimatedSize < cost ? this.estimatedSize : cost;

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
        return "-[" + edge.name + cons.toString() + "]-(" + edge.end + ")";
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

    public QueryConstraints getCons() {return this.cons;}
}
