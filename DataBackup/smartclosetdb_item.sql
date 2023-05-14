CREATE DATABASE  IF NOT EXISTS `smartclosetdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `smartclosetdb`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: smartclosetdb
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `color` varchar(100) DEFAULT NULL,
  `description` mediumtext,
  `display` int DEFAULT NULL,
  `image_path` mediumtext,
  `price` double NOT NULL,
  `id_brand` bigint NOT NULL,
  `id_category` bigint NOT NULL,
  `id_closet` bigint NOT NULL,
  `id_season` bigint NOT NULL,
  `id_subcategory` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK37dh0tf0efw6j0sq83gq0ljct` (`id_brand`),
  KEY `FK2pgqac6hgoyjsjklk6jmh0tne` (`id_category`),
  KEY `FKjug94993u3byub87mc3tmtwud` (`id_closet`),
  KEY `FKb775d245af2ubh7eokij4cl5x` (`id_season`),
  KEY `FKjrgwuysqi5wggt243iol9abt5` (`id_subcategory`),
  CONSTRAINT `FK2pgqac6hgoyjsjklk6jmh0tne` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  CONSTRAINT `FK37dh0tf0efw6j0sq83gq0ljct` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id`),
  CONSTRAINT `FKb775d245af2ubh7eokij4cl5x` FOREIGN KEY (`id_season`) REFERENCES `season` (`id`),
  CONSTRAINT `FKjrgwuysqi5wggt243iol9abt5` FOREIGN KEY (`id_subcategory`) REFERENCES `subcategory` (`id`),
  CONSTRAINT `FKjug94993u3byub87mc3tmtwud` FOREIGN KEY (`id_closet`) REFERENCES `closet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'red','i love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fraw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fjean.png?alt=media&token=0575cf15-0717-4fa7-bda8-66cc115e9224',100,4,4,1,1,3),(5,'red','i love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fraw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fjean.png?alt=media&token=daedb6e9-a572-4677-accc-87513ab9b96f',5000,7,3,1,1,1),(6,'red','i like it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fraw%3A%2Fstorage%2Femulated%2F0%2FDownload%2Fjean.png?alt=media&token=6e45ab0d-9f10-4f05-9cb3-9763b8d825c6',100,8,4,1,2,3),(7,'black','i love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fimage%3A1000004203?alt=media&token=f0cd3a41-3592-4c9e-a9a8-d89b99755c99',100,9,3,1,2,1),(8,'white','i love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2F1922764703?alt=media&token=c7e008bd-bf9e-4bbd-968f-d3c3bcf66cfd',200,10,3,6,1,6),(9,'Blue','I love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2F1755182857?alt=media&token=c77a9b92-4991-4381-a6e3-94ded56a34c9',200,11,4,6,3,3),(10,'blue','i love it',1,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fimage%3A1000004225?alt=media&token=cedea735-5659-49da-a010-7566b4851ee0',100,12,3,6,3,2),(11,'r','r',0,'https://firebasestorage.googleapis.com/v0/b/smartcloset-a6a3f.appspot.com/o/Android%20Images%2Fimage%3A1000004225?alt=media&token=a0ef613e-978a-436e-947e-d49172d40f1a',12,13,3,6,3,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13 18:23:07
