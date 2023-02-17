自动装配的模式
对于spring 有四种  （在xml 中更能体现）

默认no 

byName 

byType

constructor



```xml
<!-- 默认是不自动装配， 需要手动指定constructor-arg 或者 properties 注入-->
<!-- 自动装配可以省略这个步骤，但是对下面的基本类型， string 没有作用  -->
<bean class="xxxx.UserController">
    <constructor-arg index="0" ref="address"/>
    <constructor-arg index="1" ref="email"/>
</bean>    
<bean id="address" class="xxAddress"/>
<bean id="email" class="xxxEmail"/>


<!--使用自动装配 -->
<bean class="xxx.UserController" autowire="constructor"/>
```





spring bean 的作用域

单例，原型，对于spring 而言本质只有两种 

```xml
<bean id="accountService" class="com.something.DefaultAccountService" scope="singleton"/>
```

基于http 场景 衍生出 请求（request）、会话 （Session）、应用程序（绑定ServletContext）

```java
@RequestScope
@Component
public class LoginAction {
    // ...
}
```



自定义 bean 的特定

> 自定义bean 的

- [生命周期回调](https://springref.com/docs/spring-framework/core#beans-factory-lifecycle)
- [`ApplicationContextAware`和`BeanNameAware`](https://springref.com/docs/spring-framework/core#beans-factory-aware)
- [其他`Aware`接口](https://springref.com/docs/spring-framework/core#aware-list)

Spring`InitializingBean`和`DisposableBean`接口





org.springframework.context.Lifecycle
优雅关闭 Runtime.getRuntime().addShutdownHook


Aware 接口
BeanDefiniton 继承关系。 

模式相对固网，可以不需要扩展applicationContext
使用相应的扩展点进行扩展。 


你想在 Spring 容器完成实例化、配置和初始化 bean 之后实现一些自定义逻辑，你可以插入一个或多个自定义BeanPostProcessor实现 
例如Dubbo 的 处理Reference 的 ReferenceAnnotationBeanPostProcessor 

 BeanPostProcessor 的 postProcessBeforeInitialization 
 在init 方法之前执行  
 并在初始化回调之后, postProcessAfterInitialization  开始调用， 
 bean后处理器通常检查回到接口， 或者它用代理包装bean。 


 也就是这段逻辑在getBean 里面，因为单例bean 的初始化是在getBean 中的 

 编程方式添加bean后置处理 ，在refersh 之前。
 ConfigurableBeanFactory使用该addBeanPostProcessor


 AutowiredAnnotationBeanPostProcessor
 配置bean 的元数据信息，都直接使用BeanRegistry 做的。 
 BeanDefinitionRegistryPostProcessor

 PropertySourcesPlaceholderConfigurer 常用的占位符处理。 





org.springframework.context.annotation.PropertySource 实现





自定义实例化逻辑FactoryBean

获取FactoryBean 本身，getBean的时候，在beanId 前面+ &

```java
public Fa

getBean("myBean")
getBean("&myBean")
    
```



AutowiredAnnotationBeanPostProcessor  依赖注入的原理

`<context:annotation-config/>`  默认注册了

ConfigurationClassPostProcessor 

AutowiredAnnotationBeanPostProcessor

CommonAnnotationBeanPostProcessor  jsr-250  @PostConstruct, @PreDestroy and @Resource





```xml 
//开启事务支持 @Transactional 
<tx:annotation-driven>
```





可以将autowired 应用在任意名称和多个参数的方法