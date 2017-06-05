package Discard;


import java.sql.*;
import java.util.List;

/**
 * Created by liuche on 5/20/17.
 */
public class DBSetupUtil2 {

    Connection conn;

    public DBSetupUtil2(Connection conn) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "";
        this.conn = conn;
    }

    public boolean checkExist(String table, String field, String id) {
        String query = "select COUNT(*) c from " + table + " as t where t." + field + " = \"" + id + "\"";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            result.next();
            return (result.getInt(1) != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }

    public void insertRecord(NodeRecord record) {

        Movie movie = record.getNode2();
        Person person = record.getNode1();
        NodeRelation relation = record.getRelationship();
        if (!checkExist("Movie", "id", movie.getId())) {
            String query = movie.generateQueryString("Movie");
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!checkExist("Person", "id", person.getId())) {
            String query = person.generateQueryString("Person");
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (!checkExist("Relationship", "name", relation.getName())) {
            String query = relation.generateQueryString("Relationship");
            try {
                PreparedStatement statement = conn.prepareStatement(query);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String movieMId = movie.getId(), personPID = person.getId(), relationID = "", rel_type = "";

        try {
            ResultSet ridSet = conn.prepareStatement("SELECT rid FROM relationship r where r.name = \"" + relation.getName() + "\"")
                    .executeQuery();

            ridSet.next();
            relationID = ridSet.getString(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        String edgeQuery = "insert into Edge(pid, mid, rid, rel_type) values(" +
                "\"" + personPID + "\", " +
                "\"" + movieMId + "\", " +
                "" + relationID + "," +
                "\"" + record.getRel_type() + "\"" +
                ");";

        try {
            conn.prepareStatement(edgeQuery).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> labelStatements = record.generateStatements("NodeLabel");
        try {
            for(String labelStatement : labelStatements){
                conn.prepareStatement(labelStatement).execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

/* Discarded main function */

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
//                Discarded.Entities.DBSetupUtil2 dbSetupUtil = new Discarded.Entities.DBSetupUtil2(connection);
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
