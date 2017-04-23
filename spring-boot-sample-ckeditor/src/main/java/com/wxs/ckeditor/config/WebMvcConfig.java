package com.wxs.ckeditor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/3/23 18:33
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/public/image/**").addResourceLocations("file:D:\\data\\file\\image\\");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/ckfinder/**").addResourceLocations("classpath:/static/ckfinder/");
		super.addResourceHandlers(registry);
	}
}
