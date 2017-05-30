package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuche on 5/5/17.
 */
public class NodeRecord {

    Person node1;
    Movie node2;
    List<String> node1Label = new ArrayList<>();
    List<String> node2Label = new ArrayList<>();
    String rel_type;
    NodeRelation relationship;

    @JsonCreator
    public NodeRecord(@JsonProperty("node1") Person node1,
                      @JsonProperty("node2") Movie node2,
                      @JsonProperty("node1Label") List<String> node1Label,
                      @JsonProperty("node2Label") List<String> node2Label,
                      @JsonProperty("rel_type") String rel_type,
                      @JsonProperty("relationship") NodeRelation relationship
                      ){
        this.node1 = node1;
        this.node2 = node2;
        this.node1Label = node1Label;
        this.node2Label = node2Label;
        this.rel_type = rel_type;
        this.relationship = relationship;
    }

    public List<String> generateStatements(String tableName){
        String personId = node1.getId();
        String movieId = node2.getId();
        List<String> result = new ArrayList<>();
        for(String label : node1Label){
            String statement1 = "INSERT INTO " + tableName + "(pid, label) SELECT \""+
                    personId + "\", \"" + label + "\" FROM " + "dual" +
                    " WHERE NOT EXISTS (SELECT * FROM " + tableName +
                    " WHERE pid = \"" + personId + "\" AND label = \"" + label +
                    "\" ) LIMIT 1;";
            result.add(statement1);
        }
        for(String label : node2Label){
            String statement1 = "INSERT INTO " + tableName + "(mid, label) SELECT \""+
                    movieId + "\", \"" + label + "\" FROM " + "dual" +
                    " WHERE NOT EXISTS (SELECT * FROM " + tableName +
                    " WHERE mid = \"" + movieId + "\" AND label = \"" + label +
                    "\" ) LIMIT 1;";
            result.add(statement1);
        }
        return result;
    }

    public Person getNode1() {
        return node1;
    }

    public Movie getNode2() {
        return node2;
    }

    public List<String> getNode1Label() {
        return node1Label;
    }

    public List<String> getNode2Label() {
        return node2Label;
    }

    public String getRel_type() {
        return rel_type;
    }

    public NodeRelation getRelationship() {
        return relationship;
    }
}
