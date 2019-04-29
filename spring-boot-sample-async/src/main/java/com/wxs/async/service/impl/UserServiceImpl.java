package com.wxs.async.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxs.async.entity.AuditLog;
import com.wxs.async.entity.User;
import com.wxs.async.mapper.UserMapper;
import com.wxs.async.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AsyncServiceImpl asyncServiceImpl;


    @Override
    public void addUser(User user) {
        log.info("UserServiceImpl addUser params:{}", JSON.toJSONString(user));
        AuditLog auditLog = new AuditLog();
        auditLog.setBody(JSON.toJSONString(user));
        asyncServiceImpl.save(auditLog);
        int count = baseMapper.insert(user);
        log.info("UserServiceImpl addUser count:{}", count);
    }

    @Override
    public void addUseWithResult(User user) throws ExecutionException, InterruptedException {
        log.info("UserServiceImpl addUseWithResult params:{}", JSON.toJSONString(user));
        AuditLog auditLog = new AuditLog();
        auditLog.setBody(JSON.toJSONString(user));
        Future<Integer> result = asyncServiceImpl.saveWithResult(auditLog);
        int count = baseMapper.insert(user);
        int counter = 0;
        while (!result.isDone()) {
            counter++;
            Thread.sleep(counter);
            if (counter > 20) {
                break;
            }
        }
        Integer id = result.get();
        log.info("addUseWithResult async save log success:{}", id);
        User user1 = baseMapper.selectById(user.getId());

        log.info("UserServiceImpl addUseWithResult save user result:{}", JSON.toJSONString(user1));
        log.info("UserServiceImpl addUseWithResult count:{}", count);
    }

    @Override
    public void addUseWithListenableResult(User user) throws ExecutionException, InterruptedException {
        log.info("UserServiceImpl addUseWithListenableResult params:{}", JSON.toJSONString(user));
        AuditLog auditLog = new AuditLog();
        auditLog.setBody(JSON.toJSONString(user));
        ListenableFuture<Integer> result = asyncServiceImpl.saveWithListenableResult(auditLog);
        result.addCallback(success -> {
            saveUser(user);
        }, fail -> {
            asyncServiceImpl.saveWithListenableResult(auditLog);
        });
        log.info("UserServiceImpl addUseWithListenableResult result:{}", JSON.toJSONString(user));
        log.info("UserServiceImpl addUseWithListenableResult result:{}", result.get());
    }

    @Override
    public void addUseWithResultCompletableFuture(User user) throws ExecutionException, InterruptedException {
        log.info("UserServiceImpl addUseWithResultCompletableFuture params:{}", JSON.toJSONString(user));
        AuditLog auditLog = new AuditLog();
        auditLog.setBody(JSON.toJSONString(user));
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> asyncServiceImpl.saveWithResultCompletableFuture(auditLog));
        int count = baseMapper.insert(user);
        int counter = 0;
        while (!completableFuture.isDone()) {
            counter++;
            log.info("completableFuture counter:{}", counter);
            Integer result = completableFuture.get();
            if (null != result) {
                log.info("completableFuture counter result:{}", result);
                User user1 = baseMapper.selectById(user.getId());
                log.info("UserServiceImpl addUseWithResultCompletableFuture save user result:{}", JSON.toJSONString(user1));
            }

        }
        log.info("UserServiceImpl addUseWithResultCompletableFuture count:{}", count);
    }


    @Override
    public void addUseWithResultCompletableFutureThen(User user) throws ExecutionException, InterruptedException {
        log.info("UserServiceImpl addUseWithResultCompletableFutureThen params:{}", JSON.toJSONString(user));
        //AuditLog auditLog = new AuditLog();
        //auditLog.setBody(JSON.toJSONString(user));
        //saveUser(user),saveWithResultCompletableFutureThen 俩操作，异步执行
        //CompletableFuture<Integer> completableFuture =
        //        CompletableFuture.supplyAsync(() -> saveUser(user)).completeAsync(() -> asyncServiceImpl.saveWithResultCompletableFutureThen(auditLog));

        //saveUser(user)) \saveWithResultCompletableFutureThen，先后顺序，同步执行，依赖上一步的操作结果
        //CompletableFuture<Integer> completableFuture =
        //        CompletableFuture.supplyAsync(() ->
        //                saveUser(user)).thenApply(o -> asyncServiceImpl.saveWithResultCompletableFutureThen(AuditLog.builder().body(JSON.toJSONString(user)).build()));

        AuditLog auditLog = new AuditLog();
        auditLog.setBody(JSON.toJSONString(user));
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() ->
                saveUser(user)).whenCompleteAsync((a, b) -> {
            asyncServiceImpl.saveWithResultCompletableFutureWhenCompleteAsync(auditLog);
        });
        log.info("UserServiceImpl addUseWithResultCompletableFutureThen result:{}", completableFuture.get());
        log.info("UserServiceImpl addUseWithResultCompletableFutureThen userId:{}", user.getId());
    }

    @Override
    public User selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    private Integer saveUser(User user) {
        log.info("save user:{}", JSON.toJSONString(user));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer result = baseMapper.insert(user);
        log.info("save user userId:{}", user.getId());
        return result;
    }
}
