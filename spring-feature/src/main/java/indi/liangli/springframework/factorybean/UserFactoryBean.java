package indi.liangli.springframework.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;


public class UserFactoryBean<T> implements FactoryBean<T> {

    private Class<T> clazz;

    public UserFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }


    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("hello  \n"+ applicationContext);
    }


    @Override
    public T getObject() throws Exception {
        return clazz.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }
}
