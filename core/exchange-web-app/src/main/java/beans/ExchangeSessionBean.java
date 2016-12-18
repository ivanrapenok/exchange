package beans;

import entities.Trade;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ExchangeSessionBean {

    @EJB
    TradesManager tradesManager;

    @EJB
    OwnershipsManager ownershipsManager;

    @EJB
    UsersManager usersManager;

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public ExchangeSessionBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void createTrade(String userId, Integer type, String shareId, Long shareCount, Double price) {
        tradesManager.createTrade(userId, type, shareId, shareCount, price);
        if (type == 0) {
            ownershipsManager.changeSharesCount(userId, shareId, -shareCount);
        } else if (type == 1) {
            usersManager.changeMoney(userId, -price);
        } else {
            throw new RuntimeException();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void acceptTrade(Long tradeId, String userId) {
        Trade trade = tradesManager.getTrade(tradeId);
        String initiatorId = trade.getUserInitiatorId().getUserId();
        String shareId = trade.getShareShareId().getShareId();
        Double price = trade.getPrice();
        Long count = trade.getShareCount();

        long multiple;
        boolean cancel = initiatorId.equals(userId);
        boolean sale = trade.getTradeType() == 0;

        if (trade.getUserClientId() == null) {
            trade.setUserClientId(usersManager.getUser(userId));
            if (cancel == sale) {
                ownershipsManager.changeSharesCount(initiatorId, shareId, count);
            } else {
                usersManager.changeMoney(initiatorId, price);
            }
            if (!cancel) {
                multiple = (sale) ? -1 : 1;
                usersManager.changeMoney(userId, multiple * price);
                ownershipsManager.changeSharesCount(userId, shareId, -multiple * count);
            }
        }
    }
}
