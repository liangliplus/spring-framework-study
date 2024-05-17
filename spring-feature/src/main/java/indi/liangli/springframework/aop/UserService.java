package indi.liangli.springframework.aop;

import indi.liangli.springframework.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<Long,User> DB = new HashMap<>();

    public boolean save(User user) {
        DB.put(user.getId(),user);
        return true;
    }


    @Log(name = "查询用户日志")
    public User query(Long id) {
//        throw new RuntimeException("xxx");
        return DB.get(id);
    }
}
