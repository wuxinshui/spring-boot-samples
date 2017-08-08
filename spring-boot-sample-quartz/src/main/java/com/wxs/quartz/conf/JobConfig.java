package com.wxs.quartz.conf;

import com.wxs.quartz.task.InitJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;

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
    public Scheduler scheduler(CronTrigger cronTrigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(cronTrigger);
        Scheduler scheduler = null;
        try {
            factoryBean.afterPropertiesSet();
            scheduler = factoryBean.getScheduler();
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scheduler;
    }

    @Bean(name = "cronTrigger")
    public CronTrigger cronTrigger(JobDetail jobDetail) throws ParseException {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetail);
        cronTriggerFactoryBean.setName("InitJobTrigger");
        cronTriggerFactoryBean.setGroup("Default");
        cronTriggerFactoryBean.setCronExpression("0/1 * * * * ?");
        cronTriggerFactoryBean.afterPropertiesSet();
        CronTrigger cronTrigger = cronTriggerFactoryBean.getObject();
        return cronTrigger;
    }

    @Bean(name = "jobDetail")
    public JobDetail jobDetail() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setGroup("Default");
        jobDetailFactoryBean.setName("InitJob");
        jobDetailFactoryBean.setJobClass(InitJob.class);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }
}
