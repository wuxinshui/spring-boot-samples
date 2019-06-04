package com.wxs.jetty.controller;

import com.wxs.jetty.controller.vo.UserVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/jetty")
    public String usersFromHeader() {
        return "hello jetty";
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
