package com.wxs.rest.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;


    @GetMapping("/")
    public Map index() {
        log.info("client......");
        return restTemplate.getForObject(url+"/",Map.class);
    }

    @GetMapping("/map")
    public Map map() {
        Map<String,Object> map=Map.of("age",11,"name","aaa");
        log.info("client......map,params:{}",map);
        // return restTemplate.getForObject(url+"/map?age={age}",Map.class,map);
        return restTemplate.getForObject(url+"/map?age={age}&name={name}",Map.class,map);
    }

    @GetMapping("/uriVariables")
    public Map uriVariables() {
        Map<String,Object> map=Map.of("age",11,"name","aaa");
        log.info("client......uriVariables,params:{}",map);
        // return restTemplate.getForObject(url+"/uriVariables?age={age}&name={name}",Map.class,11,"we");
        // return restTemplate.getForObject(url+"/uriVariables",Map.class,11,"we");
        return restTemplate.getForObject(url+"/uriVariables?age={age}",Map.class,11,"we");
    }

}
