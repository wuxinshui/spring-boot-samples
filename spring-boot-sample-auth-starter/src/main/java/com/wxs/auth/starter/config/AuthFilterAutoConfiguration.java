package com.wxs.auth.starter.config;

import com.wxs.auth.starter.filter.AuthFilter;
import com.wxs.auth.starter.properties.AuthFilterProperties;
import com.wxs.auth.starter.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = AuthFilterProperties.class)
public class AuthFilterAutoConfiguration implements DisposableBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void destroy() throws Exception {
        logger.info("auth destroy success");
    }

    @ConditionalOnMissingBean
    @Bean
    public UserInfo userInfo() {
        return new UserInfo();
    }

    @ConditionalOnMissingBean
    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }
}
