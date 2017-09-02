package com.wxs.quartz.conf;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: JobConfig
 * @author: [Wuxinshui]
 * @CreateDate: 2017/8/7 18:02
 * @UpdateUser: [Wuxinshui]
 * @UpdateDate: 2017/8/7 18:02
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@Configuration
public class QuartzConfig {

    @Bean(name = "scheduler")
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory) throws Exception {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setJobFactory(quartzJobFactory);
        factoryBean.afterPropertiesSet();
        Scheduler scheduler = factoryBean.getScheduler();

        //延迟60秒启动
        scheduler.startDelayed(60);
        return scheduler;
    }

    //解决Job中注入Spring Bean为null的问题
    @Component("quartzJobFactory")
    private class QuartzJobFactory extends AdaptableJobFactory {
        //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
        @Autowired
        private AutowireCapableBeanFactory capableBeanFactory;

        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            //调用父类的方法
            Object jobInstance = super.createJobInstance(bundle);
            //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
            capableBeanFactory.autowireBean(jobInstance);
            return jobInstance;
        }
    }

    //@Bean(name = "scheduler")
    //public Scheduler scheduler(CronTrigger cronTrigger) throws SchedulerException {
    //
    //    SchedulerFactory sf = new StdSchedulerFactory();
    //    Scheduler scheduler = sf.getScheduler();
    //
    //    SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
    //    factoryBean.setSchedulerFactoryClass(StdSchedulerFactory.class);
    //    factoryBean.setTriggers(cronTrigger);
    //    Scheduler scheduler = null;
    //    try {
    //        factoryBean.afterPropertiesSet();
    //        scheduler = factoryBean.getScheduler();
    //        //scheduler.startDelayed(60);
    //        scheduler.start();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return scheduler;
    //}
    //
    //@Bean(name = "cronTrigger")
    //public CronTrigger cronTrigger(JobDetail jobDetail) throws ParseException {
    //    CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
    //    cronTriggerFactoryBean.setJobDetail(jobDetail);
    //    cronTriggerFactoryBean.setName("InitJobTrigger");
    //    cronTriggerFactoryBean.setGroup("Default");
    //    cronTriggerFactoryBean.setCronExpression("0/1 * * * * ?");
    //    cronTriggerFactoryBean.afterPropertiesSet();
    //    CronTrigger cronTrigger = cronTriggerFactoryBean.getObject();
    //    return cronTrigger;
    //}
    //
    //@Bean(name = "jobDetail")
    //public JobDetail jobDetail() {
    //    JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
    //    jobDetailFactoryBean.setGroup("Default");
    //    jobDetailFactoryBean.setName("InitJob");
    //    jobDetailFactoryBean.setJobClass(InitJob.class);
    //    jobDetailFactoryBean.afterPropertiesSet();
    //    return jobDetailFactoryBean.getObject();
    //}
}
