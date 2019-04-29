package com.wxs.async.service.impl;

import com.alibaba.fastjson.JSON;
import com.wxs.async.entity.AuditLog;
import com.wxs.async.mapper.AuditLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/28 17:47
 */
@Service
@Slf4j
public class AsyncServiceImpl {

    @Autowired
    private AuditLogMapper auditLogMapper;

    //@Override
    @Async
    public void save(Object o) {
        log.info("async save object:{}", JSON.toJSONString(o));
        int result = 0;
        if (o instanceof AuditLog) {
            result = auditLogMapper.insert((AuditLog) o);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async save success:{}", result);
    }

    @Async
    public Future<Integer> saveWithResult(Object o) {
        log.info("async saveWithResult save object:{}", JSON.toJSONString(o));
        int result = 0;
        if (o instanceof AuditLog) {
            result = auditLogMapper.insert((AuditLog) o);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async saveWithResult save success:{}", result);
        return new AsyncResult<>(result);
    }

    //@Async
    public Integer saveWithResultCompletableFuture(Object o) {
        log.info("async saveWithResultCompletableFuture save object:{}", JSON.toJSONString(o));
        int result = 0;
        if (o instanceof AuditLog) {
            result = auditLogMapper.insert((AuditLog) o);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async saveWithResultCompletableFuture save success:{}", result);
        return result;
    }

    public Integer saveWithResultCompletableFutureThen(AuditLog auditLog) {
        log.info("async saveWithResultCompletableFutureThen save object:{}", JSON.toJSONString(auditLog));
        int result = auditLogMapper.insert(auditLog);
        log.info("async saveWithResultCompletableFutureThen save result:{}", JSON.toJSONString(auditLog));
        return result;
    }

    public Integer saveWithResultCompletableFutureWhenCompleteAsync(AuditLog auditLog) {
        log.info("async saveWithResultCompletableFutureWhenCompleteAsync save object:{}", JSON.toJSONString(auditLog));
        int result = auditLogMapper.insert(auditLog);
        log.info("async saveWithResultCompletableFutureWhenCompleteAsync save result:{}", JSON.toJSONString(auditLog));
        return result;
    }

}
