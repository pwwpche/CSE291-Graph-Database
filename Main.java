// java -Xmx500M -cp "library/antlr-4.6-complete.jar:$CLASSPATH" org.antlr.v4.Tool src/Cypher.g4 -visitor

// Deployment:
// Maven add commons-csv-1.4, mongodb-driver-3.4.2

import DataImport.FileParser;
import Query.Engine.QueryIndexer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*  Graph"
    Sampling, paralleling, condensing, overlapping(DP)
*
* */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        //Parsing this CSV file:

        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";
        System.out.println("Connecting to MySQL...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("MySQL connected.");
        System.out.println("Parsing file...");
        FileParser fileParser = new FileParser("src/sample.csv", connection);
        fileParser.run();
        System.out.println("Parsing complete.");
        System.out.println("Creating index...");
        QueryIndexer queryIndexer = new QueryIndexer(connection);
        System.out.println("Index created...");
        System.out.println("Parsing ANTLR query...");
        CypherLexer lexer = new CypherLexer(new ANTLRFileStream("/Users/liuche/IdeaProjects/GraphDatabase/src/query.txt"));
        CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
        CypherParser.CypherContext cypher = parser.cypher();
        CypherCustomVisitor visitor = new CypherCustomVisitor();
        visitor.setIndexer(queryIndexer);
        visitor.visit(cypher);
        return ;


    }

}
