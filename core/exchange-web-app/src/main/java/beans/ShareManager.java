package beans;


import entities.Share;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ShareManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public Share getShareById(String shareId) {
        return (Share) em.createNamedQuery("Share.getById").setParameter("id", shareId).getResultList().get(0);
    }

    @SuppressWarnings("unchecked")
    public List<Share> getSharesList() {
        return em.createNamedQuery("Share.getAll").getResultList();
    }
}
