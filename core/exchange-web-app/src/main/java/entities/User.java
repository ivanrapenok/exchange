package entities;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "USERS_TABLE")
@NamedQueries({
        @NamedQuery(name = "User.getById", query = "SELECT user FROM User user WHERE user.userId = :id")
})
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_PSWD")
    private String userPswd;

    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "MONEY", nullable = false)
    private Double money;

    public User() {

    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userPswd, String groupId) {
        this.userId = userId;
        this.userPswd = userPswd;
        this.groupId = groupId;
        this.money = 0D;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;
        if (userPswd != null ? !userPswd.equals(user.userPswd) : user.userPswd != null) return false;
        if (groupId != null ? !groupId.equals(user.groupId) : user.groupId != null) return false;
        return money.equals(user.money);

    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", groupId='" + groupId + '\'' +
                ", money=" + money +
                '}';
    }
}
