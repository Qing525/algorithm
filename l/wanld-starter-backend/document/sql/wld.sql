/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : wld

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-03-21 15:44:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wld_user
-- ----------------------------
DROP TABLE IF EXISTS `wld_user`;
CREATE TABLE `wld_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of wld_user
-- ----------------------------
INSERT INTO `wld_user` VALUES ('1', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', null, '测试账号', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');
INSERT INTO `wld_user` VALUES ('3', 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'user@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', '1');

-- ----------------------------
-- Table structure for wld_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `wld_user_login_log`;
CREATE TABLE `wld_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Records of wld_user_login_log
-- ----------------------------
INSERT INTO `wld_user_login_log` VALUES ('5', '3', '2018-12-06 13:59:12', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('6', '3', '2018-12-17 13:23:20', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('7', '3', '2018-12-18 13:51:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('8', '3', '2018-12-18 13:51:51', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('9', '3', '2019-01-28 16:20:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('10', '3', '2019-01-29 09:16:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('11', '3', '2019-01-29 10:10:51', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('12', '3', '2019-02-18 11:03:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('13', '3', '2019-03-12 10:03:55', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('14', '3', '2019-03-12 10:06:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('15', '3', '2019-03-12 10:15:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('16', '3', '2019-03-20 15:35:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `wld_user_login_log` VALUES ('17', '3', '2019-03-20 15:38:50', '0:0:0:0:0:0:0:1', null, null);

-- ----------------------------
-- Table structure for wld_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `wld_user_permission_relation`;
CREATE TABLE `wld_user_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和权限关系表(除角色中定义的权限以外的加减权限)';
-- ----------------------------
-- Records of wld_user_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for wld_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wld_user_role_relation`;
CREATE TABLE `wld_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户和角色关系表';

-- ----------------------------
-- Records of wld_user_role_relation
-- ----------------------------
INSERT INTO `wld_user_role_relation` VALUES ('13', '3', '1');
INSERT INTO `wld_user_role_relation` VALUES ('15', '3', '2');
INSERT INTO `wld_user_role_relation` VALUES ('16', '3', '4');

-- ----------------------------
-- Table structure for wld_permission
-- ----------------------------
DROP TABLE IF EXISTS `wld_permission`;
CREATE TABLE `wld_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

-- ----------------------------
-- Records of wld_permission
-- ----------------------------
INSERT INTO `wld_permission` VALUES ('1', '0', '用户管理', 'basic:user', null, '1', '', '1', '2018-09-29 16:15:14', '0');
INSERT INTO `wld_permission` VALUES ('3', '1', '添加用户', 'basic:user:create', null, '1', '/user/add', '1', '2018-09-29 16:18:51', '0');
INSERT INTO `wld_permission` VALUES ('4', '1', '删除用户', 'basic:user:delete', null, '1', '/user/delete', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `wld_permission` VALUES ('5', '1', '更新用户', 'basic:user:update', null, '1', '/user/update', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `wld_permission` VALUES ('6', '0', '角色管理', 'basic:role', null, '1', '', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `wld_permission` VALUES ('7', '6', '添加角色', 'basic:role:create', null, '1', '', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `wld_permission` VALUES ('8', '6', '删除角色', 'basic:role:delete', null, '1', '', '1', '2018-09-29 16:23:07', '0');
INSERT INTO `wld_permission` VALUES ('9', '6', '更新角色', 'basic:role:update', null, '1', '', '1', '2018-09-29 16:23:07', '0');


INSERT INTO `wld_permission` VALUES ('10', '0', '首页', null, null, '0', null, '1', '2018-09-29 16:51:57', '0');

-- ----------------------------
-- Table structure for wld_role
-- ----------------------------
DROP TABLE IF EXISTS `wld_role`;
CREATE TABLE `wld_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `user_count` int(11) DEFAULT NULL COMMENT '用户数量@王增光@2019/8/9',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of wld_role
-- ----------------------------
INSERT INTO `wld_role` VALUES ('1', '管理员', '管理员', '0', '2018-09-30 15:46:11', '1', '0');

-- ----------------------------
-- Table structure for wld_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `wld_role_permission_relation`;
CREATE TABLE `wld_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户角色和权限关系表';

-- ----------------------------
-- Records of wld_role_permission_relation
-- ----------------------------
INSERT INTO `wld_role_permission_relation` VALUES ('1', '1', '1');
INSERT INTO `wld_role_permission_relation` VALUES ('2', '1', '2');
INSERT INTO `wld_role_permission_relation` VALUES ('3', '1', '3');
INSERT INTO `wld_role_permission_relation` VALUES ('4', '1', '4');
INSERT INTO `wld_role_permission_relation` VALUES ('5', '1', '5');
INSERT INTO `wld_role_permission_relation` VALUES ('6', '1', '6');
INSERT INTO `wld_role_permission_relation` VALUES ('7', '1', '7');
INSERT INTO `wld_role_permission_relation` VALUES ('8', '1', '8');
INSERT INTO `wld_role_permission_relation` VALUES ('9', '1', '9');
