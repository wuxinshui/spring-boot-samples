package com.wxs.quartz.mapper;

import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.util.TKMapper;

public interface JobInfoMapper extends TKMapper<JobInfo> {
	void updateJobByJobKey(String jobGroup,String jobName,String jobStatus);

	JobInfo selectJobByJobKey(String jobGroup,String jobName);
}