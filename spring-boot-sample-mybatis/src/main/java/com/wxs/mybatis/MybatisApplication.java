package com.wxs.mybatis;

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
@MapperScan(basePackages = "com.wxs.mybatis.mapper")
public class MybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}
}
