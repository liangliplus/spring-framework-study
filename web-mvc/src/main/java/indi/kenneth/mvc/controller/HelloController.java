package indi.kenneth.mvc.controller;

import indi.kenneth.mvc.common.Result;
import indi.kenneth.mvc.common.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * ModelAttribute 的作用本质就是简化Model 添加attribute属性
 * 例如标记再方法参数上，就类似于调用model.addAttribute
 * 如果给一个方法标记ModelAttribute， 所有的请求方法就会设置该
 *
 *
 * DispatcherServelt 会使用默认的DispatcherServlet.properties 配置作为各个组件的默认类
 * @author kenneth
 * @Date: 2023/3/5
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    /**
     * http://localhost:8081/web-mvc//hello-json
     * @return
     */
    @ResponseBody
    @GetMapping("/hello-json")
    public Result<User> helloJson() {
        User user = new User();
        user.setId(2L);
        user.setName("lishi");
        return Result.success(user);
    }


    /**
     * ModelAttribute("user") 标记在方法上。
     * 类似 model.addAttribute("user",new User(1,zhangsan));
     * 这样其他标记@RequestMapping 的方法都有该作用域
     * 这样做得好处可以存放一些配置属性。
     * @return
     */
    @ModelAttribute("user")
    public User  addUser() {
        User user = new User();
        user.setId(1L);
        user.setName("zhangsan");
        return user;
    }
}

