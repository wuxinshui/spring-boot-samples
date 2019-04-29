package com.wxs.async.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wxs.async.entity.User;

import java.util.concurrent.ExecutionException;

public interface UserService extends IService<User> {

    void addUser(User user);

    void addUseWithResult(User user) throws ExecutionException, InterruptedException;

    void addUseWithListenableResult(User user) throws ExecutionException, InterruptedException;

    void addUseWithResultCompletableFuture(User user) throws ExecutionException, InterruptedException;


    void addUseWithResultCompletableFutureThen(User user) throws ExecutionException, InterruptedException;


    User selectById(Integer id);


}
