package com.wxs.redis.services;

import com.wxs.redis.domain.User;
import com.wxs.redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author:Wuxisnhui
 * @Date:2017/6/12 16:58
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;


	public List<User> selectAll() {
		return userRepository.findAll();
	}
}
