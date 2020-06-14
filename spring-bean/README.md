• Bean 实例化（Instantiation） 
 • 常规方式
    • 通过构造器（配置元信息：XML、Java 注解和 Java API ）
    • 通过静态工厂方法（配置元信息：XML 和 Java API ） 
    • 通过 Bean 工厂方法（配置元信息：XML和 Java API ）
    • 通过 FactoryBean（配置元信息：XML、Java 注解和 Java API ） 
 
 • 特殊方式
    • 通过 ServiceLoaderFactoryBean（配置元信息：XML、Java 注解和 Java API ）
    • 通过 AutowireCapableBeanFactory#createBean(java.lang.Class, int, boolean)
    • 通过 BeanDefinitionRegistry#registerBeanDefinition(String,BeanDefinition)