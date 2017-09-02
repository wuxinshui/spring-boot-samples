package com.wxs.quartz.job;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * <p>BaseJob</p>
 * 解决Job中注入Spring Bean为null的问题
 *
 * @author wuxinshui
 */
public class BaseJob {
    protected void springBeanAutowiringSupport() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
