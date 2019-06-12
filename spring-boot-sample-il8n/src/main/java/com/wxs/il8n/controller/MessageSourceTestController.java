package com.wxs.il8n.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @Author: yoyo
 * @Description: MessageSource 国际化测试
 * @Date: Created in 2019/6/12 17:37
 */
@RestController
public class MessageSourceTestController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/mail")
    public String getUsers() {
        return messageSource.getMessage("email.server", null, Locale.CHINA);
    }

}
