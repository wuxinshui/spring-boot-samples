package com.wxs.aop.common;

public enum ResultCode {
    SUCCESS(200, "成功"),
    PARAM_ERR(400, "参数异常"),
    SERVER_ERR(500, "系统异常，请稍后重试");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
