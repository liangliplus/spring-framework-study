package indi.liangli.springframework.async;

import com.google.common.collect.Lists;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author liangll
 * @Date 2023/4/28
 */
@Service
public class AsyncService {


    /**
     * 标记Async 注解， 返回实现Future 对象的 AsyncResult
     * @return
     */
    @Async(value = "asyncPool")
    public Future<List<Integer>> asyncRequest() {
        try {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            Thread.sleep(3);
            int i = random.nextInt(0, 1000);
            List<Integer> list =  Lists.newArrayList();
            return new AsyncResult(list);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
