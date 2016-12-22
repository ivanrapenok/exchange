package beans;

import entities.User;

import javax.ejb.Local;

@Local
public interface UsersManager {

    Integer addUser(String userId, String userPswd, String repeatedPswd, String groupId);

    User getUser(String id);

    void changeMoney(String userId, Double price);
}
