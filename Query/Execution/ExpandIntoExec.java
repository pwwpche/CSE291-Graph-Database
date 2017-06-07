package Query.Execution;

import Entity.Constraint;
import Entity.Pair;
import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.ExpandIntoPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/5/17.
 */
public class ExpandIntoExec extends Execution {

    public ExpandIntoExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        exeUtil.startRecording();
        // Filter out valid relations
        QueryConstraints constraints = ((ExpandIntoPlan)plan).getCons();
        RelationEdge edge = ((ExpandIntoPlan)plan).getRelationEdge();

        List<String> relations = new ArrayList<>();
        List<Pair<String, String>> properties = new ArrayList<>();
        for(Constraint constraint : constraints.getConstraints()){
            assert !constraint.name.contains("range");
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }else{
                // Perform property scan
                String prop = constraint.name;
                assert constraint.value.isConstant && !constraint.value.type.contains("List");
                String val = constraint.value.val.toString();
                properties.add(new Pair<>(prop, val));
            }
        }
        // First filter by relation rel_types
        Set<String> candidates = new HashSet<>(exeUtil.getEdgeByLabel(relations));
        // Then filter by property and values
        properties.forEach(kvPair ->
                candidates.retainAll(exeUtil.getEdgeByProperty(kvPair.getV0(), kvPair.getV1()))
        );

        // Then filter by relation with original nodes
        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Set<String> endNodes = new HashSet<>(table.getAll(edge.start));
        Set<String> validEdges = new HashSet<>();
        for(String eid : candidates){
            Map<String, String> edgeInfo = exeUtil.expandEdge(eid);
            switch (edge.direction){
                case "-->":
                    if(startNodes.contains(edgeInfo.get("node1")) &&
                            endNodes.contains(edgeInfo.get("node2"))){
                        validEdges.add(eid);
                    }
                    break;
                case "<--":
                    if(endNodes.contains(edgeInfo.get("node1")) &&
                            startNodes.contains(edgeInfo.get("node2"))){
                        validEdges.add(eid);
                    }
                    break;
                case "--" :case "<-->":
                    if(startNodes.contains(edgeInfo.get("node1")) &&
                            endNodes.contains(edgeInfo.get("node2"))){
                        validEdges.add(eid);
                    }else{
                        if(endNodes.contains(edgeInfo.get("node1")) &&
                                startNodes.contains(edgeInfo.get("node2"))){
                            validEdges.add(eid);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        candidates.retainAll(validEdges);
        table.shrink(edge.name, candidates);


        // TODO: If result size is really small, we should not query the whole graph.
//        if(eids.size() < plan.getIndexer().getNumberOfRelations()){
//
//        }


        querySQL = exeUtil.getHistory();
        return table;
    }
}
