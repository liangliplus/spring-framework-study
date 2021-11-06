package indi.liangli.springframework.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 *
 * @author liangli
 * 2021/8/26 11:32
 */
public class SpringTaskDemo {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TaskConfiguration.class);


        applicationContext.scan("indi.liangli.springframework.task");
        applicationContext.refresh();

        System.in.read();
    }
}
