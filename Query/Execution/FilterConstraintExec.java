package Query.Execution;

import Entity.Constraint;
import Query.Plan.FilterConstraintPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/12/17.
 */
public class FilterConstraintExec extends Execution {
    public FilterConstraintExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    @Override
    public ResultTable execute(ResultTable table1) {
        exeUtil.startRecording();
        Constraint constraint = ((FilterConstraintPlan)plan).getConstraint();
        String varName = plan.getVariable();

        List<String> labels = new ArrayList<>();
        Map<String, String> properties = new HashMap<>();
        if("nodeLabels".equals(constraint.name)){
            labels = (List<String>) constraint.value.val;
        }else{
            properties.put(constraint.name, constraint.value.val.toString());
        }

        if(table1.rows() < plan.estimateSize()){
            Set<String> candidates = new HashSet<>(table1.getAll(varName));
            Set<String> chosen = new HashSet<>();
            for (String candidateNode : candidates) {
                switch (constraint.equality){
                    case "==" :
                        if(exeUtil.checkNode(candidateNode, properties, labels)){
                            chosen.add(candidateNode);
                        }
                        break;
                    case "!= ":
                        if(!exeUtil.checkNode(candidateNode, properties, labels)){
                            chosen.add(candidateNode);
                        }
                        break;
                    case ">" :case "<":case ">=" :case "<=":
                        String candidateVal = exeUtil.getPropertyByGid(constraint.name, candidateNode);
                        String targetVal = constraint.value.val.toString();
                        Double candidateNum = Double.valueOf(candidateVal), targetNum = Double.valueOf(targetVal);
                        if(constraint.equality.equals(">") && candidateNum > targetNum){
                            chosen.add(candidateNode);
                        }
                        if(constraint.equality.equals("<") && candidateNum < targetNum){
                            chosen.add(candidateNode);
                        }
                        if(constraint.equality.equals(">=") && candidateNum >= targetNum){
                            chosen.add(candidateNode);
                        }
                        if(constraint.equality.equals("<=") && candidateNum <= targetNum){
                            chosen.add(candidateNode);
                        }
                        break;
                    default:
                        break;
                }

            }
            table1.shrinkBySet(varName, chosen);
        }else{
            //TODO: Support other equalities.
            Set<String> validNodes = new HashSet<>();
            if(labels.size() > 0){
                validNodes.addAll(exeUtil.getNodeByLabel(labels));
            }else{
                String field = constraint.name, value = constraint.value.val.toString();
                List<String> candidates = exeUtil.getNodeGidBy(field, value);
                validNodes.addAll(exeUtil.getNodeGidBy(field, value));
            }
            table1.shrinkBySet(varName, validNodes);

        }

        this.querySQL = exeUtil.getHistory();
        return table1;
    }
}
