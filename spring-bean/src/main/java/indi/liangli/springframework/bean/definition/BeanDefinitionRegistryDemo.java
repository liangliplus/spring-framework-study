package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 注册spring bean （BeanDefinition 注册 ）
 * 1.Xml 配置元信息  定义bean的元信息
 *     <bean name =" " .../>
 * 2.java 注解配置元信息
     * @Bean
     * @Component
     * @Import
 * 3. java Api 配置元信息
 *  * 命令和非命名方式 {@link org.springframework.beans.factory.support.BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition, BeanDefinitionRegistry)}
 *  * 配置类方式 AnnotatedBeanDefinitionReader#register(Class...)
 * 4. 外部单例bean的注册
 * @author liangli
 * @Date: 2020/6/12 8:34
 */
//3. 注解 定义bean的元信息
@Import(BeanDefinitionRegistryDemo.Config.class)
public class BeanDefinitionRegistryDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 configuration Class (配置类)  Config 类上没有配置类，@Bean 也是能被扫描到。
        applicationContext.register(Config.class);

        //api 命名方式注册
        registerUserBeanDefinition(applicationContext,"kenneth");


        registerUserBeanDefinition(applicationContext);

        //启动spring 应用上下文
        applicationContext.refresh();

        //按照类型依赖查找
        //import 和 Component 如果都一个类，标注，在容器中也只有一个
        System.out.println(" Config 类型的所有beans "+applicationContext.getBeansOfType(Config.class));
        System.out.println(" User 类型的所有beans "+applicationContext.getBeansOfType(User.class));

        //显式关闭spring 应用上下文
        applicationContext.close();
    }

    //定义当前类型 作为spring组件
    //2. 注解 定义bean的元信息
    @Component
    public static class Config{
        //1. 注解 定义bean的元信息
        @Bean(name = {"user","liangli"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("小小");
            return user;
        }
    }


    /**
     * 命名方式的注册
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName){
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",1L)
                .addPropertyValue("name","liangli");
        if(StringUtils.hasText(beanName)) {
            //注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }else{
            //非命令 bean注册方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }

    /**
     * 非命名方式注册
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry){
        registerUserBeanDefinition(registry,null);
    }











}
