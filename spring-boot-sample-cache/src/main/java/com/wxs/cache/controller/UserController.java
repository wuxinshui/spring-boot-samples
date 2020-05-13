package com.wxs.cache.controller;

import com.wxs.cache.entity.User;
import com.wxs.cache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/cache")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User usersFromSession(@PathVariable("id") Integer id) {
        log.info("usersFromSession...");
        return userService.selectById(id);
    }

    @PostMapping("/users/add")
    public String usersFromHeader(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }

}
