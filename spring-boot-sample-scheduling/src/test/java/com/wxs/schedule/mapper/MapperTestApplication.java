package com.wxs.schedule.mapper;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:The @MybatisTest will detect a class that annotated @SpringBootApplication by default.
 * Therefore by depend on bean definition methods, there is case that an unexpected error is occurred or
 * unnecessary components are loaded into ApplicationContext. This behavior can prevent by creating a
 * @SpringBootApplication class into same package as test class as follow:
 * @Author:Wuxinshui
 * @Date:2017/4/20 15:35
 */
@SpringBootApplication
public class MapperTestApplication {
}
