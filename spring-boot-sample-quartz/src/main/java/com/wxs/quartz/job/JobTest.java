package com.wxs.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * <p>JobTest</p>
 *
 * @author wuxinshui
 */
//定义一个Job，必须实现org.quartz.Job 接口
public class JobTest implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("This is a test!!!" + new Date());
    }

    public static void main(String[] args) {
        try {

            //1.实例化并开始一个调度器Schedule
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // and start it off
            scheduler.start();

            //2.添加触发器
            // define the job and tie it to our MyJob class
            JobDetail jobDetail = newJob(JobTest.class).withIdentity("job1", "group1").build();
            // Trigger the job to run now, and then repeat every 40 seconds forever
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
