package com.wxs.quartz.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Result<T> implements Serializable {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Result SUCCESS = new Result(Code.SUCCESS, null, null);

	@SuppressWarnings({ "rawtypes"})
	public static boolean isSuccessResult( Result r ){
		if( r == null ){
			return false;
		}
		return r.isSuccess();
	}
	
	public enum Code {
		/**
		 * 成功
		 */
		SUCCESS,
		/**
		 * 错误
		 */
		ERROR
	}

	private Code code = Code.SUCCESS;
	private String message;
	private T data;

	public Result() {
	}

	public Result(Code code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Result(T data) {
		this.code = Code.SUCCESS;
		this.data = data;
	}

    public static <T> Result<T> newSuccess(T data) {
        return new Result<>(Code.SUCCESS, "", data);
    }

    public static Result newFailed(String message) {
        return new Result(Code.ERROR, message, null);
    }

	public static <T> Result<T> newFailed(String message, T data) {
		return new Result(Code.ERROR, message, data);
	}

	@JsonIgnore
	@JSONField(serialize = false)
	public boolean isSuccess(){
		return Code.SUCCESS.equals(this.code);
	}
	
	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
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

	public void doErrorHandle(String message) {
		this.code = Code.ERROR;
		this.message = message;
	}
}
