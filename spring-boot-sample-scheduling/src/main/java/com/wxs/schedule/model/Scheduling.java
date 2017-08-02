package com.wxs.schedule.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_scheduling")
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * job名称
     */
    private String job;

    @Column(name = "task_class")
    private String taskClass;

    /**
     * 执行类
     */
    @Column(name = "execute_class")
    private String executeClass;

    /**
     * cron表达式
     */
    @Column(name = "trigger_cron")
    private String triggerCron;

    /**
     * 当前job状态
     */
    private String status;

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
     * @return job - job名称
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置job名称
     *
     * @param job job名称
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return task_class
     */
    public String getTaskClass() {
        return taskClass;
    }

    /**
     * @param taskClass
     */
    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    /**
     * 获取执行类
     *
     * @return execute_class - 执行类
     */
    public String getExecuteClass() {
        return executeClass;
    }

    /**
     * 设置执行类
     *
     * @param executeClass 执行类
     */
    public void setExecuteClass(String executeClass) {
        this.executeClass = executeClass;
    }

    /**
     * 获取cron表达式
     *
     * @return trigger_cron - cron表达式
     */
    public String getTriggerCron() {
        return triggerCron;
    }

    /**
     * 设置cron表达式
     *
     * @param triggerCron cron表达式
     */
    public void setTriggerCron(String triggerCron) {
        this.triggerCron = triggerCron;
    }

    /**
     * 获取当前job状态
     *
     * @return status - 当前job状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置当前job状态
     *
     * @param status 当前job状态
     */
    public void setStatus(String status) {
        this.status = status;
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