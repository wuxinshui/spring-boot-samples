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
    public User selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    private Integer getTest() {
        return 1;
    }
}
