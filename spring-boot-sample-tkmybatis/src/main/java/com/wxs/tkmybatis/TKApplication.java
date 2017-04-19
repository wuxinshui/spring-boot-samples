package com.wxs.tkmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:19
 */
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "com.wxs.tkmybatis.mapper")
public class TKApplication {
	public static void main(String[] args) {
		SpringApplication.run(TKApplication.class, args);
	}
}
