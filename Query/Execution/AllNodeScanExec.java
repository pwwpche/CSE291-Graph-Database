package Query.Execution;

import Query.Plan.AllNodeScanPlan;
import Utility.DBUtil;

/**
 * Created by liuche on 6/5/17.
 */
public class AllNodeScanExec extends Execution{

    public AllNodeScanExec(DBUtil util, AllNodeScanPlan plan) {
        super(util, plan);
        this.operandCount = 0;
    }

    @Override
    public ResultTable execute() {
        this.dbUtil.startRecording();
        ResultTable table = new ResultTable();
        table.idTable.put(ResultTable.GID, getAllNodeGid());
        this.dbUtil.stopRecording();
        this.querySQL = this.dbUtil.getExecuteHistory();
        return table;
    }

}
