package Query.Execution;

import Query.Plan.Plan;
import Query.Plan.ScanByPropertyPlan;
import Utility.DBUtil;

import java.util.List;

/**
 * Created by liuche on 6/5/17.
 *
 */
public class ScanByPropertyExec extends Execution {

    public ScanByPropertyExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 0;

    }

    @Override
    public ResultTable execute() {
        this.exeUtil.startRecording();
        ResultTable table = new ResultTable();
        String prop = ((ScanByPropertyPlan)plan).getProperty();
        String val = ((ScanByPropertyPlan)plan).getValue();
        List<String> result = exeUtil.getNodeGidBy(prop, val);
        table.putAll(plan.getVariable(), ResultTable.ObjectType.Node_ID, result);
        querySQL = exeUtil.getHistory();
        return table;
    }
}
