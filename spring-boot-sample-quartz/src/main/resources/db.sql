-- ----------------------------
-- Table structure for t_job_info
-- ----------------------------
DROP TABLE IF EXISTS t_job_info;
CREATE TABLE t_job_info (
  id int(11) NOT NULL,
  job_name varchar(255) DEFAULT NULL COMMENT '任务名称',
  job_group varchar(255) DEFAULT NULL COMMENT '任务分组',
  job_description varchar(255) DEFAULT NULL COMMENT '任务描述',
  job_status varchar(255) DEFAULT NULL COMMENT '任务状态',
  job_class varchar(255) DEFAULT NULL COMMENT '任务类',
  trigger_name varchar(255) DEFAULT NULL COMMENT '触发器名称',
  trigger_group varchar(255) DEFAULT NULL COMMENT '触发器分组',
  trigger_status varchar(255) DEFAULT NULL COMMENT '触发器状态',
  trigger_description varchar(255) DEFAULT NULL COMMENT '触发器描述',
  cron_expression varchar(255) DEFAULT NULL COMMENT 'Cron表达式',
  create_user varchar(100) DEFAULT NULL COMMENT '创建人',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_user varchar(100) DEFAULT NULL COMMENT '修改者',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_job_info
-- ----------------------------
INSERT INTO t_job_info VALUES ('1', 'Job1', 'JobGroup1', 'JobGroup.Job1', 'GOING', 'com.wxs.quartz.job.Job1', 'trigger1', 'TriggerGroup1', 'GOING', 'TriggerGroup.trigger1', '0/1 * * * * ?', null, '2017-08-08 15:25:10', null, '2017-08-08 15:25:10');
INSERT INTO t_job_info VALUES ('2', 'Job2', 'JobGroup2', 'JobGroup.Job2', 'GOING', 'com.wxs.quartz.job.Job2', 'trigger2', 'TriggerGroup2', 'GOING', 'TriggerGroup.trigger1', '0/1 * * * * ?', '', '2017-08-08 15:25:10', '', '2017-08-08 15:25:10');
