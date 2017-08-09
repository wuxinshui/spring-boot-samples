package com.wxs.quartzJob.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @ClassName: Job1
 * @author: [Wuxinshui]
 * @CreateDate: 2017/8/7 18:21
 * @UpdateUser: [Wuxinshui]
 * @UpdateDate: 2017/8/7 18:21
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class Job1 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Job1:This is quartz print test!!! " + new Date());
    }

}
