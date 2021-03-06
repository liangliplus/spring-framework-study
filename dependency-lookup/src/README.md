# Spring 依赖查找 



##单一类型依赖查找 

> 单一类型总结 ： 根据名称，类型，名称+类型（里面会涉及覆盖参数，延迟等特性 ）

*  单一类型依赖查找接口 - BeanFactory
   *  根据 Bean 名称查找
      *  getBean(String)
      *  Spring 2.5 覆盖默认参数：getBean(String,Object...)
*  根据 Bean 类型查找
   *  Bean 实时查找
      *  Spring 3.0 getBean(Class)
      *  Spring 4.1 覆盖默认参数：getBean(Class,Object...)
   *  Spring 5.1 Bean 延迟查找
   *  getBeanProvider(Class)
   *  getBeanProvider(ResolvableType)
*  根据 Bean 名称 + 类型查找：getBean(String,Class)



## 集合类型依赖查找

> 根据bean 类型获取 bean 名称列表或者实例列表 ， 可以根据注解类型查找（在扩展时比较有用 ）

* 集合类型依赖查找接口 - ListableBeanFactory
  * 根据 Bean 类型查找
    * 获取同类型 Bean 名称列表
      * getBeanNamesForType(Class)
      * Spring 4.2 getBeanNamesForType(ResolvableType)
  * 获取同类型 Bean 实例列表
    * getBeansOfType(Class) 以及重载方法
  * 通过注解类型查找
    * Spring 3.0 获取标注类型 Bean 名称列表
      * getBeanNamesForAnnotation(Class<? extends Annotation>)
    * Spring 3.0 获取标注类型 Bean 实例列表
      * getBeansWithAnnotation(Class<? extends Annotation>)
    * Spring 3.0 获取指定名称 + 标注类型 Bean 实例
      * findAnnotationOnBean(String,Class<? extends Annotation>)



## 层次性依赖查找

* 层次性依赖查找接口 - HierarchicalBeanFactory 
  * 双亲 BeanFactory：getParentBeanFactory() 
  * 层次性查找 
    * 根据 Bean 名称查找
      * 基于 containsLocalBean 方法实现
      * 根据 Bean 类型查找实例列表
        *  单一类型：BeanFactoryUtils#beanOfType
        * 集合类型：BeanFactoryUtils#beansOfTypeIncludingAncestors
    * 根据 Java 注解查找名称列表
      *  BeanFactoryUtils#beanNamesForTypeIncludingAncestors





## 延迟依赖查找

* Bean 延迟依赖查找接口
  * org.springframework.beans.factory.ObjectFactory
  * org.springframework.beans.factory.ObjectProvider 
    * Spring 5 对 Java 8 特性扩展
      * 函数式接口
        * getIfAvailable(Supplier) 
        * ifAvailable(Consumer)
      * Stream 扩展 - stream() 

