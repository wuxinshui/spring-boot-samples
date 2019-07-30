package com.wxs.rule.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/24 13:34
 */
@Slf4j
public class RegisterEvent {

    @Subscribe
    public void register(String message) {
        log.info("register event:{}", message);
    }
}
