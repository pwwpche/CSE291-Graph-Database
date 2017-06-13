package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 6/5/17.
 */
public class Execution {
    public static final String GID = "gid";
    protected Plan plan;
    protected int operandCount = 0;
    protected ExecutionUtility exeUtil;

    protected List<String> querySQL = new ArrayList<>();

    public Execution(DBUtil util, Plan plan){
        this.exeUtil = new ExecutionUtility(util);
        this.plan = plan;
    }

    public List<String> getQuerySQL(){
        return this.querySQL;
    }

    public int operandCount(){
        return operandCount();
    }

    public ResultTable execute() {
        return new ResultTable();
    }

    public ResultTable execute(ResultTable table1)  {
        return table1;
    }

    public ResultTable execute(ResultTable table1, ResultTable table2){
        return table1;
    }


}
