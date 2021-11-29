package indi.liangli.springframework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangli
 * @Date: 2021/7/4 12:57
 */
@Configuration
public class CacheTest {




    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CacheTest.class);
        applicationContext.scan("indi.liangli.springframework.cache");

        applicationContext.refresh();

        BookService bean = applicationContext.getBean(BookService.class);
        Integer id = 1;
        Book book = bean.queryById(id);
        //删除id 为1的book
        int i = bean.deleteBook(id);
        bean.addBook(new Book(1,"design pattern"));
        bean.queryById(id);
        System.out.println(bean.getConcurrentMapCache());

        applicationContext.close();
    }




}
