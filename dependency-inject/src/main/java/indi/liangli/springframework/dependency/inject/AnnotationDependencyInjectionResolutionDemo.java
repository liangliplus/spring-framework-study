package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.dependency.inject.annotation.InjectUser;
import indi.liangli.springframework.dependency.inject.annotation.MyAutowired;
import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * 注解驱动 依赖注入处理的过程
 * 元数据解析 --》 依赖查找(依赖的来源 容器，本地，外部 都可以 ) --》 依赖注入
 * TODO 单一类型和集合类型的依赖注入处理过程调试
 * tips ：小技巧，调式源码的时候可以看堆栈信息(Frames 和 alt+ F8)
 *
 * {@link  AbstractAutowireCapableBeanFactory#resolveDependency(org.springframework.beans.factory.config.DependencyDescriptor, java.lang.String)}
 * {@link org.springframework.beans.factory.config.DependencyDescriptor}
 * {@link org.springframework.context.annotation.AnnotationConfigUtils}
 * @author liangli
 * @Date: 2020/6/14 9:29
 */
public class AnnotationDependencyInjectionResolutionDemo {

    //DependencyDescriptor  ->
    //必须 （required = true）
    // 实时注入 （eager = true）
    //通过类型 （User.class）
    // 字段名称 （user）
    // 是否首要 (primary = true)
    @Autowired       //依赖查找（处理）
    private User user;


    @Autowired
    private Map<String,User> userMap;

    //可以注入optional
    @MyAutowired
    private Optional<User> userOptional;

    @Inject
    private User injectUser;

    //我们要把autowiredAnnotationBeanPostProcessor 添加到BeanFactory中
    @InjectUser
    private User myInjectUser;


    /**
     * xml 和注解混用
     * @param args
     */
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);


        //启动 spring 上下文
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo bean = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo User = "+bean.user);
        System.out.println("demo UserMap = "+bean.userMap);
        System.out.println("demo userOptional = "+bean.userOptional);
        System.out.println("demo inject = "+bean.injectUser);
        System.out.println("demo MyInjectUser ="+bean.myInjectUser);

//        System.out.println("beanPostProcessor "+applicationContext.getBean("beanPostProcessor",AutowiredAnnotationBeanPostProcessor.class));

        //关闭 spring 上下文
        applicationContext.close();

    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE-3)
    public static  AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

}
