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
1. 不使用注解 @DisallowConcurrentExecution不使用关键字synchronized
```
This is a test for concurrent--Mon Aug 14 23:57:39 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:39 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:42 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:42 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:45 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:45 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:48 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:48 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4]---Mon Aug 14 23:57:49 CST 2017
2017-08-14 23:57:49.021  INFO 8020 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Mon Aug 14 23:57:39 CST 2017
[1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4]---Mon Aug 14 23:57:49 CST 2017
2017-08-14 23:57:49.024  INFO 8020 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:57:39 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:51 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:51 CST 2017
[1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Mon Aug 14 23:57:52 CST 2017
2017-08-14 23:57:52.003  INFO 8020 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:57:42 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Mon Aug 14 23:57:52 CST 2017
2017-08-14 23:57:52.011  INFO 8020 --- [eduler_Worker-4] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Mon Aug 14 23:57:42 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:54 CST 2017
This is a test for concurrent--Mon Aug 14 23:57:54 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Mon Aug 14 23:57:55 CST 2017
2017-08-14 23:57:55.011  INFO 8020 --- [eduler_Worker-6] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Mon Aug 14 23:57:45 CST 2017
2017-08-14 23:57:55.026 ERROR 8020 --- [eduler_Worker-5] org.quartz.core.JobRunShell              : Job JobGroup3.Job3 threw an unhandled Exception: 

java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:38) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:23) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]

2017-08-14 23:57:55.028 ERROR 8020 --- [eduler_Worker-5] org.quartz.core.ErrorLogger              : Job (JobGroup3.Job3 threw an exception.

org.quartz.SchedulerException: Job threw an unhandled exception.
	at org.quartz.core.JobRunShell.run(JobRunShell.java:213) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]
Caused by: java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:38) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:23) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	... 1 common frames omitted

```

2. 使用注解 @DisallowConcurrentExecution不使用关键字synchronized

```
This is a test for concurrent--Mon Aug 14 23:54:18 CST 2017
This is a test for concurrent--Mon Aug 14 23:54:18 CST 2017
[1, 1, 2, 3, 4]---Mon Aug 14 23:54:28 CST 2017
[1, 2, 3, 4, 3, 4]---Mon Aug 14 23:54:28 CST 2017
2017-08-14 23:54:28.019  INFO 2288 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:54:18 CST 2017
2017-08-14 23:54:28.020  INFO 2288 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Mon Aug 14 23:54:18 CST 2017
This is a test for concurrent--Mon Aug 14 23:54:28 CST 2017
This is a test for concurrent--Mon Aug 14 23:54:28 CST 2017
[1, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Mon Aug 14 23:54:38 CST 2017
2017-08-14 23:54:38.028  INFO 2288 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Mon Aug 14 23:54:28 CST 2017
This is a test for concurrent--Mon Aug 14 23:54:38 CST 2017
[1, 4, 3, 4, 1, 2, 1, 2, 3, 4, 1, 2, 3, 4]---Mon Aug 14 23:54:38 CST 2017
2017-08-14 23:54:38.034  INFO 2288 --- [eduler_Worker-4] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Mon Aug 14 23:54:28 CST 2017
This is a test for concurrent--Mon Aug 14 23:54:38 CST 2017
[1, 3, 4, 1, 2, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4]---Mon Aug 14 23:54:48 CST 2017
```

3. 使用关键字synchronized不使用注解 @DisallowConcurrentExecution

```
This is a test for concurrent--Tue Aug 15 00:00:42 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:42 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:45 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:45 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:48 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:48 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:51 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:51 CST 2017
[1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:00:52 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:00:52 CST 2017
2017-08-15 00:00:52.210  INFO 11948 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:00:42 CST 2017
2017-08-15 00:00:52.210  INFO 11948 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:00:42 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:54 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:54 CST 2017
[1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:00:55 CST 2017
2017-08-15 00:00:55.002  INFO 11948 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:00:45 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:00:55 CST 2017
2017-08-15 00:00:55.031  INFO 11948 --- [eduler_Worker-4] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:00:45 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:57 CST 2017
This is a test for concurrent--Tue Aug 15 00:00:57 CST 2017
[1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:00:58 CST 2017
2017-08-15 00:00:58.007  INFO 11948 --- [eduler_Worker-5] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:00:48 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:00:58 CST 2017
2017-08-15 00:00:58.009  INFO 11948 --- [eduler_Worker-6] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:00:48 CST 2017
This is a test for concurrent--Tue Aug 15 00:01:00 CST 2017
This is a test for concurrent--Tue Aug 15 00:01:00 CST 2017
[1, 2, 1, 2, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:01:01 CST 2017
2017-08-15 00:01:01.007  INFO 11948 --- [eduler_Worker-8] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:00:51 CST 2017
2017-08-15 00:01:01.021 ERROR 11948 --- [eduler_Worker-7] org.quartz.core.JobRunShell              : Job JobGroup3.Job3 threw an unhandled Exception: 

java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:38) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:23) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]

2017-08-15 00:01:01.023 ERROR 11948 --- [eduler_Worker-7] org.quartz.core.ErrorLogger              : Job (JobGroup3.Job3 threw an exception.

org.quartz.SchedulerException: Job threw an unhandled exception.
	at org.quartz.core.JobRunShell.run(JobRunShell.java:213) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]
Caused by: java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:38) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:23) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	... 1 common frames omitted
```

