package indi.liangli.springframework;

import org.springframework.util.StopWatch;

/**
 * @author kenneth
 * @Date: 2023/2/22
 */
public class App {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch(App.class.getSimpleName());
        stopWatch.start("test");
        System.out.println("手动调用");
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

    }
}
