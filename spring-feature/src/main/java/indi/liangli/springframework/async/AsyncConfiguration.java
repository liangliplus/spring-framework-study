package indi.liangli.springframework.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author liangll
 * @Date 2023/4/28
 */
@EnableAsync
@Configuration
public class AsyncConfiguration {

    @Bean
    public Executor asyncPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 500
                , 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000));
//        executor.setThreadFactory();
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
