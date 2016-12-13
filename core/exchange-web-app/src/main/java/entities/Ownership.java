package entities;

import javax.persistence.*;

@Entity
@Table(name = "OWNERSHIPS")
@NamedQueries({
        @NamedQuery(name = "Ownership.getByUser", query = "")
})
public class Ownership {
    @JoinColumn(name = "USER_OWNER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User userOwnerId;

    @JoinColumn(name = "SHARE_OWN_ID", referencedColumnName = "SHARE_ID")
    @ManyToOne(optional = false)
    private Share shareOwnId;

    @Basic(optional = false)
    @Column(name = "SHARE_COUNT")
    private Long shareCount;

    public Ownership() {
    }

    public Ownership(User userOwnerId, Share shareOwnId, Long shareCount) {
        this.userOwnerId = userOwnerId;
        this.shareOwnId = shareOwnId;
        this.shareCount = shareCount;
    }

    public User getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(User userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public Share getShareOwnId() {
        return shareOwnId;
    }

    public void setShareOwnId(Share shareOwnId) {
        this.shareOwnId = shareOwnId;
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
                "userOwnerId=" + userOwnerId +
                ", shareOwnId=" + shareOwnId +
                ", shareCount=" + shareCount +
                '}';
    }
}
