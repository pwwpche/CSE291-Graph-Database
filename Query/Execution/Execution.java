package Query.Execution;

import Query.Plan.Plan;
import Utility.DBUtil;
import DataImport.FileParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 6/5/17.
 */
public class Execution {
    private ResultTable resultTable;
    private Plan plan;
    private DBUtil dbUtil;

    public Execution(DBUtil util, Plan plan){
        this.dbUtil = util;
        this.resultTable = new ResultTable();
        this.plan = plan;
    }

    public ResultTable execute(){
        return resultTable;
    }

    private List<String> getAllNodeGid(){
        String statement = "SELECT COUNT(*) FROM ObjectType WHERE type != \"0\";";
        return dbUtil.getListFromSQL(statement);
    }

    private List<String> getNodeGidBy(String field, String value){
        String statement = "SELECT gid FROM P_" + field + "WHERE " + field + " = \"" + value + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    private List<String> getEdgeIdByFromGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node1 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    private List<String> getEdgeIdByToGid(String node1){
        String statement = "SELECT eid FROM Edge WHERE node2 = \"" + node1 + "\";";
        return dbUtil.getListFromSQL(statement);
    }

    private Map<String, String> expandEdge(String eid){
        String statement = "SELECT * FROM edge WHERE eid = \""  + eid + "\";";
        return dbUtil.getObjectFromSQL(statement);
    }

    private Map<String, String> expandObject(Integer gid){
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
