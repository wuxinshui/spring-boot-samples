-- ----------------------------
-- Table structure for t_scheduling
-- ----------------------------
DROP TABLE IF EXISTS t_scheduling;
CREATE TABLE t_scheduling (
  id int(11) NOT NULL,
  job varchar(255) DEFAULT NULL COMMENT 'job名称',
  task_class varchar(255) DEFAULT NULL,
  execute_class varchar(255) DEFAULT NULL COMMENT '执行类',
  trigger_cron varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  status varchar(20) DEFAULT NULL COMMENT '当前job状态',
  create_user varchar(100) DEFAULT NULL COMMENT '创建者',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_user varchar(100) DEFAULT NULL COMMENT '修改者',
  update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_scheduling_his
-- ----------------------------
DROP TABLE IF EXISTS t_scheduling_his;
CREATE TABLE t_scheduling_his (
  id int(11) NOT NULL,
  job_id varchar(255) DEFAULT NULL COMMENT 'jobID',
  execute_time timestamp NULL DEFAULT NULL COMMENT 'cron表达式',
  create_user varchar(100) DEFAULT NULL COMMENT '创建者',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_user varchar(100) DEFAULT NULL COMMENT '修改者',
  update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
