package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;

/**
 * Created by liuche on 6/5/17.
 */
public class AllNodeScanExec extends Execution{

    public AllNodeScanExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 0;
    }

    @Override
    public ResultTable execute() {
        this.exeUtil.startRecording();
        ResultTable table = new ResultTable();
        table.putAll(plan.getVariable(), ResultTable.ObjectType.NODE, exeUtil.getAllNodes());
        this.querySQL = this.exeUtil.getHistory();
        return table;
    }



}
