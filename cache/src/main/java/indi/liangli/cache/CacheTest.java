package indi.liangli.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangli
 * @Date: 2021/7/4 12:57
 */
@EnableCaching
@Configuration
public class CacheTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CacheTest.class);
        applicationContext.scan("indi.liangli.cache");

        applicationContext.refresh();;

        BookService bean = applicationContext.getBean(BookService.class);
        Book book = bean.queryBook(1);
        Book book1 = bean.queryBook(1);

        applicationContext.close();
    }




}
