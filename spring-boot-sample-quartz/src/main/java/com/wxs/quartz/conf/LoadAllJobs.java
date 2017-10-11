package com.wxs.quartz.conf;

import com.wxs.quartzJob.core.JobAware;
import org.quartz.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * <p>LoadAllJobs</p>
 *
 * @author wuxinshui
 */
@Component
public class LoadAllJobs implements CommandLineRunner,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... strings) throws Exception {
        loadJobs();
    }

    private void loadJobs() throws ClassNotFoundException, SchedulerException {
        Collection<JobAware> jobAwares=new LinkedList<>(this.applicationContext.getBeansOfType(JobAware.class).values());
        for (JobAware jobAware:jobAwares){
           String className=jobAware.getClass().getName();
            Class jobClass = Class.forName(className);

            JobKey jobKey = JobKey.jobKey(className, className);
            TriggerKey triggerKey = TriggerKey.triggerKey(className, className);
            JobDetail job1 = newJob(jobClass)
                    .withIdentity(jobKey)
                    .storeDurably()
                    .build();
            Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ? "))
                    .withIdentity(triggerKey)
                    .build();
            scheduler.scheduleJob(job1, trigger);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
