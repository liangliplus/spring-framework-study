package indi.liangli.springframework.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 *
 * ResolvableDependency 作为依赖来源
 * @author liangli
 * @Date: 2020/6/14 16:16
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init(){
        System.out.println(value);
    }

    public ResolvableDependencySourceDemo(){
        System.out.println("Construct is invoked !");
    }

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        //这个注册配置类也是spring的bean ， 才容器refresh 里面会对单例的bean进行实例化，触发依赖查找 和 依赖注入过程 ，
        // 放在register 之前也不行，因为容器还没有刷新，会提示容器没有刷新，可以通过添加回调的方式
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> beanFactory.registerResolvableDependency(String.class,"helloWorld"));



        //启动 spring 上下文
        applicationContext.refresh();

        /*AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        if(autowireCapableBeanFactory instanceof ConfigurableListableBeanFactory){
            ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory) autowireCapableBeanFactory;
            configurableListableBeanFactory.registerResolvableDependency(String.class,"helloWorld");

        }*/

        //关闭 spring 上下文
        applicationContext.close();
    }
}
