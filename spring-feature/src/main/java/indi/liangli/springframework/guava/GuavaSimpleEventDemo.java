package indi.liangli.springframework.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author liangli
 * 2021/7/13 11:36
 */
public class GuavaSimpleEventDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.post("hello simple guava listener");
        eventBus.post(new User("张三"));
        //父类和 userExtend 都能收到
        eventBus.post(new UserExtend("张三","13131xxxx"));
    }



}
