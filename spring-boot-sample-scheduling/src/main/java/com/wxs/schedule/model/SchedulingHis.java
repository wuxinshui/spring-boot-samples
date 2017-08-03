package com.wxs.schedule.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_scheduling_his")
public class SchedulingHis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * job名称
	 */
	@Column(name = "job_id")
	private String jobId;

	/**
	 * cron表达式
	 */
	@Column(name = "execute_time")
	private Date executeTime;

	/**
	 * 创建者
	 */
	@Column(name = "create_user")
	private String createUser;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改者
	 */
	@Column(name = "update_user")
	private String updateUser;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取job名称
	 *
	 * @return job_id - job名称
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * 设置job名称
	 *
	 * @param jobId job名称
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 获取cron表达式
	 *
	 * @return execute_time - cron表达式
	 */
	public Date getExecuteTime() {
		return executeTime;
	}

	/**
	 * 设置cron表达式
	 *
	 * @param executeTime cron表达式
	 */
	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return create_user - 创建者
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 设置创建者
	 *
	 * @param createUser 创建者
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取修改者
	 *
	 * @return update_user - 修改者
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 设置修改者
	 *
	 * @param updateUser 修改者
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}