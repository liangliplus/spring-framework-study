package indi.liangli.springframework.factorybean;

import indi.liangli.springframework.guava.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author liangli
 * 2021/9/2 14:06
 */
@Configuration
public class FactoryBeanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(FactoryBeanDemo.class);


        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(UserFactoryBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(User.class);
        beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        applicationContext.registerBeanDefinition("user",beanDefinition);


        applicationContext.scan("indi.liangli.springframework.factorybean");
        applicationContext.refresh();



    }




}
