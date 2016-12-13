package beans;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TradesManager {

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public Integer createTrade()
}
