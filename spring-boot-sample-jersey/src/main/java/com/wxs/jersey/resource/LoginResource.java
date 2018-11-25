package com.wxs.jersey.resource;

import com.wxs.jersey.service.impl.LoginServiceImpl;
import com.wxs.jersey.vo.VerifyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/login", tags = "用户登录接口", produces = MediaType.APPLICATION_JSON)
public class LoginResource {

    @Autowired
    private LoginServiceImpl loginService;

    @Path("/verify")
    @POST
    @ApiOperation(value = "校验", notes = "校验")
    @ApiResponses({@ApiResponse(code = 400, message = "失败"), @ApiResponse(code = 500, message = "服务器内部错误")})
    public int verifyLogin(VerifyInfo verifyInfo) {
        return loginService.verify(verifyInfo.getUsername(), verifyInfo.getPass());
    }

    @Path("/test")
    @GET
    @ApiOperation(value = "测试接口", notes = "测试接口")
    @ApiResponses({@ApiResponse(code = 400, message = "失败"), @ApiResponse(code = 500, message = "服务器内部错误")})
    public String test() {
        return "this is test";
    }
}
