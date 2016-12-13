package entities;

import javax.persistence.*;

@Entity
@Table(name = "OWNERSHIPS")
@NamedQueries({
        @NamedQuery(name = "Ownership.getByUserId",
                query = "SELECT ownership FROM Ownership ownership WHERE ownership.userOwner.userId = :id")
})
public class Ownership {

    @EmbeddedId
    private OwnershipId ownershipId = new OwnershipId();

    @MapsId
    @JoinColumn(name = "SHARE_OWN_ID", referencedColumnName = "SHARE_ID")
    @ManyToOne(optional = false)
    private Share shareOwn;

    @MapsId
    @JoinColumn(name = "USER_OWNER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User userOwner;

    @Basic(optional = false)
    @Column(name = "SHARE_COUNT")
    private Long shareCount;

    public Ownership() {
    }

    public Ownership(Share shareOwn, User userOwner, Long shareCount) {
        this.shareOwn = shareOwn;
        this.userOwner = userOwner;
        this.shareCount = shareCount;
    }

    public Share getShareOwn() {
        return shareOwn;
    }

    public void setShareOwn(Share shareOwn) {
        this.shareOwn = shareOwn;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
    }

    @Override
    public String toString() {
        return "Ownership{" +
                "shareOwn=" + shareOwn +
                ", userOwner=" + userOwner +
                ", shareCount=" + shareCount +
                '}';
    }
}