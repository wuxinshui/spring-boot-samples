package com.wxs.isolation.exception.handler;

import com.wxs.isolation.common.Result;
import com.wxs.isolation.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.wxs.isolation.common.IsolationConstant.ISOLATION_FAIL_CODE;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 18:25
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result authException(AuthException exception) {
        return Result.instance().fail(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result exception(Exception exception) {
        return Result.instance().fail(ISOLATION_FAIL_CODE, exception.getMessage());
    }
}
