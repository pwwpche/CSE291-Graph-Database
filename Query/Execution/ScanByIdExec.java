package Query.Execution;

import Query.Plan.Plan;
import Query.Plan.ScanByIdPlan;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 6/5/17.
 */
public class ScanByIdExec extends Execution {

    public ScanByIdExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 0;

    }

    @Override
    public ResultTable execute() {
        exeUtil.startRecording();
        ResultTable table = new ResultTable();
        List<String> gids = exeUtil.getNodeGidBy("id", ((ScanByIdPlan) plan).getId());
        table.putAll(plan.getVariable(), ResultTable.ObjectType.NODE, gids);
        querySQL = exeUtil.getHistory();
        return table;
    }
}
