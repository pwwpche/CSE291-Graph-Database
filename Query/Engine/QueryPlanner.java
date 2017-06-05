package Query.Engine;

import Entity.*;
import Query.Entities.PlanTable;
import Query.Plan.*;
import Query.Entities.RelationEdge;

import java.util.*;

/**
 * Created by liuche on 5/28/17.
 *
 */
public class QueryPlanner {

    private List<Path> pathList = new ArrayList<>();
    private Map<String, QueryConstraints> varToConstraint = new HashMap<>();
    private List<Equality> equalityList = new ArrayList<>();
    private List<Pair<String, String>> retList = new ArrayList<>();
    private Map<String, Value> retPath = new HashMap<>();
    private QueryIndexer indexer;
    private List<PlanTable> planTables = new ArrayList<>();

    private Set<String> nodes = new HashSet<>();
    private List<RelationEdge> edges = new ArrayList<>();

    public QueryPlanner(Map<String, QueryConstraints> varToConstraint,
                        List<Equality> equalityList,
                        List<Path> pathList,
                        List<Pair<String, String>> retList,
                        Map<String, Value> retPath,
                        QueryIndexer indexer) {
        // Get node list and relation list
        this.varToConstraint = varToConstraint;
        this.equalityList = equalityList;
        this.pathList = pathList;
        this.retList = retList;
        this.retPath = retPath;
        this.indexer = indexer;
    }

    private void init(){
        // Create nodes and edges table
        for (Path path : pathList) {
            nodes.addAll(path.nodes);
            for(int i = 0, size = path.relations.size() ; i < size ; i++){
                //TODO: How about edges in different matches?
                String start = path.nodes.get(i);
                String end = path.nodes.get(i + 1);
                String direction = path.direction.get(i);
                String name = path.relations.get(i);
                edges.add(new RelationEdge(name, start, end, direction));
            }
        }
    }

    private void constructLeafPlan() {


        // Push down all the selections
        // For now ignore projections
        for (String node : nodes) {
            assert varToConstraint.containsKey(node);
            PlanTable table = new PlanTable();
            if (varToConstraint.get(node).getConstraints().size() > 0) {
                //TODO: Change this to selecting the optimal plan.
                // Just pick the first and use others as filters.
                QueryConstraints constraints = varToConstraint.get(node);
                List<Constraint> constraintsList = new ArrayList<>();
                for(Constraint constraint : constraints.getConstraints()){
                    if(constraint.name.contains("id")){
                        constraintsList.add(constraint);
                    }
                }
                for(Constraint constraint : constraints.getConstraints()){
                    if(constraint.name.contains("nodeLabels")){
                        constraintsList.add(constraint);
                    }
                }
                for(Constraint constraint : constraints.getConstraints()){
                    if(!constraint.name.contains("id") && !constraint.name.contains("nodeLabels")){
                        constraintsList.add(constraint);
                    }
                }
                Constraint constraint = constraintsList.get(0);

                switch (constraint.name) {
                    case "nodeLabels":
                        assert constraint.value.type.contains("List");
                        List<String> labels = ((List<String>) constraint.value.val);
                        ScanByLabelPlan scanByLabelPlan = new ScanByLabelPlan(indexer, node, labels);
                        scanByLabelPlan.applyTo(table);
                        break;
                    case "id":
                        ScanByIdPlan scanByIdPlan = new ScanByIdPlan(indexer, node);
                        scanByIdPlan.applyTo(table);
                        break;
                    default:
                        Value val = constraint.value;
                        //TODO: Add supply for other equality types.
                        assert val.type.equals("String");
                        assert constraint.equality.equals("==");
                        String property = constraint.name;
                        String value = (String) constraint.value.val;
                        ScanByPropertyPlan scanByPropertyPlan = new ScanByPropertyPlan(indexer, node, property, value);
                        scanByPropertyPlan.applyTo(table);
                        break;
                }

                for (int i = 1, size = constraintsList.size(); i < size; i++) {
                    Constraint cons = constraintsList.get(i);
                    FilterConstraintPlan filterConstraintPlan = new FilterConstraintPlan(indexer, node, cons, table);
                    filterConstraintPlan.applyTo(table);
                }
            } else {
                AllNodeScanPlan plan = new AllNodeScanPlan(indexer, node);
                plan.applyTo(table);
            }
            planTables.add(table);
        }
    }

