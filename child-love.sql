/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : child-love

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 02/03/2023 21:50:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `activity_type` int(10) NULL DEFAULT NULL COMMENT '活动类型',
  `start_time` date NULL DEFAULT NULL COMMENT '开始事件',
  `end_time` date NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '帮助小孩学习', '小孩学习', 'java 编程 python', 1, '2023-02-27', '2023-03-01', '2023-02-28 17:03:52');
INSERT INTO `activity` VALUES (2, '123', '333', '123312312', 2, '2022-12-02', '2023-12-02', '2023-02-28 17:15:38');
INSERT INTO `activity` VALUES (4, 'ffasdfas', 'fsfasdfasf', 'afasfasf', 1, '2023-03-03', '2023-04-05', '2023-03-01 17:43:14');
INSERT INTO `activity` VALUES (5, 'ffasdfas', 'fsfasdfasf', 'afasfasf', 2, '2023-03-03', '2023-04-05', '2023-03-01 17:43:15');
INSERT INTO `activity` VALUES (6, '关爱空巢老人', '帮忙做家务', '飞机爱上了就发生了', 3, '2023-03-02', '2023-04-11', '2023-03-01 17:42:58');

-- ----------------------------
-- Table structure for activity_img
-- ----------------------------
DROP TABLE IF EXISTS `activity_img`;
CREATE TABLE `activity_img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NULL DEFAULT NULL,
  `activity_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_img
-- ----------------------------
INSERT INTO `activity_img` VALUES (1, 2, 'http://localhost:8001/static/63fdc606a3970ff1f0a296b7.jpg');
INSERT INTO `activity_img` VALUES (2, 2, 'http://localhost:8001/static/63fdc60ca3970ff1f0a296b8.jpg');
INSERT INTO `activity_img` VALUES (3, 2, 'http://localhost:8001/static/63fdc60ca3970ff1f0a296b9.jpg');
INSERT INTO `activity_img` VALUES (4, 4, 'http://localhost:8001/static/63ff1d79d1752403ab5e354f.jpg');
INSERT INTO `activity_img` VALUES (5, 4, 'http://localhost:8001/static/63ff1d7ed1752403ab5e3550.jpg');
INSERT INTO `activity_img` VALUES (6, 4, 'http://localhost:8001/static/63ff1d7ed1752403ab5e3551.jpg');
INSERT INTO `activity_img` VALUES (7, 5, 'http://localhost:8001/static/63ff1d86d1752403ab5e3552.jpg');
INSERT INTO `activity_img` VALUES (8, 5, 'http://localhost:8001/static/63ff1d86d1752403ab5e3553.jpg');
INSERT INTO `activity_img` VALUES (9, 5, 'http://localhost:8001/static/63ff1d86d1752403ab5e3554.jpg');
INSERT INTO `activity_img` VALUES (10, 6, 'http://localhost:8001/static/63ff1e21d1752403ab5e3555.jpg');

-- ----------------------------
-- Table structure for donate
-- ----------------------------
DROP TABLE IF EXISTS `donate`;
CREATE TABLE `donate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `love_id` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `tracking_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递单号',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `donate_type` int(255) NULL DEFAULT NULL COMMENT '捐赠类型',
  `state` int(1) NULL DEFAULT NULL COMMENT '是否签收',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donate
-- ----------------------------
INSERT INTO `donate` VALUES (1, NULL, NULL, '棉被，笔，纸', NULL, NULL, 0, NULL);
INSERT INTO `donate` VALUES (2, 1, 3, '棉被', '123456', '2023-03-01 12:53:32', 1, 1);
INSERT INTO `donate` VALUES (3, NULL, NULL, '123', NULL, '2023-03-01 11:47:46', 0, NULL);
INSERT INTO `donate` VALUES (4, 1, NULL, '123', '123', '2023-03-01 14:03:11', 1, 1);

