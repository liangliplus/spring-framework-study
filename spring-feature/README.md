

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

