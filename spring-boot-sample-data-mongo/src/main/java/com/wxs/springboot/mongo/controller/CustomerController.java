package com.wxs.springboot.mongo.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 17:00
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {


    @GetMapping(value = "/queyr")
    public ModelMap selectGet(@PathVariable Integer id) {
        return new ModelMap();
    }

}
