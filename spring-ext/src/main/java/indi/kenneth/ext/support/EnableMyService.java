package indi.kenneth.ext.support;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface EnableMyService {

    /**
     * 默认扫描包路径
     * @return
     */
    String[] basePackages() default {};

}
