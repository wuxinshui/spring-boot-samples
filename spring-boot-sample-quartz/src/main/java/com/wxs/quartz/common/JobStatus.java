package com.wxs.quartz.common;

/**
 * <p>JobStatus</p>
 *
 * @author wuxinshui
 */
public enum JobStatus {

    /**
     * 运行
     */
    RUNNING(100),
    /**
     * 暂停
     */
    PAUSE(101),
    /**
     * 恢复
     */
    RESUME(102),
    /**
     * 删除
     */
    DELETE(99);

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
