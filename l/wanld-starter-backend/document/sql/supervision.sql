DROP TABLE IF EXISTS `wld_supervision`;
CREATE TABLE `wld_supervision`
(
    `id`                      bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `number_no`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务编号',
    `type`                    int(1)                                                        NULL DEFAULT NULL COMMENT '任务类型 0定期任务 1周期任务',
    `title`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
    `content`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
    `level`                   int(1)                                                        NULL DEFAULT NULL COMMENT ' 0一级任务 1二级任务',
    `initiator_user_id`       bigint(20)                                                    NULL DEFAULT NULL COMMENT '发起人id',
    `initiate_time`           timestamp(0)                                                  NULL DEFAULT NULL COMMENT '发起时间',
    `supervise_user_id`       bigint(20)                                                    NULL DEFAULT NULL COMMENT '督办人',
    `supervise_time`          timestamp(0)                                                  NULL DEFAULT NULL COMMENT '督办时间',
    `end_time`                timestamp(0)                                                  NULL DEFAULT NULL COMMENT '计划完成时间',
    `commit_time`             timestamp(0)                                                  NULL DEFAULT NULL COMMENT '提交时间',
    `retract_time`            timestamp(0)                                                  NULL DEFAULT NULL COMMENT '撤回时间',
    `supervision_category_id` bigint(20)                                                    NULL DEFAULT NULL COMMENT '任务分类id，用户自定义分类',
    `is_review`               int(1)                                                        NULL DEFAULT NULL COMMENT '审核状态   0 未审核 1通过   2驳回',
    `pid`                     bigint(20)                                                    NULL DEFAULT NULL COMMENT '父任务 id',
    `
  performance`            varbinary(255)                                                NULL DEFAULT NULL COMMENT '绩效',
    `receive_user_id`         bigint(20)                                                    NULL DEFAULT NULL COMMENT '负责人/协办人',
    `remark`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `performance`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '绩效',
    `is_receive`              int(1)                                                        NULL DEFAULT NULL COMMENT '接收状态  0未接收   1接收     2录入(新建任务后的状态)',
    `is_complete`             int(1)                                                        NULL DEFAULT 0 COMMENT '完成状态   0未完成   1完成    2逾期完成',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 107571479259906049
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wld_supervision
-- ----------------------------
INSERT INTO `wld_supervision`
VALUES (107570833924292608, '201909270001', 0, '标题测试', '内容测试', 0, 3, '2019-09-27 10:13:06', 3, '2019-09-27 10:24:45',
        '2019-09-28 09:50:45', NULL, NULL, 2, 1, 0, NULL, 3, '备注测试', '400.56', 1, 0);
INSERT INTO `wld_supervision`
VALUES (107571103412518912, '201909270002', 1, '标题测试', '内容测试', 0, 3, '2019-09-27 10:14:11', 3, '2019-09-27 10:25:19',
        '2019-09-29 09:50:45', NULL, NULL, 3, 1, 0, NULL, 3, '备注测试', '400.56', 0, 0);
INSERT INTO `wld_supervision`
VALUES (107571217954766848, '201909270003', 1, '标题测试', '内容测试', 1, 3, '2019-09-27 10:14:38', 3, '2019-09-27 10:25:27',
        '2019-09-29 09:50:45', NULL, NULL, 5, 2, 0, NULL, NULL, '备注测试', '400.56', 2, 0);
INSERT INTO `wld_supervision`
VALUES (107571479259906048, '201909270004', 1, '标题测试子', '内容测试子', 1, 3, '2019-09-27 10:15:40', 3, NULL,
        '2019-09-30 09:50:45', NULL, NULL, 5, 0, 107570833924292608, NULL, NULL, '备注测试子', '400.56', 2, 0);

-- ----------------------------
-- Table structure for wld_supervision_category
-- ----------------------------
DROP TABLE IF EXISTS `wld_supervision_category`;
CREATE TABLE `wld_supervision_category`
(
    `id`    bigint(20)                                                    NOT NULL AUTO_INCREMENT,
    `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名',
    `pid`   bigint(20)                                                    NULL DEFAULT NULL COMMENT '父分类id',
    `sort`  int(2)                                                        NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wld_supervision_category
-- ----------------------------
INSERT INTO `wld_supervision_category`
VALUES (1, '2019年度', 0, 0);
INSERT INTO `wld_supervision_category`
VALUES (2, '重要事件', 1, 0);
INSERT INTO `wld_supervision_category`
VALUES (3, '一般事件', 1, 1);
INSERT INTO `wld_supervision_category`
VALUES (4, '2018年度', 0, 1);
INSERT INTO `wld_supervision_category`
VALUES (5, '重要事件', 4, 0);
INSERT INTO `wld_supervision_category`
VALUES (6, '一般事件', 4, 1);
INSERT INTO `wld_supervision_category`
VALUES (7, '恐怖事件', 4, 2);

-- ----------------------------
-- Table structure for wld_supervision_delay
-- ----------------------------
DROP TABLE IF EXISTS `wld_supervision_delay`;
CREATE TABLE `wld_supervision_delay`
(
    `id`             bigint(20)                                                    NOT NULL,
    `supervision_id` bigint(20)                                                    NULL DEFAULT NULL,
    `cause`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
    `is_review`      int(1)                                                        NULL DEFAULT NULL COMMENT '申请状态 0未审核  1通过  2驳回',
    `end_time`       timestamp(0)                                                  NULL DEFAULT NULL COMMENT '延期后的结束时间',
    `title`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wld_supervision_delay
-- ----------------------------
INSERT INTO `wld_supervision_delay`
VALUES (107582407409926144, 107570833924292608, '原因', 0, '2019-09-30 10:37:19', '延期测试');

-- ----------------------------
-- Table structure for wld_supervision_transfer
-- ----------------------------
DROP TABLE IF EXISTS `wld_supervision_transfer`;
CREATE TABLE `wld_supervision_transfer`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `receive_user_id`  bigint(20) NULL DEFAULT NULL COMMENT '接收人',
    `supervise_id`     bigint(20) NULL DEFAULT NULL COMMENT '任务id',
    `transfer_user_id` bigint(20) NULL DEFAULT NULL COMMENT '转派人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 107620529925521409
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wld_supervision_transfer
-- ----------------------------
INSERT INTO `wld_supervision_transfer`
VALUES (107620529925521408, 3, 107571103412518912, 3);
