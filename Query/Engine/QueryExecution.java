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

    public List<String> getUsedSQL() {
        return usedSQL;
    }

    private List<String> usedSQL = new ArrayList<>();

    public QueryExecution(DBUtil util, PlanTree tree){
        this.dbUtil = util;
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
                executionList.add(new ExpandIntoExec(dbUtil, plan));
            }else if(plan instanceof ExpandAllPlan){
                executionList.add(new ExpandAllExec(dbUtil, plan));
            }else if(plan instanceof RangeExpandAllPlan){

            }else if(plan instanceof RangeExpandIntoPlan){

            }else if(plan instanceof NodeHashJoinPlan){
                executionList.add(new NodeHashJoinExec(dbUtil, plan));
            }else if(plan instanceof CartesianProductPlan){
                executionList.add(new CartesianProductExec(dbUtil, plan));
            }else if(plan instanceof FilterConstraintPlan){
                executionList.add(new FilterConstraintExec(dbUtil, plan));
            }else if(plan instanceof FilterRelationEqualityPlan){
                executionList.add(new FilterRelationEqualityExec(dbUtil, plan));
            }else if(plan instanceof ProduceResultPlan) {
                executionList.add(new ProduceResultExec(dbUtil, plan));
            }

        }

        Stack<ResultTable> resStack = new Stack<>();
        List<String> querySQL = new ArrayList<>();
        for(Execution execution : executionList){
            switch (execution.operandCount()){
                case 0:
                    resStack.push(execution.execute());
                    querySQL.addAll(execution.getQuerySQL());
                    break;
                case 1:
                    ResultTable table = resStack.peek();
                    resStack.pop();
                    resStack.push(execution.execute(table));
                    querySQL.addAll(execution.getQuerySQL());
                    break;
                case 2:
                    ResultTable table1 = resStack.peek();
                    resStack.pop();
                    ResultTable table2 = resStack.peek();
                    resStack.pop();
                    resStack.push(execution.execute(table1, table2));
                    querySQL.addAll(execution.getQuerySQL());
                    break;
                default:
                    break;
            }
        }
        assert resStack.size() == 1;
        this.usedSQL = querySQL;
        return resStack.get(0);

    }

}
