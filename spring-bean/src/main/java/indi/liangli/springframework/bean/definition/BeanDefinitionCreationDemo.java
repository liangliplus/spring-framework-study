package indi.liangli.springframework.bean.definition;

import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * BeanDefinition 创建方式  ` BeanDefinitionBuilder` 或者AbstractBeanDefinition 的子类
 * @author liangli
 * @Date: 2020/6/11 20:36
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //通过BeanDefinitionBuilder 创建构建 bean的元信息
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",1L);
        beanDefinitionBuilder.addPropertyValue("name","liangli");

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//        beanDefinition.
        //获取的beanDefinition 并不是最终状态，还是对beanDefinition 继续设置属性
        System.out.println(beanDefinition);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(User.class);
        //可配置属性值
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("id",1L)
                .add("name","liangli");

        rootBeanDefinition.setPropertyValues(mutablePropertyValues);

        System.out.println(rootBeanDefinition);





    }

}
