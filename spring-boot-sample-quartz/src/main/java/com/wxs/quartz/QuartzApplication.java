package com.wxs.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:19
 */
@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.wxs.quartz.mapper")
public class QuartzApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}
}
