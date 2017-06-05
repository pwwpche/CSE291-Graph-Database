// java -Xmx500M -cp "library/antlr-4.6-complete.jar:$CLASSPATH" org.antlr.v4.Tool src/Cypher.g4 -visitor

// Deployment:
// Maven add commons-csv-1.4, mongodb-driver-3.4.2

import Query.Engine.QueryIndexer;
import Utility.DBSchema;
import Utility.FileParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;


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
        boolean importData = false;
        Connection connection = DriverManager.getConnection(url, username, password);
//        try {
//            // Connect to MySQL Database
//            // Schema is in Tables.sql
//
//
//
//            if(importData) {
//                //Create tables;
//                ScriptRunner sr = new ScriptRunner(connection);
//
//                System.out.println("Creating tables...");
//                // Give the input file to Reader
//                Reader reader = new BufferedReader(
//                        new FileReader("src/Tables.sql"));
//                // Exctute script
//                sr.runScript(reader);
//
//                connection.close();
//                connection = DriverManager.getConnection(url, username, password);
//
//                Utility.DBSetupUtil2 dbSetupUtil = new Utility.DBSetupUtil2(connection);
//                BufferedReader br = new BufferedReader(new FileReader("src/sample.csv"));
//
//                // Parse file line by line.
//                // Ignore first line containing schema of input.
//                br.readLine();
//
//                String line;
//                System.out.println("Importing data...");
//
//                while ((line = br.readLine()) != null) {
//
//                    // Carefully remove the quotes in string.
//                    line = line.substring(0, line.indexOf("biography") + 14) +
//                            line.substring(line.indexOf("biography") + 14, line.indexOf("version") - 5).replace("\"\"", "`") +
//                            line.substring(line.indexOf("version") - 5);
//
//                    String line2 = line.replaceAll("'", "`")
//                            .replaceAll("\"\"([:,\\[\\]\\{\\}:])", "'$1")
//                            .replaceAll("([:,\\[\\]\\{\\}:])\"\"", "$1'")
//                            .replaceAll("\"\"", "`")
//                            .replaceAll("'", "\"")
//                            .substring(1);
//
//                    line2 = line2.substring(0, line2.length() - 1);
//
//                    // Convert json string to object.
//                    ObjectMapper mapper = new ObjectMapper();
//                    NodeRecord record = mapper.readValue(line2, NodeRecord.class);
//
//                    // Insert into database.
//                    dbSetupUtil.insertRecord(record);
//
//
//                }
//            }
//            System.out.println("Parsing Cypher query...");
//
//            CypherLexer lexer = new CypherLexer(new ANTLRFileStream("/Users/liuche/IdeaProjects/GraphDatabase/src/query.txt"));
//            CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
//            CypherParser.CypherContext cypher = parser.cypher();
//            CypherCustomVisitor visitor = new CypherCustomVisitor();
//            visitor.setIndexer(new QueryIndexer(connection));
//            visitor.visit(cypher);
//
//
//
//            connection.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//
//

        //FileParser fileParser = new FileParser("src/sample.csv", connection);
        //DBSchema dbSchema = fileParser.run();
        QueryIndexer queryIndexer = new QueryIndexer(connection);

        CypherLexer lexer = new CypherLexer(new ANTLRFileStream("/Users/liuche/IdeaProjects/GraphDatabase/src/query.txt"));
        CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
        CypherParser.CypherContext cypher = parser.cypher();
        CypherCustomVisitor visitor = new CypherCustomVisitor();
        visitor.setIndexer(queryIndexer);
        visitor.visit(cypher);
        return ;


    }

}
