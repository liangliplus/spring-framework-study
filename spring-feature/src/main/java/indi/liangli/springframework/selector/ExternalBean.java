package indi.liangli.springframework.selector;

import indi.liangli.springframework.entity.User;
import org.springframework.context.annotation.Bean;

public class ExternalBean  {

    @Bean
    public User user() {
        System.out.println("xxxx");
        return new User();
    }


}
