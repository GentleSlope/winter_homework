DROP TABLE IF EXISTS `User`;
CREATE TABLE `mlgb`.`userinfo`  (
  `songListName` varchar(50) NOT NULL,
  `picId` int(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`picId`)
);