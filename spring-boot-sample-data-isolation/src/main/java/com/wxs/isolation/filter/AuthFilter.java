package com.wxs.isolation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxs.isolation.common.IsolationConstant;
import com.wxs.isolation.common.Result;
import com.wxs.isolation.context.AdminInfo;
import com.wxs.isolation.context.LocalMap;
import com.wxs.isolation.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wxs.isolation.common.IsolationConstant.ISOLATION_AUTH_TOKEN_VALUE;
import static com.wxs.isolation.common.IsolationConstant.ISOLATION_FAIL_CODE;

/**
 * @Author: yoyo
 * @Description: 模拟权限、token校验
 * @Date: Created in 2019/4/3 15:49
 */
@Component
public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private AdminInfo adminInfo;

    @Autowired
    private OauthService oauthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authToken = request.getHeader(IsolationConstant.ISOLATION_AUTH_TOKEN);
        if (StringUtils.isEmpty(authToken) || !ISOLATION_AUTH_TOKEN_VALUE.equals(authToken)) {
            Result ret = new Result<>(ISOLATION_FAIL_CODE, "认证异常，请重新登录", null);
            byte[] bytes = new ObjectMapper().writeValueAsString(ret).getBytes();
            response.setHeader("Content-Type", "application/json");
            response.setStatus(500);
            response.getOutputStream().write(bytes);
        }
        //模拟业务逻辑处理
        adminInfo.setAge(23);
        adminInfo.setName("气死");
        //线程隔离术
        LocalMap localMap=new LocalMap();
        localMap.put("username","气活");
        localMap.put("userage",32);
        //oauthService.put(localMap);
        OauthService.local.set(localMap);


        filterChain.doFilter(request,response);
    }
}
