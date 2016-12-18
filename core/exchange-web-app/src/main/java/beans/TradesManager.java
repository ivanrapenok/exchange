package beans;

import entities.Share;
import entities.Trade;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TradesManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public void createTrade(String userId, Integer type, String shareId, Long shareCount, Double price) {
        Trade trade = new Trade(type, new Share(shareId), new User(userId), shareCount, price);
        em.merge(trade);
    }

    @SuppressWarnings("unchecked")
    public List<Trade> getAllTrades() {
        Query query = em.createNamedQuery("Trade.getAll");
        List<Trade> resultList = query.getResultList();
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public Trade getTrade(Long id) {
        Query query = em.createNamedQuery("Trade.getById").setParameter("id", id);
        List<Trade> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
