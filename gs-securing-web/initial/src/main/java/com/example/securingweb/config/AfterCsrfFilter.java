package com.example.securingweb.config;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author liangli
 * @Date: 2020/9/13 21:42
 */
public class AfterCsrfFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("This is a filter after CsrfFilter");
        chain.doFilter(request,response);
    }
}
