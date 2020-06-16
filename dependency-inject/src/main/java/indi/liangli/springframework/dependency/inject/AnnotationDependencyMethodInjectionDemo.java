package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解的 方法注入 demo
 * @author liangli
 * 2020/6/16 10:15
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder1;

    private UserHolder userholder2;

    @Autowired
    public void init1(UserHolder userHolder){
        this.userHolder1 = userHolder;
    }


    @Resource
    public void init2(UserHolder userholder){
        this.userholder2 = userholder;
    }


    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }


    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        //混用
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        beanDefinitionReader.loadBeanDefinitions(location);

        //启动 spring 上下文
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo bean = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        System.out.println("userHolder1 :"+bean.userHolder1);
        System.out.println("userHolder2 :"+bean.userholder2);

        System.out.println(bean.userHolder1 == bean.userholder2);

        //关闭 spring 上下文
        applicationContext.close();
    }

}
