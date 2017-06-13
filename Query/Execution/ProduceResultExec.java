package Query.Execution;

import Entity.Pair;
import Query.Plan.Plan;
import Query.Plan.ProduceResultPlan;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/12/17.
 */
public class ProduceResultExec extends Execution {
    public ProduceResultExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 1;
    }

    @Override
    public ResultTable execute(ResultTable table) {
        exeUtil.startRecording();
        List<Pair<String, String>> returnList = ((ProduceResultPlan) plan).getRetList();

        List<List<String>> prevResult = table.getTable();
        Map<String, Integer> mapping = table.getKeyIdxMap();
        Map<String, ResultTable.ObjectType> varTypes = table.getKeyToType();


        List<List<Object>> finalResult = new ArrayList<>();
        Map<String, ResultTable.ObjectType> objType = new HashMap<>();

        for(Pair<String, String> retPair : returnList){
            String var = retPair.getV0();
            String prop = retPair.getV1();
            if(var.equals("Constant")){
                objType.put(var, ResultTable.ObjectType.STRING);
            }else{
                if("".equals(prop)){
                    objType.put(var, ResultTable.ObjectType.OBJECT);
                }else{
                    // Property Lookup
                    objType.put(var, ResultTable.ObjectType.STRING);
                }
            }
        }

        for(List<String> row : prevResult){
            List<Object> resRow = new ArrayList<>();
            for(Pair<String, String> retPair : returnList){
                String var = retPair.getV0();
                String prop = retPair.getV1();
                if(var.equals("Constant")){
                    resRow.add(var);
                }else{
                    Integer keyIdx = mapping.get(var);
                    String gid = row.get(keyIdx);
                    if(varTypes.get(var).equals(ResultTable.ObjectType.NODE)){
                        if("".equals(prop)){
                            resRow.add(exeUtil.expandObject(gid));
                        }else{
                            // Property Lookup
                            resRow.add(exeUtil.getPropertyByGid(prop, gid));
                        }
                    }else if(varTypes.get(var).equals(ResultTable.ObjectType.RELATIONSHIP)){
                        resRow.add(exeUtil.expandEdge(gid));
                    }
                }
            }
            finalResult.add(resRow);
        }
        table.setFinalResult(finalResult);

        this.querySQL = exeUtil.getHistory();
        return table;
    }
}
