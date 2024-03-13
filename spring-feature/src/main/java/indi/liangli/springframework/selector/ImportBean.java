package indi.liangli.springframework.selector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 通过@Import注解导入我们需要的类
 */
@Configuration
@Import(DeferredImportSelectorDemo.class)
public class ImportBean {
}
