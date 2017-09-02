package com.wxs.quartz.job;

import com.wxs.quartz.util.LoggerUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
public class Job2 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("Job2:This is quartz print test!!! " + System.currentTimeMillis());
		} catch (Exception e) {
			LoggerUtil.error("Job2 exception:",e);
			throw e;
		}
	}

}
