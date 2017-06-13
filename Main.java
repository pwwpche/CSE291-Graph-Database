// java -Xmx500M -cp "library/antlr-4.6-complete.jar:$CLASSPATH" org.antlr.v4.Tool src/Cypher.g4 -visitor

// Deployment:
// Maven add commons-csv-1.4, mongodb-driver-3.4.2

import DataImport.FileParser;
import DataImport.JsonParser;
import DataImport.MyBufferedReader;
import Query.Engine.QueryIndexer;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import jdk.nashorn.internal.parser.JSONParser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.StreamSupport;


/*  Graph"
    Sampling, paralleling, condensing, overlapping(DP)
*
* */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
//        MyBufferedReader myBufferedReader = new MyBufferedReader("src/large.csv");
//        String line;
//        int count = 0;
//        while((line = myBufferedReader.readLine()) != null){
//            if(count % 10000 == 0){
//                System.out.println(count);
//            }
//            count++;
//            JSONObject object = new JSONObject(line);
//            Map<String, Object> map = JsonParser.jsonToMap(object);
//            if(map.containsKey("relationship") && !"".equals(map.get("relationship").toString())){
//                System.out.println(line);
//            }
//        }

        //Parsing this CSV file:
        String url = "jdbc:mysql://localhost:3306/graphDB";
        String fileName = "large.csv";

        String username = "root";
        String password = "";
        System.out.println("Connecting to MySQL...");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("MySQL connected.");


//        System.out.println("Parsing file...");
//        FileParser fileParser = new FileParser(fileName, connection);
//        fileParser.run();
//        System.out.println("Parsing complete.");


        System.out.println("Creating index...");
        QueryIndexer queryIndexer = new QueryIndexer(connection);
        System.out.println("Index created...");

        System.out.println("Parsing ANTLR query...");
        CypherLexer lexer = new CypherLexer(new ANTLRFileStream("/Users/liuche/IdeaProjects/GraphDatabase/src/query.txt"));
        CypherParser parser = new CypherParser(new CommonTokenStream(lexer));
        CypherParser.CypherContext cypher = parser.cypher();
        CypherCustomVisitor visitor = new CypherCustomVisitor();
        visitor.setIndexer(queryIndexer);
        visitor.setConnection(connection);
        visitor.visit(cypher);

//            MyBufferedReader bufferedReader = new MyBufferedReader("src/small.csv");
//            String line = "";
//            while ((line = bufferedReader.readLine()) != null){
//                System.out.println(line);
//            }
    }

}
