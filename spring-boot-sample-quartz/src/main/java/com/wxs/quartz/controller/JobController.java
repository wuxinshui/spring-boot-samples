package com.wxs.quartz.controller;

import com.wxs.quartz.task.Job2;
import com.wxs.quartz.util.LoggerUtil;
import com.wxs.quartz.vo.JobVo;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wxs.quartz.util.Constant.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName: JobController
 * @author: [FujiRen]
 * @CreateDate: 2017/8/7 18:22
 * @UpdateUser: [Wuxinshui]
 * @UpdateDate: 2017/8/7 18:22
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@RestController
@RequestMapping(value = "/job")
public class JobController extends BaseController {

	@Autowired
	private Scheduler scheduler;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelMap selectAllJobs() {
		try {
			JobDetail jobDetail = new JobDetailImpl();

			scheduler.addJob(jobDetail, false);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelMap addJob(@RequestBody JobVo jobVo) {
		try {
			JobDetail job1 = newJob(Job2.class)
					.withIdentity(jobVo.getJobName(), jobVo.getJobGroup())
					.storeDurably()
					.build();
			Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
					.withIdentity(jobVo.getTriggerName(), jobVo.getTriggerGroup())
					.build();

			scheduler.scheduleJob(job1, trigger);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController addJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/pause/{group}/{name}", method = RequestMethod.GET)
	public ModelMap pauseJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			scheduler.pauseJob(jobKey);
			listingAllJobs(scheduler);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController pauseJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/resume/{group}/{name}", method = RequestMethod.GET)
	public ModelMap resumeJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			scheduler.resumeJob(jobKey);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController pauseJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}


	@RequestMapping(value = "/del/{group}/{name}", method = RequestMethod.GET)
	public ModelMap delJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			if (scheduler.checkExists(jobKey)) {
				scheduler.deleteJob(jobKey);
			}

			listingAllJobs(scheduler);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/execute/{group}/{name}", method = RequestMethod.GET)
	public ModelMap executeJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			TriggerKey triggerKey = new TriggerKey(name, group);
			if (scheduler.checkExists(jobKey)) {
				JobDetail jobDetail = scheduler.getJobDetail(jobKey);
				scheduler.getTriggersOfJob(jobKey);
				List<JobExecutionContext> jobExecutionContextList = scheduler.getCurrentlyExecutingJobs();
				for (JobExecutionContext context : jobExecutionContextList) {
					Job job = context.getJobInstance();
					System.out.println(job + "----------------------");
					job.execute(context);
				}
				//Class jobClass = jobDetail.getJobClass();
				//Method method = jobClass.getDeclaredMethod("execute");
				//method.invoke("");
			}

			listingAllJobs(scheduler);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
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
