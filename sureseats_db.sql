-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: sureseats_db
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CINEMA`
--

DROP TABLE IF EXISTS `CINEMA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CINEMA` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `CNo` int(11) NOT NULL,
  `CType` varchar(10) NOT NULL,
  `MID` int(11) NOT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `CID_UNIQUE` (`CID`),
  KEY `MID_idx` (`MID`),
  CONSTRAINT `cinema_mall` FOREIGN KEY (`MID`) REFERENCES `MALL` (`MID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CINEMA`
--

LOCK TABLES `CINEMA` WRITE;
/*!40000 ALTER TABLE `CINEMA` DISABLE KEYS */;
INSERT INTO `CINEMA` VALUES (1,2,'3D',2),(2,4,'2D',2),(3,3,'4DX',5),(4,5,'2D',3),(5,1,'4DX',1);
/*!40000 ALTER TABLE `CINEMA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CITY`
--

DROP TABLE IF EXISTS `CITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CITY` (
  `CTID` int(11) NOT NULL AUTO_INCREMENT,
  `CTName` varchar(45) NOT NULL,
  `CTType` varchar(1) NOT NULL,
  `PID` int(11) NOT NULL,
  PRIMARY KEY (`CTID`),
  UNIQUE KEY `CTID_UNIQUE` (`CTID`),
  KEY `PID_idx` (`PID`),
  CONSTRAINT `city_province` FOREIGN KEY (`PID`) REFERENCES `PROVINCE` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CITY`
--

