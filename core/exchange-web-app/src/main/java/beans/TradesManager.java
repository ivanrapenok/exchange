package beans;

import entities.Trade;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TradesManager {

    void createTrade(String userId, Integer type, String shareId, Long shareCount, Double price);

    List<Trade> getAllTrades();

    Trade getTrade(Long id);

}
