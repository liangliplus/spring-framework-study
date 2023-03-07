package indi.kenneth.ext.support;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Optional;

/**
 * @Author liangll
 * @Date 2023/3/1
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    /**
     * 注册beanDefinition的入口
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyService.class.getName()));
        String[] backPackages = annotationAttributes.getStringArray("basePackages");

        if (backPackages == null || backPackages.length <= 0) {
            return;
        }

        MyServiceClassPathBeanDefinitionScanner scanner = new MyServiceClassPathBeanDefinitionScanner(registry);
        Optional.ofNullable(resourceLoader).ifPresent(scanner::setResourceLoader);

        scanner.scan(backPackages);






    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
