package indi.liangli.springframework.dependency.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 *
 * @author liangli
 * @Date: 2020/6/13 17:53
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);

        //启动 spring 上下文
        applicationContext.refresh();

        //依赖查找集合对象

        //关闭 spring 上下文
        applicationContext.close();

    }


    @Bean
    @Primary
    public String helloWorld(){
        return "hello,world";
    }


    @Bean
    public String message(){
        return "message";
    }

}
