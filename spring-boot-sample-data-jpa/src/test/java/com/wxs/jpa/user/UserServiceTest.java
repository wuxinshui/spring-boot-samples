package com.wxs.jpa.user;

import com.wxs.jpa.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
