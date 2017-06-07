package Query.Engine;

import Query.Entities.PlanTree;
import Query.Execution.*;
import Query.Plan.*;
import Utility.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public ResultTable execute(){
        List<Execution> executionList = new ArrayList<>();
        for(Plan plan : tree.toList()){
            if(plan instanceof AllNodeScanPlan){
                executionList.add(new AllNodeScanExec(dbUtil, plan));
            }else if(plan instanceof ScanByLabelPlan){
                executionList.add(new ScanByLabelExec(dbUtil, plan));
            }else if(plan instanceof ScanByPropertyPlan){
                executionList.add(new ScanByPropertyExec(dbUtil, plan));
            }else if(plan instanceof ScanByIdPlan){
                executionList.add(new ScanByIdExec(dbUtil, plan));
            }else if(plan instanceof ExpandIntoPlan){

            }else if(plan instanceof ExpandAllPlan){

            }else if(plan instanceof RangeExpandAllPlan){

            }else if(plan instanceof RangeExpandIntoPlan){

            }else if(plan instanceof NodeHashJoinPlan){

            }else if(plan instanceof CartesianProductPlan){

            }else if(plan instanceof FilterConstraintPlan){

            }else if(plan instanceof FilterRelationEqualityPlan){

            }
        }

        Stack<ResultTable> resStack = new Stack<>();
        for(Execution execution : executionList){
            switch (execution.operandCount()){
                case 0:
                    resStack.push(execution.execute());
                    break;
                case 1:
                    ResultTable table = resStack.peek();
                    resStack.pop();
                    resStack.push(execution.execute(table));
                    break;
                case 2:
                    ResultTable table1 = resStack.peek();
                    resStack.pop();
                    ResultTable table2 = resStack.peek();
                    resStack.pop();
                    resStack.push(execution.execute(table1, table2));
                    break;
                default:
                    break;
            }
        }
        assert resStack.size() == 1;
        return resStack.peek();

    }




}
