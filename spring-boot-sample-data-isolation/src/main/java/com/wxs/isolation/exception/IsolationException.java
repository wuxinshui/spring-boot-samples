package com.wxs.isolation.exception;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 15:57
 */
public class IsolationException extends RuntimeException {
    private String code;

    public IsolationException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
