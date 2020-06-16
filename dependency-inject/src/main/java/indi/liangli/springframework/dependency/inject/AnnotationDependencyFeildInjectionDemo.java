package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的 构建器注入Demo
 * 要理解 要注入的字段首先容器要有，不然抛出{@link org.springframework.beans.factory.NoSuchBeanDefinitionException}
 * @author liangli
 * 2020/6/16 10:07
 */
public class AnnotationDependencyFeildInjectionDemo {

    @Autowired
    private
    //注意@autowired 如果是标记在静态方法或者字段上，不会被注入，参考AutowiredAnnotationBeanPostProcessor
    /*static*/
    UserHolder userHolder1;

    @Resource
    private UserHolder userHolder2;




    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(AnnotationDependencyFeildInjectionDemo.class);

        //混用
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions(location);

        //启动 spring 上下文
        applicationContext.refresh();

        AnnotationDependencyFeildInjectionDemo bean = applicationContext.getBean(AnnotationDependencyFeildInjectionDemo.class);
        System.out.println("userHolder1 :"+bean.userHolder1);
        System.out.println("userHolder2 :"+bean.userHolder2);

        System.out.println(bean.userHolder1 == bean.userHolder2);
        //关闭 spring 上下文
        applicationContext.close();
    }


    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }




}
