package indi.liangli.spring.react;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 * 最起码是像我们一样实现 hookOnSubscribe(Subscription subscription) 和 hookOnNext(T value)
 * hookOnSubscribe 方法打印一条语句到标准输出并发出第一个请求。然后 hookOnNext 方法打印一条语句并执行其它的请求
 * @Author liangll
 * @Date 2023/6/5
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
