package com.wxs.quartz.conf;

import com.wxs.quartz.common.JobStatus;
import com.wxs.quartz.mapper.JobInfoMapper;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.service.JobManagerService;
import com.wxs.quartz.util.BeanUtils;
import com.wxs.quartz.vo.JobInfoVo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.EverythingMatcher.allJobs;

/**
 * @ClassName: InitJobConfig
 * @author: [Wuxinshui]
 * @CreateDate: 2017/8/8 14:37
 * @UpdateUser: [Wuxinshui]
 * @UpdateDate: 2017/8/8 14:37
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@Component
public class ScheduleJobInit implements CommandLineRunner {

    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobManagerService jobManagerService;

    @Override
    public void run(String... strings) throws Exception {
        //查询数据库中的Job
        List<JobInfo> jobInfoList = jobInfoMapper.selectAll();
        List<JobInfoVo> jobInfoVoList = BeanUtils.copyList(jobInfoList, JobInfoVo.class);

        //根据数据库中Job的状态同步scheduler中的状态。
        for (int i = 0; i < jobInfoVoList.size(); i++) {
            JobInfoVo jobVo = jobInfoVoList.get(i);
            Class jobClass = Class.forName(jobVo.getJobClass());

            JobKey jobKey = JobKey.jobKey(jobVo.getJobName(), jobVo.getJobGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(jobVo.getTriggerName(), jobVo.getTriggerGroup());
            JobDetail job1 = newJob(jobClass)
                    .withIdentity(jobKey)
                    .storeDurably()
                    .build();
            Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
                    .withIdentity(triggerKey)
                    .build();

            JobStatus jobStatus = JobStatus.valueOf(jobVo.getJobStatus());
            switch (jobStatus) {
                case RUNNING:
                    scheduler.scheduleJob(job1, trigger);
                    break;
                case PAUSE:
                    scheduler.scheduleJob(job1, trigger);
                    scheduler.pauseJob(jobKey);
            }
        }
        //添加Job监听器
        QuartzJobListener quartzJobListener = new QuartzJobListener("quartzListener",jobManagerService);
        scheduler.getListenerManager().addJobListener(quartzJobListener, allJobs());
    }
}
