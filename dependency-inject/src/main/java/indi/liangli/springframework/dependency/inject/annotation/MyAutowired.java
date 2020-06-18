package indi.liangli.springframework.dependency.inject.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 *
 * 自定义注解 （元标注 @Autowired）
 * @author liangli
 * @Date: 2020/6/18 8:37
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {
}
