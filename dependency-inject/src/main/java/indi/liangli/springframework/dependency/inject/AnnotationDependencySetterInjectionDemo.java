package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于注解 的 setter 方法 demo
 * @author liangli
 * 2020/6/16 10:13
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        //混用
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions(location);

        //启动 spring 上下文
        applicationContext.refresh();

        System.out.println("获取 setter 方法注入的  userHolder 类 ："+applicationContext.getBean(UserHolder.class));

        //关闭 spring 上下文
        applicationContext.close();
    }


    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder();
        //setter 方法注入
        userHolder.setUser(user);
        return userHolder;
    }


}
