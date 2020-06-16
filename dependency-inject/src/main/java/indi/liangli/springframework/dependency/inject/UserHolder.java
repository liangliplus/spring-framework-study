package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;

/**
 * {@link indi.liangli.springframework.ioc.overview.domain.User}  的持有类
 * @author liangli
 * 2020/6/16 9:55
 */
public class UserHolder {
    private User user;

    //提供一个无参构造
    public UserHolder() {
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
