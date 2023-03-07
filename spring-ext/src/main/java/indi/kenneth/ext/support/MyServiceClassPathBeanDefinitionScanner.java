package indi.kenneth.ext.support;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;

/**
 * 仅扫描 {@link MyService} 注解变为BeanDefinition
 * @Author liangll
 * @Date 2023/3/1
 */
public class MyServiceClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
    public MyServiceClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        //不使用默认过滤（我们自定义filters 规则，例如我们只处理@MyService 注解）
        super(registry, false);
    }

    /*@Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return  metadataReader.getClassMetadata().isIndependent() && metadataReader.getClassMetadata().isConcrete();
    }*/

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        //注册自定义的过滤器
        registerCustomFilters();
        // 在super.doScan 内部会调用 isCandidateComponent，只有返回true 才会加入到Set集合
        // 然后我们只处理接口
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
            //可以在初始化bean 之前修改beanDefinition 例如可以对应beanClass 全部替换为工厂bean
            //设置根据构造函数注入
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

        }
        return beanDefinitionHolders;

    }

    private void registerCustomFilters() {
        addIncludeFilter(new AnnotationTypeFilter(MyService.class));
    }
}
