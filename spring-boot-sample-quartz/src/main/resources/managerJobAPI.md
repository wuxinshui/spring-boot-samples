## 手动开启所有Job
GET:`http://localhost:9090/job/schedule`

Response:
```
{
      "code": "200",
      "msg": "SUCCESS",
      "result": null
  }
```

## 新增Job
   POST:`http://172.16.36.73:9090/job/add`
   
   Response:
   ```
    {
               "jobName": "Job2",
               "jobGroup": "JobGroup3",
               "jobDescription": "JobGroup.Job2",
               "jobStatus": "GOING",
               "jobClass": "com.wxs.quartz.job.Job2",
               "triggerName": "trigger2",
               "triggerGroup": "TriggerGroup3",
               "triggerStatus": "GOING",
               "triggerDescription": "TriggerGroup.trigger1",
               "cronExpression": "0/1 * * * * ?"
   }
   ```
   
   ## 更新Job
   POST:`http://172.16.36.73:9090/job/add`
   
   Response:
   ```
    {
               "jobName": "Job2",
               "jobGroup": "JobGroup3",
               "jobDescription": "JobGroup.Job2",
               "jobStatus": "GOING",
               "jobClass": "com.wxs.quartz.job.Job2",
               "triggerName": "trigger2",
               "triggerGroup": "TriggerGroup3",
               "triggerStatus": "GOING",
               "triggerDescription": "TriggerGroup.trigger1",
               "cronExpression": "0/1 * * * * ?"
   }
   ```

## 暂停Job

GET:`http://localhost:9090/job/pause/JobGroup1/Job1`
Response:
```
{
      "code": "200",
      "msg": "SUCCESS",
      "result": null
  }
```

## 恢复Job

GET:`http://localhost:9090/job/resume/JobGroup1/Job1`

Response:
```
{
      "code": "200",
      "msg": "SUCCESS",
      "result": null
  }
```

## 删除Job

GET:`http://localhost:9090/job/del/JobGroup2/Job2`

Response:
```
{
      "code": "200",
      "msg": "SUCCESS",
      "result": null
  }
```

## 执行Job
GET:`http://localhost:9090/job/execute/JobGroup2/Job2`

Response:
```
{
      "code": "200",
      "msg": "SUCCESS",
      "result": null
  }
```

## Job列表

GET:`http://localhost:9090/job/list` 
  
Response:
```
{
    "code": "200",
    "msg": "SUCCESS",
    "result": [
        {
            "id": 1,
            "jobName": "Job1",
            "jobGroup": "JobGroup1",
            "jobDescription": "JobGroup.Job1",
            "jobStatus": "RUNNING",
            "jobClass": "com.wxs.quartz.job.Job1",
            "triggerName": "trigger1",
            "triggerGroup": "TriggerGroup1",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup.trigger1",
            "cronExpression": "0/1 * * * * ?",
            "createUser": null,
            "createTime": "2017-08-08 15:25:10",
            "updateUser": null,
            "updateTime": "2017-08-08 15:25:10"
        },
        {
            "id": 2,
            "jobName": "Job2",
            "jobGroup": "JobGroup2",
            "jobDescription": "JobGroup.Job2",
            "jobStatus": "RUNNING",
            "jobClass": "com.wxs.quartz.job.Job2",
            "triggerName": "trigger2",
            "triggerGroup": "TriggerGroup2",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup.trigger1",
            "cronExpression": "0/1 * * * * ?",
            "createUser": "",
            "createTime": "2017-08-08 15:25:10",
            "updateUser": "",
            "updateTime": "2017-08-08 15:25:10"
        },
        {
            "id": 3,
            "jobName": "Job2",
            "jobGroup": "JobGroup3",
            "jobDescription": "JobGroup.Job2",
            "jobStatus": "DELETE",
            "jobClass": "com.wxs.quartz.job.Job2",
            "triggerName": "trigger2",
            "triggerGroup": "TriggerGroup3",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup.trigger1",
            "cronExpression": "0/1 * * * * ?",
            "createUser": null,
            "createTime": "2017-08-09 15:42:08",
            "updateUser": null,
            "updateTime": "2017-08-09 14:48:47"
        },
        {
            "id": 4,
            "jobName": "Job2",
            "jobGroup": "JobGroup1",
            "jobDescription": "JobGroup1.Job2",
            "jobStatus": "PAUSE",
            "jobClass": "com.wxs.quartz.job.Job1",
            "triggerName": "trigger2",
            "triggerGroup": "TriggerGroup1",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup1.trigger2",
            "cronExpression": "0/1 * * * * ?",
            "createUser": null,
            "createTime": "2017-08-09 15:42:11",
            "updateUser": null,
            "updateTime": "2017-08-09 14:50:17"
        }
    ]
}
```