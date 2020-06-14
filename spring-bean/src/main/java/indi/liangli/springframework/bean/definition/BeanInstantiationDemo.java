package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 示例
 * 常规方式 通过 xml 配置 ，注解，java api ， 工厂方法，实例对象工厂方法，factoryBean的方式
 * @author liangli
 * @Date: 2020/6/13 15:58
 */
public class BeanInstantiationDemo {
    public static void main(String[] args) {
        //配置 XML 配置文件
        // 启动spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");

        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);


        System.out.println(user == userByInstanceMethod);
        System.out.println(user == userByFactoryBean);




    }


}
