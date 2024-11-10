package indi.kenneth.mvc.config;

import indi.kenneth.mvc.config.support.CustomizeRequestArgumentResolver;
import indi.kenneth.mvc.config.support.CustomizeResponseHandler;
import indi.kenneth.mvc.config.support.MyHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 标记了@EnableWebMvc 才会解析 webMvcConfigurer
 * 目前配置了不生效
 */
@EnableWebMvc
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private MyHandlerInterceptor interceptor;
    @Autowired
    CustomizeRequestArgumentResolver requestArgumentResolver;
    @Autowired
    CustomizeResponseHandler responseHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(interceptor);
        registration.addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestArgumentResolver);
    }


    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(responseHandler);
    }
}
