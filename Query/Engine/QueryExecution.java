package Query.Engine;

import java.sql.Connection;

/**
 * Created by liuche on 5/28/17.
 */

public class QueryExecution {
    Connection conn;

    public QueryExecution(Connection conn) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";
        this.conn = conn;
    }




}
