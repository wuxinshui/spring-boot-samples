package com.wxs.async.controller;

import com.wxs.async.entity.User;
import com.wxs.async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

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
    public String usersFromHeader(@RequestBody User user) throws ExecutionException, InterruptedException {
        //userService.addUser(user);
        //userService.addUseWithResult(user);
        userService.addUseWithResultCompletableFuture(user);
        return "success";
    }

}
