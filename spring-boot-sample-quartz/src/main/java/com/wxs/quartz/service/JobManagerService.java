package com.wxs.quartz.service;

import com.alibaba.druid.util.StringUtils;
import com.wxs.quartz.common.JobStatus;
import com.wxs.quartz.common.Result;
import com.wxs.quartz.conf.ScheduleJobInit;
import com.wxs.quartz.mapper.JobInfoMapper;
import com.wxs.quartz.mapper.ScheduleHisMapper;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.model.ScheduleHis;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

	@Autowired
	private ScheduleHisMapper scheduleHisMapper;

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


	public Result selectJobByGoupName(String jobGroup, String jobName) throws Exception {
		Result result = new Result();

		try {
			JobInfo jobInfo = jobInfoMapper.selectJobByJobKey(jobGroup, jobName);

			result.setData(jobInfo);

		} catch (Exception e) {
			LoggerUtil.error("JobManagerService pauseJob", e);
			throw e;
		}
		return result;
	}

	public Result addJob(JobInfo jobVo) throws Exception {
		Result result = new Result();
		JobKey jobKey = JobKey.jobKey(jobVo.getJobName(), jobVo.getJobGroup());
		String triggerName=jobVo.getTriggerName();
		String triggerGroup=jobVo.getTriggerGroup();

		if (StringUtils.isEmpty(triggerGroup) || StringUtils.isEmpty(triggerName)) {
			triggerName="Trigger_"+jobVo.getJobName();
			triggerGroup="Trigger_"+jobVo.getJobGroup();
		}

		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
		try {
			if (scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job已经存在。");
				return result;
			}

			Class jobClass = Class.forName(jobVo.getJobClass());
			JobDetail job1 = newJob(jobClass)
					.withIdentity(jobKey)
					.storeDurably()
					.build();
			Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
					.withIdentity(triggerKey)
					.build();

			scheduler.scheduleJob(job1, trigger);

			if (StringUtils.isEmpty(jobVo.getJobStatus())) {
				jobVo.setJobStatus(JobStatus.RUNNING.name());
			}
			jobInfoMapper.insert(jobVo);
		} catch (Exception e) {
			scheduler.deleteJob(jobKey);
			LoggerUtil.error("JobManagerService addJob", e);
			throw e;
		}
		return result;
	}

	public Result updateJob(JobInfo jobVo) throws Exception {
		Result result = new Result();

		JobKey jobKey = JobKey.jobKey(jobVo.getJobName(), jobVo.getJobGroup());

		String triggerName=jobVo.getTriggerName();
		String triggerGroup=jobVo.getTriggerGroup();

		if (StringUtils.isEmpty(triggerGroup) || StringUtils.isEmpty(triggerName)) {
			triggerName="Trigger_"+jobVo.getJobName();
			triggerGroup="Trigger_"+jobVo.getJobGroup();
		}

		TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
		try {

			if (!scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job不存在。");
				return result;
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
		return result;
	}

	public Result pauseJob(String group, String name) throws Exception {
		Result result = new Result();

		try {
			JobKey jobKey = JobKey.jobKey(name, group);

			if (!scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job不存在。");
				return result;
			}

			scheduler.pauseJob(jobKey);
			updateJobByJobKey(group, name, JobStatus.PAUSE);
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService pauseJob", e);
			throw e;
		}
		return result;
	}

	public Result resumeJob(String group, String name) throws Exception {
		Result result = new Result();

		try {
			JobKey jobKey = JobKey.jobKey(name, group);

			if (!scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job不存在。");
				return result;
			}

			scheduler.resumeJob(jobKey);
			updateJobByJobKey(group, name, JobStatus.RUNNING);
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService resumeJob", e);
			throw e;
		}
		return result;
	}


	public Result deleteJob(String group, String name) throws Exception {
		Result result = new Result();

		try {
			JobKey jobKey = JobKey.jobKey(name, group);
			if (!scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job不存在。");
				return result;
			}
			scheduler.deleteJob(jobKey);

			updateJobByJobKey(group, name, JobStatus.DELETE);
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService deleteJob", e);
			throw e;
		}

		return result;
	}

	public Result restartJob(String jobGroup, String jobName) throws Exception {

		Result result = new Result();

		try {
			JobInfo jobInfo = jobInfoMapper.selectJobByJobKey(jobGroup, jobName);

			JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
			TriggerKey triggerKey = TriggerKey.triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
			Class jobClass = Class.forName(jobInfo.getJobClass());

			JobDetail job = newJob(jobClass)
					.withIdentity(jobKey)
					.storeDurably()
					.build();
			Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression()))
					.withIdentity(triggerKey)
					.build();

			scheduler.scheduleJob(job, trigger);

			updateJobByJobKey(jobGroup, jobName, JobStatus.RUNNING);

		} catch (Exception e) {
			LoggerUtil.error("JobManagerService deleteJob", e);
			throw e;
		}

		return result;
	}

	public Result executeJob(String group, String name) throws Exception {
		Result result = new Result();

		try {
			JobKey jobKey = JobKey.jobKey(name, group);
			if (!scheduler.checkExists(jobKey)) {
				result.doErrorHandle("[" + jobKey + "]Job不存在。");
				return result;
			}
			scheduler.triggerJob(jobKey);
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService executeJob", e);
			throw e;
		}
		return result;
	}

	public Result scheduleJobs() throws Exception {
		Result result = new Result();
		try {
			jobConfig.run();
		} catch (Exception e) {
			LoggerUtil.error("JobManagerService scheduleJobs", e);
			throw e;
		}
		return result;
	}

	private Result updateJobByJobKey(String jobGroup, String jobName, JobStatus jobStatus) {
		Result result = new Result();
		jobInfoMapper.updateJobByJobKey(jobGroup, jobName, jobStatus.name());
		return result;
	}

	public void saveScheduleHis(JobExecutionContext context) throws Exception {
		try {
			ScheduleHis scheduleHis = new ScheduleHis();

			JobKey jobKey = context.getJobDetail().getKey();
			scheduleHis.setJobGroup(jobKey.getGroup());
			scheduleHis.setJobName(jobKey.getName());

			TriggerKey triggerKey = context.getTrigger().getKey();
			scheduleHis.setTriggerGroup(triggerKey.getGroup());
			scheduleHis.setTriggerName(triggerKey.getName());

			scheduleHis.setFiredTime(context.getFireTime());
			scheduleHis.setCreateTime(new Date());
			scheduleHis.setCreateUser("System");

			System.out.println(scheduleHis);
			System.out.println(scheduleHisMapper);

			//scheduleHisMapper.insert(scheduleHis);
		} catch (Exception e) {
			LoggerUtil.error("BaseJob saveScheduleHis", e);
			throw e;
		}
	}

	public Result listingAllJobs(Scheduler sched) throws SchedulerException {
		Result result = new Result();

		for (String group : sched.getJobGroupNames()) {
			// enumerate each job in group
			for (JobKey jobKey : sched.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
				System.out.println("Found job identified by: " + jobKey);
			}
		}
		return result;
	}

}
