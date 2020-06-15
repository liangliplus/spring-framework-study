package indi.liangli.springframework.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException}
 * @author liangli
 * 2020/6/15 9:58
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型NoUniqueBeanDefinitionExceptionDemo 作为配置类 （Configuration Class）
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        //启动 spring 上下文
        applicationContext.refresh();

        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("Spring 应用上下文 存在 %d 个 %s类型的bean， 具体原因：%s%n",
                    e.getNumberOfBeansFound(),String.class.getName(),e.getMessage());
        }


        //关闭 spring 上下文
        applicationContext.close();

    }

    @Bean
    public String bean1(){
        return "1";
    }

    @Bean
    public String bean2(){
        return "2";
    }

    @Bean
    public String bean3(){
        return "3";
    }

}
