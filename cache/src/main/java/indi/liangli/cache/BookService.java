package indi.liangli.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author liangli
 * @Date: 2021/7/4 12:59
 */
@Service
public class BookService {


    @Cacheable({"book"})
    public Book  queryBook(Integer id) {
        System.out.println("in ..........");
        if (id == 1) {
            return new Book(1,"2");
        }
        return null;

    }
}
