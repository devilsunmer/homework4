CREATE DATABASE  IF NOT EXISTS `aromatherapy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aromatherapy`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: aromatherapy
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productNumber` varchar(45) DEFAULT NULL,
  `productName` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `productOverview` varchar(150) DEFAULT NULL,
  `productCost` int DEFAULT NULL,
  `productPrice` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('p001','甜橙','果實類','具有淡淡香氣，是關於開花結果後帶來充分的甜味，同時也具有令人心情愉悅的氣味感染。',1200,1500),('p002','薰衣草','花類','從老到小、普羅大眾廣泛熟知、同時也是大多群眾最能接受的香氣，仍是要注意少數可能會有過敏體質。',1800,2100),('p003','玫瑰','花類','花中之王，也是可以讓自己變得更加柔嫩。',3500,4000),('p004','檸檬','果實類','千萬不要以為只有酸氣的味道而排斥，可是能夠沁人肺腑的好經喲！',900,1100),('p005','迷迭香','葉片類','可以增強記憶，也可以治療呼吸系統感染的部分，是個非常萬用的居家產品。',1300,1550),('p006','薄荷','葉片類','除了眾人皆所知的口香糖，也是中暑或是夏季讓身體涼快的經由喔！',980,1180),('p007','檀香','木質類','在精油中，是最能沉澱心靈的神聖精油，也能夠將周遭的空間淨化、清除不好的感受。',3630,4500),('p008','雪松','木質類','住在高山、更在冷峻的環境生長，帶來的感受更是莊嚴、清冷。',1650,2050),('p009','薑','根莖類','對於體虛、身體容易腫脹的人來說，每天晚上休息前用薑2k7ur/ u.6，dk3u3b;4gp wu3ur04ur04gj jc03ep e93g04。',750,900),('p010','岩蘭草','根莖類','情緒不穩、身體大起大落，除了檀香以外的替代品便是這款。',1780,2350),('p011','黑胡椒','種子類','不要以為只有食物才會加上他，吃飯前使用可以有效幫助減肥！',850,970),('p012','甜茴香','種子類','女性經期可以幫助緩解，或是身邊誰喝醉酒，讓他聞一聞。',970,1320);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-26 10:47:23
