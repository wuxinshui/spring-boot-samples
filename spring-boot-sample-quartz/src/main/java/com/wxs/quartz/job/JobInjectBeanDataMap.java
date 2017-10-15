package com.wxs.quartz.job;

import com.wxs.quartz.service.JobManagerService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: Wuxinshui
 * Date: 2017/9/4
 * Time: 17:13
 * To change this template use File | Settings | File Templates.
 */
public class JobInjectBeanDataMap implements Job {

    private JobManagerService jobManagerService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getMergedJobDataMap();
        jobManagerService= (JobManagerService) jobDataMap.get("jobManagerService");

        jobManagerService.saveScheduleHis(context);

        System.out.println("This is a test for inject bean with JobInjectBeanDataMap");
    }

}
