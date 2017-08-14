package com.wxs.quartz.conf;

import com.wxs.quartz.mapper.ScheduleHisMapper;
import com.wxs.quartz.service.JobManagerService;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Date;

/**
 * @ClassName: QuartzJobListener
 * @author: [FujiRen]
 * @CreateDate: 2017/8/10 13:36
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/10 13:36
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class QuartzJobListener extends JobListenerSupport {

	private static ApplicationContext applicationContext = null;

	@Autowired
	private JobManagerService jobManagerService;

	private String name;

	public QuartzJobListener(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context,
							   JobExecutionException jobException) {
		try {
			JobKey jobKey = context.getJobDetail().getKey();

			TriggerKey triggerKey = context.getTrigger().getKey();

			Date fireTime=context.getFireTime();

			Class jobClass=context.getJobDetail().getJobClass();

			LoggerUtil.info("JobClass:{},Job:{},Trigger:{},FireTime:{}",jobClass,jobKey,triggerKey,fireTime);
			//保存执行记录
			//JobManagerService jobManagerService=new JobManagerService();
			//jobManagerService.saveScheduleHis(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
