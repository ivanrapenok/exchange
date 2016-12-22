package entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Cacheable(false)
@Table(name = "TRADES")
@NamedQueries({
        @NamedQuery(name = "Trade.getById", query = "SELECT trade FROM Trade trade WHERE trade.tradeId = :id"),
        @NamedQuery(name = "Trade.getAll", query = "SELECT trade FROM Trade trade WHERE trade.userClientId is null")
})
public class Trade implements Serializable{

    @Id
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq", sequenceName = "TRADES_SEQ", allocationSize = 1)
    @Column(name = "TRADE_ID")
    private Long tradeId;

    @NotNull
    @Column(name = "TRADE_TYPE")
    private Integer tradeType;

    @JoinColumn(name = "SHARE_SHARE_ID", referencedColumnName = "SHARE_ID")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Share shareShareId;

    @JoinColumn(name = "USER_INITIATOR_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private User userInitiatorId;

    @JoinColumn(name = "USER_CLIENT_ID", referencedColumnName = "USER_ID")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User userClientId;

    @Basic(optional = false)
    @Column(name = "SHARE_COUNT")
    private Long shareCount;

    @Digits(integer = 20, fraction = 4)
    @Basic(optional = false)
    @Column(name = "PRICE")
    private Double price;

    public Trade() {
    }

    public Trade(Integer tradeType, Share shareShareId, User userInitiatorId, Long shareCount, Double price) {
        this.tradeType = tradeType;
        this.shareShareId = shareShareId;
        this.userInitiatorId = userInitiatorId;
        this.shareCount = shareCount;
        this.price = price;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Share getShareShareId() {
        return shareShareId;
    }

    public void setShareShareId(Share shareShareId) {
        this.shareShareId = shareShareId;
    }

    public User getUserInitiatorId() {
        return userInitiatorId;
    }

    public void setUserInitiatorId(User userInitiatorId) {
        this.userInitiatorId = userInitiatorId;
    }

    public User getUserClientId() {
        return userClientId;
    }

    public void setUserClientId(User userClientId) {
        this.userClientId = userClientId;
    }

    public Long getShareCount() {
        return shareCount;
    }

    public void setShareCount(Long shareCount) {
        this.shareCount = shareCount;
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

        Trade trade = (Trade) o;

        if (!tradeId.equals(trade.tradeId)) return false;
        if (!tradeType.equals(trade.tradeType)) return false;
        if (!shareShareId.equals(trade.shareShareId)) return false;
        if (!userInitiatorId.equals(trade.userInitiatorId)) return false;
        if (userClientId != null ? !userClientId.equals(trade.userClientId) : trade.userClientId != null) return false;
        if (!shareCount.equals(trade.shareCount)) return false;
        return price.equals(trade.price);

    }

    @Override
    public int hashCode() {
        int result = tradeId.hashCode();
        result = 31 * result + tradeType.hashCode();
        result = 31 * result + shareShareId.hashCode();
        result = 31 * result + userInitiatorId.hashCode();
        result = 31 * result + (userClientId != null ? userClientId.hashCode() : 0);
        result = 31 * result + shareCount.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", tradeType=" + tradeType +
                ", shareShareId=" + shareShareId +
                ", userInitiatorId=" + userInitiatorId +
                ", userClientId=" + userClientId +
                ", shareCount=" + shareCount +
                ", price=" + price +
                '}';
    }
}
