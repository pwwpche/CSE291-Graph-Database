package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;
import DataImport.FileParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/5/17.
 */
public class Execution {
    protected ResultTable resultTable;
    protected Plan plan;
    protected int operandCount = 0;
    protected DBUtil dbUtil;
    protected List<String> querySQL = new ArrayList<>();

    public Execution(DBUtil util, Plan plan){
        this.dbUtil = util;
        this.resultTable = new ResultTable();
        this.plan = plan;
    }

    public int operandCount(){
        return operandCount();
    }

    public List<String> getQuerySQL(){
        return this.querySQL;
    }

    public ResultTable execute(){
        return resultTable;
    }

    public ResultTable execute(ResultTable table1){
        return resultTable;
    }

    public ResultTable execute(ResultTable table1, ResultTable table2){
        return resultTable;
    }

    protected List<String> getAllNodeGid(){
        String statement = "SELECT COUNT(*) FROM ObjectType WHERE type != \"0\";";
        return dbUtil.getListFromSQL(statement);
    }

    protected List<String> getNodeGidBy(String field, String value){
        String statement = "SELECT gid FROM P_" + field + "WHERE " + field + " = \"" + value + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    protected List<String> getEdgeIdByFromGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node1 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    protected List<String> getEdgeIdByToGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node2 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    protected Map<String, String> expandEdge(String eid){
        String statement = "SELECT * FROM edge WHERE eid = \""  + eid + "\";";
        return dbUtil.getObjectFromSQL(statement);
    }

    protected String getPropertyByGid(String field, String gid){
        String statement = "SELECT gid FROM P_" + field + "WHERE gid = \"" + gid + "\";";
        return dbUtil.getStringFromSQL(statement);
    }

    protected Map<String, String> expandObject(Integer gid){
        String statement = "SELECT type FROM ObjectType WHERE gid = \"" + gid.toString() + "\";";
        Integer nodeType = dbUtil.getIntegerFromSQL(statement);
        statement = "SELECT name FROM typeProperty WHERE id = \"" + nodeType.toString() + "\";";
        List<String> properties = dbUtil.getListFromSQL(statement);
        Map<String, String> result = new HashMap<>();
        for(String property : properties){
            if(nodeType == 0){
                statement = "SELECT value FROM P_" + FileParser.RELATION_PREFIX + property + " WHERE gid = \"" + gid.toString() + "\";";
            }else{
                statement = "SELECT value FROM P_" + property + " WHERE gid = \"" + gid.toString() + "\";";
            }
            String val = dbUtil.getStringFromSQL(statement);
            result.put(property, val);
        }
        return result;
    }
}
