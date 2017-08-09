package com.wxs.quartz.service;

import com.wxs.quartz.common.Result;
import com.wxs.quartz.conf.ScheduleJobInit;
import com.wxs.quartz.mapper.JobInfoMapper;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private JobInfoMapper jobInfoMapper;

	public Result selectAllJobs() throws Exception {
		try {
			List<JobInfo> jobInfoList = jobInfoMapper.selectAll();
			;
			Result<List<JobInfo>> infoResult = new Result<>(Result.Code.SUCCESS, null, jobInfoList);
			return infoResult;
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService selectAllJobs", e);
			throw e;
		}
	}

	public void addJob(JobInfo jobVo) throws Exception {
		JobKey jobKey = JobKey.jobKey(jobVo.getJobName(), jobVo.getJobGroup());
		TriggerKey triggerKey = TriggerKey.triggerKey(jobVo.getTriggerName(), jobVo.getTriggerGroup());
		try {
			if (scheduler.checkExists(jobKey)) {
				updateJob(jobVo);
			} else {

				Class jobClass = Class.forName(jobVo.getJobClass());
				JobDetail job1 = newJob(jobClass)
						.withIdentity(jobKey)
						.storeDurably()
						.build();
				Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
						.withIdentity(triggerKey)
						.build();

				scheduler.scheduleJob(job1, trigger);

				jobInfoMapper.insert(jobVo);
			}
		} catch (Exception e) {
			scheduler.deleteJob(jobKey);
			LoggerUtil.error("JobManagerService addJob", e);
			throw e;
		}
	}

	public void updateJob(JobInfo jobVo) throws Exception {
		JobKey jobKey = JobKey.jobKey(jobVo.getJobName(), jobVo.getJobGroup());
		TriggerKey triggerKey = TriggerKey.triggerKey(jobVo.getTriggerName(), jobVo.getTriggerGroup());
		try {
			if (scheduler.checkExists(jobKey)) {
				scheduler.resumeJob(jobKey);
			}
			Class jobClass = Class.forName(jobVo.getJobClass());
			JobDetail job1 = newJob(jobClass)
					.withIdentity(jobKey)
					.storeDurably()
					.build();
			Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
					.withIdentity(triggerKey)
					.build();

			scheduler.addJob(job1, true);
			scheduler.rescheduleJob(triggerKey, trigger);
			jobInfoMapper.updateByPrimaryKey(jobVo);
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService addJob", e);
			throw e;
		}
	}

	public void pauseJob(String group, String name) throws Exception {
		try {
			JobKey jobKey = new JobKey(name, group);
			if (scheduler.checkExists(jobKey)) {
				scheduler.pauseJob(jobKey);
			}
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService pauseJob", e);
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
			LoggerUtil.error("JobManagerService resumeJob", e);
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
			LoggerUtil.error("JobManagerService deleteJob", e);
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
			LoggerUtil.error("JobManagerService executeJob", e);
			throw e;
		}
	}

	public void scheduleJobs() throws Exception {
		try {
			jobConfig.run();
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService scheduleJobs", e);
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
