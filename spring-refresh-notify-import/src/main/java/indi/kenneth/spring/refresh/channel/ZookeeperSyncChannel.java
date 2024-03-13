package indi.kenneth.spring.refresh.channel;

import indi.kenneth.spring.refresh.anno.RefreshCache;
import indi.kenneth.spring.refresh.notify.RefreshCacheService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 这里需要标记@Configuration
 * 标记之后方法就会被代理
 */
public class ZookeeperSyncChannel implements SyncDataChannel,ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperSyncChannel.class);
    /**
     * 保留结点数
     */
    public static final int RETAIN_NODES = 10;

    @Autowired
    private CuratorFramework curatorFramework;

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, RefreshCacheService> serviceMap = applicationContext.getBeansOfType(RefreshCacheService.class);
        if (CollectionUtils.isEmpty(serviceMap)) {
            this.LOGGER.info("no have RefreshCacheService impl");
            return;
        }
        for (RefreshCacheService refreshCacheService : serviceMap.values()) {
            try {
                this.watch(refreshCacheService);
                LOGGER.info("{} load finish",refreshCacheService.getClass().getSimpleName());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void watch(RefreshCacheService refreshCacheService) throws Exception {
        RefreshCache refreshCache = refreshCacheService.getClass().getAnnotation(RefreshCache.class);
        if (refreshCache == null) {
            LOGGER.warn("refreshCacheService subClass:{} not mark @RefreshCache",refreshCacheService.getClass().getSimpleName());
            return;
        }
        String key = parseKey(refreshCache);
        //这里可以优化为线程池。
        this.autoClean(key, refreshCache);

        // 创建节点监听
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,key,true);
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                if (event.getType().equals(Type.CHILD_ADDED)) {
                    refreshCacheService.refresh(new String(event.getData().getData()));
                }
            }
        });

        pathChildrenCache.start(StartMode.BUILD_INITIAL_CACHE);
    }


    private String parseKey(RefreshCache refreshCache) {
        String resolveKey = this.applicationContext.getEnvironment().resolvePlaceholders(refreshCache.key());
        if (!resolveKey.startsWith("/")) {
            resolveKey = "/" + resolveKey;
        }
        return resolveKey;
    }

    private void autoClean(String key, RefreshCache refreshCache) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(refreshCache.refreshTime());
                    List<String> nodeList = this.curatorFramework.getChildren().forPath(key);
                    Collections.sort(nodeList);
                    //仅最后10个节点，防止节点无限增多
                    for (int i = 0; i < nodeList.size() - RETAIN_NODES; i++) {
                        this.curatorFramework.delete().forPath(key + "/" + nodeList.get(i));
                    }
                } catch (Exception e) {
                    this.LOGGER.error("zk autoClean node error: key:{}", key, e);
                }

            }
        }).start();
    }



}
