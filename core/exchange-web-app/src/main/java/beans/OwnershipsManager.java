package beans;

import entities.Ownership;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OwnershipsManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public OwnershipsManager() {
    }

    @SuppressWarnings("unchecked")
    public List<Ownership> getUsersOwnerships(String userId) {
        try {
            Query query = em.createNamedQuery("Ownership.getByUserId").setParameter("id", userId);
            List<Ownership> resultList = query.getResultList();
            if (resultList == null || resultList.size() == 0) {
                return null;
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
