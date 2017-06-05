package Discard;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuche on 5/4/17.
 */
public class Person {
    String birthday;
    String birthplace;
    Integer deg;
    String name;
    String lastModified;
    String id;
    String biography;
    Integer version;
    String profileImageUrl;

    @JsonCreator
    public Person(
            @JsonProperty("birthday") String birthday,
            @JsonProperty("birthplace") String birthplace,
            @JsonProperty("deg") Integer deg,
            @JsonProperty("name") String name,
            @JsonProperty("lastModified") String lastModified,
            @JsonProperty("id") String id,
            @JsonProperty("biography") String biography,
            @JsonProperty("version") Integer version,
            @JsonProperty("profileImageUrl") String profileImageUrl
            ){
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.deg = deg;
        this.name = name;
        this.lastModified = lastModified;
        this.id = id;
        this.biography = biography.replace("\"", "\\\"");
        this.version = version;
        this.profileImageUrl = profileImageUrl;
    }

    public String generateQueryString(String table){
        String query = "insertObject into " + table +
                "(birthday,birthplace,deg,name,lastModified,id," +
                "biography,version,profileImageUrl) values(" +
                "\"" + this.birthday + "\", " +
                "\"" + this.birthplace + "\", " +
                "" + this.deg + ", " +
                "\"" + this.name + "\", " +
                "\"" + this.lastModified + "\", " +
                "\"" + this.id + "\", " +
                "\"" + this.biography + "\", " +
                "" + this.version + ", " +
                "\"" + this.profileImageUrl + "\"" +
                ");";
        return query;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public Integer getDeg() {
        return deg;
    }

    public String getName() {
        return name;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getId() {
        return id;
    }

    public String getBiography() {
        return biography;
    }

    public Integer getVersion() {
        return version;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
