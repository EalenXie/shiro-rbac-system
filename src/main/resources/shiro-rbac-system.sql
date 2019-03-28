
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (8);
INSERT INTO `hibernate_sequence` VALUES (8);
INSERT INTO `hibernate_sequence` VALUES (8);

-- ----------------------------
-- Table structure for system_shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_shiro_permission`;
CREATE TABLE `system_shiro_permission`  (
  `id` int(11) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_c47qrsguwc5qhsl3sqlrxmwpr`(`name`) USING BTREE,
  UNIQUE INDEX `UK_88ikw8276ch7dlivtlvl2yglg`(`url`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_shiro_permission
-- ----------------------------
INSERT INTO `system_shiro_permission` VALUES (1, '2019-03-27 15:39:06', '2019-03-27 15:39:06', '新增权限', 'add', '/add');
INSERT INTO `system_shiro_permission` VALUES (2, '2019-03-27 15:39:06', '2019-03-27 15:39:06', '修改权限', 'update', '/update');
INSERT INTO `system_shiro_permission` VALUES (3, '2019-03-27 15:39:06', '2019-03-27 15:39:06', '删除权限', 'delete', '/delete');

-- ----------------------------
-- Table structure for system_shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `system_shiro_role`;
CREATE TABLE `system_shiro_role`  (
  `id` int(11) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_5pf87uwoywwd1f7s6rfd0h51o`(`name`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_shiro_role
-- ----------------------------
INSERT INTO `system_shiro_role` VALUES (4, '2019-03-27 16:03:50', '2019-03-27 16:03:50', '管理员权限', 'administrator');
INSERT INTO `system_shiro_role` VALUES (5, '2019-03-27 16:03:50', '2019-03-27 16:03:50', '用户权限', 'user');

-- ----------------------------
-- Table structure for system_shiro_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `system_shiro_role_permissions`;
CREATE TABLE `system_shiro_role_permissions`  (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) NOT NULL,
  INDEX `FKbcw322i3a5p5h7oxkj1np3num`(`permissions_id`) USING BTREE,
  INDEX `FKid5g3kh3jhoj05a1jbt3gwxe9`(`role_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of system_shiro_role_permissions
-- ----------------------------
INSERT INTO `system_shiro_role_permissions` VALUES (4, 1);
INSERT INTO `system_shiro_role_permissions` VALUES (4, 2);
INSERT INTO `system_shiro_role_permissions` VALUES (4, 3);
INSERT INTO `system_shiro_role_permissions` VALUES (5, 1);
INSERT INTO `system_shiro_role_permissions` VALUES (5, 2);

-- ----------------------------
-- Table structure for system_shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `system_shiro_user`;
CREATE TABLE `system_shiro_user`  (
  `id` int(11) NOT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password_salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ckf0f8nat99csfy1bmbv2qbif`(`username`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_shiro_user
-- ----------------------------
INSERT INTO `system_shiro_user` VALUES (6, '2019-03-27 16:04:04', '2019-03-27 16:04:04', 'dde5deadfcaa4267804832b063f4f8f9', '5371f568a45e5ab1f442c38e0932aef24447139b', 'ealenxie');
INSERT INTO `system_shiro_user` VALUES (7, '2019-03-27 16:04:04', '2019-03-27 16:04:04', '3b574a9959cd4f8a9a3752d34e0f5f33', '5371f568a45e5ab1f442c38e0932aef24447139b', 'zhangsan');

-- ----------------------------
-- Table structure for system_shiro_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `system_shiro_user_roles`;
CREATE TABLE `system_shiro_user_roles`  (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  INDEX `FK17tnfmqx52t71ct60elbg66rv`(`roles_id`) USING BTREE,
  INDEX `FK4oc2e7dhga26ke5k08n4nx85l`(`user_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of system_shiro_user_roles
-- ----------------------------
INSERT INTO `system_shiro_user_roles` VALUES (6, 4);
INSERT INTO `system_shiro_user_roles` VALUES (7, 5);

SET FOREIGN_KEY_CHECKS = 1;
