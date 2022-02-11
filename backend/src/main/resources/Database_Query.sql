CREATE DATABASE IF NOT EXISTS `speech_helper` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE IF NOT EXISTS `User` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `userUsername_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `Speech` (
  `speechId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `speechText` varchar(1000) DEFAULT NULL,
  `speechTextConverted` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`speechId`,`userId`),
  UNIQUE KEY `speechId_UNIQUE` (`speechId`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE IF NOT EXISTS `Report` (
  `reportId` int NOT NULL AUTO_INCREMENT,
  `speechId` int NOT NULL,
  `userId` int NOT NULL,
  `reportType` varchar(45) DEFAULT NULL,
  `reportReference` varchar(100) DEFAULT NULL,
  `reportOutcome` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`reportId`,`speechId`,`userId`),
  KEY `speechId_idx` (`speechId`),
  KEY `userId1_idx` (`userId`),
  CONSTRAINT `speechId` FOREIGN KEY (`speechId`) REFERENCES `Speech` (`speechId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId1` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
