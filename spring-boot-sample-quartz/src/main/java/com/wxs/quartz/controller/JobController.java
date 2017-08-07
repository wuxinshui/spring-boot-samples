package com.wxs.quartz.controller;

import com.wxs.quartz.task.Job1;
import com.wxs.quartz.util.LoggerUtil;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.wxs.quartz.util.Constant.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
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

	@RequestMapping(value = "/add/{group}/{name}", method = RequestMethod.GET)
	public ModelMap addJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobDetail job1 = newJob(Job1.class)
					.withIdentity("job2", "test")
					.storeDurably()
					.build();
			Trigger trigger = newTrigger()
					.withIdentity("trigger1", "test")
					.startNow().forJob(job1)
					.build();
			;

			scheduler.scheduleJob(job1, (org.quartz.Trigger) trigger);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/pause/{group}/{name}", method = RequestMethod.GET)
	public ModelMap pauseJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			scheduler.pauseJob(jobKey);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}


	@RequestMapping(value = "/del/{group}/{name}", method = RequestMethod.GET)
	public ModelMap delJob(@PathVariable String group, @PathVariable String name) {
		try {
			JobKey jobKey = new JobKey(name, group);
			scheduler.deleteJob(jobKey);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}
}
