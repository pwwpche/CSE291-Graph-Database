package Query.Engine;

import Query.Entities.PlanTree;
import Query.Execution.AllNodeScanExec;
import Query.Execution.ResultTable;
import Query.Plan.AllNodeScanPlan;
import Utility.DBUtil;

/**
 * Created by liuche on 5/28/17.
 *
 */

public class QueryExecution {
    private DBUtil dbUtil;
    private PlanTree tree;
    QueryExecution(DBUtil util){
        this.dbUtil = util;
    }


    public void setTree(PlanTree tree){
         this.tree = tree;
    }

    public ResultTable execute(AllNodeScanPlan plan){
        AllNodeScanExec allNodeScanExec = new AllNodeScanExec(dbUtil, plan);
        return allNodeScanExec.execute();
    }




}
