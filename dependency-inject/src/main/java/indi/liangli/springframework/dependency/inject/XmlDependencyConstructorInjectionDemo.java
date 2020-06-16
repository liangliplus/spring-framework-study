package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于xml 资源 Constructor  注入示例
 * @author liangli
 * 2020/6/16 9:51
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        //创建BeanFactory 容器默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML 配置文件 Classpath路径
        String location = "classpath:/META-INF/dependency-constructor-inject.xml";
        reader.loadBeanDefinitions(location);

        //通过依赖查找获取bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }
}
