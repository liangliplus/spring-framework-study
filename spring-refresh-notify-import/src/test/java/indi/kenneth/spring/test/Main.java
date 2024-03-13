package indi.kenneth.spring.test;

import indi.kenneth.spring.refresh.anno.EnableRefreshCache;
import indi.kenneth.spring.refresh.channel.ChannelEnum;
import indi.kenneth.spring.refresh.util.RefreshUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Random;

/**
 * 目前实现需要主类上标记`@EnableRefreshCache`
 */
@PropertySource(value = "classpath:zookeeper.properties")
@EnableRefreshCache(channel = ChannelEnum.ZK)
@ComponentScan(basePackages = {"indi.kenneth.spring.test"})
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(Main.class);

        String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        RefreshUtils bean = configApplicationContext.getBean(RefreshUtils.class);
        while (true) {
            bean.zookeeper().addRefresh("/test/refresh/",new Random().nextInt(10000)+"");
            Thread.sleep(3000);
        }


    }
}
