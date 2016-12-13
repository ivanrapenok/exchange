package beans;

import entities.User;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public UserManager() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer addUser(String userId, String userPswd, String repeatedPswd, String groupId) {
        try {
            if (userId != null && userPswd != null && repeatedPswd != null && userPswd.equals(repeatedPswd)) {
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

    public User getUser(String id) {
        try {
            Query query = em.createNamedQuery("User.getById").setParameter("id", id);
            query.getSingleResult();
            return (User) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
