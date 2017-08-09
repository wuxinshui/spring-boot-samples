package com.wxs.quartz.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_job_info")
public class JobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 任务分组
     */
    @Column(name = "job_group")
    private String jobGroup;

    /**
     * 任务描述
     */
    @Column(name = "job_description")
    private String jobDescription;

    /**
     * 任务状态
     */
    @Column(name = "job_status")
    private String jobStatus;

    /**
     * 任务类
     */
    @Column(name = "job_class")
    private String jobClass;

    /**
     * 触发器名称
     */
    @Column(name = "trigger_name")
    private String triggerName;

    /**
     * 触发器分组
     */
    @Column(name = "trigger_group")
    private String triggerGroup;

    /**
     * 触发器状态
     */
    @Column(name = "trigger_status")
    private String triggerStatus;

    /**
     * 触发器描述
     */
    @Column(name = "trigger_description")
    private String triggerDescription;

    /**
     * Cron表达式
     */
    @Column(name = "cron_expression")
    private String cronExpression;

    /**
     * 创建人
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
     * 获取任务名称
     *
     * @return job_name - 任务名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置任务名称
     *
     * @param jobName 任务名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取任务分组
     *
     * @return job_group - 任务分组
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * 设置任务分组
     *
     * @param jobGroup 任务分组
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 获取任务描述
     *
     * @return job_description - 任务描述
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * 设置任务描述
     *
     * @param jobDescription 任务描述
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * 获取任务状态
     *
     * @return job_status - 任务状态
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * 设置任务状态
     *
     * @param jobStatus 任务状态
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * 获取任务类
     *
     * @return job_class - 任务类
     */
    public String getJobClass() {
        return jobClass;
    }

    /**
     * 设置任务类
     *
     * @param jobClass 任务类
     */
    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    /**
     * 获取触发器名称
     *
     * @return trigger_name - 触发器名称
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * 设置触发器名称
     *
     * @param triggerName 触发器名称
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * 获取触发器分组
     *
     * @return trigger_group - 触发器分组
     */
    public String getTriggerGroup() {
        return triggerGroup;
    }

    /**
     * 设置触发器分组
     *
     * @param triggerGroup 触发器分组
     */
    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    /**
     * 获取触发器状态
     *
     * @return trigger_status - 触发器状态
     */
    public String getTriggerStatus() {
        return triggerStatus;
    }

    /**
     * 设置触发器状态
     *
     * @param triggerStatus 触发器状态
     */
    public void setTriggerStatus(String triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    /**
     * 获取触发器描述
     *
     * @return trigger_description - 触发器描述
     */
    public String getTriggerDescription() {
        return triggerDescription;
    }

    /**
     * 设置触发器描述
     *
     * @param triggerDescription 触发器描述
     */
    public void setTriggerDescription(String triggerDescription) {
        this.triggerDescription = triggerDescription;
    }

    /**
     * 获取Cron表达式
     *
     * @return cron_expression - Cron表达式
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * 设置Cron表达式
     *
     * @param cronExpression Cron表达式
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
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