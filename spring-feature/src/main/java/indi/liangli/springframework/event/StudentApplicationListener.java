package indi.liangli.springframework.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author liangli
 * 2021/7/13 10:44
 */
@Component
public class StudentApplicationListener implements ApplicationListener<StudentEvent> {
    @Override
    public void onApplicationEvent(StudentEvent event) {
        System.out.println(event.getSource());
    }
}
