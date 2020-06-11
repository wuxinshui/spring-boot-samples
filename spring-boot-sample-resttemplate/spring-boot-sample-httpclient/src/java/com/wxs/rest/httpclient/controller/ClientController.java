package com.wxs.rest.httpclient.controller;

import com.alibaba.fastjson.JSON;
import com.wxs.rest.httpclient.bo.StuInfo;
import com.wxs.rest.httpclient.config.RestHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 测试授权登陆流程
 */
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    public static final String url="http://127.0.0.1:8080/server";

    @Autowired
    private RestHttpClient restHttpClient;


    @GetMapping("/")
    public Map index() throws IOException {
        log.info("client......");
        HttpResponse response= restHttpClient.doGet("/");

        log.info("response:{}", response.getEntity());

        // return Map.of("code",200,"data",response);
        return Map.of("code",200,"data","success");

    }

    @GetMapping("/json")
    public Map json() throws IOException {
        log.info("client......");

        HttpResponse response = restHttpClient.doPost("json", JSON.toJSONString(StuInfo.builder().age("12").name("aa").build()));

        log.info("response:{}", response.getEntity());

        // return Map.of("code",200,"data",response);
        return Map.of("code", 200, "data", "success");

    }

    @GetMapping("/map")
    public Map map() {
        Map<String, Object> map = Map.of("age", 11, "name", "aaa");
        log.info("client......map,params:{}", map);
        // return restTemplate.getForObject(url+"/map?age={age}",Map.class,map);
        // return restHttpClient.doGet(url+"/map?age={age}&name={name}",Map.class,map);
        return null;
    }

    @GetMapping("/uriVariables")
    public Map uriVariables() {
        Map<String, Object> map = Map.of("age", 11, "name", "aaa");
        log.info("client......uriVariables,params:{}", map);
        // return restTemplate.getForObject(url+"/uriVariables?age={age}&name={name}",Map.class,11,"we");
        // return restTemplate.getForObject(url+"/uriVariables",Map.class,11,"we");
        // return restHttpClient.getForObject(url+"/uriVariables?age={age}",Map.class,11,"we");
        return null;
    }

}
