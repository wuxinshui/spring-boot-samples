package com.wxs.guava.controller;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/24 13:38
 */
@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private EventBus eventBus;

    @PostMapping("/register")
    //public void register(Object message) {
    public void register(String message) {
        log.info("posting register event");
        eventBus.post(message);
    }
}
