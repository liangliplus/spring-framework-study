package indi.liangli.ioc.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author liangli
 * 2021/8/9 17:11
 */
public class BeaninstantiationDemo {

    public static void main(String[] args) {

        /*BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/ioc-demo.xml");

        Person bean = beanFactory.getBean(Person.class);
        System.out.println(bean);*/


        //方式二， 通过 GenericApplicationContext
        GenericApplicationContext context = new GenericApplicationContext();
        new XmlBeanDefinitionReader(context).loadBeanDefinitions("META-INF/ioc-demo.xml");
        context.refresh();

        Person bean = context.getBean(Person.class);
        Object person1 = context.getBean("person1");
        System.out.println(person1);
        System.out.println(bean);

    }
}
