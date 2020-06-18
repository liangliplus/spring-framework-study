package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.dependency.inject.annotation.UserGroup;
import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解依赖注入
 * @author liangli
 * 2020/6/18 9:39
 */
@Configuration  //标记一个Configuration class 不标记注解会通过cglib 来提升
public class QualifierAnnotationDependencyInjectDemo {

    @Autowired
    private User user; // superUser --> primary = true


    @Autowired
    @Qualifier("user")   //指定bean名称或者id
    private User nameUser;



    @Autowired          //测试结论 @bean的 初始化晚于Autowired 目前还不清楚@Bean 是那个类处理
    private Collection<User> allUsers;


    @Autowired
    @Qualifier  //qualifier 可以进行分组
    private Collection<User> qualifiedUsers;


    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;  //2个bean 因为他们两个打了 @UserGroupuser3 和 user4


    @Bean
    @Qualifier
    public static User user1(){
        return createUser(7L);
    }


    @Bean
    @Qualifier
    public static User user2(){
        return createUser(8L);
    }


    @Bean
    @UserGroup
    public static User user3(){
        return createUser(9L);
    }


    @Bean
    @UserGroup
    public static User user4(){
        return createUser(10L);
    }



    public static User createUser(Long userId){
        User user = new User();
        user.setId(userId);
        return user;

    }


    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(QualifierAnnotationDependencyInjectDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);


        //启动 spring 上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectDemo.class);
        //期待输出 superUser
        System.out.println("demo.user =" +demo.user );

        //期待输出 普通user
        System.out.println("demo namedUser = "+demo.nameUser);

        //期待 输出 superUser ，user ，user1,user2 ,user3 ,user4
        System.out.println("demo  allUser ="+demo.allUsers);

        //期待输出 user1 user2
        System.out.println("demo QualifiedUsers = "+demo.qualifiedUsers);

        //期待输出 user3 ， user4
        System.out.println("demo groupedUsers = "+demo.groupedUsers);

        //关闭 spring 上下文
        applicationContext.close();
    }

}
