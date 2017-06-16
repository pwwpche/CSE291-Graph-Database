package Query.Execution;

import Entity.Pair;
import Query.Plan.Plan;
import Query.Plan.ProduceResultPlan;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/12/17.
 */
public class ProduceResultExec extends Execution {
    private List<RangePath> pathMem;

    public ProduceResultExec(DBUtil util, Plan plan, List<RangePath> pathMem) {
        super(util, plan);
        this.operandCount = 1;
        this.pathMem = pathMem;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        exeUtil.startRecording();
        List<Pair<String, String>> returnList = ((ProduceResultPlan) plan).getRetList();

        List<List<String>> prevResult = table.getTable();
        Map<String, Integer> mapping = table.getKeyIdxMap();
        Map<String, ResultTable.ObjectType> varTypes = table.getKeyToType();

        List<String> finalVars = new ArrayList<>();
        List<List<Object>> finalResult = new ArrayList<>();
        List<ResultTable.ObjectType> finalType = new ArrayList<>();

        for(Pair<String, String> retPair : returnList){
            String var = retPair.getV0();
            String prop = retPair.getV1();
            if(var.equals("Constant")){
                finalVars.add(prop);
                finalType.add(ResultTable.ObjectType.Constant);
            }else{
                if(!"".equals(prop)){
                    finalVars.add(var + "." + prop);
                    finalType.add(ResultTable.ObjectType.PropertyLookup);
                }else{
                    finalVars.add(var);
                    finalType.add(varTypes.get(var));
                }
            }
        }

        for(List<String> row : prevResult){
            List<Object> resRow = new ArrayList<>();
            for(Pair<String, String> retPair : returnList){
                String var = retPair.getV0();
                String prop = retPair.getV1();
                if(var.equals("Constant")){
                    resRow.add(prop);
                }else{
                    Integer keyIdx = mapping.get(var);
                    String gid = row.get(keyIdx);
                    switch (varTypes.get(var)){
                        case Node_ID:
                            if("".equals(prop)){
                                resRow.add(exeUtil.expandObject(gid));
                            }else{
                                // Property Lookup
                                resRow.add(exeUtil.getPropertyByGid(prop, gid));
                            }
                            break;
                        case Edge_ID:
                            resRow.add(exeUtil.expandEdge(gid));
                            break;
                        case RangePath:
                            resRow.add(pathMem.get(Integer.valueOf(gid)));
                            break;
                        default:
                            break;
                    }

                }
            }
            finalResult.add(resRow);
        }
        table.setFinals(finalVars, finalType, finalResult);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
