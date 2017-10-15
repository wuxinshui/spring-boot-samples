package com.wxs.quartz.job;

import com.wxs.quartz.service.JobManagerService;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created with IntelliJ IDEA.
 * User: Wuxinshui
 * Date: 2017/9/5
 * Time: 6:07
 * To change this template use File | Settings | File Templates.
 */

public class JobInjectBeanDataMapTest {

    @Autowired
    private JobManagerService jobManagerService;

    public static void main(String[] args) {
        JobInjectBeanDataMapTest test = new JobInjectBeanDataMapTest();
        test.test();
    }

    public void test() {
        try {

            //1.实例化并开始一个调度器Schedule
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();

            //2.添加触发器
            // define the job and tie it to our MyJob class
            JobDetail jobDetail = newJob(JobInjectBeanDataMap.class).withIdentity("job1", "group1").build();
            // Trigger the job to run now, and then repeat every 40 seconds forever
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
            jobDetail.getJobDataMap().put("jobManagerService", jobManagerService);
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}