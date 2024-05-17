package indi.kenneth.mvc.config.support;

import indi.kenneth.mvc.annotation.CustomizeResponse;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CustomizeResponseHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        System.out.println("===================== HandlerMethodReturnValueHandler supportsReturnType =====================");
        return returnType.getMethod().isAnnotationPresent(CustomizeResponse.class);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        System.out.println("===================== HandlerMethodReturnValueHandler handleReturnValue =====================");
    }

}
