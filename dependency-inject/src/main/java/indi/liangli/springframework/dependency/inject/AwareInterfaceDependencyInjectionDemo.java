package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.Context;
import java.util.Calendar;

/**
 * Aware 接口回调，自动模式
 * @author liangli
 * 2020/6/18 9:28
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private  static ApplicationContext applicationContext;


    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(AwareInterfaceDependencyInjectionDemo.class);
        //启动 spring 上下文
        applicationContext.refresh();

        System.out.println(beanFactory == applicationContext.getBeanFactory());

        System.out.println(AwareInterfaceDependencyInjectionDemo.applicationContext == applicationContext);



        //关闭 spring 上下文
        applicationContext.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
