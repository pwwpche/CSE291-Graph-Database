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
        QueryConstraints constraints = ((ExpandIntoPlan)plan).getCons();
        RelationEdge edge = ((ExpandIntoPlan)plan).getRelationEdge();

        List<String> relations = new ArrayList<>();

        for(Constraint constraint : constraints.getConstraints()){
            if(constraint.name.equals("rel_type")){
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }
        }

        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        Map<String, List<List<String>>> outingEdges = new HashMap<>();
        List<String> retItems = new ArrayList<>(Arrays.asList(edge.end, edge.name));
        for(String node : startNodes){
            Map<String, String> constraint = new HashMap<>();
            constraint.put("from", node);
            List<List<String>> res = exeUtil.getEdgesBy(retItems, constraint, relations);
            outingEdges.put(node, res);
        }

        table.expand(edge.start, retItems, outingEdges);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
