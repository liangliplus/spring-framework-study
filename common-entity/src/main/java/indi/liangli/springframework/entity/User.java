package indi.liangli.springframework.entity;

public  class User {
    private Long id;
    private String name;

    public User() {}
    public User(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User createUser(){
        User user = new User();
        user.setId(1L);
        user.setName("kenneth");
        return user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
