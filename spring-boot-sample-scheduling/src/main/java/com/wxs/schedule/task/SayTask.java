package com.wxs.schedule.task;

/**
 * @ClassName: SayTask
 * @author: [FujiRen]
 * @CreateDate: 2017/8/2 15:02
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/2 15:02
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class SayTask extends Task {
	@Override
	public void run() {
		System.out.println("这是一个说话的任务！");
	}
}
