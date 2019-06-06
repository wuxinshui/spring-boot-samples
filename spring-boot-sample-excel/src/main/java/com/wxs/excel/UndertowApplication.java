package com.wxs.excel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class UndertowApplication {

    public static void main(String[] args) {
        System.out.println("main args:"+ Arrays.toString(args));
        System.out.println("java command system property:"+System.getProperty("ddd"));
        SpringApplication.run(UndertowApplication.class, args);
    }

}