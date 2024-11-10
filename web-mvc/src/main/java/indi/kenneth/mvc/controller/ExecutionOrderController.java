package indi.kenneth.mvc.controller;

import indi.kenneth.mvc.annotation.CustomizeRequestBody;
import indi.kenneth.mvc.annotation.CustomizeResponse;
import indi.kenneth.mvc.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * ResponseBodyAdvice 需要标记@ControlerAdvice
 */
@ControllerAdvice
@RestController
public class ExecutionOrderController implements  ResponseBodyAdvice<Object> {

    @CustomizeRequestBody
    @CustomizeResponse
    @RequestMapping("/execution-order")
    public Result<Boolean> executionOrder() {
        return Result.success(true);
    }


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("===================== ResponseBodyAdvice supports =====================");
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("===================== ResponseBodyAdvice beforeBodyWrite =====================");
        return body;
    }
}
