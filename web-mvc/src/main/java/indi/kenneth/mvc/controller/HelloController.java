package indi.kenneth.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kenneth
 * @Date: 2023/3/5
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello,world1";
    }


    @GetMapping("/sayHello")
    public ModelAndView sayHello() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","zhangsan");
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
