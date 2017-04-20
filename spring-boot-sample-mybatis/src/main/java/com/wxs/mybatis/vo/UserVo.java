package com.wxs.mybatis.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/19 16:35
 */
public class UserVo implements Serializable {
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	private MultipartFile file;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "UserVo{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
