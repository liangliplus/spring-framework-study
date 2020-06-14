package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.bean.factory.DefaultUserFactory;
import indi.liangli.springframework.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Bean的初始化
 * 思考 当bean 延时初始化和非延时初始化区别
 *      结论 ： 延时初始化在使用的时候初始化的，非延时初始化的bean（默认都时单例），在容器初始化完成，就初始化好了
 * 垃圾回收spring 的bean
 * @see javax.annotation.PostConstruct
 * @see javax.annotation.PreDestroy
 * @see org.springframework.beans.factory.InitializingBean
 * @see org.springframework.beans.factory.DisposableBean
 * 注意顺序
 * PostConstruct  -->  InitializationBean --> 自定义初始化方法  init-method
 * PreDestroy  --> DisposableBean --> destroy-method
 *
 * @author liangli
 * @Date: 2020/6/13 16:43
 */
@Configuration   // configuration class 在注册org.springframework.context.annotation.AnnotationConfigApplicationContext.register时可以不打。
public class BeanInitializationDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //添加配置类
        applicationContext.register(BeanInitializationDemo.class);

        //启动 spring 上下文
        applicationContext.refresh();
        System.out.println("Spring 上下文 已完成启动 。。。");
        UserFactory bean = applicationContext.getBean(UserFactory.class);
//        System.out.println(bean);
        bean = null;  //help gc

        System.out.println("Spring 上下文 准备关闭  。。。");
        //关闭 spring 上下文
        applicationContext.close();

        System.out.println("Spring 上下文 已关闭  。。。");

        //如果jvm 配置参数显示关闭，将不起作用

        Thread.sleep(3000);
        System.gc(); //注意这里不能被回收确认下是否还有其他对象引用
        Thread.sleep(1000);




    }

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")  //名称可以自定义的 这个等同于xml 配置destroy-method 或者 org.springframework.beans.factory.support.AbstractBeanDefinition.setDestroyMethodName
//    @Lazy //注意标记lazy 和不标记区别
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
