package com.wxs.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.i18n.dao")
@ComponentScan("com.wxs.i18n")
public class I18nMessageApplication {

    @Autowired
    private MessageSource messageSource;

    public static void main(String[] args) {
        SpringApplication.run(I18nMessageApplication.class, args);
    }

    //application config
    //@Bean
    //public MessageSource messageSource() {
    //	ResourceBundleMessageSource messageSource
    //			= new ResourceBundleMessageSource();
    //
    //	messageSource.setBasename("messages");
    //	messageSource.setDefaultEncoding("UTF-8");
    //	return messageSource;
    //}

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }



}
