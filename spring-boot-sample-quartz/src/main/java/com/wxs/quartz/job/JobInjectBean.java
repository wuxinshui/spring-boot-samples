package com.wxs.quartz.job;

import com.wxs.quartz.service.JobManagerService;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>JobInjectBean</p>
 * 注入业务Bean的Job
 * @author wuxinshui
 */
public class JobInjectBean extends BaseJob implements Job {

    //注入业务Bean
    @Autowired
    private JobManagerService jobManagerService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //保存执行记录
            jobManagerService.saveScheduleHis(context);
        } catch (Exception e) {
            LoggerUtil.error("JobInjectBean exception:",e);
            throw e;
        }
    }
}
