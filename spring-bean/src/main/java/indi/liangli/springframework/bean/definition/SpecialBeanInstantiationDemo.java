package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.bean.factory.DefaultUserFactory;
import indi.liangli.springframework.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊bean的实例化 方式 ServiceLoader
 * {@link ServiceLoaderFactoryBean}
 * TODO 后续分析 getBean  具体实现逻辑
 * @author liangli
 * @Date: 2020/6/13 16:15
 */
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        //配置 XML 配置文件
        // 启动spring 应用上下文  当文件路径不存在 抛出 BeanDefinitionStoreException
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        ServiceLoader<UserFactory> serviceLoader = applicationContext.getBean("serviceLoaderFactoryBean", ServiceLoader.class);

        displayServiceLoader(serviceLoader);

        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        UserFactory userFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());


        //通过 AutowireCapableBeanFactory 实例化bean



//        demoServiceLoader();



    }

    /**
     * java spi 如果接口文件中配置多个相同实现类，会去重
     */
    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    public static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }



}
