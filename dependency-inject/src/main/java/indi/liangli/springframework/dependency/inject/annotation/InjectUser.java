package indi.liangli.springframework.dependency.inject.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 * {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}
 * @author liangli
 * @Date: 2020/6/18 8:40
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
