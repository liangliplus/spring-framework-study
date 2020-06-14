package indi.liangli.springframework.bean.factory;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 {@link UserFactory} 实现
 * 基于这个类演示 Bean PostConstruct --》 Initialization --》 自定义 init-method 顺序
 *
 * @author liangli
 * @Date: 2020/6/13 15:39
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {


    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct UserFactory 初始化中 。。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet  UserFactory 初始化中 。。。。");
    }

    public void initMethod(){
        System.out.println("自定义初始化方法 initMethod UserFactory 初始化中 。。。。");

    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy UserFactory 销毁中 。。。。");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy  UserFactory 销毁中 。。。。");
    }

    public void destroyMethod(){
        System.out.println("自定义的destroyMethod    UserFactory 销毁中 。。。。");
    }

    /**
     * 这个方法在垃圾回收的时候可能被调用
     * @throws Throwable
     */
    @Override
    public void finalize() throws Throwable {
        System.out.println(this.getClass().getName()+" 对象正在被垃圾回收。。。。");
    }
}
