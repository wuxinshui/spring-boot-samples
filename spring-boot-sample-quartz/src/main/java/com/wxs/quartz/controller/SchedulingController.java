package com.wxs.quartz.controller;

import com.wxs.quartz.services.SchedulingService;
import com.wxs.quartz.task.TaskException;
import com.wxs.quartz.task.Task;
import com.wxs.quartz.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static com.wxs.quartz.util.Constant.*;

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

	private static final Map<String, Task> TASKS = new HashMap<>(12);
	private static final Map<String, ScheduledFuture<?>> SCHEDULED_FUTURE = new HashMap<>(16);
	private final static int POOL_SIZE = 64;

	private final ConcurrentTaskScheduler ct = new ConcurrentTaskScheduler(Executors.newScheduledThreadPool(POOL_SIZE));


	@RequestMapping(value = "/start/{taskId}", method = RequestMethod.GET)
	public ModelMap startTask(@PathVariable Integer taskId) {
		try {
			Task printTask = taskService.selectById(taskId);
			start(printTask);
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/stop/{taskId}", method = RequestMethod.GET)
	public ModelMap stopTask(@PathVariable Integer taskId) {
		try {
			stop(taskId.toString());
			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/del/{taskId}", method = RequestMethod.GET)
	public ModelMap delTask(@PathVariable Integer taskId) {
		try {
			stop(taskId.toString());

			return result(SUCCESS_CODE, SUCCESS_MSG, null);
		} catch (Exception e) {
			LoggerUtil.error("SchedulingController startTask", e);
		}
		return result(FAIL_CODE, FAIL_MSG, null);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
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
			LoggerUtil.info("the task with " + task.getTaskId() + "has bean already started.");
		} catch (Exception e) {
			LoggerUtil.info("the task starts failed", e);
			throw e;
		}
	}

	/**
	 * 停止一个计划任务.
	 *
	 * @param taskId 任务编号.
	 */
	public void stop(String taskId) throws TaskException {
		LoggerUtil.info("正在停止任务 " + taskId);
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
			LoggerUtil.info("the task stop failed", e);
			throw e;
		}
	}

	/**
	 * 重新设置当前任务的执行频率.
	 *
	 * @param taskId 任务编号.
	 */
	public void resetTrigger(String taskId, String cronExpression) throws TaskException {
		LoggerUtil.info("正在修改当前任务 " + taskId + "执行频率.");
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
		LoggerUtil.info("正在终止自动任务的线程池资源.");
		ScheduledExecutorService scheduledExecutor = (ScheduledExecutorService) ct.getConcurrentExecutor();
		try {
			scheduledExecutor.shutdownNow();
		} catch (Exception e) {
			LoggerUtil.info("自动任务的线程池资源清理发生异常.", e);
		} finally {
			LoggerUtil.info("自动任务的线程池资源清理完成.");
		}
	}
}
