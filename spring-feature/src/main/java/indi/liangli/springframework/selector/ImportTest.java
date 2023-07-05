package indi.liangli.springframework.selector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 *
 * {@link DeferredImportSelector} 使用
 * 我们可以定义一个导入资源的Selector类，继承 DeferredImportSelector
 * 然后重写
 * 1.{@link DeferredImportSelector#selectImports(AnnotationMetadata)}
 * 2.{@link DeferredImportSelector#getImportGroup()} （方法是可选的）
 *
 * 如果需要Group ， 可以声明一个内部类实现Group接口
 * 重写
 * 可以对调用selector 的selectImports 方法获取到待导入的列表，然后执行相应操作（过滤等 ）
 * {@link org.springframework.context.annotation.DeferredImportSelector.Group#selectImports(AnnotationMetadata)}
 * 最后通过getImportGroup 返回一个List<Entry></>
 * {@link DeferredImportSelector.Group#selectImports}
 *
 *
 * 最后这些Entry 会被变为BeanDefinition， 然后再容器启动时，单例Bean就会被创建
 * {@link org.springframework.context.annotation.ConfigurationClassParser} 来处理@Import实现
 * 注意@Configuration 也是一个@Component注解
 * 核心方法 {@link org.springframework.context.annotation.ConfigurationClassParser.DeferredImportSelectorHandler#handle(org.springframework.context.annotation.ConfigurationClass, org.springframework.context.annotation.DeferredImportSelector)}
 *
 * 主要搞清楚一点：就是不是直接调用 DeferredImportSelector#selectImports
 * 如果有group情况，是先调用group#process --》 然后开发者在里面调用 DeferredImportSelector#selectImports
 * 并把结果保存起来，最后通过Group#selectImports 返回
 *
 *
 * @Import 的语义一般是用来导入配置类（标记@Configuration注解的类） ， 或者通过导入实现ImportSelector的类
 * {@link @Import}
 *
 *
 *
 */
public class ImportTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ImportBean.class);
        //我们的externalBean就导入到容器中了
        System.out.println(configApplicationContext.getBean(ExternalBean.class));


    }

}
