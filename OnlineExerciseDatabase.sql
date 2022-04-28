/*
MySQL Backup
Database: online_exercise
Backup Time: 2022-03-12 11:32:23
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `online_exercise`.`tb_exam`;
DROP TABLE IF EXISTS `online_exercise`.`tb_exam_to_question`;
DROP TABLE IF EXISTS `online_exercise`.`tb_param`;
DROP TABLE IF EXISTS `online_exercise`.`tb_question`;
DROP TABLE IF EXISTS `online_exercise`.`tb_user`;
CREATE TABLE `tb_exam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `start_time` varchar(40) NOT NULL,
  `score` int NOT NULL,
  `finished` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `tb_exam_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tb_exam_to_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `eid` int NOT NULL,
  `qid` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`id`,`eid`,`qid`),
  KEY `eid` (`eid`),
  KEY `qid` (`qid`),
  CONSTRAINT `tb_exam_to_question_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `tb_exam` (`id`),
  CONSTRAINT `tb_exam_to_question_ibfk_2` FOREIGN KEY (`qid`) REFERENCES `tb_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tb_param` (
  `id` int NOT NULL AUTO_INCREMENT,
  `qid` int NOT NULL,
  `param` varchar(40) NOT NULL,
  `weight` int NOT NULL,
  `answer1` int NOT NULL,
  `answer2` int NOT NULL,
  `answer3` int NOT NULL,
  `answer4` int NOT NULL,
  `answer5` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `qid` (`qid`),
  CONSTRAINT `tb_param_ibfk_1` FOREIGN KEY (`qid`) REFERENCES `tb_question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tb_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stem` text NOT NULL,
  `weight` int NOT NULL,
  `chosen_times` int NOT NULL,
  `total_score` int NOT NULL,
  `cheat_times` int NOT NULL,
  `option1` varchar(200) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `option3` varchar(200) NOT NULL,
  `option4` varchar(200) NOT NULL,
  `option5` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
BEGIN;
LOCK TABLES `online_exercise`.`tb_exam` WRITE;
DELETE FROM `online_exercise`.`tb_exam`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `online_exercise`.`tb_exam_to_question` WRITE;
DELETE FROM `online_exercise`.`tb_exam_to_question`;
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `online_exercise`.`tb_param` WRITE;
DELETE FROM `online_exercise`.`tb_param`;
INSERT INTO `online_exercise`.`tb_param` (`id`,`qid`,`param`,`weight`,`answer1`,`answer2`,`answer3`,`answer4`,`answer5`) VALUES (1, 1, 'default param1', 50, 0, 0, 0, 0, 0),(2, 2, 'default param2', 50, 0, 0, 0, 0, 0),(3, 3, 'default param3', 50, 0, 0, 0, 0, 0),(4, 4, 'default param4', 50, 0, 0, 0, 0, 0),(5, 5, 'default param5', 50, 0, 0, 0, 0, 0),(6, 6, 'default param6', 50, 0, 0, 0, 0, 0),(7, 7, 'default param7', 50, 0, 0, 0, 0, 0),(8, 8, 'default param8', 50, 0, 0, 0, 0, 0),(9, 9, 'default param9', 50, 0, 0, 0, 0, 0),(10, 10, 'default param10', 50, 0, 0, 0, 0, 0),(11, 11, 'default param11', 50, 0, 0, 0, 0, 0),(12, 12, 'default param12', 50, 0, 0, 0, 0, 0),(13, 13, 'default param13', 50, 0, 0, 0, 0, 0),(14, 14, 'default param14', 50, 0, 0, 0, 0, 0),(15, 15, 'default param15', 50, 0, 0, 0, 0, 0),(16, 16, 'default param16', 50, 0, 0, 0, 0, 0),(17, 17, 'default param17', 50, 0, 0, 0, 0, 0),(18, 18, 'default param18', 50, 0, 0, 0, 0, 0),(19, 19, 'default param19', 50, 0, 0, 0, 0, 0),(20, 20, 'default param20', 50, 0, 0, 0, 0, 0);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `online_exercise`.`tb_question` WRITE;
DELETE FROM `online_exercise`.`tb_question`;
INSERT INTO `online_exercise`.`tb_question` (`id`,`stem`,`weight`,`chosen_times`,`total_score`,`cheat_times`,`option1`,`option2`,`option3`,`option4`,`option5`) VALUES (1, '1: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(2, '2: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(3, '3: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(4, '4: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(5, '5: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(6, '6: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(7, '7: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(8, '8: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(9, '9: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(10, '10: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(11, '11: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(12, '12: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(13, '13: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(14, '14: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(15, '15: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(16, '16: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(17, '17: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(18, '18: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(19, '19: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e'),(20, '20: %param', 50, 0, 0, 0, 'test a', 'test b', 'test c', 'test d', 'test e');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `online_exercise`.`tb_user` WRITE;
DELETE FROM `online_exercise`.`tb_user`;
INSERT INTO `online_exercise`.`tb_user` (`id`,`username`,`password`) VALUES (1, 'test1', '123456'),(2, 'test2', '123456'),(3, 'test3', '123456'),(4, 'test4', '123456'),(5, 'test5', '123456'),(6, 'test6', '123456'),(7, 'test7', '123456'),(8, 'test8', '123456'),(9, 'test9', '123456'),(10, 'test10', '123456');
UNLOCK TABLES;
COMMIT;
