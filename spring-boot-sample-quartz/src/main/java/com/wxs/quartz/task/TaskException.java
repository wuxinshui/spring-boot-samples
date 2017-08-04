package com.wxs.quartz.task;

import org.springframework.scheduling.SchedulingException;

/**
 * <p>TaskException</p>
 *
 * @author wuxinshui
 */
public class TaskException extends SchedulingException {
	public TaskException(String msg) {
		super(msg);
	}

	public TaskException(String msg, Exception e) {
		super(msg, e);
	}
}
