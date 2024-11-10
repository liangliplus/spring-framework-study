package indi.liangli.spring.react;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 1.mono 和 flux 中对象转换为另外一个对象写法
 * 2.流处理的其他方法
 * 3.需要知道如何把我们对象转换为mono， 例如返回Mono<Boolean> 有哪些方法
 *  3.1 Mono.just(false)
 * @Author liangll
 * @Date 2023/6/5
 */
public class ReactorDemo {


    @Test
    public void testFluxAndMono() {
        //枚举
        Flux<String> seq = Flux.just("foo", "bar", "foobar");
        //放入一个集合
        List<String> list = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(list);

        //subscribe(Consumer<? super T> consumer)  对每个产生的值做一些操作
        //在Reactor中，使用通用 Disposable 接口来表示取消和清理行为
        seq.subscribe(System.out::println);

        //第一个参数范围开始， 第二个参数产生元素个数
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);

        Mono<String> empty = Mono.empty();
        Mono<String> data = Mono.just("foo");

    }


    @Test
    public void testSubscribeValueAndError() {
        //例如我们订阅 处理值也处理错误
        //subscribe(@Nullable Consumer<? super T> consumer, Consumer<? super Throwable> errorConsumer)
        //从1开始 生产4个元素，
        Flux<Integer> range = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) {
                        return i;
                    }
                    throw new RuntimeException("Got to 4");
                });
        range.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error" + error));

        //错误信息号和完成信号是互斥的（不会同时得到两种信号），如果我们要完成消费，必须不能触发错误。
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));


    }

    @Test
    public void testSubscribe2() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }


    @Test
    public void testBaseSubscribe() {

        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("error" + error),
                () -> System.out.println("done"),
                s -> s.request(10));
        ints.subscribe(ss);
    }

    @Test
    public void testSinkGenerate() {
        //3 的乘法口诀表
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
    }


    @Test
    public void testSink2() {
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));



        /*Flux<String> bridge = Flux.create(sink -> {
            myEventProcessor.register(
                    new MyEventListener<String>() {

                        public void onDataChunk(List<String> chunk) {
                            for(String s : chunk) {
                                sink.next(s);
                            }
                        }

                        public void processComplete() {
                            sink.complete();
                        }
                    });
        });*/
    }
}
