package com.wxs.tkmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:19
 */
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = "com.wxs")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
