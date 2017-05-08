package Entities;

/**
 * Created by liuche on 5/4/17.
 */
public class Movie {
    String studio;
    String releaseDate;
    String imdbId;
    Integer runtime;
    String description;
    String language;
    String title;
    Integer version;
    String imageUrl;
    String genre;
    String tagline;
    String lastModified;
    String id;
    String homepage;

    public String getStudio() {
        return studio;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getImdbId() {
        return imdbId;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public Integer getVersion() {
        return version;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getGenre() {
        return genre;
    }

    public String getTagline() {
        return tagline;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getId() {
        return id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (studio != null ? !studio.equals(movie.studio) : movie.studio != null) return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null) return false;
        if (imdbId != null ? !imdbId.equals(movie.imdbId) : movie.imdbId != null) return false;
        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;
        if (description != null ? !description.equals(movie.description) : movie.description != null) return false;
        if (language != null ? !language.equals(movie.language) : movie.language != null) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (version != null ? !version.equals(movie.version) : movie.version != null) return false;
        if (imageUrl != null ? !imageUrl.equals(movie.imageUrl) : movie.imageUrl != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (tagline != null ? !tagline.equals(movie.tagline) : movie.tagline != null) return false;
        if (lastModified != null ? !lastModified.equals(movie.lastModified) : movie.lastModified != null) return false;
        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        return homepage != null ? homepage.equals(movie.homepage) : movie.homepage == null;

    }

    @Override
    public int hashCode() {
        int result = studio != null ? studio.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (imdbId != null ? imdbId.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (tagline != null ? tagline.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
        return result;
    }
}
