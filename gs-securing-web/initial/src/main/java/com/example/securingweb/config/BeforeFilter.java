package com.example.securingweb.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * spring security添加自定义filter
 *
 * @author liangli
 * @Date: 2020/9/13 21:39
 */
public class BeforeFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("this is a filter before  UsernamePasswordAuthenticationFilter");
        chain.doFilter(request,response);
    }
}