    private void processRetrurn(PlanTable bestTable){
        //TODO; Extract return variables.
    }

    private List<PlanTable> candidates = new ArrayList<>();
    public void plan() {
        System.out.println("Generating Plan...");
        init();

        // Find start point
        constructLeafPlan();
        do {
            candidates.clear();
            for(int i = 0, size = planTables.size() ; i < size ; i++){
                for(int j = 0 ; j < i ; j++){
                    constructJoin(planTables.get(i), planTables.get(j));
                }
            }
            for(PlanTable table : planTables){
                constructExpand(table);
            }

            // TODO: Wrong estimation of join and expandAll.
            if(candidates.isEmpty()){
                break;
            }

            // TODO: Add cost calculation to each plan.
            double bestEstimate = Double.MAX_VALUE;
            int index = -1;
            for(int i = 0 , size = candidates.size() ; i < size ; i++){
                if(candidates.get(i).cost < bestEstimate){
                    bestEstimate = candidates.get(i).cost;
                    index = i;
                }
            }
            assert index != -1;
            PlanTable best = candidates.get(index);


            planTables.removeIf(table -> covers(best, table));

            planTables.add(best);

            for(Plan plan : best.plans.toList()){
                removeRelated(plan);
            }



        }while(candidates.size() > 0);
        assert planTables.size() == 1;

        // TODO: Process return list


        PlanTable bestPlan = planTables.get(0);
        for(Plan plan : bestPlan.plans.toList()){
            System.out.println(plan.getName() + "|" + plan.getVariable() + "|" + plan.getParams());
        }



    }

    private void removeRelated(Plan plan){
        if(plan instanceof ExpandAllPlan || plan instanceof ExpandIntoPlan){
            for(RelationEdge edge : edges){
                if(plan instanceof ExpandAllPlan){
                    String name = ((ExpandAllPlan) plan).getRelationEdge().name;
                    if(edge.name.equals(name)){
                        edge.used = true;
                        break;
                    }
                }

                if(plan instanceof ExpandIntoPlan){
                    String name = ((ExpandIntoPlan) plan).getRelationEdge().name;
                    if(edge.name.equals(name)){
                        edge.used = true;
                        break;
                    }
                }
            }
        }else if(plan instanceof NodeHashJoinPlan ||
                plan instanceof FilterRelationEqualityPlan){
            if(plan instanceof NodeHashJoinPlan){
                equalityList.removeAll(((NodeHashJoinPlan) plan).getEquality());
            }else{
                equalityList.removeAll(((FilterRelationEqualityPlan) plan).getEquality());
            }

        }

    }

    private PlanTable addAdditionalFilter(PlanTable table){
        // Apply additional filter operations on the table.
        List<Equality> relationEquality = new ArrayList<>();
        for(Equality equality : equalityList){
            // Filter of relation.
            if(!nodes.contains(equality.var1)){
                if(table.relations.contains(equality.var1) && table.relations.contains(equality.var2)){
                    relationEquality.add(equality);
                }
            }
        }

        if(!relationEquality.isEmpty()){
            PlanTable newTable = new PlanTable(table);
            FilterRelationEqualityPlan plan = new FilterRelationEqualityPlan(indexer, relationEquality, newTable);
            plan.applyTo(newTable);
            table = newTable;
        }
        return table;
    }

