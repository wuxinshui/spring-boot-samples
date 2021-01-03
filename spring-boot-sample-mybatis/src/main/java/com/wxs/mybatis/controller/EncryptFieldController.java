package com.wxs.mybatis.controller;

import com.wxs.mybatis.mapper.UserMapper;
import com.wxs.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:模拟登录
 * @Author:Wuxinshui
 * @Date:2017/4/18 20:32
 */
@RestController
@RequestMapping("/user/encrypt")
public class EncryptFieldController extends BaseController {

    @Autowired
    private UserMapper userMapper;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map testInsert() {
        User user = new User();
        user.setPassword("addTest");
        user.setUsername("addTest");
        user.setCreateTime(new Date());
        user.setCreateUser("addTest");
        user.setUpdateTime(new Date());
        user.setUpdateUser("addTest");
        userMapper.insert(user);
        return Map.of("code", 200, "data", user);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map testInsertList() {
        List<User> list = userMapper.selectAll();
        return Map.of("code", 200, "data", list);
    }


}
