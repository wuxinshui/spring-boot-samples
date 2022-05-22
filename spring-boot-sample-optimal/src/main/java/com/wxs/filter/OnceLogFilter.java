package com.wxs.filter;

import com.wxs.service.LogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OnceLogFilter extends OncePerRequestFilter {

    @Autowired
    private LogServices logServices;

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return super.shouldNotFilter(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("OnceLogFilter get bean: " + logServices);
        filterChain.doFilter(request, response);
    }


}
