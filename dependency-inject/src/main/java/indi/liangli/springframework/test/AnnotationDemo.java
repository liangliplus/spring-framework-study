package indi.liangli.springframework.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AnnotationDemo {

    /**
     * 如果就管理该测试类一个类， 使用register 就可以，不用scan
     * 本质上 register 就是传入class 调用 registerBeanDefinition 注册
     * scan 就是先扫描一系列类，然后在调用 registerBeanDefinition  注册。
     * 使用spring 注解的容器
     * 1.构建容器
     * 2.扫描包 （负责扫描指定配置路径的bean ）
     * 3.刷新上下文
     * @param args
     */
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(AnnotationDemo.class);
//        applicationContext.scan("indi.liangli.springframework.test");



        //启动 spring 上下文
        applicationContext.refresh();


        Demo1 bean = applicationContext.getBean(Demo1.class);

        System.out.println(bean == bean.demo1);



    }

    @Component
    public class Demo1 {

        @Autowired
        private Demo1 demo1;
    }



}


