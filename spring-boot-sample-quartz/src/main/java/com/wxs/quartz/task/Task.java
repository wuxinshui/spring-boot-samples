package com.wxs.quartz.task;

/**
 * <p>Task</p>
 *
 * @author wuxinshui
 */
public abstract class Task {
	private String taskId;
	private String trigger;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	@Override
	public String toString() {
		return "Task{" +
				"taskId='" + taskId + '\'' +
				", trigger='" + trigger + '\'' +
				'}';
	}
}
