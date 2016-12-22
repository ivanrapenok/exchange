package beans;

import entities.Ownership;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OwnershipsManager {

    List<Ownership> getUsersOwnerships(String userId);

    void changeSharesCount(String userId, String shareId, Long tradesCount);

}
