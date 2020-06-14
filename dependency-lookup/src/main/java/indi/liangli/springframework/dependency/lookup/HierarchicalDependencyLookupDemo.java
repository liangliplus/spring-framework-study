package indi.liangli.springframework.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找实例
 *
 * @author liangli
 * @Date: 2020/6/13 20:04
 */
public class HierarchicalDependencyLookupDemo {
    public static void main(String[] args) {
        //创建一个应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类型ObjectProviderDemo 作为配置类 （Configuration Class）
        applicationContext.register(ObjectProviderDemo.class);

        // ConfigurableListableBeanFactory --》 ConfigurableBeanFactory --》 HierarchicalBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();

        //设置当前BeanFactory 的parent BeanFactory
        beanFactory.setParentBeanFactory(parentBeanFactory);

        //org.springframework.beans.factory.HierarchicalBeanFactory.containsLocalBean 判断当前bean Factory 是否包含该bean
        // 比如我们取查找父容器  user ，返回 false
        displayContainsLocalBean(beanFactory,"user");
        displayContainsLocalBean(parentBeanFactory,"user");


        //通过递归的方式实现双亲委派
        displayContainsBean(beanFactory,"user");
        displayContainsBean(parentBeanFactory,"user");


        //启动 spring 上下文
        applicationContext.refresh();

        //依赖查找集合对象



        //关闭 spring 上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    /**
     * spring 的实现时当前BeanFactory 中没有，采取父BeanFactory 查找
     * 我们这里采用和java 一样的双亲委派， 先找父容器，父容器找不到在找子容器
     * <code>
     *     if (parentBeanFactory != null && !containsBeanDefinition(beanName))
     * </code>
     * @param beanFactory
     * @param beanName
     * @return
     */
    private static Boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        //隐含 不等于null 的判断，因为如果等于null 就不会是 HierarchicalBeanFactory ， 这个条件 相当于parentBeanFactory != null &&  parentBeanFactory instanceof HierarchicalBeanFactory
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);

            //如果有
            if(containsBean(parentHierarchicalBeanFactory,beanName)){
                return true;
            }
        }

        return beanFactory.containsLocalBean(beanName);


    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name ： %s]   :%s \n",
                beanFactory,beanName,beanFactory.containsLocalBean(beanName));


    }

    private static ConfigurableListableBeanFactory createParentBeanFactory(){
        //创建BeanFactory 容器默认实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //XML 配置文件 Classpath路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;



    }

}
