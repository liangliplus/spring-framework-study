package indi.liangli.springframework.cache;

import com.google.common.collect.Sets;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author liangli
 * 2021/11/29 16:23
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public ConcurrentMapCache concurrentMapCache() {
        return new ConcurrentMapCache("books");
    }

    @Bean(name = "cacheManager")
    public CacheManager cacheManager(ConcurrentMapCache cache) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Sets.newHashSet(cache));
        return cacheManager;
    }
}
