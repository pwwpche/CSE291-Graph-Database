package Query.Execution;

import Query.Plan.AllNodeScanPlan;
import Query.Plan.Plan;
import Utility.DBUtil;

/**
 * Created by liuche on 6/5/17.
 */
public class AllNodeScanExec extends Execution{

    public AllNodeScanExec(DBUtil util, AllNodeScanPlan plan) {
        super(util, plan);
    }
    
}
