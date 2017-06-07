package Query.Execution;

import Query.Plan.Plan;
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
        List<String> gids = new ArrayList<>();
        gids.add(plan.getVariable());
        table.putAll(plan.getVariable(), gids);


        querySQL = exeUtil.getHistory();;
        return super.execute();
    }
}
