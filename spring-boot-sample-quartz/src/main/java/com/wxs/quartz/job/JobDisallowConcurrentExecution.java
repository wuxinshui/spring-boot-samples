package com.wxs.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * <p>JobDisallowConcurrentExecution</p>
 *
 * @author wuxinshui
 */
@DisallowConcurrentExecution
public class JobDisallowConcurrentExecution extends BaseJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("JobDisallowConcurrentExecution test ...");
    }
}
