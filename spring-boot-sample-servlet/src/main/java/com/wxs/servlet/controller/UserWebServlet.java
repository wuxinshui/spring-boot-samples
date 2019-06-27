package com.wxs.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/27 17:09
 */
@WebServlet(name = "userServlet",urlPatterns = "/web-servlet")
public class UserWebServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("WebServlet test....");
        resp.setStatus(200);
        resp.getWriter().println("Hello Web Servlet");
    }
}
