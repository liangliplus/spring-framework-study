package indi.liangli.springframework.guava;

import com.google.common.eventbus.Subscribe;
import indi.liangli.springframework.event.StudentEvent;

/**
 *
 * @author liangli
 * 2021/7/13 11:35
 */
public class SimpleListener {


    @Subscribe
    public void handle(String msg) {
        System.out.println("接受事件 ：" + msg);
    }

    @Subscribe
    public void handle(User event) {
        System.out.println("接受事件 "+ event);
    }
    @Subscribe
    public void handle(UserExtend event) {
        System.out.println("userExtend" + event);
    }
}
