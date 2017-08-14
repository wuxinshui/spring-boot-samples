# quartz任务并发

## Job的间隔时间小于执行时长

1. 不使用注解 @DisallowConcurrentExecution

```
This is a test for concurrent--Mon Aug 14 23:42:57 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:00 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:03 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:06 CST 2017
[1, 3, 4]---Mon Aug 14 23:43:07 CST 2017
2017-08-14 23:43:07.725  INFO 6980 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:42:57 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:09 CST 2017
[1, 3, 4]---Mon Aug 14 23:43:10 CST 2017
2017-08-14 23:43:10.007  INFO 6980 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:43:00 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:12 CST 2017
[1, 3, 4]---Mon Aug 14 23:43:13 CST 2017
2017-08-14 23:43:13.003  INFO 6980 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:43:03 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:15 CST 2017
[1, 3, 4]---Mon Aug 14 23:43:16 CST 2017
2017-08-14 23:43:16.003  INFO 6980 --- [eduler_Worker-4] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:43:06 CST 2017
This is a test for concurrent--Mon Aug 14 23:43:18 CST 2017
```
2. 使用注解 @DisallowConcurrentExecution

```
This is a test for concurrent--Mon Aug 14 23:44:57 CST 2017
[1, 3, 4]---Mon Aug 14 23:45:07 CST 2017
2017-08-14 23:45:07.919  INFO 2724 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:44:57 CST 2017
This is a test for concurrent--Mon Aug 14 23:45:07 CST 2017
[1, 3, 4]---Mon Aug 14 23:45:17 CST 2017
2017-08-14 23:45:17.928  INFO 2724 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:45:07 CST 2017
This is a test for concurrent--Mon Aug 14 23:45:17 CST 2017
[1, 3, 4]---Mon Aug 14 23:45:27 CST 2017
2017-08-14 23:45:27.934  INFO 2724 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:45:17 CST 2017
```

## 不同TriggerKey相同定时表达式