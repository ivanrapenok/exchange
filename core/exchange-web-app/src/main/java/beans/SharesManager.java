package beans;

import entities.Share;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SharesManager {

    Share getShareById(String shareId);

    List<Share> getSharesList();

}
