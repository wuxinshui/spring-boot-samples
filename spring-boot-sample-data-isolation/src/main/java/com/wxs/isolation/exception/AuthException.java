package com.wxs.isolation.exception;

import com.wxs.isolation.common.IsolationConstant;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 15:54
 */
public class AuthException extends IsolationException {

    public AuthException(String message) {
        super(message);
        setCode(IsolationConstant.ISOLATION_AUTH_TOKEN_VALUE);
    }
}
