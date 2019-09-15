package com.wxs.shiro.controller;

import com.google.common.eventbus.EventBus;
import com.wxs.shiro.event.ExceptionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yoyo
 * @Description: 测试验证 异常情况下，对其他事件的影响
 * @Date: Created in 2019/7/24 13:38
 */
@RestController
@Slf4j
public class EventBusExceptionController {
    @Autowired
    private EventBus eventBus;

    @PostMapping("/exception")
    public void register(@RequestBody ExceptionEvent exceptionVo) {
        log.info("posting exception event");
        eventBus.post(exceptionVo);
    }
}