-- ----------------------------
-- Table structure for donate_use
-- ----------------------------
DROP TABLE IF EXISTS `donate_use`;
CREATE TABLE `donate_use`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `donate_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '物品使用去向',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of donate_use
-- ----------------------------
INSERT INTO `donate_use` VALUES (1, 2, 1, '湖北', '2023-02-28 15:27:36');
INSERT INTO `donate_use` VALUES (2, 4, 1, '长沙', '2023-03-01 14:39:38');

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES (1, 'http://localhost:8001/static/63f87825b1652de742c653e3.jpg');
INSERT INTO `img` VALUES (2, 'http://localhost:8001/static/63f87825b1652de742c653e4.jpg');
INSERT INTO `img` VALUES (3, 'http://localhost:8001/static/63f87825b1652de742c653e5.jpg');
INSERT INTO `img` VALUES (4, 'http://localhost:8001/static/63f878abb165af035be14ccb.jpg');
INSERT INTO `img` VALUES (5, 'http://localhost:8001/static/63f878abb165af035be14ccc.jpg');
INSERT INTO `img` VALUES (6, 'http://localhost:8001/static/63f878abb165af035be14ccd.jpg');

-- ----------------------------
-- Table structure for join_activity
-- ----------------------------
DROP TABLE IF EXISTS `join_activity`;
CREATE TABLE `join_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_activity
-- ----------------------------
INSERT INTO `join_activity` VALUES (2, 2, 1, '2023-03-01 13:42:23');
INSERT INTO `join_activity` VALUES (3, 6, 1, '2023-03-01 17:45:06');

-- ----------------------------
-- Table structure for love_img
-- ----------------------------
DROP TABLE IF EXISTS `love_img`;
CREATE TABLE `love_img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `love_id` int(11) NULL DEFAULT NULL,
  `img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love_img
-- ----------------------------
INSERT INTO `love_img` VALUES (5, 3, 'http://localhost:8001/static/63f82ec1b1653dec8faf46f7.jpg');
INSERT INTO `love_img` VALUES (6, 3, 'http://localhost:8001/static/63f82eceb1653dec8faf46f8.jpg');
INSERT INTO `love_img` VALUES (9, 10, 'http://localhost:8001/static/63ff1430d175a0a6e8fca3bf.jpg');
INSERT INTO `love_img` VALUES (10, 10, 'http://localhost:8001/static/63ff1430d175a0a6e8fca3c0.jpg');
INSERT INTO `love_img` VALUES (11, 11, 'http://localhost:8001/static/63ff1481d175a0a6e8fca3c1.jpg');
INSERT INTO `love_img` VALUES (12, 11, 'http://localhost:8001/static/63ff1481d175a0a6e8fca3c2.jpg');
INSERT INTO `love_img` VALUES (13, 11, 'http://localhost:8001/static/63ff1481d175a0a6e8fca3c3.jpg');
INSERT INTO `love_img` VALUES (14, 11, 'http://localhost:8001/static/63ff1481d175a0a6e8fca3c4.jpg');
INSERT INTO `love_img` VALUES (15, 11, 'http://localhost:8001/static/63ff1481d175a0a6e8fca3c5.jpg');
INSERT INTO `love_img` VALUES (16, 12, 'http://localhost:8001/static/63ff1b85d175a0a6e8fca3c6.jpg');
INSERT INTO `love_img` VALUES (17, 12, 'http://localhost:8001/static/63ff1b85d175a0a6e8fca3c7.jpg');
INSERT INTO `love_img` VALUES (18, 12, 'http://localhost:8001/static/63ff1b85d175a0a6e8fca3c8.jpg');

-- ----------------------------
-- Table structure for love_thing
-- ----------------------------
DROP TABLE IF EXISTS `love_thing`;
CREATE TABLE `love_thing`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `child_need` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '孩子需要',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of love_thing
-- ----------------------------
INSERT INTO `love_thing` VALUES (3, '123', '简介', 'jjj', 'pen', '2023-02-24 11:27:54');
INSERT INTO `love_thing` VALUES (10, '发放', '方法', '发啊手动阀', '发', '2023-03-01 17:00:32');
INSERT INTO `love_thing` VALUES (11, '关爱空巢老人', '老人家', '解放拉萨机法律上的肌肤数据的方法就够了垃圾啊', '米 油', '2023-03-01 17:01:54');
INSERT INTO `love_thing` VALUES (12, 'ajfaf', 'jfa;fj', 'fjaf', 'undefined', '2023-03-01 17:31:49');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zzy', '333', '2023-03-04', '131231233', '0', NULL, '2023-03-01 15:29:20');
INSERT INTO `user` VALUES (2, 'admin', 'admin', NULL, NULL, '2', NULL, '2023-02-24 09:18:32');
INSERT INTO `user` VALUES (7, '111', '111', NULL, NULL, '0', NULL, '2023-03-02 09:19:06');
INSERT INTO `user` VALUES (8, '111', '111', NULL, NULL, '2', NULL, '2023-03-02 09:30:08');

SET FOREIGN_KEY_CHECKS = 1;
