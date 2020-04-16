package com.wxs.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信测试
 */
@Controller
@RequestMapping("/success")
@Slf4j
public class WechatTestController {

    public static final String BASE_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxaee4080cd8554f5d&secret=69bced1876f2e7a6e8226e9ea223a948&code=";
    public static final String postFix = "&grant_type=authorization_code";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String index(String code, String state, @RequestParam(required = false,defaultValue = "false") boolean key) {
        log.info("用户同意授权，获取code-code:{},state:{}", code, state);
        StringBuilder sb = new StringBuilder(BASE_URL);
        sb.append(code);
        sb.append(postFix);
        String openid = null;
        if (key) {
            openid = call(sb.toString());
        } else {
            openid = code;
        }

        ModelAndView mv = new ModelAndView();

        mv.addObject("username", openid);

        return "success";
    }


    private String call(String url) {
        log.info("通过code换取网页授权access_token request url:{}", url);
        String response = restTemplate.getForObject(url, String.class);
        log.info("通过code换取网页授权access_token request response:{}", response);
        return response;
    }


}
