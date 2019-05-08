package com.wxs.aop.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2018/12/20 17:31
 */
@WebFilter(urlPatterns = "/login/*")
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LogFilter doFilter....");
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy....");
    }
}
