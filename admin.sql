# Host: 127.0.0.1  (Version: 5.5.5-10.0.21-MariaDB)
# Date: 2015-12-07 17:06:20
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "role"
#

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Data for table "role"
#

INSERT INTO `role` VALUES (1,'管理员','admin');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `ename` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'陈浩','chenhao','123456',36,'2015-12-02','男','管理员'),(2,'胡浩','chendong','123456',26,'2015-12-02','女','管理员'),(24,'陈冬','chendong','123456',26,NULL,'男',NULL),(26,'陈冬','chendong','123456',26,NULL,'男',NULL),(27,'陈冬','chendong','123456',26,NULL,'男',NULL),(32,'陈冬','chendong','123456',26,NULL,'男',NULL),(33,'陈晨','chenchen','123456',14,'2015-12-08','男','管理员'),(34,'陈晨','chenchen','123456',12,'2015-12-02','男','管理员'),(35,'陈浩然','chenhaoran','123456',8,'2015-12-02','男','管理员'),(36,'陈浩然','chenhaoran','123456',23,'2015-12-02','男','管理员'),(38,'陈浩','chenhao','123456',19,'2015-12-03',NULL,'管理员');
