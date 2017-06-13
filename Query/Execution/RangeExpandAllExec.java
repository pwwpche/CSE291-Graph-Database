package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;

/**
 * Created by liuche on 6/12/17.
 */
public class RangeExpandAllExec extends Execution {
    public RangeExpandAllExec(DBUtil util, Plan plan) {
        super(util, plan);
    }

    @Override
    public ResultTable execute(ResultTable table1) {
        return super.execute(table1);
    }
}
