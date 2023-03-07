package indi.liangli.springframework.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 如何在spring 中 创建和发布自定义事件?
 * 1. 声明一个事件类 实现 {@link org.springframework.context.ApplicationEvent}
 * 2. 声明一个监听自定义事件的监听类 ，实现 {@link org.springframework.context.ApplicationListener}
 *    并注册为spring 的bean ， 可以在类上标记component
 * 3. 然后通过 context.publishEvent 发布对应事件
 *
 *
 *
 *
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
