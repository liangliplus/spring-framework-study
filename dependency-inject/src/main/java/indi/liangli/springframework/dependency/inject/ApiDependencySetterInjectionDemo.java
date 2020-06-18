package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于java api方式 setter注入
 *
 * @author liangli
 * @Date: 2020/6/18 8:13
 */
public class ApiDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建一个应用上下文
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();

        //使用 BeanFactory注册bean 定义信息
        beanFactory.registerBeanDefinition("userHolder",userHolderBeanDefinition);


        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(location);


        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }

    private static BeanDefinition createUserHolderBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //依赖注入user对象
        beanDefinitionBuilder.addPropertyReference("user","superUser");

        return beanDefinitionBuilder.getBeanDefinition();



    }

}
