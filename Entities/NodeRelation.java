package Entities;

/**
 * Created by liuche on 5/5/17.
 */
public class NodeRelation {
    String relationship;

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeRelation that = (NodeRelation) o;

        return relationship != null ? relationship.equals(that.relationship) : that.relationship == null;

    }

    @Override
    public int hashCode() {
        return relationship != null ? relationship.hashCode() : 0;
    }
}
