package indi.kenneth.spring.refresh.util;

/**
 * 该类不暴露给用户
 */
public interface PutCache {

    /**
     * 添加刷新事件
     * @param key
     * @param value
     */
    void addRefresh(String key,String value);
}
