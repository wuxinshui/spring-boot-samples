package com.wxs.redis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxs.redis.domain.User;
import com.wxs.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.wxs.redis.util.Constant.*;

/**
 * @ClassName: CacheRedisController
 * @author: [FujiRen]
 * @CreateDate: 2017/7/26 13:59
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/7/26 13:59
 * @UpdateRemark: [说明本次修改内容]
 * @Description: DB数据添加到Redis中
 * @version: [V1.0]
 */
@RestController
@RequestMapping(value = "/redis")
public class CacheRedisController extends BaseResult {

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate<String,String> redisTemplate;

	private static final String REDIS_KEY_USER_PRIFIX = "user";

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelMap addAllUsersToRedis() {
		try {
			List<User> userList = userService.selectAll();
			for (User user : userList) {
				String jsonStr= JSONObject.toJSONString(user);
				redisTemplate.opsForValue().set(REDIS_KEY_USER_PRIFIX + user.getId(), jsonStr);
			}

			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/selectUser/{id}", method = RequestMethod.GET)
	public ModelMap selectUserById(@PathVariable int id) {
		try {
			String jsonStr =  redisTemplate.opsForValue().get(REDIS_KEY_USER_PRIFIX + id);
			User user= JSONObject.toJavaObject(JSON.parseObject(jsonStr),User.class);

			if (null == user) {
				user = userService.selectByPrimaryKey(id);
				String valueJsonStr= JSONObject.toJSONString(user);
				redisTemplate.opsForValue().set(REDIS_KEY_USER_PRIFIX + user.getId(), valueJsonStr);
			}
			int tempTime = this.redisTemplate.getExpire("max").intValue();
			this.redisTemplate.expire("max", tempTime, TimeUnit.SECONDS);

			return result(SUCCESS_CODE, SUCCESS_MSG, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/flushCache", method = RequestMethod.GET)
	private ModelMap flushCache() throws Exception {
		try {
			Iterable<String> execute = redisTemplate.execute(new RedisCallback<Iterable<String>>() {

				@Override
				public Iterable<String> doInRedis(RedisConnection connection) throws DataAccessException {
					Set<String> keys = new HashSet<String>();
					Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().count(5).build());
					while (cursor.hasNext()) {
						byte[] key = cursor.next();
						keys.add(new String(key, StandardCharsets.UTF_8));
						connection.del(key);
					}
					return keys;
				}

			});

			return result(SUCCESS_CODE, SUCCESS_MSG, execute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}
}
