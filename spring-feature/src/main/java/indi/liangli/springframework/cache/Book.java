package indi.liangli.springframework.cache;

/**
 * @author liangli
 * @Date: 2021/7/4 12:59
 */
public class Book {
    private Integer id;
    private String name;

    public Book() {
    }

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
