package com.wxs.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wxs.mybatisplus.entity.User;
import com.wxs.mybatisplus.mapper.UserMapper;
import com.wxs.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mp")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users/{id}")
    public User usersFromSession(@PathVariable("id") Integer id) {
        return userService.selectById(id);
    }

    @PostMapping("/users/add")
    public String usersFromHeader(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }


    @GetMapping("/test/")
    public List<User> test() {
        log.info("usersFromSession...");
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.gt("age", "22");
        queryWrapper.lt("age", "26");

        return userMapper.selectList(queryWrapper);

    }

    @GetMapping("/test1")
    public List<User> test1() {
        log.info("usersFromSession...");
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.gt("age", "22");
        queryWrapper.lt("age", "26");

        queryWrapper.inSql()

        return userMapper.selectList(queryWrapper);

    }


}
