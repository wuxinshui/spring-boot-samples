package com.wxs.quartz.service;

import com.wxs.quartz.conf.ScheduleJobInit;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * <p>JobManager</p>
 *
 * @author wuxinshui
 */
@Service
public class JobManagerService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobInit jobConfig;

    public List<JobInfo> selectAllJobs() throws Exception {
        try {
            JobDetail jobDetail = new JobDetailImpl();

            scheduler.addJob(jobDetail, false);
            List<JobInfo> jobInfoList = new ArrayList<>();
            return jobInfoList;
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController startJob", e);
            throw e;
        }
    }

    public void addJob(JobInfo jobVo) {
        try {
            Class jobClass = Class.forName(jobVo.getJobClass());
            JobDetail job1 = newJob(jobClass)
                    .withIdentity(jobVo.getJobName(), jobVo.getJobGroup())
                    .storeDurably()
                    .build();
            Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
                    .withIdentity(jobVo.getTriggerName(), jobVo.getTriggerGroup())
                    .build();

            scheduler.scheduleJob(job1, trigger);
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController addJob", e);
        }
    }

    public void pauseJob(String group, String name) throws Exception {
        try {
            JobKey jobKey = new JobKey(name, group);
            if (scheduler.checkExists(jobKey)) {
                scheduler.pauseJob(jobKey);
            }
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController pauseJob", e);
            throw e;
        }
    }

    public void resumeJob(String group, String name) throws Exception {
        try {
            JobKey jobKey = new JobKey(name, group);
            if (scheduler.checkExists(jobKey)) {
                scheduler.resumeJob(jobKey);
            }
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController pauseJob", e);
            throw e;
        }
    }


    public void deleteJob(String group, String name) throws Exception {
        try {
            JobKey jobKey = new JobKey(name, group);
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
            }
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController delJob", e);
            throw e;
        }
    }

    public void executeJob(String group, String name) throws Exception {
        try {
            JobKey jobKey = JobKey.jobKey(name, group);
            if (scheduler.checkExists(jobKey)) {
                scheduler.triggerJob(jobKey);
            }
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController delJob", e);
            throw e;
        }
    }

    public void scheduleJobs() throws Exception {
        try {
            jobConfig.run();
        } catch (Exception e) {
            LoggerUtil.error("SchedulingController delJob", e);
            throw e;
        }
    }

    public void listingAllJobs(Scheduler sched) throws SchedulerException {
        for (String group : sched.getJobGroupNames()) {
            // enumerate each job in group
            for (JobKey jobKey : sched.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
                System.out.println("Found job identified by: " + jobKey);
            }
        }
    }

}
