DROP DATABASE fitnectdb;

CREATE DATABASE fitnectdb;

USE fitnectdb;


DROP TABLE IF EXISTS `USERS`;

CREATE TABLE `USERS` (
	`userId`	bigint(20) AUTO_INCREMENT PRIMARY KEY,
	`email`	varchar(100)	NOT NULL,
	`password`	varchar(255)	NOT NULL,
	`phone`	varchar(13)	NOT NULL,
	`name`	varchar(100)	NOT NULL,
	`address`	varchar(255)	NOT NULL,
	`longitude`	double	NOT NULL,
	`latitude`	double	NOT NULL,
	`profileImgUrl`	text	NULL,
	`height`	int	NULL,
	`weight`	int	NULL,
	`auth`	varchar(255)	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now()
);

DROP TABLE IF EXISTS `GYM`;

CREATE TABLE `GYM` (
	`gymId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`name`	varchar(255)	NOT NULL,
	`address`	varchar(255)	NOT NULL,
	`longitude`	double	NOT NULL,
	`latitude`	double	NOT NULL,
	`phone`	varchar(20)	NOT NULL,
	`content`	varchar(255)	NULL,
	`type`	varchar(255)	NOT NULL,
	`operationHours`	varchar(255)	NULL,
	`closedDay`	varchar(255)	NULL,
	`gymImgUrl`	text	NULL,
	`capacity`	int(10)	NOT NULL,
	`rating`	decimal(2, 1)	NOT NULL	DEFAULT 0,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now()
);

DROP TABLE IF EXISTS `GYM_MACHINE`;

CREATE TABLE `GYM_MACHINE` (
	`infoId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`machineCount`	int(3)	NOT NULL	DEFAULT 1,
	`machineName`	varchar(255)	NOT NULL
);

DROP TABLE IF EXISTS `REGIST_USERS`;

CREATE TABLE `REGIST_USERS` (
	`registId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`userId`	bigint(20)	NOT NULL,
	`priceId`	bigint(20)	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`expiredDate`	timestamp	NOT NULL
);

DROP TABLE IF EXISTS `REVIEW_GYM`;

CREATE TABLE `REVIEW_GYM` (
	`reviewGymId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`gymId`	bigint(20)	NOT NULL,
	`content`	varchar(255)	NOT NULL,
	`rating`	int(1)	NOT NULL	DEFAULT 0,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now()
);

DROP TABLE IF EXISTS `REGIST_CLASS`;

CREATE TABLE `REGIST_CLASS` (
	`regClassId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`classId`	bigint(20)	NOT NULL
);

DROP TABLE IF EXISTS `REVIEW_TRAINOR`;

CREATE TABLE `REVIEW_TRAINOR` (
	`reviewTrainorId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`trainorId`	bigint(20)	NOT NULL,
	`content`	varchar(255)	NOT NULL,
	`rating`	int(1)	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now()
);

DROP TABLE IF EXISTS `FAV_GYM`;

CREATE TABLE `FAV_GYM` (
	`favId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`gymId`	bigint(20)	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now()
);

DROP TABLE IF EXISTS `CLASS`;

CREATE TABLE `CLASS` (
	`classId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`trainorId`	bigint(20)	NOT NULL,
	`name`	varchar(255)	NOT NULL,
	`startDate`	timestamp	NOT NULL,
	`endDate`	timestamp	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now(),
	`minimum`	int(3)	NOT NULL	DEFAULT 0,
	`current`	int(3)	NOT NULL	DEFAULT 0,
	`maximum`	int(3)	NOT NULL	DEFAULT 0,
	`classPrice`	bigint(20)	NOT NULL	DEFAULT 0
);

DROP TABLE IF EXISTS `TRAINOR`;

CREATE TABLE `TRAINOR` (
	`trainorId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`gymId`	bigint(20)	NOT NULL,
	`career`	varchar(100)	NULL,
	`major`	varchar(100)	NULL
);

DROP TABLE IF EXISTS `PRICES`;

CREATE TABLE `PRICES` (
	`priceId` bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`name`	VARCHAR(255)	NOT NULL,
	`price`	bigint(20)	NOT NULL	DEFAULT 0,
	`text`	VARCHAR(255)	NOT NULL
);

DROP TABLE IF EXISTS `AMENITY`;

CREATE TABLE `AMENITY` (
	`amenityId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`freeParking`	boolean	NOT NULL,
	`paidParking`	boolean	NOT NULL,
	`allDay`	boolean	NOT NULL,
	`showerFacilities`	boolean	NOT NULL,
	`Sportswear`	boolean	NOT NULL,
	`sharedLocker`	boolean	NOT NULL,
	`personalLocker`	boolean	NOT NULL,
	`unmanded`	boolean	NOT NULL
);

ALTER TABLE `GYM_MACHINE` ADD CONSTRAINT `FK_GYM_TO_GYM_MACHINE_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `REGIST_USERS` ADD CONSTRAINT `FK_GYM_TO_REGIST_USERS_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `REGIST_USERS` ADD CONSTRAINT `FK_USERS_TO_REGIST_USERS_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REGIST_USERS` ADD CONSTRAINT `FK_PRICES_TO_REGIST_USERS_1` FOREIGN KEY (
	`priceId`
)
REFERENCES `PRICES` (
	`priceId`
);

ALTER TABLE `REVIEW_GYM` ADD CONSTRAINT `FK_USERS_TO_REVIEW_GYM_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REVIEW_GYM` ADD CONSTRAINT `FK_GYM_TO_REVIEW_GYM_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `REGIST_CLASS` ADD CONSTRAINT `FK_USERS_TO_REGIST_CLASS_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REGIST_CLASS` ADD CONSTRAINT `FK_CLASS_TO_REGIST_CLASS_1` FOREIGN KEY (
	`classId`
)
REFERENCES `CLASS` (
	`classId`
);

ALTER TABLE `REVIEW_TRAINOR` ADD CONSTRAINT `FK_USERS_TO_REVIEW_TRAINOR_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REVIEW_TRAINOR` ADD CONSTRAINT `FK_TRAINOR_TO_REVIEW_TRAINOR_1` FOREIGN KEY (
	`trainorId`
)
REFERENCES `TRAINOR` (
	`trainorId`
);

ALTER TABLE `FAV_GYM` ADD CONSTRAINT `FK_USERS_TO_FAV_GYM_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `FAV_GYM` ADD CONSTRAINT `FK_GYM_TO_FAV_GYM_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `CLASS` ADD CONSTRAINT `FK_GYM_TO_CLASS_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `CLASS` ADD CONSTRAINT `FK_TRAINOR_TO_CLASS_1` FOREIGN KEY (
	`trainorId`
)
REFERENCES `TRAINOR` (
	`trainorId`
);

ALTER TABLE `TRAINOR` ADD CONSTRAINT `FK_USERS_TO_TRAINOR_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `TRAINOR` ADD CONSTRAINT `FK_GYM_TO_TRAINOR_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `PRICES` ADD CONSTRAINT `FK_GYM_TO_PRICES_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `AMENITY` ADD CONSTRAINT `FK_GYM_TO_AMENITY_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);