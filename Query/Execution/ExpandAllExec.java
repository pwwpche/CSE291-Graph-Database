package Query.Execution;

import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.ExpandAllPlan;
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
        RelationEdge edge = ((ExpandAllPlan)plan).getRelationEdge();
        String expandedNode = ((ExpandAllPlan)plan).getExpandedNode();
        String insideNode = edge.start.equals(expandedNode) ? edge.end : edge.start;

        List<String> relationTypes = relationConstraints.getEdgeLabels();
        List<String> nodeLabels = nodeConstraints.getNodeLabels();
        Map<String, String> nodeProperties = nodeConstraints.getNodeProperties();


        Set<String> startNodes = new HashSet<>(table.getAll(insideNode));
        Map<String, List<List<String>>> candidates = new HashMap<>();
        List<String> retItems;

        for(String node : startNodes){
            Map<String, String> condition = new HashMap<>();
            List<List<String>> res = new ArrayList<>();
            switch(edge.direction){
                case "-->" :
                    if(insideNode.equals(edge.start)){
                        condition.put("from", node);
                        retItems = new ArrayList<>(Arrays.asList("to", "name"));
                    }else{
                        condition.put("to", node);
                        retItems = new ArrayList<>(Arrays.asList("from", "name"));
                    }
                    res = exeUtil.getEdgesBy(retItems, condition, relationTypes);
                    candidates.put(node, res);
                    break;
                case "<--":
                    if(insideNode.equals(edge.start)){
                        condition.put("to", node);
                        retItems = new ArrayList<>();
                        retItems.add("from");
                    }else{
                        retItems = new ArrayList<>();
                        retItems.add("to");
                        condition.put("from", node);
                    }
                    res = exeUtil.getEdgesBy(retItems, condition, relationTypes);
                    candidates.put(node, res);
                    break;
                case "--" :case "<-->":
                    condition.put("to", node);
                    retItems = new ArrayList<>(Arrays.asList("from", "name"));
                    res = exeUtil.getEdgesBy(retItems, condition, relationTypes);
                    condition.remove("to");

                    condition.put("from", node);
                    retItems = new ArrayList<>(Arrays.asList("to", "name"));
                    res.addAll(exeUtil.getEdgesBy(retItems, condition, relationTypes));
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
        retItems = new ArrayList<>(Arrays.asList(expandedNode, edge.name));
        List<ResultTable.ObjectType> retTypes = new ArrayList<>(Arrays.asList(ResultTable.ObjectType.Node_ID, ResultTable.ObjectType.Edge_ID));
        table.expand(insideNode, retItems, retTypes, outingEdges);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
