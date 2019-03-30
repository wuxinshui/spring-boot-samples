package com.wxs.paginator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxs.paginator.dao")
public class PaginatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(PaginatorApplication.class, args);
	}
}
