package indi.liangli.springframework.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;


/**
 * 外部化配置 来源实例
 * 涉及 @value : 给定默认值
 * @see org.springframework.context.annotation.PropertySource
 * @see org.springframework.beans.factory.annotation.Value
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 * @author liangli
 * @Date: 2020/6/14 16:27
 */
@Configuration
//标记PropertySource 的类只有在有@Configuration 注解的类才会生效
@PropertySource(value = "classpath:/META-INF/default.properties",encoding = "utf-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    //注意系统变量优先级高于我们外部外配置，-D参数或者系统变量
    @Value("${usr.name}")
    private String name;

    //外部化资源  把一个文件路径变为一个资源
    @Value("${user.resource:classpath:/default.properties}")
    private Resource resource;


    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);


        //启动 spring 上下文
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo bean = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println("user id = "+ bean.id);
        System.out.println("user name = "+ bean.name);
        System.out.println("user resource = "+ bean.resource);


        //关闭 spring 上下文
        applicationContext.close();
    }
}
