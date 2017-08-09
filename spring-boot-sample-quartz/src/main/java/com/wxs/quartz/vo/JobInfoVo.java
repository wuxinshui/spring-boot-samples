package com.wxs.quartz.vo;

import java.util.Date;

/**
 * @ClassName: JobInfoVo
 * @author: [Wuxinshui]
 * @CreateDate: 2017/8/9 10:29
 * @UpdateUser: [Wuxinshui]
 * @UpdateDate: 2017/8/9 10:29
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class JobInfoVo {
	private Integer id;

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务分组
	 */
	private String jobGroup;

	/**
	 * 任务描述
	 */
	private String jobDescription;

	/**
	 * 任务状态
	 */
	private String jobStatus;

	/**
	 * 任务类
	 */
	private String jobClass;

	/**
	 * 触发器名称
	 */
	private String triggerName;

	/**
	 * 触发器分组
	 */
	private String triggerGroup;

	/**
	 * 触发器状态
	 */
	private String triggerStatus;

	/**
	 * 触发器描述
	 */
	private String triggerDescription;

	/**
	 * Cron表达式
	 */
	private String cronExpression;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改者
	 */
	private String updateUser;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
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

	public String getTriggerStatus() {
		return triggerStatus;
	}

	public void setTriggerStatus(String triggerStatus) {
		this.triggerStatus = triggerStatus;
	}

	public String getTriggerDescription() {
		return triggerDescription;
	}

	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "JobInfoVo{" +
				"id=" + id +
				", jobName='" + jobName + '\'' +
				", jobGroup='" + jobGroup + '\'' +
				", jobDescription='" + jobDescription + '\'' +
				", jobStatus='" + jobStatus + '\'' +
				", jobClass='" + jobClass + '\'' +
				", triggerName='" + triggerName + '\'' +
				", triggerGroup='" + triggerGroup + '\'' +
				", triggerStatus='" + triggerStatus + '\'' +
				", triggerDescription='" + triggerDescription + '\'' +
				", cronExpression='" + cronExpression + '\'' +
				", createUser='" + createUser + '\'' +
				", createTime=" + createTime +
				", updateUser='" + updateUser + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
