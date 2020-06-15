package indi.liangli.springframework.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  {@link org.springframework.beans.BeanInstantiationException} demo
 *  比如我们这里示例化一个接口
 *
 * @author liangli
 * 2020/6/15 10:14
 */
public class BeanInstantiationExceptionDemo {
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        DefaultListableBeanFactory defaultListableBeanFactory = applicationContext.getDefaultListableBeanFactory();

        //POJO 是一个不同的BeanDefinition，但是注册回调时主动抛出异常
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        defaultListableBeanFactory.registerBeanDefinition("errorBean",beanDefinition.getBeanDefinition());

        //启动 spring 上下文
        applicationContext.refresh();

        //关闭 spring 上下文
        applicationContext.close();
    }
}
