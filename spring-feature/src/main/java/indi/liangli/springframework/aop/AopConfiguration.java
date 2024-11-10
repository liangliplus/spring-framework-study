package indi.liangli.springframework.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "indi.liangli.springframework.aop")
@EnableAspectJAutoProxy
public class AopConfiguration {
}
