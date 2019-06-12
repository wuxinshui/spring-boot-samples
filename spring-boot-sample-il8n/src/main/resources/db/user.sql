
-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `AGE` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;



INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (1, '小米', 21);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (2, '小兰', 22);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (3, '小四', 23);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (4, '张三', 24);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (5, 'wangsi', 43);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (6, '小米', 21);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (7, '小兰', 22);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (8, '小四', 23);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (9, '张三', 24);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (10, '李四', 25);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (11, '王五', 26);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (12, '泽六', 27);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (13, '小米', 21);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (14, '小兰', 22);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (15, '小四', 23);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (16, '张三', 24);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (17, '李四', 25);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (18, '王五', 26);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (19, '泽六', 27);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (20, '小米', 21);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (21, '小兰', 22);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (22, '小四', 23);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (23, '张三', 24);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (24, '李四', 25);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (25, '王五', 26);
INSERT INTO `test`.`user`(`ID`, `NAME`, `AGE`) VALUES (26, '泽六', 27);


