package Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuche on 5/5/17.
 */
public class NodeRelation {
    String name;
    @JsonCreator
    public NodeRelation(@JsonProperty("name") String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String generateQueryString(String table){
        String query = "insert into " + table + "(name) values(" +
                "\"" + this.name + "\"" +
                ");";
        return query;

    }
}
