package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 的别名 Demo
 * @author liangli
 * @Date: 2020/6/12 8:24
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-definition-context.xml");
        User bean = applicationContext.getBean("liangli-user", User.class);
        System.out.println(bean);
    }





}
