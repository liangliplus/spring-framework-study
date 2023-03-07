package indi.kenneth.ext.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @Author liangll
 * @Date 2023/3/1
 */
public class App {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class);
        ctx.refresh();


        for (String beanDefinitionName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

        IMyService myServiceImpl = (IMyService) ctx.getBean(IMyService.class);

        System.out.println(myServiceImpl);


    }
}
