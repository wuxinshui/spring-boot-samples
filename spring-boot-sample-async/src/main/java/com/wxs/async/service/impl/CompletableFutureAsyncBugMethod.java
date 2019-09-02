package com.wxs.async.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/8/29 19:07
 */
@Service
public class CompletableFutureAsyncBugMethod {


    @Async
    public void send(String msg){
        System.out.println(msg);
    }
}
