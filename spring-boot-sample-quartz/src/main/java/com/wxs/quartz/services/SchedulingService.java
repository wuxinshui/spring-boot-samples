package com.wxs.quartz.services;

import com.wxs.quartz.task.SayTask;
import com.wxs.quartz.task.PrintTask;
import com.wxs.quartz.task.Task;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TaskService
 * @author: [FujiRen]
 * @CreateDate: 2017/8/2 15:07
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/2 15:07
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@Service
public class SchedulingService {

	public Task selectById(Integer id) {
		if (id == 1) {
			Task printTask = new PrintTask();
			printTask.setTaskId("1");
			printTask.setTrigger("0/1 * *  * * * ");
			return printTask;
		} else if (id == 2) {
			Task printTask = new SayTask();
			printTask.setTaskId("1");
			printTask.setTrigger("0/1 * *  * * * ");
			return printTask;
		}
		return null;
	}
}
