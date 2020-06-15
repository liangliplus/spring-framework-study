package indi.liangli.springframework.dependency.lookup;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 * 可以使用 getIfAvailable 查找 ，也可以 getObject 都是延时查找，还可以通过Stream 操作
 * {@link ObjectProvider#getIfAvailable(java.util.function.Supplier) }查找安全的
 * {@link ObjectProvider#getObject()}  查找不安全
 * {@link ObjectProvider#stream}
 *
 * @author liangli
 * @Date: 2020/6/13 17:53
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);

        //启动 spring 上下文
        applicationContext.refresh();

        //依赖查找集合对象  通过objectProvider 进行查找，也是查找不安全的，当不存在会抛出NoSuchBeanException
        lookupByObjectProvider(applicationContext);
        lookupByIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        
        //关闭 spring 上下文
        applicationContext.close();

    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        //stream --> method reference
        beanProvider.stream().forEach(System.out::println);

    }

    //查找安全的
    private static void lookupByIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        //比如我当前BeanFactory 中没有User这个bean
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getIfAvailable());

        //方法引用， 并且如果容器没有改bean ，返回兜底方案
        User user = beanProvider.getIfAvailable(User::createUser);
        System.out.println("当前User 对象 ："+user);


    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        //5.1版本新增特性 ObjectProvider 类似FactoryBean ，在容器内部一样对其进行了实现
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
//        ObjectProvider<Integer> beanProvider1 = applicationContext.getBeanProvider(Integer.class);
//        System.out.println(beanProvider1.getObject());

    }


    @Bean
    @Primary
    public String helloWorld(){
        return "hello,world";
    }


    @Bean
    public String message(){
        return "message";
    }

}
