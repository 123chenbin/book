/*
SQLyog Ultimate v11.22 (64 bit)
MySQL - 5.7.12-log : Database - books
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`books` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `books`;

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `umessage` varchar(200) DEFAULT NULL,
  `ustate` int(10) DEFAULT NULL,
  `admessage` varchar(200) DEFAULT NULL,
  `adstate` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `t_message` */

insert  into `t_message`(`id`,`username`,`umessage`,`ustate`,`admessage`,`adstate`) values (25,'doudou','你好豆豆',0,NULL,0),(26,'doudou','人呢',0,NULL,0),(27,'doudou',NULL,0,'你好dou',0),(28,'doudou','投诉你啊',0,NULL,0),(29,'doudou',NULL,0,'nini',0),(30,'doudou','qwert',0,NULL,0),(31,'toms',NULL,0,'tom',1),(32,'sure','在么\r\n',0,NULL,0),(33,'sure',NULL,0,'在呢？亲',0),(34,'sure','doudo',0,NULL,0),(35,'sure',NULL,0,'sure有事吗',0),(36,'sure','投诉你',0,NULL,0),(37,'sure',NULL,0,'滚',0);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `orderno` int(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `uname` varchar(20) DEFAULT NULL COMMENT '收货人',
  `totalmoney` decimal(20,0) DEFAULT NULL COMMENT '总价',
  `state` varchar(20) DEFAULT NULL COMMENT '订单状态',
  `ordertime` date DEFAULT NULL COMMENT '下单时间',
  `username` varchar(20) DEFAULT NULL COMMENT '账户名',
  `tel` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `adress` varchar(100) DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`orderno`,`uname`,`totalmoney`,`state`,`ordertime`,`username`,`tel`,`adress`) values (1,'kkk','90','已发货','2017-10-20','kangkang','123456','郑州'),(2,'小明','2700','已发货','2017-10-20','kangkang','12345678','郑州'),(3,'toms','200','待审核','2017-10-20','toms','1234567','郑州'),(4,'tom','310','待审核','2017-10-20','toms','1234567','郑州'),(5,'toms','412','待审核','2017-10-20','toms','123456','zz'),(6,'zzz','12','待审核','2017-10-20','sure','1233','zss'),(7,'豆豆','240','已发货','2017-10-20','doudou','123456','郑州'),(8,'sure','40','待审核','2017-10-20','sure','1234567','郑州'),(9,'贾豆豆','23','待审核','2017-10-20','sure','12345','qwert'),(10,'sure','440','待审核','2017-10-21','sure','1234567','郑州'),(11,'doudou','116','待审核','2017-10-23','sure','1345678','郑州');

/*Table structure for table `t_orderdetail` */

DROP TABLE IF EXISTS `t_orderdetail`;

CREATE TABLE `t_orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pid` int(20) DEFAULT NULL COMMENT '商品id',
  `oid` int(20) DEFAULT NULL COMMENT '订单id',
  `num` int(20) DEFAULT NULL COMMENT '商品数量',
  `price` decimal(20,0) DEFAULT NULL COMMENT '商品单价',
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  `pname` varchar(50) DEFAULT NULL COMMENT '商品名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_orderdetail` */

insert  into `t_orderdetail`(`id`,`pid`,`oid`,`num`,`price`,`img`,`pname`) values (1,6,1,3,'30','images/book/book_02.gif','天堂之旅'),(2,7,2,30,'30','images/book/book_02.gif','天堂之旅'),(3,8,2,90,'20','images/book/book_02.gif','西游记'),(4,9,3,10,'20','images/book/book_02.gif','西游记'),(5,10,4,1,'20','images/book/book_02.gif','西游记'),(6,11,4,5,'12','images/book/book_02.gif','aewf'),(7,12,4,10,'23','images/book/book_03.gif','dsfg'),(8,13,5,1,'12','images/book/book_02.gif','aewf'),(9,14,5,10,'40','images/book/book_07.gif','三国演义'),(10,17,6,1,'12','images/book/book_02.gif','aewf'),(11,19,7,10,'20','images/book/book_02.gif','西游记'),(12,21,7,1,'40','images/book/book_07.gif','三国演义'),(13,16,8,2,'20','images/book/book_02.gif','西游记'),(14,18,9,1,'23','images/book/book_03.gif','dsfg'),(15,25,10,1,'20','images/book/book_02.gif','西游记'),(16,26,10,12,'35','images/book/book_04.gif','红楼梦'),(17,28,11,1,'50','images/book/book_05.gif','水浒传'),(18,29,11,1,'20','images/book/book_02.gif','西游记'),(19,32,11,1,'46','images/book/book_01.gif','javawbe');

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `bname` varchar(20) DEFAULT NULL COMMENT '书名',
  `price` decimal(20,0) DEFAULT NULL COMMENT '价格',
  `store` int(20) DEFAULT NULL COMMENT '库存',
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  `author` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`bname`,`price`,`store`,`img`,`author`) values (2,'西游记','20',176,'images/book/book_02.gif','吴承恩'),(5,'红楼梦','35',197,'images/book/book_04.gif','曹雪芹'),(6,'三国演义','40',289,'images/book/book_07.gif','罗贯中'),(7,'水浒传','50',198,'images/book/book_05.gif','施耐庵'),(8,'javawbe','46',199,'images/book/book_01.gif','jerry'),(9,'linux','24',210,'images/book/book_03.gif','张三'),(10,'java','20',200,'images/book/book_03.gif','李四'),(12,'javascript','21',211,'images/book/book_09.gif','王五'),(13,'三毛流浪记','32',200,'images/book/book_07.gif','李二');

/*Table structure for table `t_shopcart` */

DROP TABLE IF EXISTS `t_shopcart`;

CREATE TABLE `t_shopcart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `bname` varchar(20) DEFAULT NULL COMMENT '书名',
  `price` decimal(10,0) DEFAULT NULL COMMENT '书的单价',
  `num` int(20) DEFAULT NULL COMMENT '数量',
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `t_shopcart` */

insert  into `t_shopcart`(`id`,`bname`,`price`,`num`,`img`,`username`) values (22,'水浒传','50',1,'images/book/book_05.gif','doudou'),(23,'红楼梦','35',2,'images/book/book_04.gif','doudou'),(34,'红楼梦','35',19,'images/book/book_04.gif','sure'),(36,'水浒传','50',10,'images/book/book_05.gif','sure'),(38,'javascript','21',2,'images/book/book_09.gif','sure');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `uname` varchar(20) DEFAULT NULL COMMENT '用户名',
  `upwd` varchar(20) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(20) DEFAULT NULL COMMENT '用户邮箱',
  `is_admin` int(10) DEFAULT NULL COMMENT '是否是管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`uname`,`upwd`,`email`,`is_admin`) values (6,'jerry','123456','1150111@qq.com',1),(8,'tom','123456','12377285@qq.com',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
