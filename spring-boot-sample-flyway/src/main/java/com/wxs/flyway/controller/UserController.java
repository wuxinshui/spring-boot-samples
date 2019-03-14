package com.wxs.flyway.controller;

import com.wxs.flyway.entity.User;
import com.wxs.flyway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mp")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User usersFromSession(@PathVariable("id") Integer id) {
        return userService.selectById(id);
    }

    @PostMapping("/users/add")
    public String usersFromHeader(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }

}
