package indi.liangli.springframework.bean.factory;

import indi.liangli.springframework.ioc.overview.domain.User;

/**
 * 工厂类
 * @author liangli
 * @Date: 2020/6/13 15:36
 */
public interface UserFactory {


    /**
     *  java 8 方法默认实现
     */
    default User createUser(){
        return User.createUser();
    }

}
