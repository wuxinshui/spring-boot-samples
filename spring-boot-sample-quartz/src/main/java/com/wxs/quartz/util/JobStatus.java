package com.wxs.quartz.util;

/**
 * <p>JobStatus</p>
 *
 * @author wuxinshui
 */
public enum JobStatus {
    RUNNING(100), PAUSE(101), RESUME(102), DELETE(99);
    private int key;

    private JobStatus(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
