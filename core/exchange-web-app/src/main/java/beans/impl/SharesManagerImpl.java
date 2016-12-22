package beans.impl;


import beans.SharesManager;
import entities.Share;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SharesManagerImpl implements SharesManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    @Override
    public Share getShareById(String shareId) {
        return (Share) em.createNamedQuery("Share.getById").setParameter("id", shareId).getResultList().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Share> getSharesList() {
        return em.createNamedQuery("Share.getAll").getResultList();
    }
}
