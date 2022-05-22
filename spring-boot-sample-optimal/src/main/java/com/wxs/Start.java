package com.wxs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.wxs")
@SpringBootApplication(scanBasePackages = "com.wxs")
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
        System.out.println("Start Main Done~");
    }
}
