package Query.Engine;

import Utility.DBUtil;
import Utility.FileParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuche on 5/28/17.
 *
 */

public class QueryExecution {
    private DBUtil dbUtil;
    QueryExecution(DBUtil util){
        this.dbUtil = util;
    }




    public Map<String, String> expandObject(Integer gid){
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
