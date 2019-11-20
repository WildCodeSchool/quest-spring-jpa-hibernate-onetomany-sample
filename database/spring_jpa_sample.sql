CREATE DATABASE  IF NOT EXISTS `spring_jpa_sample` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring_jpa_sample`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `potion`;

CREATE USER 'h4rryp0tt3r'@'localhost' IDENTIFIED BY 'Horcrux4life!';
GRANT ALL ON spring_jpa_sample.* TO 'h4rryp0tt3r'@'localhost';