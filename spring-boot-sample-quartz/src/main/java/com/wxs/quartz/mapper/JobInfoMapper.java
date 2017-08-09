package com.wxs.quartz.mapper;

import com.wxs.quartz.model.JobInfo;
import com.wxs.quartz.util.TKMapper;
import org.springframework.data.repository.query.Param;

public interface JobInfoMapper extends TKMapper<JobInfo> {
	void updateJobByJobKey(@Param("jobGroup") String jobGroup, @Param("jobName") String jobName, @Param("jobStatus") String jobStatus);

	JobInfo selectJobByJobKey(@Param("jobGroup") String jobGroup, @Param("jobName") String jobName);
}