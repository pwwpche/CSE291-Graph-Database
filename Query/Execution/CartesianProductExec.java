package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;

/**
 * Created by liuche on 6/12/17.
 */
public class CartesianProductExec extends Execution {
    public CartesianProductExec(DBUtil util, Plan plan) {
        super(util, plan);
        this.operandCount = 2;
    }

    @Override
    public ResultTable execute(ResultTable table1, ResultTable table2) {
        exeUtil.startRecording();




        this.querySQL = exeUtil.getHistory();
        return table1;
    }
}
