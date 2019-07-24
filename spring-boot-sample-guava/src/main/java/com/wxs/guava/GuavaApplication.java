package com.wxs.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.guava.dao")
public class GuavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuavaApplication.class, args);
    }
}
