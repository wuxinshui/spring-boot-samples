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
POST:`http://172.16.36.73:9090/job/add/test/job2`

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
            "jobStatus": "GOING",
            "jobClass": "com.wxs.quartz.job.Job1",
            "triggerName": "trigger1",
            "triggerGroup": "TriggerGroup1",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup.trigger1",
            "cronExpression": "0/1 * * * * ?",
            "createUser": null,
            "createTime": 1502177110000,
            "updateUser": null,
            "updateTime": 1502177110000
        },
        {
            "id": 2,
            "jobName": "Job2",
            "jobGroup": "JobGroup2",
            "jobDescription": "JobGroup.Job2",
            "jobStatus": "GOING",
            "jobClass": "com.wxs.quartz.job.Job2",
            "triggerName": "trigger2",
            "triggerGroup": "TriggerGroup2",
            "triggerStatus": "GOING",
            "triggerDescription": "TriggerGroup.trigger1",
            "cronExpression": "0/1 * * * * ?",
            "createUser": "",
            "createTime": 1502177110000,
            "updateUser": "",
            "updateTime": 1502177110000
        }
    ]
}
```