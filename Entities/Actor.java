package Entities;

import java.util.Date;

/**
 * Created by liuche on 5/4/17.
 */
public class Actor {
    String birthday;
    String birthplace;
    Integer deg;
    String name;
    String lastModified;
    String id;
    String biography;
    Integer version;
    String profileImageUrl;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (birthday != null ? !birthday.equals(actor.birthday) : actor.birthday != null) return false;
        if (birthplace != null ? !birthplace.equals(actor.birthplace) : actor.birthplace != null) return false;
        if (deg != null ? !deg.equals(actor.deg) : actor.deg != null) return false;
        if (name != null ? !name.equals(actor.name) : actor.name != null) return false;
        if (lastModified != null ? !lastModified.equals(actor.lastModified) : actor.lastModified != null) return false;
        if (id != null ? !id.equals(actor.id) : actor.id != null) return false;
        if (biography != null ? !biography.equals(actor.biography) : actor.biography != null) return false;
        if (version != null ? !version.equals(actor.version) : actor.version != null) return false;
        return profileImageUrl != null ? profileImageUrl.equals(actor.profileImageUrl) : actor.profileImageUrl == null;

    }

    @Override
    public int hashCode() {
        int result = birthday != null ? birthday.hashCode() : 0;
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (biography != null ? biography.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        return result;
    }
}
