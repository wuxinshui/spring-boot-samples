package com.wxs.tkmybatis.mapper;

import com.wxs.tkmybatis.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Description:The @MybatisTest can be used if you want to test MyBatis components(Mapper interface and SqlSession).
 * By default it will configure MyBatis(MyBatis-Spring) components(SqlSessionFactory and SqlSessionTemplate),
 * configure MyBatis mapper interfaces and configure an in-memory embedded database. MyBatis tests are transactional
 * and rollback at the end of each test by default, for more details refer to the relevant section in the Spring Reference
 * Documentation. Also regular @Component beans will not be loaded into the ApplicationContext.
 * <p>
 * AutoConfigureTestDatabase:Using a real database
 * @Author:Wuxinshui
 * @Date:2017/4/18 19:00
 */
@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan(basePackages = "com.wxs.mybatis.mapper")
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void save() {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("user1");
		user.setCreateUser("user1");
		user.setCreateTime(new Date());
		user.setUpdateUser("user1");
		user.setUpdateTime(new Date());
//		userMapper.insert(user);
userMapper.selectOne(1);
		System.out.println(user);
	}

}