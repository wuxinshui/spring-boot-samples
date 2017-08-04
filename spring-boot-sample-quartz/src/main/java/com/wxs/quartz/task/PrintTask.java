package com.wxs.quartz.task;

/**
 * @ClassName: PrintTask
 * @author: [FujiRen]
 * @CreateDate: 2017/8/2 14:59
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/2 14:59
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
public class PrintTask extends Task {
	@Override
	public void run() {
		System.out.println("这是一个打印任务！！！");
	}
}
