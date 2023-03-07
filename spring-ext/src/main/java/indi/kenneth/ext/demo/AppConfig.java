package indi.kenneth.ext.demo;

import indi.kenneth.ext.support.EnableMyService;
import org.springframework.context.annotation.Configuration;

/**
 * @Author liangll
 * @Date 2023/3/1
 */
@Configuration
@EnableMyService(basePackages = {"indi.kenneth.ext.demo"})
public class AppConfig {
}
