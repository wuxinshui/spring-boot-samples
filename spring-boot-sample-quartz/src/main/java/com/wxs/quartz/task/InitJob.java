package com.wxs.quartz.task;

import com.wxs.quartz.mapper.JobInfoMapper;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
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
