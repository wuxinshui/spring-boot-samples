package com.wxs.guava;

import com.google.common.eventbus.EventBus;
import com.wxs.guava.eventbus.RegisterEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.guava.dao")
public class GuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new RegisterEvent());
        return eventBus;
    }
}