    private List<Equality> getRelatedEqualities(PlanTable t1, PlanTable t2){
        List<Equality> result = new ArrayList<>();
        for(Equality equality : equalityList){
            String type = nodes.contains(equality.var1) ? "node" : "relation";
            String start = equality.var1;
            String end = equality.var2;
            if(type.equals("node")){
                if(t1.nodes.contains(start) && !t1.nodes.contains(end) &&
                        t2.nodes.contains(end) && !t2.nodes.contains(start)){
                    result.add(equality);
                    continue;
                }


                if(t1.nodes.contains(end) && !t1.nodes.contains(start) &&
                        t2.nodes.contains(start) && !t2.nodes.contains(end)){
                    Equality equality1 = new Equality(equality);
                    equality1.swap();
                    result.add(equality1);
                }

            }
        }

        return result;
    }

    private void constructJoin(PlanTable t1, PlanTable t2){
        boolean canHashJoin = false;
        List<Equality> equalities = getRelatedEqualities(t1, t2);
        canHashJoin = equalities.size() > 0;
        Set<String> tt1 = new HashSet<>(t1.nodes);
        tt1.retainAll(t2.nodes);
        canHashJoin = canHashJoin || (!tt1.isEmpty());
        PlanTable newTable;
        if(canHashJoin){
            // Add NodeHashJoin product
            NodeHashJoinPlan plan = new NodeHashJoinPlan(indexer, t1, t2, equalities);
            newTable = new PlanTable(t2);
            plan.applyTo(newTable);

        }else{
            // Add Cartesian Product
            CartesianProductPlan plan = new CartesianProductPlan(indexer, t1, t2);
            newTable = new PlanTable(t2);
            plan.applyTo(newTable);
        }

        newTable = addAdditionalFilter(newTable);
        candidates.add(newTable);

    }

    private void constructExpand(PlanTable table){
        for(RelationEdge edge : edges){
            if(edge.used){
                continue;
            }
            PlanTable newTable = new PlanTable(table);

            boolean hasRangeExpand = false;
            QueryConstraints constraints = varToConstraint.get(edge.name);
            for(Constraint constraint : constraints.getConstraints()){
                if (constraint.value.type.contains("Range")){
                    hasRangeExpand = true; break;
                }
            }
            if(table.nodes.contains(edge.start) && table.nodes.contains(edge.end)){
                if(!hasRangeExpand){
                    ExpandIntoPlan plan = new ExpandIntoPlan(indexer,edge,
                            constraints,
                            table);

                    plan.applyTo(newTable);
                }else{
                    RangeExpandIntoPlan plan = new RangeExpandIntoPlan(indexer,
                            edge,
                            constraints,
                            table);
                    plan.applyTo(newTable);
                }

                newTable = addAdditionalFilter(newTable);
                candidates.add(newTable);
            }else if(table.nodes.contains(edge.start) || table.nodes.contains(edge.end)){
                String addedNode = "";
                if(!hasRangeExpand){
                    ExpandAllPlan plan = new ExpandAllPlan(indexer, edge,
                            constraints, table);
                    plan.applyTo(newTable);
                    addedNode = plan.getExpandedNode();
                }else{
                    RangeExpandAllPlan plan = new RangeExpandAllPlan(indexer, edge,
                            constraints,
                            table);
                    plan.applyTo(newTable);
                    addedNode = plan.getExpandedNode();
                }

                newTable = addAdditionalFilter(newTable);

                // If a node is added, should we add filters before or after it?

                if(varToConstraint.get(addedNode).getConstraints().size() > 0){
                    for(Constraint constraint : varToConstraint.get(addedNode).getConstraints()){
                        FilterConstraintPlan plan1 = new FilterConstraintPlan(indexer, addedNode, constraint, newTable);
                        plan1.applyTo(newTable);
                    }
                }
                candidates.add(newTable);
            }

        }
    }

    private boolean covers(PlanTable larger, PlanTable smaller){
        return larger.nodes.containsAll(smaller.nodes);
    }


}
