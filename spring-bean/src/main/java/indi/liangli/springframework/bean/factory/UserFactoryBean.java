package indi.liangli.springframework.bean.factory;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 *
 * user 工厂bean
 * @author liangli
 * @Date: 2020/6/13 16:03
 */
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
