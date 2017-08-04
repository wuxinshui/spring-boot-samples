package com.wxs.quartz.controller;

import com.wxs.quartz.model.TaskInfo;
import com.wxs.quartz.services.TaskServiceImpl;
import com.wxs.quartz.task.ServiceException;
import com.wxs.quartz.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wxs.quartz.util.Constant.*;

/**
 * <p>SchedulingController</p>
 *
 * @author wuxinshui
 */
@RestController
@RequestMapping("/task")
public class QuartzController extends BaseController {

	@Autowired
	private TaskServiceImpl taskServiceImpl;


	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelMap startTask(TaskInfo info) {
		try {
			if (info.getId() == 0) {
				taskServiceImpl.addJob(info);
			} else {
				taskServiceImpl.edit(info);
			}
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/pause/{jobName}/{jobGroup}", method = RequestMethod.GET)
	public ModelMap pauseTask(@PathVariable String jobName, @PathVariable String jobGroup) {
		try {
			taskServiceImpl.pause(jobName, jobGroup);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/resume/{jobName}/{jobGroup}", produces = "application/json; charset=UTF-8")
	public ModelMap resume(@PathVariable String jobName, @PathVariable String jobGroup) {
		try {
			taskServiceImpl.resume(jobName, jobGroup);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (ServiceException e) {
			LoggerUtil.error("SchedulingController startTask", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/del/{jobName}/{jobGroup}", method = RequestMethod.GET)
	public ModelMap delTask(@PathVariable String jobName, @PathVariable String jobGroup) {
		try {
			taskServiceImpl.delete(jobName, jobGroup);

			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelMap searchAllTasks() {
		try {
			List<TaskInfo> infos = taskServiceImpl.list();
			return result(SUCCESS_CODE, SUCCESS_MSG, infos);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
			return result(FAIL_CODE, FAIL_MSG, null);
		}
	}

}
