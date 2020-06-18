package indi.liangli.springframework.dependency.inject.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 用户组注解，扩展{@link org.springframework.beans.factory.annotation.Qualifier} 元标注Qualifier
 * @author liangli
 * 2020/6/18 9:58
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}
