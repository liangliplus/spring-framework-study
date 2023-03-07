package indi.liangli.springframework.dependency.inject.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 * {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}
 * 通过 beanPostProcessor.setAutowiredAnnotationType(InjectUser.class) 设置自定义的注解类型也可以实现依赖注入
 *
 * @author liangli
 * @Date: 2020/6/18 8:40
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
