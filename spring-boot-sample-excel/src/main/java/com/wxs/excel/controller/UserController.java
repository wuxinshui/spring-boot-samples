package com.wxs.excel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/undertow")
public class UserController {

    @GetMapping
    public String usersFromHeader() {
        return "hello excel";
    }



}
