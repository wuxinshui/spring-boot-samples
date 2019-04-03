package com.wxs.isolation.common;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/3 18:27
 */

public class Result<T> {
    public static final String success_code = "200";
    public static final String fail_code = "500";
    private String code;
    private String message;
    private T data;

    private Result() {
    }

    private Result(String code) {
        this.code = code;
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result instance() {
        return new Result(success_code);
    }

    public Result success(T data) {
        Result result = instance();
        result.setData(data);
        return result;
    }

    public Result fail(String code, String msg) {
        Result result = instance();
        result.setCode(code);
        result.setData(msg);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
