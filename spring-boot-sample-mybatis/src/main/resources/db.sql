DROP TABLE IF EXISTS user;
CREATE TABLE t_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(32) DEFAULT NULL COMMENT '用户名',
  password varchar(32) DEFAULT NULL COMMENT '密码',
  create_user varchar(32) DEFAULT NULL COMMENT '创建者',
  create_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  update_user varchar(32) DEFAULT NULL COMMENT '更新者',
  update_time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


insert into t_user values (1,'test','1234','test',NOW(),'test',now());
insert into t_user values (2,'test2','12342','test2',NOW(),'test2',now());

