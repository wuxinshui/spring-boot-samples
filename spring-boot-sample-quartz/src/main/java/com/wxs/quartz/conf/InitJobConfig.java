package com.wxs.quartz.conf;

import com.wxs.quartz.mapper.JobInfoMapper;
import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.util.BeanUtils;
import com.wxs.quartz.vo.JobInfoVo;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName: InitJobConfig
 * @author: [FujiRen]
 * @CreateDate: 2017/8/8 14:37
 * @UpdateUser: [FujiRen]
 * @UpdateDate: 2017/8/8 14:37
 * @UpdateRemark: [说明本次修改内容]
 * @Description: [TODO(用一句话描述该文件做什么)]
 * @version: [V1.0]
 */
@Component
public class InitJobConfig implements CommandLineRunner {

	@Autowired
	private JobInfoMapper jobInfoMapper;

	@Autowired
	private Scheduler scheduler;

	@Override
	public void run(String... strings) throws Exception {
		List<JobInfo> jobInfoList = jobInfoMapper.selectAll();

		List<JobInfoVo> jobInfoVoList = BeanUtils.copyList(jobInfoList, JobInfoVo.class);

		for (int i = 0; i < jobInfoVoList.size(); i++) {
			JobInfoVo jobVo = jobInfoVoList.get(i);
			Class jobClass = Class.forName(jobVo.getJobClass());
			JobDetail job1 = newJob(jobClass)
					.withIdentity(jobVo.getJobName(), jobVo.getJobGroup())
					.storeDurably()
					.build();
			Trigger trigger = newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobVo.getCronExpression()))
					.withIdentity(jobVo.getTriggerName(), jobVo.getTriggerGroup())
					.build();
			scheduler.scheduleJob(job1, trigger);
		}
	}
}
