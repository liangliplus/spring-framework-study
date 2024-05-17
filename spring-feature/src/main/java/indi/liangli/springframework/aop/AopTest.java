package indi.liangli.springframework.aop;

import indi.liangli.springframework.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AopConfiguration.class);
        applicationContext.refresh();

        UserService bean = applicationContext.getBean(UserService.class);
        User user = new User("张三");
        boolean save = bean.save(user);

        bean.query(user.getId());

        applicationContext.close();
    }
}
