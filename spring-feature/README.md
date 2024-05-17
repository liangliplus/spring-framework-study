## spring aop

> 需要理解joinPoint 的方法 和 切点

引入spring-aop 和 aspectj 的依赖 

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.6</version>
</dependency>


<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>5.2.20.RELEASE</version>
</dependency>
```



编写代码 

1.启用spring切面 `@EnableAspectJAutoProxy`

1.通过注解声明一个aop 切面 `Aspect` 和 标记为spring bean`@component`

2.声明通知 `@Before,@After,@Around 等` ， 在切面类方法添加特定逻辑

```java
//scope ：方法作用域，如public,private,protect
//returnt-type：方法返回值类型
//fully-qualified-class-name：方法所在类的完全限定名称
//parameters 方法参数
execution(<scope> <return-type> <fully-qualified-class-name>..*(parameters)) 
```



> 注意，连接点对象必须放在第一个参数
>
> Signature 可以强转为 MethodSignature

例如环绕通知，使用`ProceedingJoinPoint` 

```java
public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    //business logic ...
}
```

方法执行成功后通知 `@afterReturning`

```java
//可以获取到结果对象 operateLog 必须和参数中 operateLog 一致
@AfterReturning(returning = "result", value = "@annotation(operateLog)")
public void doAfterReturning(JoinPoint joinPoint, Object result,OperateLog operateLog) {
    
}
```











## spring cache

`@Cacheable`  支持spring EL 表达式，  可以通过 `#参数名` 或者 `#p 参数下标`  ,还可以使用root对象属性，包含`methodName`, `args` 等 ， condition 属性指定发生条件

```java
@Cacheable(value="users", key="#id")

   public User find(Integer id) {

      returnnull;

   }

 

   @Cacheable(value="users", key="#p0")

   public User find(Integer id) {

      returnnull;

   }

 

   @Cacheable(value="users", key="#user.id")

   public User find(User user) {

      returnnull;

   }

 

   @Cacheable(value="users", key="#p0.id")
   public User find(User user) {
      return null;

   }
```



`@CachePut`  对于标记`@cacheable` 的方法，每次执行前都会检查cache 是否存在相同key 元素，存在不在执行该方法，  `@CachePut` 不会检查是否存在。

`CacheEvict`  标注在需要清除缓存元素得方法或者类上 

`Caching` 组合注解



## spring task





## spring retry 

