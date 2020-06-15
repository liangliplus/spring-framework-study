package indi.liangli.springframework.dependency.lookup;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}
 * @author liangli
 * 2020/6/15 10:04
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        DefaultListableBeanFactory defaultListableBeanFactory = applicationContext.getDefaultListableBeanFactory();

        //POJO 是一个不同的BeanDefinition，但是注册回调时主动抛出异常
        BeanDefinitionBuilder beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        defaultListableBeanFactory.registerBeanDefinition("errorBean",beanDefinition.getBeanDefinition());




        //启动 spring 上下文
        applicationContext.refresh();

        //关闭 spring 上下文
        applicationContext.close();
    }


    public static class POJO implements InitializingBean {

        @PostConstruct
        public void init() throws Throwable{
            throw new Throwable("init() : for purposes");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() : for purposes");
        }
    }


}
