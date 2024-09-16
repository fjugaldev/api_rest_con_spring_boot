-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (x86_64)
--
-- Host: db-mysql.docker-compose-files.orb.local    Database: api_db
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilege` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` (`id`, `code`, `name`) VALUES ('1c529af9-8164-4904-9e54-e5647f1ba626','EDIT_PRIVILEGE','Privilegio de Edición'),('36739fb0-c06c-4ba8-8892-316936db159b','READ_PRIVILEGE','Privilegio de Lectura'),('454dbc6d-4f77-4e00-aec5-c205ff486c32','DELETE_PRIVILEGE','Privilegio de Borrado'),('f9ada705-14bb-4cc9-b2e4-04c6837edb4d','CREATE_PRIVILEGE','Privilegio de Escritura');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reset_password_token`
--

DROP TABLE IF EXISTS `reset_password_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reset_password_token` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `expires_at` varbinary(255) DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf2tlmidtga0ohscum2abphe9o` (`user_id`),
  CONSTRAINT `FKf2tlmidtga0ohscum2abphe9o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reset_password_token`
--

LOCK TABLES `reset_password_token` WRITE;
/*!40000 ALTER TABLE `reset_password_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `reset_password_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `code`, `name`) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf','ADMIN','Admin'),('a56c332e-56e6-487d-ba23-7e3819fe293f','USER','Usuario'),('f75e9881-1a8c-4897-a64e-dc6cc4282003','MANAGER','Manager');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privileges`
--

DROP TABLE IF EXISTS `role_privileges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privileges` (
  `role_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `privilege_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  KEY `FK9bh6h5cm4bq0u3q9pcotkydq8` (`privilege_id`),
  KEY `FKgelpp2j5e63axp7bcguwaqec5` (`role_id`),
  CONSTRAINT `FK9bh6h5cm4bq0u3q9pcotkydq8` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FKgelpp2j5e63axp7bcguwaqec5` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privileges`
--

LOCK TABLES `role_privileges` WRITE;
/*!40000 ALTER TABLE `role_privileges` DISABLE KEYS */;
INSERT INTO `role_privileges` (`role_id`, `privilege_id`) VALUES ('885e3671-04f4-4b13-81ae-b350ce22daaf','1c529af9-8164-4904-9e54-e5647f1ba626'),('885e3671-04f4-4b13-81ae-b350ce22daaf','36739fb0-c06c-4ba8-8892-316936db159b'),('885e3671-04f4-4b13-81ae-b350ce22daaf','454dbc6d-4f77-4e00-aec5-c205ff486c32'),('885e3671-04f4-4b13-81ae-b350ce22daaf','f9ada705-14bb-4cc9-b2e4-04c6837edb4d'),('a56c332e-56e6-487d-ba23-7e3819fe293f','36739fb0-c06c-4ba8-8892-316936db159b'),('f75e9881-1a8c-4897-a64e-dc6cc4282003','36739fb0-c06c-4ba8-8892-316936db159b'),('f75e9881-1a8c-4897-a64e-dc6cc4282003','f9ada705-14bb-4cc9-b2e4-04c6837edb4d');
/*!40000 ALTER TABLE `role_privileges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `enabled`, `firstname`, `lastname`, `password`) VALUES ('40a81fdd-f12b-4189-960d-64ecdf81e023','hola@franciscougalde.com',_binary '','Francisco','Ugalde','$2a$10$CQ0ggZx03wCbtYBVXqFHP..aqcSPLWaBo3LWPg3Zl6SzmAk8gKoci'),('a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1','admin@sample.com',_binary '','Admin','Sample','$2a$10$20kUWnUkqL2sPsH/bGHRneWjuMml1dLdqEaLFJ9MNJKFFytcMkc8q'),('fe72f141-4c73-4d63-8a65-5d20b7d0d2cc','user@sample.com',_binary '','User','Sample','$2a$10$0Ul.O4VWzlWMdqALB42sxOlRg6iCSnefWRdaJNGFU.2T9hdvmnEUm');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  KEY `FKrhfovtciq1l558cw6udg0h0d3` (`role_id`),
  KEY `FK55itppkw3i07do3h7qoclqd4k` (`user_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrhfovtciq1l558cw6udg0h0d3` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES ('40a81fdd-f12b-4189-960d-64ecdf81e023','a56c332e-56e6-487d-ba23-7e3819fe293f'),('a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1','885e3671-04f4-4b13-81ae-b350ce22daaf'),('fe72f141-4c73-4d63-8a65-5d20b7d0d2cc','a56c332e-56e6-487d-ba23-7e3819fe293f');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tokens`
--

DROP TABLE IF EXISTS `user_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tokens` (
  `id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `token_type` enum('BEARER') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `user_id` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcd1yoodas8b3t22j5jhd9vjvw` (`user_id`),
  CONSTRAINT `FKcd1yoodas8b3t22j5jhd9vjvw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tokens`
--

LOCK TABLES `user_tokens` WRITE;
/*!40000 ALTER TABLE `user_tokens` DISABLE KEYS */;
INSERT INTO `user_tokens` (`id`, `expired`, `revoked`, `token`, `token_type`, `user_id`) VALUES ('0e09e0cf-a745-4f32-89b9-f2a82a4a92d7',_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJGcmFuY2lzY28iLCJyb2xlcyI6WyJVU0VSIl0sImVtYWlsIjoiaG9sYUBmcmFuY2lzY291Z2FsZGUuY29tIiwibGFzdG5hbWUiOiJVZ2FsZGUiLCJzdWIiOiJob2xhQGZyYW5jaXNjb3VnYWxkZS5jb20iLCJpYXQiOjE3MTM3NDE2ODEsImV4cCI6MTcxMzgyODA4MX0.MJEKpf_0zbNsv58lYt__2y15z1-QMzC-gxTBNuWmqTA','BEARER','40a81fdd-f12b-4189-960d-64ecdf81e023'),('218c10b5-42ff-4e12-99df-1a422dc08ac6',_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImVtYWlsIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImlhdCI6MTcyNjQ5MTMwOSwiZXhwIjoxNzI2NTc3NzA5fQ.FZuA1OGkKwpYxgJ8EXG_0H2jiYqn2_1Du9vaUvZGuxA','BEARER','a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1'),('40c47e5d-7fff-4b9e-a7b3-bdae82345fa6',_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImVtYWlsIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImlhdCI6MTcxNDMzNjk3MCwiZXhwIjoxNzE0NDIzMzcwfQ.j-9JHu5vAetqAJOitXuRlfKKIgtOFbm7y7qbwS3BUvo','BEARER','a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1'),('70f2c38a-7dfc-4426-adb8-f854d10837bf',_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImVtYWlsIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImlhdCI6MTcxNDMzNjA3MCwiZXhwIjoxNzE0NDIyNDcwfQ.XbcI2UmmSXsiT47CzG3pGIp4LH1aSZ7OfQj3L5lqCzg','BEARER','a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1'),('8b50816a-d191-4f0e-8161-ac35c0046bfb',_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImVtYWlsIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImlhdCI6MTcxNDMzNzAwOSwiZXhwIjoxNzE0NDIzNDA5fQ.MSnpRFVu4p29jyZaulyprDGmKnu7eRBkNgTBFK_ssfY','BEARER','a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1'),('a731b305-169a-4758-b03b-cf138d2ce7d3',_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJBZG1pbiIsInJvbGVzIjpbIkFETUlOIl0sImVtYWlsIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoiYWRtaW5Ac2FtcGxlLmNvbSIsImlhdCI6MTcxNDMzNjg1MiwiZXhwIjoxNzE0NDIzMjUyfQ.0LTXhyFXpHXlcRBrVjV-hB0hcXHx5Y5MKvH2aDG5pPk','BEARER','a2b5f7ac-eb6f-4dbf-8227-e0e22cd6c1a1'),('e1e32431-b0b4-4194-8d0c-9da14343972d',_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdG5hbWUiOiJVc2VyIiwicm9sZXMiOlsiVVNFUiJdLCJlbWFpbCI6InVzZXJAc2FtcGxlLmNvbSIsImxhc3RuYW1lIjoiU2FtcGxlIiwic3ViIjoidXNlckBzYW1wbGUuY29tIiwiaWF0IjoxNzE0MzM2MDgzLCJleHAiOjE3MTQ0MjI0ODN9.j6QdDb-j-BmzCbRZoCVQJNzqT_-W_hssY0yIvznA2fE','BEARER','fe72f141-4c73-4d63-8a65-5d20b7d0d2cc');
/*!40000 ALTER TABLE `user_tokens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-16  8:08:33
