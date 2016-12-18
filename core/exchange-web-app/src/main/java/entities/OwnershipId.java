package entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
class OwnershipId implements Serializable {

    @Column
    private String userOwner;
    @Column
    private String shareOwn;

    public OwnershipId() {
    }

    public OwnershipId(String userOwner, String shareOwn) {
        this.userOwner = userOwner;
        this.shareOwn = shareOwn;
    }

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public String getShareOwn() {
        return shareOwn;
    }

    public void setShareOwn(String shareOwn) {
        this.shareOwn = shareOwn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnershipId that = (OwnershipId) o;

        if (!userOwner.equals(that.userOwner)) return false;
        return shareOwn.equals(that.shareOwn);

    }

    @Override
    public int hashCode() {
        int result = userOwner.hashCode();
        result = 31 * result + shareOwn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OwnershipId{" +
                "userOwner=" + userOwner +
                ", shareOwn=" + shareOwn +
                '}';
    }
}
