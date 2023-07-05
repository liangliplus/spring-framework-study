package indi.kenneth.spring.refresh.notify;

public interface RefreshCacheService<T> {
    /**
     * 当收到刷新请求，调用refresh通知监听者
     * @param t
     */
    void refresh(T t);


    void init();

}
