package indi.liangli.springframework.dependency.inject;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于xml 资源的 setter 方法注入示例
 * @author liangli
 * 2020/6/16 10:02
 */
public class XmlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML 配置文件 Classpath路径 注入autowire 需要标注为constructor
        String location = "classpath:/META-INF/dependency-setter-inject.xml";
        reader.loadBeanDefinitions(location);

        //通过依赖查找获取bean
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean);

    }

}
