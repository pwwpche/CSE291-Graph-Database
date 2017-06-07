package Query.Execution;

import Entity.Constraint;
import Entity.Pair;
import Entity.QueryConstraints;
import Query.Entities.RelationEdge;
import Query.Plan.ExpandIntoPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Pair<String, String>> properties = new ArrayList<>();
        for(Constraint constraint : constraints.getConstraints()){
            assert !constraint .name.contains("range");
            if(!constraint.name.equals("rel_type")){
                // Perform property scan
                String prop = constraint.name;
                assert constraint.value.isConstant && !constraint.value.type.contains("List");
                String val = constraint.value.val.toString();
                properties.add(new Pair<>(prop, val));
            }else{
                assert constraint.value.type.contains("List");
                List<String> types = (List<String>) constraint.value.val;
                relations.addAll(types);
            }
        }


        // First filter by relation rel_types
        Set<String> candidates = new HashSet<>(exeUtil.getEdgeByLabel(relations));
        // Then filter by property and values
        properties.forEach(kvPair ->
                candidates.retainAll(exeUtil.getEdgeByProperty(kvPair.getV0(), kvPair.getV1()))
        );
        Set<String> startNodes = new HashSet<>(table.getAll(edge.start));
        


        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
