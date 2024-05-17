package indi.kenneth.mvc.config.support;

import indi.kenneth.mvc.annotation.CustomizeRequestBody;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CustomizeRequestArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        System.out.println("HandlerMethodReturnValueHandler supportsParameter  ");
        return parameter.hasParameterAnnotation(CustomizeRequestBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("===================== HandlerMethodReturnValueHandler supportsReturnType =====================");
        return parameter.getParameter();
    }

}
