package com.wxs.rule;

import com.google.common.eventbus.EventBus;
import com.ql.util.express.ExpressRunner;
import com.wxs.rule.eventbus.RegisterEventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.rule.dao")
public class RuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RuleApplication.class, args);
    }

    @Bean
    public EventBus eventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new RegisterEventHandler());
        return eventBus;
    }

    @Bean
    public ExpressRunner expressRunner() {
        return new ExpressRunner();
    }
}