LOCK TABLES `CITY` WRITE;
/*!40000 ALTER TABLE `CITY` DISABLE KEYS */;
INSERT INTO `CITY` VALUES (1,'Makati','C',1),(2,'Pateros','M',1),(3,'Abucay','M',3),(4,'Passi','C',4),(5,'Alfonso','M',5);
/*!40000 ALTER TABLE `CITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FILM`
--

DROP TABLE IF EXISTS `FILM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FILM` (
  `FID` int(11) NOT NULL AUTO_INCREMENT,
  `FTitle` varchar(45) NOT NULL,
  `FGenre` varchar(45) NOT NULL,
  `FDate` date NOT NULL,
  `FRating` varchar(10) NOT NULL,
  `FCast` varchar(200) NOT NULL,
  `FRuntime` int(11) NOT NULL,
  `FPrice` decimal(10,2) NOT NULL,
  `FSynopsis` varchar(200) NOT NULL,
  `FImage` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `FID_UNIQUE` (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FILM`
--

LOCK TABLES `FILM` WRITE;
/*!40000 ALTER TABLE `FILM` DISABLE KEYS */;
INSERT INTO `FILM` VALUES (1,'Happy Death Day','Horror','2017-04-08','R-13','Israel Broussard, Jessica Rothe',110,250.00,'...','hdd.jpg'),(2,'Bad Genius','Comedy','2017-10-18','PG','Chutimon Chuengcharoensukying, Eisaya Hosuwan, Teeradon Supapunpinyo',129,180.00,'...','bg.jpg'),(3,'The Foreigner','Thriller','2017-11-20','R-13','Jackie Chan',111,235.00,'..','tf.jpg'),(4,'Seven Sundays','Drama','2017-12-25','PG','Aga Muhlach, Dingdong Dantes, Enrique Gil',128,135.00,'...','ss.jpg'),(5,'My Little Pony: The Movie','Animation','2017-10-04','G','Emily Blunt, Kristin Chenoweth, Liev Schreiber',106,250.00,'...','mlp.jpg'),(6,'Chesapeake Shores','Drama','2017-12-26','PG','Meghan Ory, Jesse Metcalfe, Treat Williams, Brendan Penny, Andrew Francis, Diane Ladd',128,135.00,'A young woman returns to her hometown to help save her sister\'s failing inn, where she contends with memories and faces from her past.','cs.jpg');
/*!40000 ALTER TABLE `FILM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MALL`
--

DROP TABLE IF EXISTS `MALL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MALL` (
  `MID` int(11) NOT NULL AUTO_INCREMENT,
  `MName` varchar(45) NOT NULL,
  `CTID` int(11) NOT NULL,
  PRIMARY KEY (`MID`),
  UNIQUE KEY `MID_UNIQUE` (`MID`),
  KEY `mall_city_idx` (`CTID`),
  CONSTRAINT `mall_city` FOREIGN KEY (`CTID`) REFERENCES `CITY` (`CTID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MALL`
--

LOCK TABLES `MALL` WRITE;
/*!40000 ALTER TABLE `MALL` DISABLE KEYS */;
INSERT INTO `MALL` VALUES (1,'Trinoma',1),(2,'UP Town Center',2),(3,'Greenbelt',3),(4,'Alabang Town Center',4),(5,'Glorietta 4',5);
/*!40000 ALTER TABLE `MALL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PREFERS`
--

DROP TABLE IF EXISTS `PREFERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PREFERS` (
  `UID` int(11) NOT NULL,
  `MID` int(11) NOT NULL,
  PRIMARY KEY (`UID`,`MID`),
  KEY `MID_idx` (`MID`),
  CONSTRAINT `prefers_mall` FOREIGN KEY (`MID`) REFERENCES `MALL` (`MID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `prefers_user` FOREIGN KEY (`UID`) REFERENCES `USER` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PREFERS`
--

LOCK TABLES `PREFERS` WRITE;
/*!40000 ALTER TABLE `PREFERS` DISABLE KEYS */;
INSERT INTO `PREFERS` VALUES (1,1),(1,2),(1,3),(3,3),(4,5);
/*!40000 ALTER TABLE `PREFERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROVINCE`
--

DROP TABLE IF EXISTS `PROVINCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROVINCE` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `PName` varchar(45) NOT NULL,
  PRIMARY KEY (`PID`),
  UNIQUE KEY `PID_UNIQUE` (`PID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROVINCE`
--

LOCK TABLES `PROVINCE` WRITE;
/*!40000 ALTER TABLE `PROVINCE` DISABLE KEYS */;
INSERT INTO `PROVINCE` VALUES (1,'Metro Manila'),(2,'Abra'),(3,'Bataan'),(4,'Iloilo'),(5,'Cavite');
/*!40000 ALTER TABLE `PROVINCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RESERVATION`
--

DROP TABLE IF EXISTS `RESERVATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RESERVATION` (
  `RID` int(11) NOT NULL AUTO_INCREMENT,
  `RCode` varchar(45) NOT NULL,
  `RType` varchar(1) NOT NULL,
  `RDateTime` datetime NOT NULL,
  `RStatus` varchar(9) NOT NULL,
  `UID` int(11) NOT NULL,
  `SeID` int(11) NOT NULL,
  `SID` int(11) NOT NULL,
  PRIMARY KEY (`RID`),
  UNIQUE KEY `RID_UNIQUE` (`RID`),
  KEY `reservation_user_idx` (`UID`),
  KEY `reservation_seat_idx` (`SeID`),
  KEY `reservation_schedule_idx` (`SID`),
  CONSTRAINT `reservation_schedule` FOREIGN KEY (`SID`) REFERENCES `SCHEDULE` (`SID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reservation_seat` FOREIGN KEY (`SeID`) REFERENCES `SEAT` (`SeID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reservation_user` FOREIGN KEY (`UID`) REFERENCES `USER` (`UID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RESERVATION`
--

LOCK TABLES `RESERVATION` WRITE;
/*!40000 ALTER TABLE `RESERVATION` DISABLE KEYS */;
INSERT INTO `RESERVATION` VALUES (1,'SR1456','R','2017-10-20 14:00:00','pending',4,1,1),(2,'GD7854','R','2017-08-17 12:00:00','pending',1,3,2),(3,'NK1285','R','2017-01-01 10:00:00','pending',3,2,3),(4,'YJ7532','B','2017-02-26 08:00:00','pending',5,4,4),(5,'LA0411','R','2017-09-03 10:00:00','claimed',2,5,5);
/*!40000 ALTER TABLE `RESERVATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SCHEDULE`
--

DROP TABLE IF EXISTS `SCHEDULE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SCHEDULE` (
  `SID` int(11) NOT NULL AUTO_INCREMENT,
  `SStart` datetime NOT NULL,
  `SEnd` datetime NOT NULL,
  `CID` int(11) NOT NULL,
  `FID` int(11) NOT NULL,
  PRIMARY KEY (`SID`),
  UNIQUE KEY `SID_UNIQUE` (`SID`),
  KEY `schedule_cinema_idx` (`CID`),
  KEY `schedule_film_idx` (`FID`),
  CONSTRAINT `schedule_cinema` FOREIGN KEY (`CID`) REFERENCES `CINEMA` (`CID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `schedule_film` FOREIGN KEY (`FID`) REFERENCES `FILM` (`FID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SCHEDULE`
--

LOCK TABLES `SCHEDULE` WRITE;
/*!40000 ALTER TABLE `SCHEDULE` DISABLE KEYS */;
INSERT INTO `SCHEDULE` VALUES (1,'2017-04-08 14:00:00','2017-04-08 15:50:00',1,1),(2,'2017-12-25 12:00:00','2017-12-25 14:10:00',3,4),(3,'2017-10-18 10:00:00','2017-10-18 12:00:00',2,2),(4,'2017-10-04 08:00:00','2017-10-04 09:50:00',4,5),(5,'2017-11-20 10:00:00','2017-11-20 11:55:00',5,3);
/*!40000 ALTER TABLE `SCHEDULE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEAT`
--

DROP TABLE IF EXISTS `SEAT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEAT` (
  `SeID` int(11) NOT NULL AUTO_INCREMENT,
  `SeRow` varchar(1) NOT NULL,
  `SeCol` int(11) NOT NULL,
  `CID` int(11) NOT NULL,
  PRIMARY KEY (`SeID`),
  UNIQUE KEY `SeID_UNIQUE` (`SeID`),
  KEY `seat_cinema_idx` (`CID`),
  CONSTRAINT `seat_cinema` FOREIGN KEY (`CID`) REFERENCES `CINEMA` (`CID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEAT`
--

LOCK TABLES `SEAT` WRITE;
/*!40000 ALTER TABLE `SEAT` DISABLE KEYS */;
INSERT INTO `SEAT` VALUES (1,'A',1,1),(2,'B',3,2),(3,'C',6,3),(4,'G',2,4),(5,'F',2,5);
/*!40000 ALTER TABLE `SEAT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `UUsername` varchar(45) NOT NULL,
  `UEmail` varchar(45) NOT NULL,
  `UPassword` varchar(45) NOT NULL,
  `UMobileNo` varchar(45) NOT NULL,
  `UFirstName` varchar(45) NOT NULL,
  `ULastName` varchar(45) NOT NULL,
  `UGender` varchar(45) NOT NULL,
  `UBDate` date NOT NULL,
  `URDate` date NOT NULL,
  `ULastLogin` datetime NOT NULL,
  `UIsLocked` bit(1) NOT NULL DEFAULT b'0',
  `PID` int(11) NOT NULL,
  `CTID` int(11) NOT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UID_UNIQUE` (`UID`),
  KEY `user_province_idx` (`PID`),
  KEY `user_city_idx` (`CTID`),
  CONSTRAINT `user_city` FOREIGN KEY (`CTID`) REFERENCES `CITY` (`CTID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_province` FOREIGN KEY (`PID`) REFERENCES `PROVINCE` (`PID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` VALUES (1,'diane1998','diane1998@gmail.com','******','+639471012375','Diane','Cruz','F','1998-02-06','2017-09-29','2017-10-20 00:00:00','\0',1,1),(2,'SirPepe','JJ_59@yahoo.com','*****','+637441258554','Jose','Jimenez','M','1960-06-12','2011-04-06','2016-08-17 00:00:00','\0',3,3),(3,'Efrit','eldron_calma@gmail.com','*****','+639254468522','Fritz','Calimag','M','1998-01-01','2013-07-19','2017-01-01 00:00:00','\0',1,2),(4,'Toast22A','dontspamme@gmail.com','***********','+631478965547','Luis','Lopez','M','1999-09-20','2010-12-25','2015-02-26 00:00:00','\0',5,5),(5,'Merpy','adsol@yahoo.com','******','+639328462637','Adrienne','Soliven','F','1997-12-07','2017-06-05','2017-09-03 00:00:00','\0',1,1),(6,'test','test@hotmail.com','password','+639272222222','Test','Account','M','1997-02-05','2017-11-30','2017-12-01 10:49:00','\0',1,1);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-01 11:00:34
