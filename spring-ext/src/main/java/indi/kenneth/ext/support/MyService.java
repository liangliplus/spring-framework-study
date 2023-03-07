package indi.kenneth.ext.support;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyService {

    /**
     * 指定service 名称
     * @return
     */
    String value() default  "";

}
