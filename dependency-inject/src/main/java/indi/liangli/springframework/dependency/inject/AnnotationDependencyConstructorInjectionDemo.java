package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import sun.plugin.com.BeanDispatchImpl;

/**
 * 基于注解的 构建器注入Demo
 * @author liangli
 * 2020/6/16 10:07
 */
public class AnnotationDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);

        //混用
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions(location);

        //启动 spring 上下文
        applicationContext.refresh();

        System.out.println("获取userHolder 类 ："+applicationContext.getBean(UserHolder.class));

        //关闭 spring 上下文
        applicationContext.close();
    }


    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }




}
