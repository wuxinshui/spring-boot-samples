package com.wxs.filter;

import com.wxs.service.LogServices;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class PureFilter implements Filter {

    @Autowired
    private LogServices logServices;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("PureFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("PureFilter doFilter get bean: " + logServices);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("PureFilter destroy");

    }
}
