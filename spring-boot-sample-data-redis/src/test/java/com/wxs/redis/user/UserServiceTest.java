package com.wxs.redis.user;

import com.wxs.redis.domain.User;
import com.wxs.redis.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author:FujiRen
 * @Date:2017/6/12 18:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void selectAll() {
		System.out.println("===========selectAll===============");
		System.out.println(userService.selectAll());
	}

	@Test
	public void selectByUsernameAndPassword() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		List<User> userList = userService.selectByUsernameAndPassword(user);
		System.out.println("===========selectByUsernameAndPassword===============");
		System.out.println(userList);
	}
}
