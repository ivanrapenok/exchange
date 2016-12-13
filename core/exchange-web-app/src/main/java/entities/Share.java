package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "SHARES_TABLE")
@NamedQueries({
        @NamedQuery(name = "Share.getById", query = "SELECT share FROM Share share WHERE share.shareId = :id")
})
public class Share {

    @Id
    @Column(name = "SHARE_ID", nullable = false)
    private String shareId;

    @Column(name = "SHARE_TYPE")
    private String shareType;

    @Digits(integer = 20, fraction = 4)
    @Column(name = "PRICE", nullable = false)
    private Double price;

    public Share() {

    }

    public Share(String shareId, String shareType, Double price) {
        this.shareId = shareId;
        this.shareType = shareType;
        this.price = price;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Share share = (Share) o;

        if (!shareId.equals(share.shareId)) return false;
        if (shareType != null ? !shareType.equals(share.shareType) : share.shareType != null) return false;
        return price.equals(share.price);

    }

    @Override
    public int hashCode() {
        int result = shareId.hashCode();
        result = 31 * result + (shareType != null ? shareType.hashCode() : 0);
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Share{" +
                "shareId='" + shareId + '\'' +
                ", shareType='" + shareType + '\'' +
                ", price=" + price +
                '}';
    }
}
