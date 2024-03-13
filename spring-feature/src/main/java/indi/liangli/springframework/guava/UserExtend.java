package indi.liangli.springframework.guava;

import indi.liangli.springframework.entity.User;

/**
 *
 * @author liangli
 * 2021/7/13 11:42
 */
public class UserExtend extends User {

    private String phone;

    public UserExtend(String name, String phone) {
        super(name);
        this.phone = phone;
    }
}
