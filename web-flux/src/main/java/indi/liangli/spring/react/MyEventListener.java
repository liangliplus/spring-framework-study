package indi.liangli.spring.react;

import java.util.List;

/**
 * @Author liangll
 * @Date 2023/6/5
 */
public interface MyEventListener<T> {
    void onDataChunk(List<T> chunk);
    void processComplete();
}
