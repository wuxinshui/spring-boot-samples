package com.wxs.quartzJob.core;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

/**
 * <p>JobAdmin</p>
 *
 * @author wuxinshui
 */
@Component
public class JobAdmin implements CommandLineRunner,ApplicationContextAware {

    private volatile ApplicationContext applicationContext;


    @Override
    public void run(String... strings) throws Exception {
        init();
    }

    public void init(){
        Collection<JobAware> jobList=new LinkedList<>(this.applicationContext.getBeansOfType(JobAware.class).values());
        for (JobAware jobAware:jobList){
            System.out.println(jobAware.getClass().getName());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
