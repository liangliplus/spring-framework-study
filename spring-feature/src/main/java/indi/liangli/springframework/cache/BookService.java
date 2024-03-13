package indi.liangli.springframework.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangli
 */
@Service
public class BookService {
    @Autowired
    ConcurrentMapCache concurrentMapCache;


    public final Map<Integer, Book> db;

    public BookService() {
        db = new HashMap<>();
        db.put(1,new Book(1,"java programming"));
        db.put(2,new Book(2,"c++ programming"));
    }

    @Cacheable(value = "books", key = "#id")
    public Book queryById(Integer id) {
        System.out.println("in .............");
        return db.get(id);
    }

    @Cacheable(value = "books", key = "#p0")
    public Book find(Integer id) {
        return db.get(id);
    }

    @Cacheable(value = "books", key = "#book.id")
    public Book find(Book book) {
        return db.get(book.getId());
    }


    /**
     * CachePut 是把返回值作为缓存中元素
     * @param book
     * @return
     */
    @CachePut(value = "books",key = "#book.id")
    public Book addBook(Book book) {
         db.put(book.getId(),book);
         return book;
    }



    @CacheEvict(value = "books",key = "#id")
    public int deleteBook(Integer id) {
        db.remove(id);
        return 1;
    }


    public ConcurrentMapCache getConcurrentMapCache() {
        return concurrentMapCache;
    }
}
