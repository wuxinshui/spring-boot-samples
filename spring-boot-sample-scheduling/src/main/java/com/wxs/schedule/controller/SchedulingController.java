package com.wxs.schedule.controller;

import com.wxs.schedule.services.SchedulingService;
import com.wxs.schedule.task.PrintTask;
import com.wxs.schedule.task.Task;
import com.wxs.schedule.task.TaskException;
import com.wxs.schedule.util.LoggerUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static com.wxs.schedule.util.Constant.*;

/**
 * <p>SchedulingController</p>
 *
 * @author wuxinshui
 */
@RestController
@RequestMapping("/schedule")
public class SchedulingController extends BaseController {

	@Autowired
	private SchedulingService taskService;

	/**
	 * logger日志.
	 */
	public static final Logger LOGGER = Logger.getLogger(SchedulingController.class);
	private static final Map<String, Task> TASKS = new HashMap<>(12);
	private static final Map<String, ScheduledFuture<?>> SCHEDULED_FUTURE = new HashMap<>(16);
	private final static int POOL_SIZE = 64;

	private final ConcurrentTaskScheduler ct = new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(POOL_SIZE));


	@RequestMapping(value = "/start/{taskId}", method = RequestMethod.GET)
	public ModelMap startTask(Integer taskId) {
		try {
			PrintTask printTask = taskService.selectById(taskId);
			start(printTask);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public ModelMap stopTask(Integer taskId) {
		try {
			stop(taskId.toString());
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ModelMap delTask(Integer taskId) {
		try {
			stop(taskId.toString());

			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelMap searchAllTasks() {
		try {
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}


	/**
	 * 启动一个计划任务.
	 *
	 * @param task 当前进行的任务.
	 */
	public void start(Task task) throws Exception {
		try {
			if (StringUtils.isEmpty(task.getTaskId())) {
				throw new TaskException("the taskid must be not empty.");
			}

			if (StringUtils.isEmpty(task.getTrigger())) {
				throw new TaskException("任务的调度表达式不能为空.");
			}

			ScheduledFuture<?> scheduledFuture = ct.schedule(task, new CronTrigger(task.getTrigger()));
			SCHEDULED_FUTURE.put(task.getTaskId(), scheduledFuture);
			TASKS.put(task.getTaskId(), task);
			LOGGER.info("the task with " + task.getTaskId() + "has bean already started.");
		} catch (Exception e) {
			LOGGER.info(null, e);
			throw new TaskException(e);
		}
	}

	/**
	 * 停止一个计划任务.
	 *
	 * @param taskId 任务编号.
	 */
	public void stop(String taskId) throws TaskException {
		LOGGER.info("正在停止任务 " + taskId);
		if (StringUtils.isEmpty(taskId)) {
			throw new TaskException("the taskid must be not empty.");
		}

		try {
			ScheduledFuture<?> scheduledFuture = SCHEDULED_FUTURE.remove(taskId);
			if (scheduledFuture == null) {
				throw new TaskException("the task with id " + taskId + " is not exists.");
			} else {
				if (!scheduledFuture.isCancelled()) {
					/** false 表示当前任务若正在执行，则待其执行结束，再结束此任务. */
					scheduledFuture.cancel(false);
				}
			}
		} catch (Exception e) {
			LOGGER.info(null, e);
			throw new TaskException(e);
		}
	}

	/**
	 * 重新设置当前任务的执行频率.
	 *
	 * @param taskId 任务编号.
	 */
	public void resetTrigger(String taskId, String cronExpression) throws TaskException {
		LOGGER.info("正在修改当前任务 " + taskId + "执行频率.");
		if (StringUtils.isEmpty(taskId)) {
			throw new TaskException("the taskid must be not empty.");
		}

		if (StringUtils.isEmpty(cronExpression)) {
			throw new TaskException("任务的调度表达式不能为空.");
		}

		Task task = TASKS.get(taskId);
		if (task != null) {
			if (cronExpression.equals(task.getTrigger())) {
				return;
			}

			/** first, stop the task. */
			ScheduledFuture<?> scheduledFuture = SCHEDULED_FUTURE.remove(taskId);
			scheduledFuture.cancel(false);

			/** second, reset the task with cronExpression. */
			task.setTrigger(cronExpression);
			/** third, restart the task. */
			scheduledFuture = ct.schedule(task, new CronTrigger(cronExpression));
			SCHEDULED_FUTURE.put(taskId, scheduledFuture);
		}
	}

	/**
	 * 仅执行一次.
	 *
	 * @param task 所要执行任务.
	 */
	public void onlyOneTime(Task task) throws TaskException {
		if (StringUtils.isEmpty(task.getTaskId())) {
			throw new TaskException("the taskid must be not empty.");
		}

		ct.execute(task, 2000);
	}

	/**
	 * 销毁线程池中的任务.
	 */
	public void destrory() {
		LOGGER.info("正在终止自动任务的线程池资源.");
		ScheduledExecutorService scheduledExecutor = (ScheduledExecutorService) ct.getConcurrentExecutor();
		try {
			scheduledExecutor.shutdownNow();
		} catch (Exception e) {
			LOGGER.info("自动任务的线程池资源清理发生异常.", e);
		} finally {
			LOGGER.info("自动任务的线程池资源清理完成.");
		}
	}
}
