/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : weichat

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-04 17:30:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for orderitems
-- ----------------------------
DROP TABLE IF EXISTS `orderitems`;
CREATE TABLE `orderitems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userCard` varchar(30) NOT NULL,
  `money` decimal(10,0) NOT NULL,
  `status` int(11) NOT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `openid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitems
-- ----------------------------
INSERT INTO `orderitems` VALUES ('1', '12345678910', '100', '0', '2017-10-30 16:50:25', '11111111');
INSERT INTO `orderitems` VALUES ('2', '12345678910', '100', '0', '2017-10-30 16:52:02', '11111111');
INSERT INTO `orderitems` VALUES ('3', '12345678910', '100', '0', '2017-10-30 16:52:54', '11111111');
INSERT INTO `orderitems` VALUES ('4', '12345678910', '100', '0', '2017-10-30 16:53:16', '11111111');
INSERT INTO `orderitems` VALUES ('5', '12345678910', '100', '0', '2017-11-01 16:49:58', '11111111');
INSERT INTO `orderitems` VALUES ('6', '565656565655656565', '100', '0', '2017-11-02 10:06:08', '11111111');
INSERT INTO `orderitems` VALUES ('7', '565656565655656565', '100', '0', '2017-11-02 10:12:11', '11111111');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `userCard` varchar(30) NOT NULL,
  `userMoney` decimal(10,0) NOT NULL,
  `openid` varchar(50) DEFAULT NULL,
  `headIcon` varchar(50) DEFAULT NULL,
  `accessToken` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', '12345678910', '800', '11111111', null, null, null);
INSERT INTO `users` VALUES ('2', 'cfl', '暂缺', '0', '11111111', null, null, null);
INSERT INTO `users` VALUES ('3', '张三', '565656565655656565', '200', '11111111', null, null, null);
INSERT INTO `users` VALUES ('4', 'cfl1', '222222222222222222', '0', '11111111', null, null, null);
