package com.wxs.ckeditor.config;

import com.ckfinder.connector.ConnectorServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>CKFinderServletConfig</p>
 *
 * @author wuxinshui
 */
@Configuration
public class CKFinderServletConfig {

    @Value("${ckeditor.storage.image.path}")
    private String baseDir;
    @Value("${ckeditor.access.image.url}")
    private String baseURL;

    @Bean
    public ServletRegistrationBean connectCKFinder(){
        ServletRegistrationBean registrationBean=new ServletRegistrationBean(new ConnectorServlet(),"/ckfinder/core/connector/java/connector.java");
        registrationBean.addInitParameter("XMLConfig","classpath:/static/ckfinder.xml");
        registrationBean.addInitParameter("debug","false");
        registrationBean.addInitParameter("configuration","com.wxs.ckeditor.config.CKFinderConfig");
        //初始化ckfinder.xml 配置
        registrationBean.addInitParameter("baseDir",baseDir);
        registrationBean.addInitParameter("baseURL",baseURL);
        return registrationBean;
    }

}
