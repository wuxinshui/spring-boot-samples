package com.wxs.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2018/12/20 16:35
 */

//@Aspect
@Slf4j
//@Component
public class LogAspect {

    @Pointcut("execution(public * com.wxs.aop.controller..*.*(..))\"")
    public void log() {
    }
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void withInLog() {
    }
    @Pointcut("within(org.springframework.web.bind.annotation.RestController)")
    public void withInLog1() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void annotationLog() {
    }

    @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
    public void targetLog() {
    }

    @Before("log()")
    public Object doBefore(JoinPoint joinPoint) {
        log.info("LogAspect doBefore....");
        return joinPoint;
    }

    @After("log()")
    public Object doAfter(JoinPoint joinPoint) {
        log.info("LogAspect doAfter....");
        return joinPoint;
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("LogAspect doAround....");
        return proceedingJoinPoint;
    }
}
