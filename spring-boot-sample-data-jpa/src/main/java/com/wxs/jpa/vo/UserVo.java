package com.wxs.jpa.vo;

import com.wxs.jpa.domain.User;

import java.util.List;

/**
 * @Description:
 * @Author:FujiRen
 * @Date:2017/6/26 17:44
 */
public class UserVo {
	private String code;
	private String msg;
	private List<User> result;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "UserVo{" +
				"code='" + code + '\'' +
				", msg='" + msg + '\'' +
				", result=" + result +
				'}';
	}
}

