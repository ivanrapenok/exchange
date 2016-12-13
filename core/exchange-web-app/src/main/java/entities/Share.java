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
    @Column(name = "PRICE")
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
    public String toString() {
        return "Share{" +
                "shareId='" + shareId + '\'' +
                ", shareType='" + shareType + '\'' +
                ", price=" + price +
                '}';
    }
}
