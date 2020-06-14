package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.bean.factory.DefaultUserFactory;
import indi.liangli.springframework.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 外部化单例bean 交给spring 管理
 * @author liangli
 * @Date: 2020/6/13 15:30
 */
public class SingletonBeanRegistryDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建一个外部单例bean
        UserFactory userFactory = new DefaultUserFactory();

        SingletonBeanRegistry singletonBeanRegistry =  applicationContext.getBeanFactory();
        //将外部单例对象注册到 spring 容器中
        singletonBeanRegistry.registerSingleton("userFactory",userFactory);

        //启动 spring 上下文
        applicationContext.refresh();

        //通过依赖查询的方法 获取 UserFactory
        UserFactory userFactoryLookup = applicationContext.getBean(UserFactory.class);

        System.out.println("userFactory == userFactoryLookup : "+ (userFactory == userFactoryLookup));

        //关闭 spring 上下文
        applicationContext.close();



    }

}
