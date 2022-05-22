package com.wxs.afterRunners;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Springboot 启动后执行
 */
@Component
public class AfterApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("AfterApplicationRunner Done~");
    }
}
