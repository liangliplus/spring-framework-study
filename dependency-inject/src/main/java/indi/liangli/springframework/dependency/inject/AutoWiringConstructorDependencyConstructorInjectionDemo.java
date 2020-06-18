package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "constructor" autowiring 依赖构造器注入 演示
 * constructor autowiring 也是根据类型去匹配
 *
 * @author liangli
 * @Date: 2020/6/18 8:10
 */
public class AutoWiringConstructorDependencyConstructorInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //XML 配置文件 Classpath路径
        String location = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        reader.loadBeanDefinitions(location);

        //通过依赖查找获取bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }

}
