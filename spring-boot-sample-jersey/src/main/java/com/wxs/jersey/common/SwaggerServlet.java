package com.wxs.jersey.common;

import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@Component
@WebServlet(initParams = {@WebInitParam(name = "jersey.config.server.provider.packages",value = "io.swagger.v3.jaxrs2.integration.resources,com.example.jersey.rest.servlets")})
public class SwaggerServlet extends ServletContainer {
    @Value("${spring.jersey.application-path}")
    private String apiPath;
    //@Override
    //protected void init(WebConfig webConfig) throws ServletException {
    //    super.init(webConfig);
    //    BeanConfig beanConfig = new BeanConfig();
    //    beanConfig.setVersion("1.0.2");
    //    beanConfig.setSchemes(new String[]{"http"});
    //    beanConfig.setHost("localhost:8002");
    //    beanConfig.setBasePath("/");
    //    beanConfig.setResourcePackage("io.swagger.resources");
    //    beanConfig.setScan(true);
    //}

    //@Override
    //public void init(FilterConfig filterConfig) throws ServletException {
    //    super.init(filterConfig);
    //    BeanConfig beanConfig = new BeanConfig();
    //    beanConfig.setVersion("1.0.2");
    //    beanConfig.setSchemes(new String[]{"http"});
    //    beanConfig.setHost("localhost:8002");
    //    beanConfig.setBasePath("/");
    //    beanConfig.setResourcePackage("io.swagger.resources");
    //    beanConfig.setScan(true);
    //}
}
