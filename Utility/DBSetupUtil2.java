package Utility;

import Entities.Movie;
import Entities.NodeRecord;
import Entities.NodeRelation;
import Entities.Person;

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
