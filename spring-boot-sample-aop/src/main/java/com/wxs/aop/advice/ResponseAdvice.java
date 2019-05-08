package com.wxs.aop.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: yoyo
 * @Description: ResponseBodyAdvice 只支持 @ResponseBody 注解的 controller 方法，
 * 同样，RequestBodyAdvice 只支持带有 @RequestBody 注解的 controller 方法参数的方法，同时上报的数据必须是 json or xml
 * @Date: Created in 2019/4/2 11:44
 */
//@ControllerAdvice
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return null;
    }
}
