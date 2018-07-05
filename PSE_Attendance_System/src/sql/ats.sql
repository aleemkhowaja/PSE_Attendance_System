/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.17-log : Database - psasdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`psasdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `psasdb`;

/*Table structure for table `attendance_detail` */

DROP TABLE IF EXISTS `attendance_detail`;

CREATE TABLE `attendance_detail` (
  `employee_id` int(11) DEFAULT NULL,
  `attendance_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NULL DEFAULT NULL,
  `in_time` varchar(10) DEFAULT NULL,
  `out_time` varchar(10) DEFAULT NULL,
  `in_time_description` varchar(400) DEFAULT NULL,
  `out_time_description` varchar(400) DEFAULT NULL,
  `in_late_time` varchar(200) DEFAULT NULL,
  `in_time_status` varchar(2) DEFAULT NULL,
  `out_late_time` varchar(200) DEFAULT NULL,
  `out_time_status` varchar(2) DEFAULT NULL,
  `present_at` enum('onsite','office') DEFAULT NULL,
  `leave_sick_leave` enum('Absent','Leave','Present') DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`attendance_detail_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `attendance_detail_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;

/*Data for the table `attendance_detail` */

insert  into `attendance_detail`(`employee_id`,`attendance_detail_id`,`date`,`in_time`,`out_time`,`in_time_description`,`out_time_description`,`in_late_time`,`in_time_status`,`out_late_time`,`out_time_status`,`present_at`,`leave_sick_leave`,`is_deleted`,`remarks`) values (20,125,'2018-07-02 00:00:00','05:05 PM','05:05 PM',NULL,'',NULL,NULL,'Hours = 0 \n Minutes = -25\n Seconds = 0','B',NULL,'Present',0,'');

/*Table structure for table `attendance_status` */

DROP TABLE IF EXISTS `attendance_status`;

CREATE TABLE `attendance_status` (
  `attendance_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`attendance_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `attendance_status` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`department_id`,`department_name`) values (1,'PE'),(2,'PS'),(3,'PM');

/*Table structure for table `designation` */

DROP TABLE IF EXISTS `designation`;

CREATE TABLE `designation` (
  `designation_id` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`designation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `designation` */

insert  into `designation`(`designation_id`,`designation`) values (1,'Software Engineer'),(2,'Product Manager');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `location_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `designation_id` int(11) DEFAULT NULL,
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `mobile_no` varchar(15) DEFAULT NULL,
  `registration_no` varchar(255) DEFAULT NULL,
  `present_address` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('admin','employee') DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `department_id` (`department_id`),
  KEY `location_id` (`location_id`),
  KEY `designation_id` (`designation_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`designation_id`) REFERENCES `designation` (`designation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

insert  into `employee`(`location_id`,`department_id`,`designation_id`,`employee_id`,`name`,`father_name`,`mobile_no`,`registration_no`,`present_address`,`username`,`password`,`role`,`isActive`,`isDeleted`) values (NULL,1,1,2,'Yasir','-','0335-89898988','13','Defence phase-II Karachi','y','A@413_hwhLp_44LLL144_PL3dLdp2wPP1twLthDP_@tAL14dDPhd2PP@@@_LPP4A','admin',1,0),(1,1,NULL,20,'Aleem','Amir Ali','03352692160','112','Karachi Sindh Pakistan','alim','A@413_hwhLp_44LLL144_PL3dLdp2wPP1twLthDP_@tAL14dDPhd2PP@@@_LPP4A','employee',1,0),(1,1,NULL,21,'Yasir Jilani','Ghulam Jilani','0321-8292829','100451','test','yjilani','AwwpA_pDtP_tt4D2_@d3_Lwd3421_4PLAP_A@4h444@4APd3DDL3Lw4d4dAtdA3h','employee',1,0);

/*Table structure for table `holidays` */

DROP TABLE IF EXISTS `holidays`;

CREATE TABLE `holidays` (
  `holiday_id` int(11) NOT NULL AUTO_INCREMENT,
  `holiday` date DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`holiday_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `holidays` */

insert  into `holidays`(`holiday_id`,`holiday`,`description`) values (2,'2018-06-16','sat'),(6,'2017-07-29','asdasd');

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `parent_location_id` int(11) DEFAULT NULL,
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(255) DEFAULT NULL,
  `location_type` enum('country','province/state','city') DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  KEY `parent_location_id` (`parent_location_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`parent_location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `location` */

insert  into `location`(`parent_location_id`,`location_id`,`location_name`,`location_type`) values (NULL,1,'Pakistan','country'),(1,2,'Sindh','province/state'),(2,3,'karachi','city');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
