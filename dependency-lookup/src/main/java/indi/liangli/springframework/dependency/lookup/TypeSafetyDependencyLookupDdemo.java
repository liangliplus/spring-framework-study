package indi.liangli.springframework.dependency.lookup;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.beans.Beans;
import java.util.concurrent.Executors;

/**
 * 类型安全的 依赖查找示例
 * @author liangli
 * 2020/6/15 13:18
 */
public class TypeSafetyDependencyLookupDdemo {
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型NoUniqueBeanDefinitionExceptionDemo 作为配置类 （Configuration Class）
        applicationContext.register(TypeSafetyDependencyLookupDdemo.class);

        //启动 spring 上下文
        applicationContext.refresh();

        //演示 BeanFactory#getBean 方法的安全性  异常
        displayBeanFactoryGetBean(applicationContext);
        //演示 ObjectFactory#getObject 方法的安全性 异常
        displayObjectFactoryGetObject(applicationContext);
        //演示ObjectProvider#getIfAvaiable 方法安全性 无异常
        displayObjectProviderIfAvailable(applicationContext);
        //演示 ListableBeanFactory#getBeansOfType 方法的安全性  无异常
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        //演示 ObjectProvider stream 操作的安全性 无异常
        displayObjectProviderStreamOps(applicationContext);
        


        //关闭 spring 上下文
        applicationContext.close();

    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps",()-> beanProvider.stream().forEach(System.out::println));

    }

    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayListableBeanFactoryGetBeansOfType",()-> applicationContext.getBeansOfType(User.class));
    }

    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable",()-> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        //ObjectProvider is ObjectFactory
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject",()-> beanProvider.getObject());

    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        printBeansException("displayBeanFactoryGetBean",()-> applicationContext.getBean(User.class));
    }

    private static void printBeansException(String source,Runnable runnable){
        //使用同一个err 输出流
        System.err.println("===============================");
        System.err.println("Source from ："+source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }



}
