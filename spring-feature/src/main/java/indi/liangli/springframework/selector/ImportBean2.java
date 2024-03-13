package indi.liangli.springframework.selector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 通过@Import注解导入我们需要的类
 * 我们这里的ExternalBean 类上没有标记任何注解，它是如何处理为BeanDefinition的
 */
@Configuration
@Import(ExternalBean.class)
public class ImportBean2 {
}
