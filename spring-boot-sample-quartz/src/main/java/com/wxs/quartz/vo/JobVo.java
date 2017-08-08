package com.wxs.quartz.vo;

import java.io.Serializable;

/**
 * @ClassName: JobVo
 * @author: [Wuxinshui]
 * @CreateDate: 2017/8/8 9:39
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/8 9:39
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class JobVo implements Serializable{
	//Job名称
	private String jobName;
	//Job所属组名
	private String jobGroup;
	//触发器名称
	private String triggerName;
	//触发器所属组名
	private String triggerGroup;
	//cron表达式
	private String cronExpression;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
}
