package Query.Execution;

import Entity.Constraint;
import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.ExpandAllPlan;
import Query.Plan.ExpandIntoPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/6/17.
 */
public class ExpandAllExec extends Execution {

    public ExpandAllExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        exeUtil.startRecording();

        // Filter out valid relations
        QueryConstraints relationConstraints = ((ExpandAllPlan)plan).getRelationConstraint();
        QueryConstraints nodeConstraints = ((ExpandAllPlan)plan).getNodeConstraints();
        RelationEdge edge = ((ExpandIntoPlan)plan).getRelationEdge();

        List<String> relations = new ArrayList<>();
        List<String> nodeLabels = new ArrayList<>();
        Map<String, String> nodeProperties = new HashMap<>();

        for(Constraint constraint : nodeConstraints.getConstraints()){
            if(constraint.name.equals("nodeLabels")){
                List<String> labels = (List<String>) constraint.value.val;
                nodeLabels.addAll(labels);
            }else{
                String key = constraint.name;
                String value = constraint.value.val.toString();
                nodeProperties.put(key, value);
            }
        }

        for(Constraint constraint : relationConstraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }
        }



        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Map<String, List<List<String>>> candidates = new HashMap<>();
        List<String> retItems = new ArrayList<>(Arrays.asList(edge.end, edge.name));

        for(String node : startNodes){
            Map<String, String> constraint = new HashMap<>();
            List<List<String>> res = new ArrayList<>();
            switch(edge.direction){
                case "-->" :
                    constraint.put("from", node);
                    res = exeUtil.getEdgesBy(retItems, constraint, relations);
                    candidates.put(node, res);
                    break;
                case "<--":
                    constraint.put("to", node);
                    res = exeUtil.getEdgesBy(retItems, constraint, relations);
                    candidates.put(node, res);
                    break;
                case "--" :case "<-->":
                    constraint.put("to", node);
                    res = exeUtil.getEdgesBy(retItems, constraint, relations);
                    constraint.remove("to");

                    constraint.put("from", node);
                    res.addAll(exeUtil.getEdgesBy(retItems, constraint, relations));
                    candidates.put(node, res);
                    break;
                default:
                    break;
            }
        }
        Map<String, List<List<String>>> outingEdges = new HashMap<>();

        for(String beginNode : candidates.keySet()){
            List<List<String>> outings = candidates.get(beginNode);
            for(List<String> row : outings){
                String endNode = row.get(0);
                if(exeUtil.checkNode(endNode, nodeProperties, nodeLabels)){
                    if(!outingEdges.containsKey(beginNode)){
                        outingEdges.put(beginNode, new ArrayList<>());
                    }
                    outingEdges.get(beginNode).add(row);
                }
            }
        }

        table.expand(edge.start, retItems, outingEdges);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
