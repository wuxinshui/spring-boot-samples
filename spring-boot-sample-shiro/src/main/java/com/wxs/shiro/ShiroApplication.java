package com.wxs.shiro;

import com.google.common.eventbus.EventBus;
import com.wxs.shiro.subscribers.ExceptionSubscriber;
import com.wxs.shiro.subscribers.RegisterSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.*.dao")
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus("test");
        eventBus.register(new RegisterSubscriber());
        eventBus.register(new ExceptionSubscriber());
        return eventBus;
    }
}
