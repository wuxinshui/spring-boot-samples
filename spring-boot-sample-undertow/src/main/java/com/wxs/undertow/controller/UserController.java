package com.wxs.undertow.controller;

import com.wxs.undertow.vo.UserVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/undertow")
    public String usersFromHeader() {
        return "hello undertow";
    }


    @GetMapping("/user")
    public ModelMap getUser() {
        ModelMap modelMap = new ModelMap();
        UserVo userVo = new UserVo();
        userVo.setAge("12");
        userVo.setName("12");
        userVo.setSex("12");
        modelMap.put("code", 200);
        modelMap.put("msg", "success");
        modelMap.put("data", userVo);
        return modelMap;
    }


}
