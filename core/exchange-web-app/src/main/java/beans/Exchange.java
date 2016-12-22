package beans;

import entities.Ownership;
import entities.Share;
import entities.Trade;
import entities.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Exchange {

    User getUser(String id);

    Integer addUser(String userId, String userPswd, String repeatedPswd, String groupId);

    List<Share> getSharesList();

    List<Trade> getAllTrades();

    void createTrade(String userId, Integer type, String shareId, Long shareCount, Double price);

    void acceptTrade(Long tradeId, String userId);

    List<Ownership> getUsersOwnerships(String userId);
}
