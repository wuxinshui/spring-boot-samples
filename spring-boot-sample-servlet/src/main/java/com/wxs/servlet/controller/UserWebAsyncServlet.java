package com.wxs.servlet.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.AsyncContext;
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
@WebServlet(name = "asyncServlet", urlPatterns = "/async-servlet", asyncSupported = true)
public class UserWebAsyncServlet extends HttpServlet {

    /**
     * test autowired
     */
    @Autowired
    private UserController userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("WebServlet asyncSupported test....");
        if (req.isAsyncSupported()) {
            //java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
            //req.startAsync();
            //AsyncContext asyncContext = req.getAsyncContext();
            AsyncContext asyncContext = req.startAsync();
            //asyncContext.dispatch("/servlet/dispatch");
            asyncContext.start(() -> {
                try {
                    System.out.println("mock async...");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("mock async exception...");
                    e.printStackTrace();
                }
            });
            asyncContext.complete();
            System.out.println("mock async complete...");
            System.out.println("mock async complete...userService" + userService);
        }
        resp.setStatus(200);
        resp.getWriter().println("Hello Web Servlet asyncSupported");
    }
}
