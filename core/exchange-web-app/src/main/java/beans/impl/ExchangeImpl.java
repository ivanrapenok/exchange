package beans.impl;

import beans.*;
import entities.Ownership;
import entities.Share;
import entities.Trade;
import entities.User;

import javax.ejb.*;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ExchangeImpl implements Exchange {

    @EJB
    TradesManager tradesManager;

    @EJB
    OwnershipsManager ownershipsManager;

    @EJB
    UsersManager usersManager;

    @EJB
    SharesManager shareManager;

    public ExchangeImpl() {
    }

    @Override
    public User getUser(String id) {
        return usersManager.getUser(id);
    }

    @Override
    public Integer addUser(String userId, String userPswd, String repeatedPswd, String groupId) {
        return usersManager.addUser(userId, userPswd, repeatedPswd, groupId);
    }

    @Override
    public List<Share> getSharesList() {
        return shareManager.getSharesList();
    }

    @Override
    public List<Trade> getAllTrades() {
        return tradesManager.getAllTrades();
    }

    @Override
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

    @Override
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

    @Override
    public List<Ownership> getUsersOwnerships(String userId) {
        return ownershipsManager.getUsersOwnerships(userId);
    }
}
