package indi.liangli.springframework.dependency.inject;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解驱动 依赖注入处理的过程
 * 元数据解析 --》 依赖查找(依赖的来源 容器，本地，外部 都可以 ) --》 依赖注入
 * TODO 单一类型和集合类型的依赖注入处理过程调试
 * 小技巧，调式源码的时候可以看堆栈信息(Frames + alt+ F8)
 * @author liangli
 * @Date: 2020/6/14 9:29
 */
public class AnnotationInjectionResolutionDemo {

    //依赖注入user 对象
    @Autowired
    private User user;

    private String abc;


    /**
     * xml 和注解混用
     * @param args
     */
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(AnnotationInjectionResolutionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);


        //启动 spring 上下文
        applicationContext.refresh();

        AnnotationInjectionResolutionDemo bean = applicationContext.getBean(AnnotationInjectionResolutionDemo.class);

        System.out.println("demo User = "+bean.user);
        System.out.println("demo abc(set 方法注入 ) "+bean.abc);

        //关闭 spring 上下文
        applicationContext.close();

    }

}
