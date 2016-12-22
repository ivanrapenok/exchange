package beans.impl;

import beans.UsersManager;
import entities.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsersManagerImpl implements UsersManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public UsersManagerImpl() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer addUser(String userId, String userPswd, String repeatedPswd, String groupId) {
        try {
            if (userId != null && userPswd != null && repeatedPswd != null && !userPswd.equals("") && userPswd.equals(repeatedPswd)) {
                if (getUser(userId) == null) {
                    User newUser = new User(userId, userPswd, (groupId != null) ? groupId : "user");
                    em.persist(newUser);
                    return 0;
                }
                return 1;
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }

    @SuppressWarnings("unchecked")
    public User getUser(String id) {
        try {
            Query query = em.createNamedQuery("User.getById").setParameter("id", id);
            List<User> list=query.getResultList();
            if (list == null || list.size() == 0) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public void changeMoney(String userId, Double price) {
        List<User> list = em.createNamedQuery("User.getById").setParameter("id", userId).getResultList();
        if (list == null || list.size() == 0) {
            throw new RuntimeException();
        } else {
            Double currentMoney = list.get(0).getMoney();
            list.get(0).setMoney(currentMoney + price);
        }
    }

}
