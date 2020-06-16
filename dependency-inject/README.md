

## 依赖注入的模式和类型 

### 依赖注入模式 

>  分为手动和自动模式 

手动模式： 配置或者编程方式，比如xml 资源配置，java 注解，java api 

自动模式： 实现方提供依赖自动关联方式

### 依赖注入类型

构造器注入，字段注入，setter 方法注入，方法注入，接口回调注入

setter 注入 <property name="user" ref ="user">



## 依赖处理过程
• 基础知识
• 入口 - DefaultListableBeanFactory#resolveDependency
• 依赖描述符 - DependencyDescriptor
• 自定绑定候选对象处理器 - AutowireCandidateResolver


__
有多少种依赖注入的方式？
答：
构造器注入
Setter 注入 多依赖，非强制依赖  
字段注入
方法注入    主要做声明 比如 @Bean ，声明一个bean ，@Autowired 标记在方法上的  
接口回调注入  比如ApplicationContextAware，BeanClassLoaderAware