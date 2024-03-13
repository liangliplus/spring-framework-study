package indi.kenneth.spring.refresh.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RefreshCache {
    /**
     * 刷新的key
     * @return
     */
    String key();

    /**
     * 间隔刷新毫秒数
     * @return
     */
    long refreshTime() default 60 * 1000;


}
