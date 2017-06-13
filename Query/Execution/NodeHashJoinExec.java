package Query.Execution;

import Entity.Pair;
import Query.Plan.Plan;
import Utility.DBUtil;

import java.util.*;

/**
 * Created by liuche on 6/12/17.
 */
public class NodeHashJoinExec extends Execution {
    public NodeHashJoinExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 2;
    }

    @Override
    public ResultTable execute(ResultTable table1, ResultTable table2) {
        exeUtil.startRecording();

        Set<String> joinKeys = new HashSet<>(table1.getKeys());
        joinKeys.retainAll(new HashSet<>(table2.getKeys()));
        List<String> keyList = new ArrayList<>(joinKeys);

        List<String> additionalFields = table2.getJoinFields(keyList);
        List<ResultTable.ObjectType> additionalTypes = table2.getJoinObjectType(keyList);
        Map<String, List<List<String>>>   joinValues = table2.generateJoinMap(keyList);
        table1.hashJoin(keyList, additionalFields, additionalTypes, joinValues);

        this.querySQL = exeUtil.getHistory();
        return table1;
    }
}
