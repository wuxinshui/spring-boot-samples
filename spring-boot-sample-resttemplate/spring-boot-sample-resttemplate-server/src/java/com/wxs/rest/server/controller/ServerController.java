package com.wxs.rest.server.controller;

import com.wxs.rest.server.bo.StuInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 测试授权登陆流程
 */
@Slf4j
@RestController
@RequestMapping("/server")
public class ServerController {


    @GetMapping("/")
    public Map index() {
        log.info("server......");
        return Map.of("code", 200, "msg", "OK", "data", Map.of("age", 1, "name", 1));
    }


    @GetMapping("/map")
    public Map map(@RequestParam("age")Integer age, @RequestParam("name") String name) {
        log.info("server......map,params age:{},name:{}",age,name);
        return Map.of("code", 200, "msg", "OK", "data", Map.of("age", age, "name", name));
    }

    @PostMapping("/json")
    public Map json(@RequestBody StuInfo stu) {
        log.info("server......json,params stu:{}", stu);
        return Map.of("code", 200, "msg", "OK", "data", Map.of("age", stu.getAge(), "name", stu.getName()));
    }

    @PostMapping("/form")
    public Map form(StuInfo stu) {
        log.info("server......form,params stu:{}", stu);
        return Map.of("code", 200, "msg", "OK", "data", Map.of("age", stu.getAge(), "name", stu.getName()));
    }


    @GetMapping("/uriVariables")
    public Map uriVariablesFalse(@RequestParam(value = "age",required = false)Integer age, @RequestParam(value = "name",required = false) String name) {
        log.info("server......map,params age:{},name:{}",age,name);
        return Map.of("age",null==age?1:age,"name",null==name?"ddd":name);
    }


}
