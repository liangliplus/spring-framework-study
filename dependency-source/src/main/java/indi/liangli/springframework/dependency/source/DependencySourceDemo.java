package indi.liangli.springframework.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源demo
 * 依赖查找 和依赖注入来源都可以 是XML 配置文件，注解，BeanDefinitionBuilder  ，单例对象通过Api实现
 * 依赖注入的来源还有非 spring 容器管理对象，比如BeanFactory，可以@Autowired 但是getBean(BeanFactory.class) 报错
 *
 * spring beanDefinition， 单例对象通过 SingletonRegistry 注册，
 * Spring 的内建对象，如ConfigurationClassPostProcessor，AutowiredAnnotationBeanPostProcessor等
 * Spring 内部的单例对象 Environment， systemProperties ， systemEnvironment， MessageSource， lifecycleProcessor, applicationEventMulticaster
 *
 *  非spring容器管理对象 如BeanFactory等 不能作为依赖查找来源 但可以作为依赖注入使用
 *  {@link ConfigurableListableBeanFactory#registerResolvableDependency(java.lang.Class, java.lang.Object)}
 *  {@link DefaultListableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String, java.util.Set, org.springframework.beans.TypeConverter)}
 *
 * @author liangli
 * @Date: 2020/6/14 15:53
 */
public class DependencySourceDemo {

    @Autowired   //注入在 postProcessProperties  方法执行，早于setter 注入，也早于@PostConstruct
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @PostConstruct
    public void initByInjection(){
        //false
        System.out.println("beanFactory == applicationContext "+(beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory "+(beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext "+(resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext "+(applicationEventPublisher == applicationContext));

    }
    @PostConstruct
    public void initByLookup(){
        //内建的类在容器中没有
        getBean(BeanFactory.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
    }


    private <T> T  getBean(Class<T> beanType){
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型 "+beanType.getName() + " 无法在BeanFactory 中找到");
        }
        return null;

    }



    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(DependencySourceDemo.class);


        //启动 spring 上下文
        applicationContext.refresh();

        //关闭 spring 上下文
        applicationContext.close();
    }
}
