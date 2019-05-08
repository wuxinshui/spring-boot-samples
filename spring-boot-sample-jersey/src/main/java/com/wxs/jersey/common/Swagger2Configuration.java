package com.wxs.jersey.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class Swagger2Configuration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //@Bean
    //public Docket createRestApi() {
    //    return new Docket(DocumentationType.SWAGGER_2)
    //            .apiInfo(apiInfo())
    //            .select()
    //            .apis(RequestHandlerSelectors.basePackage("com.wxs.jersey.resource"))
    //            .paths(PathSelectors.any())
    //            .build();
    //}
    //
    ////构建 api文档的详细信息函数
    //private ApiInfo apiInfo() {
    //    return new ApiInfoBuilder()
    //            //页面标题
    //            .title("后端API")
    //            //创建人
    //            .contact(new Contact("yoo", "https://www.yoyo.com/", "yoyo.com"))
    //            //版本号
    //            .version("1.0")
    //            //描述
    //            .description("API 描述")
    //            .build();
    //}

}