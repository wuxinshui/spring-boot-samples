package com.wxs.quartzJob.job;

import com.wxs.quartzJob.core.JobAware;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * <p>Job4</p>
 *
 * @author wuxinshui
 */
@Component
public class Job4 implements JobAware {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("This is Job4");
    }
}
