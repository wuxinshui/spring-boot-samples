package com.wxs.aop.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Result<T> implements Serializable {
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_FAIL = "fail";
    private int code;
    private String msg;
    private T data;

    public Result() {
        this.code = 200;
        this.msg = MSG_SUCCESS;
        this.setData(data);
    }


    public void doErrorHandle(String msg) {
        this.msg = null == msg ? MSG_FAIL : msg;
        this.code = 500;
    }

    public static Result fail() {
        return new Result(500, MSG_FAIL, null);
    }

    public static Result success() {
        return new Result(200, MSG_SUCCESS, null);
    }

}
