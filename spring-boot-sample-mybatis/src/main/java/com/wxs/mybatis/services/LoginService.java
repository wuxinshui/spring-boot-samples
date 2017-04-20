package com.wxs.mybatis.services;

import com.wxs.mybatis.mapper.UserMapper;
import com.wxs.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>LoginService</p>
 *
 * @author wuxinshui
 */
@Service
public class LoginService {
	@Autowired
	private UserMapper userMapper;

	public User selectOne(Integer id) throws Exception {
		try {
			return userMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
