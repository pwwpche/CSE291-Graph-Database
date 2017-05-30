// java -Xmx500M -cp "library/antlr-4.6-complete.jar:$CLASSPATH" org.antlr.v4.Tool src/Cypher.g4 -visitor

// Deployment:
// Maven add commons-csv-1.4, mongodb-driver-3.4.2

import Entities.NodeRecord;
import Query.QueryIndexer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;


import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.ibatis.jdbc.ScriptRunner;

public class Main {

    public static void main(String[] args) throws IOException {
        //Parsing this CSV file:

        boolean importData = false;
        try {
            // Connect to MySQL Database
            // Schema is in Tables.sql
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String password = "";
            Connection connection = DriverManager.getConnection(url, username, password);

            if(importData) {
                //Create tables;
                ScriptRunner sr = new ScriptRunner(connection);

                System.out.println("Creating tables...");
                // Give the input file to Reader
                Reader reader = new BufferedReader(
                        new FileReader("src/Tables.sql"));
                // Exctute script
                sr.runScript(reader);

                connection.close();
                connection = DriverManager.getConnection(url, username, password);

                Utility.DBSetupUtil dbSetupUtil = new Utility.DBSetupUtil(connection);
                BufferedReader br = new BufferedReader(new FileReader("src/sample.csv"));

                // Parse file line by line.
                // Ignore first line containing schema of input.
                br.readLine();

                String line;
                System.out.println("Importing data...");

                while ((line = br.readLine()) != null) {

                    // Carefully remove the quotes in string.
                    line = line.substring(0, line.indexOf("biography") + 14) +
                            line.substring(line.indexOf("biography") + 14, line.indexOf("version") - 5).replace("\"\"", "`") +
                            line.substring(line.indexOf("version") - 5);

                    String line2 = line.replaceAll("'", "`")
                            .replaceAll("\"\"([:,\\[\\]\\{\\}:])", "'$1")
                            .replaceAll("([:,\\[\\]\\{\\}:])\"\"", "$1'")
                            .replaceAll("\"\"", "`")
                            .replaceAll("'", "\"")
                            .substring(1);

                    line2 = line2.substring(0, line2.length() - 1);

                    // Convert json string to object.
                    ObjectMapper mapper = new ObjectMapper();
                    NodeRecord record = mapper.readValue(line2, NodeRecord.class);

                    // Insert into database.
                    dbSetupUtil.insertRecord(record);


                }
            }
            System.out.println("Parsing Cypher query...");

            CypherLexer lexer = new CypherLexer(new ANTLRFileStream("/Users/liuche/IdeaProjects/GraphDatabase/src/query.txt"));
            CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
            CypherParser.CypherContext cypher = parser.cypher();
            CypherCustomVisitor visitor = new CypherCustomVisitor();
            visitor.setIndexer(new QueryIndexer(connection));
            visitor.visit(cypher);



            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }




        return ;


    }

}
