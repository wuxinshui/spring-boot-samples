package com.wxs.tomcat.vo;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/9/2 10:34
 */
// java.lang.IllegalArgumentException: No converter found for return value of type: class com.wxs.tomcat.vo.Greeting
//	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:187) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.handleReturnValue(RequestResponseBodyMethodProcessor.java:174) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.handleReturnValue(HandlerMethodReturnValueHandlerComposite.java:81) ~[spring-web-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:113) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861) ~[spring-webmvc-4.3.17.RELEASE.jar:4.3.17.RELEASE]
//@Data
public class Greeting {
    private String message;

    public Greeting(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}