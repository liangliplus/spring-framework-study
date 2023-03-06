创建子模块web项目注意点
1.创建maven 类型为web的项目，创建成功会有webapp,并且打包是war
2.添加java 和 resources 目录
3.添加jetty 插件 （注意jetty插件，servlet-api，以及spring-webmvc 版本对应）

```xml
<!-- jetty插件 -->
<plugin>
    <groupId>org.mortbay.jetty</groupId>
    <artifactId>maven-jetty-plugin</artifactId>
    <version>6.1.25</version>
    <configuration>
        <connectors>
            <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
                <port>8081</port>
                <maxIdleTime>60000</maxIdleTime>
            </connector>
        </connectors>
        <contextPath>/${project.artifactId}</contextPath>
        <scanIntervalSeconds>5</scanIntervalSeconds>
    </configuration>
</plugin>
```

4.mvn clean jetty:run 启动jetty服务

