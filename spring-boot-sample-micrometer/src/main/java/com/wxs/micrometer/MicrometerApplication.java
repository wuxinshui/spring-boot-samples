package com.wxs.micrometer;

import com.google.common.eventbus.EventBus;
import com.wxs.micrometer.eventbus.RegisterEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.micrometer.dao")
public class MicrometerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicrometerApplication.class, args);
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new RegisterEvent());
        return eventBus;
    }
}
