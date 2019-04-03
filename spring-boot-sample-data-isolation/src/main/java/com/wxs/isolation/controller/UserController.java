package com.wxs.isolation.controller;

import com.wxs.isolation.context.AdminInfo;
import com.wxs.isolation.entity.User;
import com.wxs.isolation.service.OauthService;
import com.wxs.isolation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/mp")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminInfo adminInfo;

    @Autowired
    private OauthService oauthService;

    @GetMapping("/users/{id}")
    public User usersFromSession(@PathVariable Integer id) {
        return userService.selectById(id);
    }

    @PostMapping("/users/add")
    public String usersFromHeader(@RequestBody User user) {
        userService.addUser(user);
        return "success";
    }

    @GetMapping("/users/{id}/detail")
    public User getUserDetail(@PathVariable Integer id) {
        User user = new User();
        user.setAge(adminInfo.getAge());
        user.setName(adminInfo.getName());
        user.setId(new Random().ints().boxed().filter(o -> o > 0).findFirst().get());
        return user;
    }

    @GetMapping("/users/{id}/local")
    public User getUserDetailFromLocal(@PathVariable Integer id) {
        User user = new User();
        user.setAge(oauthService.getUserAge());
        user.setName(oauthService.getUsername());
        user.setId(new Random().ints().boxed().filter(o -> o > 0).findFirst().get());
        return user;
    }
}
