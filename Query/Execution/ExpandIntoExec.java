package Query.Execution;

import Entity.Constraint;
import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.ExpandIntoPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/5/17.
 *
 */
public class ExpandIntoExec extends Execution {

    public ExpandIntoExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    private void addNode(String startNode, String endNode, String edgeId,
            Map<String, Map<String, List<String>>> valuesList){
        if(!valuesList.containsKey(startNode)){
            valuesList.put(startNode, new HashMap<>());
        }
        if(!valuesList.get(startNode).containsKey(endNode)){
            valuesList.get(startNode).put(endNode, new ArrayList<>());
        }
        valuesList.get(startNode).get(endNode).add(edgeId);
    }

    @Override
    public ResultTable execute(ResultTable table) {

        exeUtil.startRecording();
        // Filter out valid relations

        QueryConstraints constraints = ((ExpandIntoPlan)plan).getCons();
        RelationEdge edge = ((ExpandIntoPlan)plan).getRelationEdge();

        List<String> relations = new ArrayList<>();

        for(Constraint constraint : constraints.getConstraints()){
            assert !constraint.name.contains("range");
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }
        }

        // First filter by relation rel_types
        Set<String> candidates = new HashSet<>();
        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Set<String> endNodes = new HashSet<>(table.getAll(edge.end));

        // ValueList: {from -> {to -> [eid1, eid2, ..., eid_n]}}
        Map<String, Map<String, List<String>>> valuesList = new HashMap<>();

        if(table.rows() < plan.getIndexer().getRelationsWithLabels(relations)){
            List<String> retItems;
            Map<String, String> constraint = new HashMap<>();

            switch (edge.direction){
                case "-->": case "--" :case "<-->":
                    retItems = new ArrayList<>(Arrays.asList("to", "name"));
                    for (String startNode : startNodes) {
                        constraint.put("from", startNode);

                        List<List<String>> res = exeUtil.getEdgesBy(retItems, constraint, relations);
                        Map<String, List<String>> neighbors = new HashMap<>();
                        for(List<String> row : res){
                            String toNode = row.get(0);
                            String edgeEid = row.get(1);
                            if(endNodes.contains(toNode)){
                                // Found a match
                                addNode(startNode, toNode, edgeEid, valuesList);
                            }
                        }
                        if(!neighbors.isEmpty()){
                            valuesList.put(startNode, neighbors);
                        }
                    }
                    if(edge.direction.equals("-->")){
                        break;
                    }

                case "<--" :
                    retItems = new ArrayList<>(Arrays.asList("from", "name"));
                    for (String startNode : startNodes) {
                        constraint.put("to", startNode);

                        List<List<String>> res = exeUtil.getEdgesBy(retItems, constraint, relations);
                        Map<String, List<String>> neighbors = new HashMap<>();
                        for(List<String> row : res){
                            String toNode = row.get(0), edgeEid = row.get(1);
                            if(endNodes.contains(toNode)){
                                addNode(startNode, toNode, edgeEid, valuesList);
                            }
                        }
                        if(!neighbors.isEmpty()){
                            valuesList.put(startNode, neighbors);
                        }
                    }
                    break;
                default:
                    break;
            }

        }else{
            // Fetch all the edges that confront the constraints.
            candidates.addAll(exeUtil.getEdgesByLabel(relations));

            for(String eid : candidates){

                // Check if this edge lies inside our query graph.
                // Lies inside means that both ends are in the graph.
                // Edge.name is the variable to be expanded.
                // Edge.from, Edge.to are variables that already check.
                Map<String, String> edgeInfo = exeUtil.expandEdge(eid);
                String from = edgeInfo.get("from");
                String to = edgeInfo.get("to");
                switch (edge.direction){
                    case "-->": case "--" :case "<-->":
                        // from -> eid1 -> to
                        // from -> eid2...n -> to
                        if(startNodes.contains(from) && endNodes.contains(to)){
                            addNode(from, to, eid, valuesList);
                        }
                        if(edge.direction.equals("-->")){
                            break;
                        }
                    case "<--":
                        if(startNodes.contains(to) && endNodes.contains(from)){
                            addNode(from, to, eid, valuesList);
                        }
                        break;
                    default:
                        break;
                }
            }
        }


        table.expandMultiKeyList(edge.start, edge.end, edge.name, ResultTable.ObjectType.RELATIONSHIP, valuesList);
        querySQL = exeUtil.getHistory();
        return table;
    }
}
