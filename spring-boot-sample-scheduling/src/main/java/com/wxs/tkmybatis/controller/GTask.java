package com.wxs.tkmybatis.controller;

/**
 * <p>GTask</p>
 *
 * @author wuxinshui
 */
public class GTask implements Runnable{
    private String taskId;
    private String trigger;

    public String getTaskId() {
        return taskId;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    @Override
    public void run() {

    }
}
