package com.wxs.servlet.config;

import com.wxs.servlet.controller.UserRegistrationServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/27 17:21
 */
@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistration() {
        return new ServletRegistrationBean(new UserRegistrationServlet(), "/register-servlet");
    }
}
