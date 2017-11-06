package com.wxs.tkmybatis.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = "com.wxs.tkmybatis.mapper", sqlSessionFactoryRef="sqlSessionFactory", sqlSessionTemplateRef="sqlSessionTemplate")
@Configuration
public class MybatisConfig {
}
