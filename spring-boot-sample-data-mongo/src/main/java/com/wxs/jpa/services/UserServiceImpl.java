package com.wxs.jpa.services;

import com.wxs.jpa.domain.User;
import com.wxs.jpa.repository.UserRepository;
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

	@Override
	public User selectOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public void selectDelete(Integer id) {
		userRepository.delete(id);
	}

	@Override
	public List<User> selectPost(String username, String password) {
		return userRepository.findByUsernameAndPassword(username,password);
	}

	@Override
	public User selectPut(Integer id) {

		return userRepository.getOne(id);
	}
}
