package Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liuche on 5/4/17.
 */

//((String)|(Integer))\W(\w+);    =>     @JsonProperty\("$4"\) $1 $4,
// => this.$4 = $4;

public class Movie {
    String studio;
    String releaseDate;
    String imdbId;
    Integer runtime;
    String description;
    String language;
    String title;
    Integer version;
    String trailer;
    String imageUrl;
    String genre;
    String tagline;
    String lastModified;
    String id;
    String homepage;

    public Movie(
        @JsonProperty("studio") String studio,
        @JsonProperty("releaseDate") String releaseDate,
        @JsonProperty("imdbId") String imdbId,
        @JsonProperty("runtime") Integer runtime,
        @JsonProperty("description") String description,
        @JsonProperty("language") String language,
        @JsonProperty("title") String title,
        @JsonProperty("version") Integer version,
        @JsonProperty("trailer") String trailer,
        @JsonProperty("imageUrl") String imageUrl,
        @JsonProperty("genre") String genre,
        @JsonProperty("tagline") String tagline,
        @JsonProperty("lastModified") String lastModified,
        @JsonProperty("id") String id,
        @JsonProperty("homepage") String homepage
    ){
        this.studio = studio;
        this.releaseDate = releaseDate;
        this.imdbId = imdbId;
        this.runtime = runtime;
        this.description = description.replace("\"", "\\\"");
        this.language = language;
        this.title = title;
        this.version = version;
        this.trailer = trailer;
        this.imageUrl = imageUrl;
        this.genre = genre;
        this.tagline = tagline;
        this.lastModified = lastModified;
        this.id = id;
        this.homepage = homepage;
    }

    public String generateQueryString(String table){
        String query = "insert into " + table +
                "(studio, releaseDate, imdbId, runtime, description, language, title, version, " +
                "trailer, imageUrl, genre, tagline, lastModified, id, homepage) " +
                "values( " +
                "\"" + this.studio + "\", " +
                "\"" + this.releaseDate + "\", " +
                "\"" + this.imdbId + "\", " +
                "" + this.runtime + ", " +
                "\"" + this.description + "\", " +
                "\"" + this.language + "\", " +
                "\"" + this.title + "\", " +
                "" + this.version + ", " +
                "\"" + this.trailer + "\", " +
                "\"" + this.imageUrl + "\", " +
                "\"" + this.genre + "\", " +
                "\"" + this.tagline + "\", " +
                "\"" + this.lastModified + "\", " +
                "\"" + this.id + "\", " +
                "\"" + this.homepage + "\"" +
                ");";
        return query;

    }

    public String getId() {
        return id;
    }


}
