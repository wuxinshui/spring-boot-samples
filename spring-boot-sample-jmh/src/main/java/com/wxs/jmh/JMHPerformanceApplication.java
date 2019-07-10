package com.wxs.pagehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wxs.pagehelper.dao")
public class PageHelperApplication {
	public static void main(String[] args) {
		SpringApplication.run(PageHelperApplication.class, args);
	}
}
