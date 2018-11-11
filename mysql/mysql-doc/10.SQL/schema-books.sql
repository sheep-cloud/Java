/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.19 : Database - books
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

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) DEFAULT NULL COMMENT '编号',
  `b_name` varchar(20) DEFAULT NULL COMMENT '图书名',
  `price` double DEFAULT NULL COMMENT '价格',
  `author` int(11) DEFAULT NULL COMMENT '作者编号',
  `pub_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '出版日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图书表';

/*Data for the table `book` */

/*Table structure for table `book_author` */

DROP TABLE IF EXISTS `book_author`;

CREATE TABLE `book_author` (
  `id` int(11) DEFAULT NULL COMMENT '编号',
  `au_name` varchar(20) DEFAULT NULL COMMENT '作者名称',
  `nation` varchar(10) DEFAULT NULL COMMENT '国籍'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='作者表';

/*Data for the table `book_author` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
