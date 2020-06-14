## 依赖处理过程
• 基础知识
• 入口 - DefaultListableBeanFactory#resolveDependency
• 依赖描述符 - DependencyDescriptor
• 自定绑定候选对象处理器 - AutowireCandidateResolver



有多少种依赖注入的方式？
答：
构造器注入
Setter 注入 多依赖，非强制依赖  
字段注入
方法注入    主要做声明 比如 @Bean ，声明一个bean ，@Autowired 一般很少标记在方法上，当然式可以标记在方法上的  
接口回调注入  比如setApplicationContext