4.使用关键字synchronized使用注解 @DisallowConcurrentExecution

```
This is a test for concurrent--Tue Aug 15 00:03:27 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:27 CST 2017
[1, 1, 2, 3, 4]---Tue Aug 15 00:03:37 CST 2017
[1, 2, 3, 4, 3, 4]---Tue Aug 15 00:03:37 CST 2017
2017-08-15 00:03:37.026  INFO 11100 --- [eduler_Worker-2] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:03:27 CST 2017
2017-08-15 00:03:37.026  INFO 11100 --- [eduler_Worker-1] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:03:27 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:37 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:37 CST 2017
[1, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:03:47 CST 2017
2017-08-15 00:03:47.037  INFO 11100 --- [eduler_Worker-3] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:03:37 CST 2017
[1, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:03:47 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:47 CST 2017
2017-08-15 00:03:47.041  INFO 11100 --- [eduler_Worker-4] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:03:37 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:47 CST 2017
[1, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:03:57 CST 2017
2017-08-15 00:03:57.042  INFO 11100 --- [eduler_Worker-5] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:03:47 CST 2017
[1, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:03:57 CST 2017
2017-08-15 00:03:57.045  INFO 11100 --- [eduler_Worker-6] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:03:47 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:57 CST 2017
This is a test for concurrent--Tue Aug 15 00:03:57 CST 2017
[1, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4]---Tue Aug 15 00:04:07 CST 2017
[1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:04:07 CST 2017
2017-08-15 00:04:07.048  INFO 11100 --- [eduler_Worker-7] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:03:57 CST 2017
2017-08-15 00:04:07.049  INFO 11100 --- [eduler_Worker-8] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:03:57 CST 2017
This is a test for concurrent--Tue Aug 15 00:04:07 CST 2017
This is a test for concurrent--Tue Aug 15 00:04:07 CST 2017
[1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4, 1, 2, 1, 2, 3, 4, 3, 4]---Tue Aug 15 00:04:17 CST 2017
2017-08-15 00:04:17.056  INFO 11100 --- [eduler_Worker-9] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job3,Trigger:TriggerGroup3.trigger3,FireTime:Tue Aug 15 00:04:07 CST 2017
This is a test for concurrent--Tue Aug 15 00:04:17 CST 2017
2017-08-15 00:04:17.070 ERROR 11100 --- [duler_Worker-10] org.quartz.core.JobRunShell              : Job JobGroup3.Job4 threw an unhandled Exception: 

java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:40) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:25) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]

2017-08-15 00:04:17.072 ERROR 11100 --- [duler_Worker-10] org.quartz.core.ErrorLogger              : Job (JobGroup3.Job4 threw an exception.

org.quartz.SchedulerException: Job threw an unhandled exception.
	at org.quartz.core.JobRunShell.run(JobRunShell.java:213) ~[quartz-2.2.1.jar:na]
	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573) [quartz-2.2.1.jar:na]
Caused by: java.util.ConcurrentModificationException: null
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901) ~[na:1.8.0_111]
	at java.util.ArrayList$Itr.next(ArrayList.java:851) ~[na:1.8.0_111]
	at java.util.AbstractCollection.toString(AbstractCollection.java:461) ~[na:1.8.0_111]
	at com.wxs.quartz.job.Job3.print(Job3.java:40) ~[classes/:na]
	at com.wxs.quartz.job.Job3.execute(Job3.java:25) ~[classes/:na]
	at org.quartz.core.JobRunShell.run(JobRunShell.java:202) ~[quartz-2.2.1.jar:na]
	... 1 common frames omitted

2017-08-15 00:04:17.073  INFO 11100 --- [duler_Worker-10] com.wxs.quartz.conf.QuartzJobListener    : JobClass:class com.wxs.quartz.job.Job3,Job:JobGroup3.Job4,Trigger:TriggerGroup3.trigger4,FireTime:Tue Aug 15 00:04:07 CST 2017
This is a test for concurrent--Tue Aug 15 00:04:17 CST 2017
```