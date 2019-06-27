package com.wxs.servlet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/servlet/dispatch")
    public String usersFromSession() {
        return "dispatch success";
    }


}
