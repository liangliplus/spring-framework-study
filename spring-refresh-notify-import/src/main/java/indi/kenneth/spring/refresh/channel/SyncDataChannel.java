package indi.kenneth.spring.refresh.channel;

import indi.kenneth.spring.refresh.notify.RefreshCacheService;

public interface SyncDataChannel {
    /**
     * 为refreshCacheService 注册监听
     * @param refreshCacheService
     */
    void watch(RefreshCacheService refreshCacheService) throws Exception;
}
