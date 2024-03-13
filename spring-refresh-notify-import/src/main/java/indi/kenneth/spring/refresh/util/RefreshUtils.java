package indi.kenneth.spring.refresh.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * FIXME 这里不应该提供一个zookeeper() 方法，然要在RefreshUtils 通过Enable注解配置对象来决定调用哪个渠道addRefresh
 */
@Component
public class RefreshUtils  {
    //静态属性无法注入，我们通过方法注入后赋值给它
    private static ApplicationContext applicationContext;

    @Resource
    public  void setApplicationContext(ApplicationContext applicationContext) {
        RefreshUtils.applicationContext = applicationContext;
    }


    public  PutCache zookeeper() {
        return Zookeeper.zookeeper;
    }

    static class  Zookeeper implements  PutCache {
        private static Zookeeper zookeeper = new Zookeeper();

        @Override
        public synchronized void addRefresh(String key, String value) {
            try {
                if (!key.startsWith("/")) {
                    key = "/" + key;
                }
                RefreshUtils.applicationContext.getBean(CuratorFramework.class)
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                        .forPath(key,value.getBytes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

}
