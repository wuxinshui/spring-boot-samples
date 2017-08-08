package com.wxs.quartz.job;

import org.quartz.*;
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
