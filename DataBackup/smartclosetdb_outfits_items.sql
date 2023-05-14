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
-- Table structure for table `outfits_items`
--

DROP TABLE IF EXISTS `outfits_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `outfits_items` (
  `outfit_id` bigint NOT NULL,
  `item_id` bigint NOT NULL,
  KEY `FK3no6vjd2i71uad6qw8p3ka2is` (`item_id`),
  KEY `FKatikjuklbelndrbnybwjb7v6b` (`outfit_id`),
  CONSTRAINT `FK3no6vjd2i71uad6qw8p3ka2is` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKatikjuklbelndrbnybwjb7v6b` FOREIGN KEY (`outfit_id`) REFERENCES `outfit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `outfits_items`
--

LOCK TABLES `outfits_items` WRITE;
/*!40000 ALTER TABLE `outfits_items` DISABLE KEYS */;
INSERT INTO `outfits_items` VALUES (1,2),(1,5),(2,5),(2,2),(3,5),(3,2),(4,5),(4,2),(5,5),(5,2),(6,5),(6,2),(7,7),(7,2),(8,5),(8,2),(9,8),(9,9),(10,10),(10,9),(11,8),(11,9),(12,10),(12,9);
/*!40000 ALTER TABLE `outfits_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13 18:23:04
