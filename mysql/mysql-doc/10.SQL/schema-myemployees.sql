/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.19 : Database - myemployees
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myemployees` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myemployees`;

/*Table structure for table `departments` */

DROP TABLE IF EXISTS `departments`;

CREATE TABLE `departments` (
  `department_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `department_name` varchar(3) DEFAULT NULL COMMENT '部门名称',
  `manager_id` int(6) DEFAULT NULL COMMENT '部门领导的员工编号',
  `location_id` int(11) DEFAULT NULL COMMENT '位置编号',
  PRIMARY KEY (`department_id`),
  KEY `loc_id_fk` (`location_id`),
  CONSTRAINT `loc_id_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `departments` */

insert  into `departments`(`department_id`,`department_name`,`manager_id`,`location_id`) values 
(10,'Adm',200,1700),
(20,'Mar',201,1800),
(30,'Pur',114,1700),
(40,'Hum',203,2400),
(50,'Shi',121,1500),
(60,'IT',103,1400),
(70,'Pub',204,2700),
(80,'Sal',145,2500),
(90,'Exe',100,1700),
(100,'Fin',108,1700),
(110,'Acc',205,1700),
(120,'Tre',NULL,1700),
(130,'Cor',NULL,1700),
(140,'Con',NULL,1700),
(150,'Sha',NULL,1700),
(160,'Ben',NULL,1700),
(170,'Man',NULL,1700),
(180,'Con',NULL,1700),
(190,'Con',NULL,1700),
(200,'Ope',NULL,1700),
(210,'IT ',NULL,1700),
(220,'NOC',NULL,1700),
(230,'IT ',NULL,1700),
(240,'Gov',NULL,1700),
(250,'Ret',NULL,1700),
(260,'Rec',NULL,1700),
(270,'Pay',NULL,1700);

/*Table structure for table `employees` */

DROP TABLE IF EXISTS `employees`;

CREATE TABLE `employees` (
  `employee_id` int(6) NOT NULL COMMENT '员工编号',
  `first_name` varchar(25) DEFAULT NULL COMMENT '名',
  `last_name` varchar(25) DEFAULT NULL COMMENT '姓',
  `email` varchar(25) DEFAULT NULL COMMENT '邮箱',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `job_id` varchar(10) DEFAULT NULL COMMENT '工种编号',
  `salary` double(10,2) DEFAULT NULL COMMENT '月薪',
  `commission_pct` double(4,2) DEFAULT NULL COMMENT '奖金率',
  `manager_id` int(6) DEFAULT NULL COMMENT '上级领导的员工编号',
  `department_id` int(4) DEFAULT NULL COMMENT '部门编号',
  `hiredate` datetime DEFAULT NULL COMMENT '入职日期',
  PRIMARY KEY (`employee_id`),
  KEY `dept_id_fk` (`department_id`),
  KEY `job_id_fk` (`job_id`),
  CONSTRAINT `dept_id_fk` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`),
  CONSTRAINT `job_id_fk` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

/*Data for the table `employees` */

insert  into `employees`(`employee_id`,`first_name`,`last_name`,`email`,`phone_number`,`job_id`,`salary`,`commission_pct`,`manager_id`,`department_id`,`hiredate`) values 
(100,'Steven','K_ing','SKING','515.123.4567','AD_PRES',24000.00,NULL,NULL,90,'2018-09-24 15:42:31'),
(101,'Neena','Kochhar','NKOCHHAR','515.123.4568','AD_VP',17000.00,NULL,100,90,'2015-02-17 12:43:40'),
(102,'Lex','De Haan','LDEHAAN','515.123.4569','AD_VP',17000.00,NULL,100,90,'2016-01-15 16:30:54'),
(103,'Alexander','Hunold','AHUNOLD','590.423.4567','IT_PROG',9000.00,NULL,102,60,'2015-12-12 20:23:35'),
(104,'Bruce','Ernst','BERNST','590.423.4568','IT_PROG',6000.00,NULL,103,60,'2016-08-13 04:25:16'),
(105,'David','Austin','DAUSTIN','590.423.4569','IT_PROG',4800.00,NULL,103,60,'2016-05-18 08:55:37'),
(106,'Valli','Pataballa','VPATABAL','590.423.4560','IT_PROG',4800.00,NULL,103,60,'2017-01-16 07:22:21'),
(107,'Diana','Lorentz','DLORENTZ','590.423.5567','IT_PROG',4200.00,NULL,103,60,'2017-03-22 10:05:00'),
(108,'Nancy','Greenberg','NGREENBE','515.124.4569','FI_MGR',12000.00,NULL,101,100,'2016-02-13 04:17:58'),
(109,'Daniel','Faviet','DFAVIET','515.124.4169','FI_ACCOUNT',9000.00,NULL,108,100,'2017-10-13 15:14:58'),
(110,'John','Chen','JCHEN','515.124.4269','FI_ACCOUNT',8200.00,NULL,108,100,'2017-11-04 15:20:55'),
(111,'Ismael','Sciarra','ISCIARRA','515.124.4369','FI_ACCOUNT',7700.00,NULL,108,100,'2017-01-03 06:59:44'),
(112,'Jose Manuel','Urman','JMURMAN','515.124.4469','FI_ACCOUNT',7800.00,NULL,108,100,'2016-07-04 12:56:02'),
(113,'Luis','Popp','LPOPP','515.124.4567','FI_ACCOUNT',6900.00,NULL,108,100,'2016-07-06 19:41:03'),
(114,'Den','Raphaely','DRAPHEAL','515.127.4561','PU_MAN',11000.00,NULL,100,30,'2018-01-17 11:37:11'),
(115,'Alexander','Khoo','AKHOO','515.127.4562','PU_CLERK',3100.00,NULL,114,30,'2017-12-19 23:02:47'),
(116,'Shelli','Baida','SBAIDA','515.127.4563','PU_CLERK',2900.00,NULL,114,30,'2016-11-03 08:22:27'),
(117,'Sigal','Tobias','STOBIAS','515.127.4564','PU_CLERK',2800.00,NULL,114,30,'2015-04-20 20:43:56'),
(118,'Guy','Himuro','GHIMURO','515.127.4565','PU_CLERK',2600.00,NULL,114,30,'2018-09-14 06:32:27'),
(119,'Karen','Colmenares','KCOLMENA','515.127.4566','PU_CLERK',2500.00,NULL,114,30,'2017-03-02 18:30:26'),
(120,'Matthew','Weiss','MWEISS','650.123.1234','ST_MAN',8000.00,NULL,100,50,'2018-08-05 00:54:53'),
(121,'Adam','Fripp','AFRIPP','650.123.2234','ST_MAN',8200.00,NULL,100,50,'2018-09-26 21:03:10'),
(122,'Payam','Kaufling','PKAUFLIN','650.123.3234','ST_MAN',7900.00,NULL,100,50,'2015-03-10 06:31:12'),
(123,'Shanta','Vollman','SVOLLMAN','650.123.4234','ST_MAN',6500.00,NULL,100,50,'2016-04-21 17:26:38'),
(124,'Kevin','Mourgos','KMOURGOS','650.123.5234','ST_MAN',5800.00,NULL,100,50,'2017-02-07 19:39:37'),
(125,'Julia','Nayer','JNAYER','650.124.1214','ST_CLERK',3200.00,NULL,120,50,'2017-09-30 21:58:16'),
(126,'Irene','Mikkilineni','IMIKKILI','650.124.1224','ST_CLERK',2700.00,NULL,120,50,'2018-07-27 02:52:31'),
(127,'James','Landry','JLANDRY','650.124.1334','ST_CLERK',2400.00,NULL,120,50,'2016-11-14 20:27:48'),
(128,'Steven','Markle','SMARKLE','650.124.1434','ST_CLERK',2200.00,NULL,120,50,'2017-07-08 21:41:35'),
(129,'Laura','Bissot','LBISSOT','650.124.5234','ST_CLERK',3300.00,NULL,121,50,'2018-02-11 23:04:32'),
(130,'Mozhe','Atkinson','MATKINSO','650.124.6234','ST_CLERK',2800.00,NULL,121,50,'2015-04-21 02:17:58'),
(131,'James','Marlow','JAMRLOW','650.124.7234','ST_CLERK',2500.00,NULL,121,50,'2018-09-28 14:16:22'),
(132,'TJ','Olson','TJOLSON','650.124.8234','ST_CLERK',2100.00,NULL,121,50,'2017-05-12 16:27:57'),
(133,'Jason','Mallin','JMALLIN','650.127.1934','ST_CLERK',3300.00,NULL,122,50,'2015-08-02 15:30:44'),
(134,'Michael','Rogers','MROGERS','650.127.1834','ST_CLERK',2900.00,NULL,122,50,'2018-07-24 04:09:54'),
(135,'Ki','Gee','KGEE','650.127.1734','ST_CLERK',2400.00,NULL,122,50,'2015-08-08 22:17:20'),
(136,'Hazel','Philtanker','HPHILTAN','650.127.1634','ST_CLERK',2200.00,NULL,122,50,'2015-01-20 02:57:04'),
(137,'Renske','Ladwig','RLADWIG','650.121.1234','ST_CLERK',3600.00,NULL,123,50,'2017-04-25 19:53:25'),
(138,'Stephen','Stiles','SSTILES','650.121.2034','ST_CLERK',3200.00,NULL,123,50,'2018-09-13 18:35:57'),
(139,'John','Seo','JSEO','650.121.2019','ST_CLERK',2700.00,NULL,123,50,'2018-11-02 09:19:29'),
(140,'Joshua','Patel','JPATEL','650.121.1834','ST_CLERK',2500.00,NULL,123,50,'2015-05-12 14:49:38'),
(141,'Trenna','Rajs','TRAJS','650.121.8009','ST_CLERK',3500.00,NULL,124,50,'2016-11-14 22:09:51'),
(142,'Curtis','Davies','CDAVIES','650.121.2994','ST_CLERK',3100.00,NULL,124,50,'2015-07-24 18:20:22'),
(143,'Randall','Matos','RMATOS','650.121.2874','ST_CLERK',2600.00,NULL,124,50,'2016-01-18 01:12:24'),
(144,'Peter','Vargas','PVARGAS','650.121.2004','ST_CLERK',2500.00,NULL,124,50,'2018-07-19 23:00:58'),
(145,'John','Russell','JRUSSEL','011.44.1344.429268','SA_MAN',14000.00,0.40,100,80,'2018-01-08 15:27:39'),
(146,'Karen','Partners','KPARTNER','011.44.1344.467268','SA_MAN',13500.00,0.30,100,80,'2015-08-09 08:15:23'),
(147,'Alberto','Errazuriz','AERRAZUR','011.44.1344.429278','SA_MAN',12000.00,0.30,100,80,'2016-09-02 18:54:05'),
(148,'Gerald','Cambrault','GCAMBRAU','011.44.1344.619268','SA_MAN',11000.00,0.30,100,80,'2017-09-09 21:44:21'),
(149,'Eleni','Zlotkey','EZLOTKEY','011.44.1344.429018','SA_MAN',10500.00,0.20,100,80,'2015-09-20 03:59:31'),
(150,'Peter','Tucker','PTUCKER','011.44.1344.129268','SA_REP',10000.00,0.30,145,80,'2018-03-28 02:44:40'),
(151,'David','Bernstein','DBERNSTE','011.44.1344.345268','SA_REP',9500.00,0.25,145,80,'2017-06-13 01:44:48'),
(152,'Peter','Hall','PHALL','011.44.1344.478968','SA_REP',9000.00,0.25,145,80,'2017-07-13 00:30:02'),
(153,'Christopher','Olsen','COLSEN','011.44.1344.498718','SA_REP',8000.00,0.20,145,80,'2016-06-11 21:15:50'),
(154,'Nanette','Cambrault','NCAMBRAU','011.44.1344.987668','SA_REP',7500.00,0.20,145,80,'2018-07-01 08:49:09'),
(155,'Oliver','Tuvault','OTUVAULT','011.44.1344.486508','SA_REP',7000.00,0.15,145,80,'2016-07-27 04:18:17'),
(156,'Janette','K_ing','JKING','011.44.1345.429268','SA_REP',10000.00,0.35,146,80,'2016-03-19 19:04:03'),
(157,'Patrick','Sully','PSULLY','011.44.1345.929268','SA_REP',9500.00,0.35,146,80,'2016-05-14 10:25:28'),
(158,'Allan','McEwen','AMCEWEN','011.44.1345.829268','SA_REP',9000.00,0.35,146,80,'2018-03-11 18:55:16'),
(159,'Lindsey','Smith','LSMITH','011.44.1345.729268','SA_REP',8000.00,0.30,146,80,'2015-04-11 15:19:58'),
(160,'Louise','Doran','LDORAN','011.44.1345.629268','SA_REP',7500.00,0.30,146,80,'2018-05-20 19:54:09'),
(161,'Sarath','Sewall','SSEWALL','011.44.1345.529268','SA_REP',7000.00,0.25,146,80,'2015-08-26 05:30:49'),
(162,'Clara','Vishney','CVISHNEY','011.44.1346.129268','SA_REP',10500.00,0.25,147,80,'2015-10-26 15:51:47'),
(163,'Danielle','Greene','DGREENE','011.44.1346.229268','SA_REP',9500.00,0.15,147,80,'2017-02-20 14:46:31'),
(164,'Mattea','Marvins','MMARVINS','011.44.1346.329268','SA_REP',7200.00,0.10,147,80,'2015-07-12 02:17:16'),
(165,'David','Lee','DLEE','011.44.1346.529268','SA_REP',6800.00,0.10,147,80,'2015-01-26 15:06:53'),
(166,'Sundar','Ande','SANDE','011.44.1346.629268','SA_REP',6400.00,0.10,147,80,'2017-08-19 20:42:40'),
(167,'Amit','Banda','ABANDA','011.44.1346.729268','SA_REP',6200.00,0.10,147,80,'2016-05-17 10:12:45'),
(168,'Lisa','Ozer','LOZER','011.44.1343.929268','SA_REP',11500.00,0.25,148,80,'2017-11-02 12:55:48'),
(169,'Harrison','Bloom','HBLOOM','011.44.1343.829268','SA_REP',10000.00,0.20,148,80,'2017-05-05 10:00:49'),
(170,'Tayler','Fox','TFOX','011.44.1343.729268','SA_REP',9600.00,0.20,148,80,'2018-03-13 11:16:44'),
(171,'William','Smith','WSMITH','011.44.1343.629268','SA_REP',7400.00,0.15,148,80,'2016-03-27 02:21:15'),
(172,'Elizabeth','Bates','EBATES','011.44.1343.529268','SA_REP',7300.00,0.15,148,80,'2015-06-13 01:56:09'),
(173,'Sundita','Kumar','SKUMAR','011.44.1343.329268','SA_REP',6100.00,0.10,148,80,'2017-05-22 02:37:06'),
(174,'Ellen','Abel','EABEL','011.44.1644.429267','SA_REP',11000.00,0.30,149,80,'2017-11-17 07:17:07'),
(175,'Alyssa','Hutton','AHUTTON','011.44.1644.429266','SA_REP',8800.00,0.25,149,80,'2018-05-15 04:34:17'),
(176,'Jonathon','Taylor','JTAYLOR','011.44.1644.429265','SA_REP',8600.00,0.20,149,80,'2015-06-27 01:00:06'),
(177,'Jack','Livingston','JLIVINGS','011.44.1644.429264','SA_REP',8400.00,0.20,149,80,'2015-01-15 15:17:45'),
(178,'Kimberely','Grant','KGRANT','011.44.1644.429263','SA_REP',7000.00,0.15,149,NULL,'2017-08-10 01:28:31'),
(179,'Charles','Johnson','CJOHNSON','011.44.1644.429262','SA_REP',6200.00,0.10,149,80,'2016-05-01 09:29:24'),
(180,'Winston','Taylor','WTAYLOR','650.507.9876','SH_CLERK',3200.00,NULL,120,50,'2017-09-12 19:01:29'),
(181,'Jean','Fleaur','JFLEAUR','650.507.9877','SH_CLERK',3100.00,NULL,120,50,'2016-10-11 18:39:18'),
(182,'Martha','Sullivan','MSULLIVA','650.507.9878','SH_CLERK',2500.00,NULL,120,50,'2015-10-19 12:12:08'),
(183,'Girard','Geoni','GGEONI','650.507.9879','SH_CLERK',2800.00,NULL,120,50,'2017-07-07 05:02:50'),
(184,'Nandita','Sarchand','NSARCHAN','650.509.1876','SH_CLERK',4200.00,NULL,121,50,'2017-06-15 12:37:48'),
(185,'Alexis','Bull','ABULL','650.509.2876','SH_CLERK',4100.00,NULL,121,50,'2015-11-15 00:00:34'),
(186,'Julia','Dellinger','JDELLING','650.509.3876','SH_CLERK',3400.00,NULL,121,50,'2015-11-07 10:09:33'),
(187,'Anthony','Cabrio','ACABRIO','650.509.4876','SH_CLERK',3000.00,NULL,121,50,'2016-08-21 02:46:04'),
(188,'Kelly','Chung','KCHUNG','650.505.1876','SH_CLERK',3800.00,NULL,122,50,'2016-10-11 07:21:45'),
(189,'Jennifer','Dilly','JDILLY','650.505.2876','SH_CLERK',3600.00,NULL,122,50,'2015-02-12 04:30:38'),
(190,'Timothy','Gates','TGATES','650.505.3876','SH_CLERK',2900.00,NULL,122,50,'2017-12-19 00:28:02'),
(191,'Randall','Perkins','RPERKINS','650.505.4876','SH_CLERK',2500.00,NULL,122,50,'2017-11-24 12:48:15'),
(192,'Sarah','Bell','SBELL','650.501.1876','SH_CLERK',4000.00,NULL,123,50,'2016-09-25 14:37:13'),
(193,'Britney','Everett','BEVERETT','650.501.2876','SH_CLERK',3900.00,NULL,123,50,'2018-11-03 10:41:27'),
(194,'Samuel','McCain','SMCCAIN','650.501.3876','SH_CLERK',3200.00,NULL,123,50,'2017-05-31 09:35:34'),
(195,'Vance','Jones','VJONES','650.501.4876','SH_CLERK',2800.00,NULL,123,50,'2015-07-19 15:53:37'),
(196,'Alana','Walsh','AWALSH','650.507.9811','SH_CLERK',3100.00,NULL,124,50,'2018-03-20 02:41:26'),
(197,'Kevin','Feeney','KFEENEY','650.507.9822','SH_CLERK',3000.00,NULL,124,50,'2017-11-07 13:46:21'),
(198,'Donald','OConnell','DOCONNEL','650.507.9833','SH_CLERK',2600.00,NULL,124,50,'2015-10-03 12:47:30'),
(199,'Douglas','Grant','DGRANT','650.507.9844','SH_CLERK',2600.00,NULL,124,50,'2017-12-08 22:38:35'),
(200,'Jennifer','Whalen','JWHALEN','515.123.4444','AD_ASST',4400.00,NULL,101,10,'2015-11-05 02:50:27'),
(201,'Michael','Hartstein','MHARTSTE','515.123.5555','MK_MAN',13000.00,NULL,100,20,'2018-02-15 18:16:36'),
(202,'Pat','Fay','PFAY','603.123.6666','MK_REP',6000.00,NULL,201,20,'2016-07-08 10:51:39'),
(203,'Susan','Mavris','SMAVRIS','515.123.7777','HR_REP',6500.00,NULL,101,40,'2017-01-26 23:28:33'),
(204,'Hermann','Baer','HBAER','515.123.8888','PR_REP',10000.00,NULL,101,70,'2016-12-12 12:47:52'),
(205,'Shelley','Higgins','SHIGGINS','515.123.8080','AC_MGR',12000.00,NULL,101,110,'2018-07-10 17:33:45'),
(206,'William','Gietz','WGIETZ','515.123.8181','AC_ACCOUNT',8300.00,NULL,205,110,'2015-03-11 01:25:11');

/*Table structure for table `job_grades` */

DROP TABLE IF EXISTS `job_grades`;

CREATE TABLE `job_grades` (
  `grade_level` varchar(3) DEFAULT NULL COMMENT '工资级别编号',
  `lowest_sal` int(11) DEFAULT NULL COMMENT '最低工资',
  `highest_sal` int(11) DEFAULT NULL COMMENT '最高工资'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资级别';

/*Data for the table `job_grades` */

insert  into `job_grades`(`grade_level`,`lowest_sal`,`highest_sal`) values 
('A',1000,2999),
('B',3000,5999),
('C',6000,9999),
('D',10000,14999),
('E',15000,24999),
('F',25000,40000);

/*Table structure for table `jobs` */

DROP TABLE IF EXISTS `jobs`;

CREATE TABLE `jobs` (
  `job_id` varchar(10) NOT NULL COMMENT '工种编号',
  `job_title` varchar(35) DEFAULT NULL COMMENT '工种名称',
  `min_salary` int(6) DEFAULT NULL COMMENT '最低工资',
  `max_salary` int(6) DEFAULT NULL COMMENT '最高工资',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工种表';

/*Data for the table `jobs` */

insert  into `jobs`(`job_id`,`job_title`,`min_salary`,`max_salary`) values 
('AC_ACCOUNT','Public Accountant',4200,9000),
('AC_MGR','Accounting Manager',8200,16000),
('AD_ASST','Administration Assistant',3000,6000),
('AD_PRES','President',20000,40000),
('AD_VP','Administration Vice President',15000,30000),
('FI_ACCOUNT','Accountant',4200,9000),
('FI_MGR','Finance Manager',8200,16000),
('HR_REP','Human Resources Representative',4000,9000),
('IT_PROG','Programmer',4000,10000),
('MK_MAN','Marketing Manager',9000,15000),
('MK_REP','Marketing Representative',4000,9000),
('PR_REP','Public Relations Representative',4500,10500),
('PU_CLERK','Purchasing Clerk',2500,5500),
('PU_MAN','Purchasing Manager',8000,15000),
('SA_MAN','Sales Manager',10000,20000),
('SA_REP','Sales Representative',6000,12000),
('SH_CLERK','Shipping Clerk',2500,5500),
('ST_CLERK','Stock Clerk',2000,5000),
('ST_MAN','Stock Manager',5500,8500);

/*Table structure for table `locations` */

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '位置编号',
  `street_address` varchar(40) DEFAULT NULL COMMENT '街道',
  `postal_code` varchar(12) DEFAULT NULL COMMENT '邮编',
  `city` varchar(30) DEFAULT NULL COMMENT '城市',
  `state_province` varchar(25) DEFAULT NULL COMMENT '州/省',
  `country_id` varchar(2) DEFAULT NULL COMMENT '国家编号',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3201 DEFAULT CHARSET=utf8 COMMENT='位置表';

/*Data for the table `locations` */

insert  into `locations`(`location_id`,`street_address`,`postal_code`,`city`,`state_province`,`country_id`) values 
(1000,'1297 Via Cola di Rie','00989','Roma',NULL,'IT'),
(1100,'93091 Calle della Testa','10934','Venice',NULL,'IT'),
(1200,'2017 Shinjuku-ku','1689','Tokyo','Tokyo Prefecture','JP'),
(1300,'9450 Kamiya-cho','6823','Hiroshima',NULL,'JP'),
(1400,'2014 Jabberwocky Rd','26192','Southlake','Texas','US'),
(1500,'2011 Interiors Blvd','99236','South San Francisco','California','US'),
(1600,'2007 Zagora St','50090','South Brunswick','New Jersey','US'),
(1700,'2004 Charade Rd','98199','Seattle','Washington','US'),
(1800,'147 Spadina Ave','M5V 2L7','Toronto','Ontario','CA'),
(1900,'6092 Boxwood St','YSW 9T2','Whitehorse','Yukon','CA'),
(2000,'40-5-12 Laogianggen','190518','Beijing',NULL,'CN'),
(2100,'1298 Vileparle (E)','490231','Bombay','Maharashtra','IN'),
(2200,'12-98 Victoria Street','2901','Sydney','New South Wales','AU'),
(2300,'198 Clementi North','540198','Singapore',NULL,'SG'),
(2400,'8204 Arthur St',NULL,'London',NULL,'UK'),
(2500,'Magdalen Centre, The Oxford Science Park','OX9 9ZB','Oxford','Oxford','UK'),
(2600,'9702 Chester Road','09629850293','Stretford','Manchester','UK'),
(2700,'Schwanthalerstr. 7031','80925','Munich','Bavaria','DE'),
(2800,'Rua Frei Caneca 1360 ','01307-002','Sao Paulo','Sao Paulo','BR'),
(2900,'20 Rue des Corps-Saints','1730','Geneva','Geneve','CH'),
(3000,'Murtenstrasse 921','3095','Bern','BE','CH'),
(3100,'Pieter Breughelstraat 837','3029SK','Utrecht','Utrecht','NL'),
(3200,'Mariano Escobedo 9991','11932','Mexico City','Distrito Federal,','MX');

/* Function  structure for function  `myf1` */

/*!50003 DROP FUNCTION IF EXISTS `myf1` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `myf1`() RETURNS int(11)
begin
	declare c int default 0;	-- 定义变量
	select count(*) into c
	from employees;
	return c;
end */$$
DELIMITER ;

/* Function  structure for function  `myf2` */

/*!50003 DROP FUNCTION IF EXISTS `myf2` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `myf2`(empName varchar(20)) RETURNS double
begin
	set @sal = 0;			-- 定义用户变量
	select e.salary into @sal	-- 赋值
	from employees e
	where e.last_name = empName;
	return @sal;
end */$$
DELIMITER ;

/* Function  structure for function  `myf3` */

/*!50003 DROP FUNCTION IF EXISTS `myf3` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `myf3`(departmentName varchar(3)) RETURNS double
begin
	declare sal double;		-- 定义局部变量
	select avg(salary) into sal
	from employees e
	inner join departments d on d.department_id = e.department_id
	where d.department_name = departmentName;
	return sal;
end */$$
DELIMITER ;

/* Function  structure for function  `myf4` */

/*!50003 DROP FUNCTION IF EXISTS `myf4` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `myf4`(num1 double, num2 double) RETURNS double
begin
	set @sum = num1 + num2;
	return @sum;
end */$$
DELIMITER ;

/* Function  structure for function  `random_dateTime` */

/*!50003 DROP FUNCTION IF EXISTS `random_dateTime` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `random_dateTime`(start_date VARCHAR(32), end_date VARCHAR(32)) RETURNS datetime
BEGIN
/*
	获取随机日期，范围 start_date ~ end_date
*/
	DECLARE return_dateTime DATETIME DEFAULT NULL;
	SET return_dateTime = FROM_UNIXTIME(random_int(UNIX_TIMESTAMP(start_date), UNIX_TIMESTAMP(end_date)));
	RETURN return_dateTime;
END */$$
DELIMITER ;

/* Function  structure for function  `random_int` */

/*!50003 DROP FUNCTION IF EXISTS `random_int` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `random_int`(start_num INT, end_num INT) RETURNS int(11)
BEGIN
/*
	获得指定范围内的随机数
	
	@params start_num 最小数（包含）
	@params end_num 最大数（包含）
	@return 随机数
*/
	DECLARE i INT DEFAULT 0;
	# 若要在i ≤ R ≤ j 这个范围得到一个随机整数R ，需要用到表达式 FLOOR(i + RAND() * (j – i + 1))。
	SET i = FLOOR(start_num + RAND() * (end_num - start_num + 1));
	RETURN i;
END */$$
DELIMITER ;

/* Function  structure for function  `test_if` */

/*!50003 DROP FUNCTION IF EXISTS `test_if` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` FUNCTION `test_if`(score double) RETURNS varchar(32) CHARSET utf8
begin
	if score > 100 or score < 0 then return '成绩输入错误，请输入 0-100 的数字';
	elseif score <= 100 then return 'A';
	elseif score < 90 then return 'B';
	elseif score < 80 then return 'C';
	else return 'D';
	end if;
end */$$
DELIMITER ;

/* Procedure structure for procedure `test_case` */

/*!50003 DROP PROCEDURE IF EXISTS  `test_case` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`::1` PROCEDURE `test_case`(in score double)
begin
	case
		when score > 100 or score < 0 then select '成绩输入错误，请输入 0-100 的数字';
		when score <= 100 then select 'A';
		when score < 90 then select 'B';
		when score < 80 then select 'C';
		else select 'D';
	end case;
end */$$
DELIMITER ;

/*Table structure for table `myv1` */

DROP TABLE IF EXISTS `myv1`;

/*!50001 DROP VIEW IF EXISTS `myv1` */;
/*!50001 DROP TABLE IF EXISTS `myv1` */;

/*!50001 CREATE TABLE  `myv1`(
 `employee_id` int(6) ,
 `last_name` varchar(25) ,
 `department_id` int(4) ,
 `department_name` varchar(3) ,
 `job_id` varchar(10) ,
 `job_title` varchar(35) ,
 `min_salary` int(6) ,
 `max_salary` int(6) 
)*/;

/*Table structure for table `myv2` */

DROP TABLE IF EXISTS `myv2`;

/*!50001 DROP VIEW IF EXISTS `myv2` */;
/*!50001 DROP TABLE IF EXISTS `myv2` */;

/*!50001 CREATE TABLE  `myv2`(
 `department_id` int(4) ,
 `ag` double(14,6) 
)*/;

/*Table structure for table `myv3` */

DROP TABLE IF EXISTS `myv3`;

/*!50001 DROP VIEW IF EXISTS `myv3` */;
/*!50001 DROP TABLE IF EXISTS `myv3` */;

/*!50001 CREATE TABLE  `myv3`(
 `department_id` int(4) ,
 `ag` double(14,6) 
)*/;

/*View structure for view myv1 */

/*!50001 DROP TABLE IF EXISTS `myv1` */;
/*!50001 DROP VIEW IF EXISTS `myv1` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`::1` SQL SECURITY DEFINER VIEW `myv1` AS select `e`.`employee_id` AS `employee_id`,`e`.`last_name` AS `last_name`,`d`.`department_id` AS `department_id`,`d`.`department_name` AS `department_name`,`j`.`job_id` AS `job_id`,`j`.`job_title` AS `job_title`,`j`.`min_salary` AS `min_salary`,`j`.`max_salary` AS `max_salary` from ((`employees` `e` join `departments` `d` on((`d`.`department_id` = `e`.`department_id`))) join `jobs` `j` on((`j`.`job_id` = `e`.`job_id`))) */;

/*View structure for view myv2 */

/*!50001 DROP TABLE IF EXISTS `myv2` */;
/*!50001 DROP VIEW IF EXISTS `myv2` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`::1` SQL SECURITY DEFINER VIEW `myv2` AS select `e`.`department_id` AS `department_id`,avg(`e`.`salary`) AS `ag` from `employees` `e` group by `e`.`department_id` */;

/*View structure for view myv3 */

/*!50001 DROP TABLE IF EXISTS `myv3` */;
/*!50001 DROP VIEW IF EXISTS `myv3` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`::1` SQL SECURITY DEFINER VIEW `myv3` AS select `m2`.`department_id` AS `department_id`,`m2`.`ag` AS `ag` from `myv2` `m2` order by `m2`.`ag` limit 1 */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
