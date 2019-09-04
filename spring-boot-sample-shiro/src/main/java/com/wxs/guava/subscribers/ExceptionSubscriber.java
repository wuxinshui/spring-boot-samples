package com.wxs.guava.subscribers;

import com.google.common.eventbus.Subscribe;
import com.wxs.guava.event.ExceptionEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/24 13:34
 */
@Slf4j
public class ExceptionSubscriber {

    @Subscribe
    public void exceptionHandle(ExceptionEvent exceptionEvent) {
        log.info("exception event:{}", exceptionEvent);
        throw new RuntimeException("exception occur...");
    }

    @Subscribe
    public void normalHandle(ExceptionEvent exceptionEvent) {
        log.info("exception event normal handle:{}", exceptionEvent);
    }
}
