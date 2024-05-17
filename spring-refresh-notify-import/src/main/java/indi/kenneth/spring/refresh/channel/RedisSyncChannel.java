package indi.kenneth.spring.refresh.channel;

import indi.kenneth.spring.refresh.anno.RefreshCache;
import indi.kenneth.spring.refresh.notify.RefreshCacheService;
import indi.kenneth.spring.refresh.util.TimestampUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 该类会通过BeanDefinitionRegistry 进行注册
 */
public class RedisSyncChannel implements SyncDataChannel, DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSyncChannel.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private List<Thread> threadList = new ArrayList<>();

    @Override
    public void watch(RefreshCacheService refreshCacheService) throws Exception {
        RefreshCache refreshCache = refreshCacheService.getClass().getAnnotation(RefreshCache.class);
        if (refreshCache == null) {
            LOGGER.warn("refreshCacheService subClass:{} not mark @RefreshCache", refreshCacheService.getClass().getSimpleName());
            return;
        }
        String key = refreshCache.key();

        //TODO 清理key
        new Thread(() -> {
            long start = TimestampUtil.get();
            while (Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(refreshCache.refreshTime());
                    long end = TimestampUtil.get();
                    Set<String> valueSet = stringRedisTemplate.opsForZSet().rangeByScore(key, start, end);
                    if (valueSet != null && valueSet.size() > 0) {
                        for (String data : valueSet) {
                            refreshCacheService.refresh(data);
                        }
                    }
                    //继续下一次循环
                    start = end;
                } catch (InterruptedException e) {
                    LOGGER.info("InterruptedException happend");
                    //出现InterruptedException 会复位中断标记
                    //在阻塞的时候被中断过，无法响应，此处通过break 跳出循环
                    break;
                } catch (Exception e) {
                    LOGGER.error("refreshCache sync data error, key={} }", key, e);
                }
            }
        }).start();
    }

    @Override
    public void destroy() throws Exception {
        for (Thread thread : threadList) {
            try {
                thread.interrupt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
