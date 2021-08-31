package indi.liangli.springframework;

import indi.liangli.springframework.event.StudentEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *  spring 的观察者模式实现ApplicationListener 标记注解（会被扫描到注册监听）
 *  然后当通过ApplicationEventPublisher 发布事件后所有依赖的对象就会收到相应事件
 * {@link org.springframework.context.ApplicationListener} 依赖对象 Observable
 * {@link org.springframework.context.ApplicationEventPublisher} observer
 * {@link org.springframework.context.ApplicationEvent} 具体消息对象
 * 注册方式
 * {@link org.springframework.context.event.EventListener}
 * 通过创建一个ApplicationListenerMethodAdapter 然后向容器注册监听器
 * org.springframework.context.event.ApplicationListenerMethodAdapter#ApplicationListenerMethodAdapter(java.lang.String, java.lang.Class, java.lang.reflect.Method)
 *
 * @author liangli
 * 2021/7/13 10:45
 */
@Configuration
//@ComponentScan("indi.liangli.springframework.event")
public class SpringEventDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringEventDemo.class);

        context.scan("indi.liangli.springframework.event");
        context.refresh();


        context.publishEvent(new StudentEvent("张三"));

        context.close();


    }
}
