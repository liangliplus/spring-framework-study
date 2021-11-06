package indi.liangli.springframework.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author liangli
 * 2021/7/13 10:43
 */
public class StudentEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     * @param source the object on which the event initially occurred or with
     * which the event is associated (never {@code null})
     */
    public StudentEvent(Object source) {
        super(source);
    }
}
