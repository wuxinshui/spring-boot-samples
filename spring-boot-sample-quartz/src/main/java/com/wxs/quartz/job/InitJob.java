package com.wxs.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Controller;

/**
 * <p>InitJobConfig</p>
 *
 * @author wuxinshui
 */
@Controller
public class InitJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    }
}
