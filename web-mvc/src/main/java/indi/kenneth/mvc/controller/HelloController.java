package indi.kenneth.mvc.controller;

import indi.kenneth.mvc.common.Result;
import indi.liangli.springframework.ioc.overview.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * ModelAttribute �����ñ��ʾ��Ǽ�Model ���attribute����
 * �������ٷ��������ϣ��������ڵ���model.addAttribute
 * �����һ���������ModelAttribute�� ���е����󷽷��ͻ����ø�
 *
 * @author kenneth
 * @Date: 2023/3/5
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }


    @ResponseBody
    @GetMapping("/hello-json")
    public Result<User> helloJson() {
        User user = new User();
        user.setId(2L);
        user.setName("lishi");
        return Result.success(user);
    }


    /**
     * ModelAttribute("user") ����ڷ����ϡ�
     * ���� model.addAttribute("user",new User(1,zhangsan));
     * �����������@RequestMapping �ķ������и�������
     * �������úô����Դ��һЩ�������ԡ�
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
