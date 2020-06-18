package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "byName" Autowiring 依赖Setter 方法注入
 *  自动模式，自动取上下文找该名称的bean注入
 * @author liangli
 * @Date: 2020/6/18 8:03
 */
public class AutoWiringByNameDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        //XML 配置文件 Classpath路径
        String location = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        reader.loadBeanDefinitions(location);

        //通过依赖查找获取bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }
}
