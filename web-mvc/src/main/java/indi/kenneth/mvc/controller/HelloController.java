package indi.kenneth.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenneth
 * @Date: 2023/3/5
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello,world1";
    }
}
