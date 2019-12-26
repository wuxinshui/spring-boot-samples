package com.wxs.jpa.user;

import com.wxs.jpa.services.UserService;
import com.wxs.jpa.vo.UserVo;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
	public void selectPost1() {
		//参数
		String url = "http://localhost:9091/user/selectPost";
		String username = "admin";
		String password = "1212";

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("username", username);
		map.add("password", password);
		//设置响应头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		//封装参数响应头
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

		//创建默认示例，初始化HttpMessageConverter
		RestTemplate template = new RestTemplate();
		//执行请求，并接受返回结果
		ResponseEntity<String> result = template.exchange(url, HttpMethod.POST, entity, String.class);
		JSONObject jsonObject = JSONObject.fromString(result.getBody());
		System.out.println("===========selectPost1===============");

		System.out.println(jsonObject);

	}

	@Test
	public void selectPost2() {
		//参数
		String url = "http://localhost:9091/user/selectPost";
		String username = "admin";
		String password = "1212";

		//封装参数
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("username", username);
		map.add("password", password);

		//设置响应头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		//封装参数到HttpEntity
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

		//创建默认示例，初始化HttpMessageConverter
		RestTemplate template = new RestTemplate();

		//执行请求并获取返回结果
		UserVo result = template.postForObject(url, entity, UserVo.class);

		System.out.println("===========selectPost2===============");

		System.out.println(result);
	}

	@Test
	public void selectPost3() {
		//参数
		String url = "http://localhost:9091/user/selectPost";
		String username = "admin";
		String password = "1212";

		try {
			//初始化
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse httpResponse = null;
			HttpPost httpPost = new HttpPost(url);

			//请求参数
			List<NameValuePair> nameValuePairs = new ArrayList<>();
			nameValuePairs.add(new BasicNameValuePair("username", username));
			nameValuePairs.add(new BasicNameValuePair("password", password));

			//编码 封装参数
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
			httpPost.setEntity(formEntity);
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

			//执行请求
			httpResponse = httpClient.execute(httpPost);

			//返回结果
			org.apache.http.HttpEntity respHttpEntity = httpResponse.getEntity();
			String resultStr = EntityUtils.toString(respHttpEntity, "utf-8");

			System.out.println(resultStr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
