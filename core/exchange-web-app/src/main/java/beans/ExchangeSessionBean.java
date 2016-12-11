package beans;

import entities.User;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ExchangeSessionBean {

    private long count = 0;

    @PersistenceContext(unitName = "em")
    private EntityManager em;

    public ExchangeSessionBean() {
    }

    public long getCount() {
        return count;
    }

    public User getUser(String id) {
        count++;
        if (id.equals("johny")) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Query query = em.createNamedQuery("User.getById").setParameter("id", id);
        query.getSingleResult();
        System.out.println(count + " AAAAAAAAAAAAAAAAAAAAAAAAAA");
        return (User) query.getSingleResult();
    }

}
