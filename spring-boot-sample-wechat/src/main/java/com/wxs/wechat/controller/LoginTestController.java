package com.wxs.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试授权登陆流程
 */
@Controller
@RequestMapping("/wechat")
public class LoginTestController {

    @GetMapping
    public String index() {
        System.out.println("index......");
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/resource")
    public String resource() {
        return "";
    }

}
