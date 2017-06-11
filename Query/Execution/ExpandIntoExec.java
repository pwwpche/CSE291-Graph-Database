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
        Set<String> starts = new HashSet<>(), ends = new HashSet<>();
        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Set<String> endNodes = new HashSet<>(table.getAll(edge.end));

        if(table.rows() < plan.getIndexer().getRelationsWithLabels(relations)){
            startNodes.forEach(node -> starts.addAll(exeUtil.getEdgeByStart(node)));
            endNodes.forEach(node -> ends.addAll(exeUtil.getEdgeByEnd(node)));
            candidates = starts;
            candidates.retainAll(ends);
        }else{
            candidates.addAll(exeUtil.getNodeByLabel(relations));
        }

        // Then filter by relation with original nodes
        Set<String> validEdges = new HashSet<>();
        Map<String, Map<String, String>> values = new HashMap<>();
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

//        table.expandMulti();

        querySQL = exeUtil.getHistory();
        return table;
    }
}
