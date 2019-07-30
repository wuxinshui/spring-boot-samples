package com.wxs.rule.eventbus;

import com.google.common.eventbus.Subscribe;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/7/24 13:34
 */
@Slf4j
public class RegisterEventHandler {

    @Autowired
    private ExpressRunner expressRunner;

    @Subscribe
    public void register(String message) {
        log.info("register event:{}", message);
        //    触发规则
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a+b*c";
        Object r = null;
        try {
            r = expressRunner.execute(express, context, null, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(r);
    }
}
