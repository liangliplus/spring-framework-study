package indi.liangli.springframework.async;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author liangll
 * @Date 2023/4/28
 */
public class AsyncApp {

    public static void main(String[] args)  {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AsyncConfiguration.class);
        context.scan("indi.liangli.springframework.async");

        context.refresh();


        AsyncService bean = context.getBean(AsyncService.class);
        for (int j = 0; j < 1000; j++) {
            Map<Integer,Future<List<Integer>>>  map = Maps.newHashMap();
            for (int i = 0; i < 4; i++) {
                Future<List<Integer>> listFuture = bean.asyncRequest();
                map.put(i,listFuture);
            }

            List<Integer> res = new ArrayList<>();
            map.forEach((index,future) -> {
                try {
                    List<Integer> integers = future.get(100, TimeUnit.MILLISECONDS);
                    res.addAll(integers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            System.out.println(res);
        }
        System.out.println("done");


    }
}
