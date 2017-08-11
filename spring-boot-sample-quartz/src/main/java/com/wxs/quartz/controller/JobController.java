package com.wxs.quartz.controller;

import com.wxs.quartz.common.Result;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.service.JobManagerService;
import com.wxs.quartz.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static com.wxs.quartz.common.Constant.*;

/**
 * @ClassName: JobController
 * @author: [Wuxinshui]
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
	private JobManagerService jobManagerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelMap selectAllJobs() {
		try {
			Result jobInfoList = jobManagerService.selectAllJobs();
			if (jobInfoList.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, jobInfoList.getData());
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startJob", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}


	@RequestMapping(value = "/select/{group}/{name}", method = RequestMethod.GET)
	public ModelMap selectJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.selectJobByGoupName(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController addJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelMap addJob(@RequestBody JobInfo jobVo) {
		try {
			Result result = jobManagerService.addJob(jobVo);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController addJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelMap updateJob(@RequestBody JobInfo jobVo) {
		try {
			Result result = jobManagerService.updateJob(jobVo);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController addJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/pause/{group}/{name}", method = RequestMethod.GET)
	public ModelMap pauseJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.pauseJob(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController pauseJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/resume/{group}/{name}", method = RequestMethod.GET)
	public ModelMap resumeJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.resumeJob(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController pauseJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}


	@RequestMapping(value = "/del/{group}/{name}", method = RequestMethod.GET)
	public ModelMap delJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.deleteJob(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/restartJob/{group}/{name}", method = RequestMethod.GET)
	public ModelMap restartJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.restartJob(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/execute/{group}/{name}", method = RequestMethod.GET)
	public ModelMap executeJob(@PathVariable String group, @PathVariable String name) {
		try {
			Result result = jobManagerService.executeJob(group, name);
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public ModelMap scheduleAllJobs() {
		try {
			Result result = jobManagerService.scheduleJobs();
			if (result.isSuccess()) {
				return result(SUCCESS_CODE, SUCCESS_MSG, null);
			} else {
				return result(FAIL_CODE, result.getMessage(), null);
			}
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController delJob", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}
}
