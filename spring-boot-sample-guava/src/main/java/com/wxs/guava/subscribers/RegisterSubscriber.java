package com.wxs.guava.subscribers;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/24 13:34
 */
@Slf4j
public class RegisterSubscriber {

    @Subscribe
    public void register(String message) {
        log.info("register event:{}", message);
    }
}
