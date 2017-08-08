package com.wxs.quartz.conf;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: JobConfig
 * @author: [FujiRen]
 * @CreateDate: 2017/8/7 18:02
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/7 18:02
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@Configuration
public class JobConfig {
	@Bean(name = "scheduler")
	public Scheduler scheduler() {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			InitJob initJob = new InitJob();
			initJob.initScheduler();
			return scheduler;
		} catch (SchedulerException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


	//@Bean(name = "scheduler")
	//public Scheduler scheduler(CronTrigger cronTrigger) {
	//	SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
	//	factoryBean.setTriggers(cronTrigger);
	//	Scheduler scheduler = null;
	//	try {
	//		factoryBean.afterPropertiesSet();
	//		scheduler = factoryBean.getScheduler();
	//		scheduler.start();
	//	} catch (Exception e) {
	//		e.printStackTrace();
	//	}
	//	return scheduler;
	//}

	//@Bean(name = "cronTrigger")
	//public CronTrigger cronTrigger(JobDetail jobDetail) throws ParseException {
	//	CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
	//	cronTriggerFactoryBean.setJobDetail(jobDetail);
	//	cronTriggerFactoryBean.setName("trigger1");
	//	cronTriggerFactoryBean.setGroup("test");
	//	cronTriggerFactoryBean.setCronExpression("0/1 * * * * ?");
	//	cronTriggerFactoryBean.afterPropertiesSet();
	//	CronTrigger cronTrigger = cronTriggerFactoryBean.getObject();
	//	return cronTrigger;
	//}
	//
	//@Bean(name = "jobDetail")
	//public JobDetail jobDetail() {
	//	JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
	//	jobDetailFactoryBean.setGroup("test");
	//	jobDetailFactoryBean.setName("job1");
	//	jobDetailFactoryBean.setGroup("test");
	//	jobDetailFactoryBean.setJobClass(Job1.class);
	//	jobDetailFactoryBean.afterPropertiesSet();
	//	return jobDetailFactoryBean.getObject();
	//}
}
