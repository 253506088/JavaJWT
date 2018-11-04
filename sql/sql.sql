/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.5.53 : Database - jwt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jwt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jwt`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码的MD5',
  `nikeName` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `isDelete` tinyint(4) DEFAULT '1' COMMENT '1为存在，0为删除',
  `gmtCreate` datetime DEFAULT NULL COMMENT '创建字段时间戳',
  `gmtModified` datetime DEFAULT NULL COMMENT '上一次修改时间戳',
  `activation` tinyint(4) DEFAULT NULL COMMENT '1为激活，0为未激活',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userId`,`username`,`password`,`nikeName`,`email`,`isDelete`,`gmtCreate`,`gmtModified`,`activation`) values 
(1,NULL,'c77d80624830dcecb136b3a747b9593b','黑白大彩电','2842513372@qq.com',1,'2018-11-04 11:29:59','2018-11-04 11:29:59',1),
(2,'一梦千朝风萧萧','c77d80624830dcecb136b3a747b9593b','一梦千朝',NULL,1,'2018-11-04 11:32:27','2018-11-04 11:32:27',1),
(3,NULL,'25f9e794323b453885f5181f1b624d0b','我的单车','2842513371@qq.com',1,'2018-11-04 12:33:48','2018-11-04 12:33:48',0),
(4,'我就给你说吧','25f9e794323b453885f5181f1b624d0b','是的啊',NULL,1,'2018-11-04 13:39:12','2018-11-04 13:39:12',1),
(5,'可口不可乐。','25f9e794323b453885f5181f1b624d0b','1',NULL,1,'2018-11-04 13:40:59','2018-11-04 13:40:59',1),
(6,'测试一下下啊','25f9e794323b453885f5181f1b624d0b','你的我的',NULL,1,'2018-11-04 13:46:14','2018-11-04 13:46:14',1),
(7,NULL,'25f9e794323b453885f5181f1b624d0b','曲兆瑞大脸盘子','1026974135@qq.com',1,'2018-11-04 13:47:51','2018-11-04 13:47:51',0),
(8,NULL,'25f9e794323b453885f5181f1b624d0b','曲兆脸盘子啊是','1026971351@qq.com',1,'2018-11-04 14:23:54','2018-11-04 14:23:54',0),
(9,'253506088啊啊啊','25f9e794323b453885f5181f1b624d0b','曲兆脸是盘子啊是',NULL,1,'2018-11-04 14:25:48','2018-11-04 14:25:48',1),
(10,NULL,'25f9e794323b453885f5181f1b624d0b','曲兆脸是盘子啊啊','25350088s@qq.com',1,'2018-11-04 14:47:51','2018-11-04 14:47:51',0),
(11,'我来测试一下下虾','c77d80624830dcecb136b3a747b9593b','我来测试一下下虾',NULL,1,'2018-11-04 17:59:52','2018-11-04 17:59:52',1),
(12,'爱神的箭阿克苏','45de78cf949466b51a41254a26a8d928','爱神的箭阿克苏',NULL,1,'2018-11-04 18:01:30','2018-11-04 18:01:30',1),
(13,'爱神的箭阿克苏2','7a78b8ab6123f08bbaa0e1a7d6b081ab','爱神的箭阿克苏2',NULL,1,'2018-11-04 18:02:56','2018-11-04 18:02:56',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
