//package indi.kenneth.mvc.config;
//
//import indi.kenneth.mvc.support.MyHandlerInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author kenneth
// * @Date: 2023/3/7
// */
//@EnableWebMvc
//@Configuration
//public class AppConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private MyHandlerInterceptor interceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(interceptor);
//        registration.addPathPatterns("/**");
//    }
//}
