package com.wxs.tkmybatis.mapper;

import com.wxs.tkmybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/18 19:00
 */
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UserMapperTest{

	@Autowired
	private UserMapper userMapper;

	@Test
	public void save(){
		User user=new User();
		user.setUsername("user1");
		user.setPassword("user1");
		user.setCreateUser("user1");
		user.setCreateTime(new Date());
		user.setUpdateUser("user1");
		user.setUpdateTime(new Date());
		userMapper.insert(user);

		System.out.println(user);
	}

}