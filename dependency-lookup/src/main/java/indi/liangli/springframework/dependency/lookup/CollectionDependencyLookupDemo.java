package indi.liangli.springframework.dependency.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

/**
 * 集合类型依赖查找接口实例
 *
 * @author liangli
 * @Date: 2020/6/13 19:43
 */
public class CollectionDependencyLookupDemo {

    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(CollectionDependencyLookupDemo.class);

        //启动 spring 上下文
        applicationContext.refresh();

        //依赖查找集合对象

        //获取同类型bean 的名称列表
        String[] beanNamesForType = applicationContext.getBeanNamesForType(String.class);
        //获取同类型bean的实例（注意如果在扩展spring 框架时，可能提前初始化bean ）
        Map<String, String> beansOfType = applicationContext.getBeansOfType(String.class);
        //获取标注类型注解 bean 名称列表
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(Bean.class);
        //获取所有标注类型注解 bean实例列表
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Bean.class);
        //获取指定名称标注类型注解  依赖注入查找不安全的， 查找不到会报错
        Bean helloWord = applicationContext.findAnnotationOnBean("hello", Bean.class);

        System.out.println("容器中所有String 类型 bean 名称列表："+Arrays.toString(beanNamesForType));
        System.out.println("容器中所有String 类型 bean 实例列表: "+ beansOfType.toString());
        System.out.println("容器中所有标注@bean 注解的 bean名称列表"+Arrays.toString(beanNamesForAnnotation));
        System.out.println("容器中所有标注@bean 注解的 bean实例列表 "+beansWithAnnotation.toString());
        System.out.println("容器中所有标注@bean 注解的 bean实例： "+Arrays.toString(helloWord.value()));





        //关闭 spring 上下文
        applicationContext.close();
    }


    @Bean(value = "hello")
    public String helloWord(){
        return "helloWord value";
    }
    @Bean
    public String message(){
        return "message value";
    }

}
