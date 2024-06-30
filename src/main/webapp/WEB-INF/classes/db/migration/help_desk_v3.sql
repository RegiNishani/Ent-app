-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema help_desk
--

CREATE DATABASE IF NOT EXISTS help_desk;
USE help_desk;

--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `COMPANY_NAME` varchar(145) NOT NULL,
  `ADDRESS` varchar(450) default NULL,
  `COMPANY_SIZE` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`,`COMPANY_NAME`,`ADDRESS`,`COMPANY_SIZE`) VALUES 
 (1,'Apple','New York',19),
 (2,'Bite Dance','China',5000),
 (3,'Samsung','Korean',2000);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `PROJECT_NAME` varchar(145) NOT NULL,
  `PROJECT_TYPE` varchar(45) default NULL,
  `ACTIVE_STATUS` varchar(1) default NULL,
  `COMPANY_NO` varchar(45) NOT NULL,
  `project_desc` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projects`
--

/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` (`ID`,`PROJECT_NAME`,`PROJECT_TYPE`,`ACTIVE_STATUS`,`COMPANY_NO`,`project_desc`) VALUES 
 (1,'Hospital Management System',NULL,'Y','2','Hospital Management System');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;


--
-- Definition of table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` bigint(20) NOT NULL auto_increment,
  `task_desc` varchar(255) default NULL,
  `task_owner` varchar(255) default NULL,
  `task_status` varchar(255) default NULL,
  `task_time` varchar(255) default NULL,
  `task_title` varchar(255) default NULL,
  `task_type` varchar(255) default NULL,
  `project_id` varchar(255) default NULL,
  `task_date` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tasks`
--

/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` (`id`,`task_desc`,`task_owner`,`task_status`,`task_time`,`task_title`,`task_type`,`project_id`,`task_date`) VALUES 
 (1,'Major Bug in Menu System','5','COMPLETED','5.3','Major Bug in Menu System','BUG','1','2024-06-30 16:23:32');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `USERNAME` varchar(145) NOT NULL,
  `PASSWORD` varchar(145) NOT NULL,
  `USER_TYPE` varchar(45) NOT NULL,
  `COMPANY_NO` int(10) unsigned default NULL,
  `ACTIVE_STATUS` varchar(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`,`USERNAME`,`PASSWORD`,`USER_TYPE`,`COMPANY_NO`,`ACTIVE_STATUS`) VALUES 
 (1,'admin','123','ADMIN',NULL,'Y'),
 (4,'customer','123','CUSTOMER',2,'Y'),
 (5,'employee1','123','EMPLOYEE',1,'Y'),
 (6,'employee2','123','EMPLOYEE',3,'Y'),
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
