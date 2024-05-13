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

DROP TABLE IF EXISTS `REGIST_CLASSES`;

CREATE TABLE `REGIST_CLASSES` (
	`regClassId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`classId`	bigint(20)	NOT NULL
);

DROP TABLE IF EXISTS `REVIEW_TRAINER`;

CREATE TABLE `REVIEW_TRAINER` (
	`reviewTrainerId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`trainerId`	bigint(20)	NOT NULL,
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

DROP TABLE IF EXISTS `CLASSES`;

CREATE TABLE `CLASSES` (
	`classId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`gymId`	bigint(20)	NOT NULL,
	`trainerId`	bigint(20)	NOT NULL,
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

DROP TABLE IF EXISTS `TRAINER`;

CREATE TABLE `TRAINER` (
	`trainerId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
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
	`text`	VARCHAR(255)	NOT NULL,
    `days` int NOT NULL
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

ALTER TABLE `REGIST_CLASSES` ADD CONSTRAINT `FK_USERS_TO_REGIST_CLASSES_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REGIST_CLASSES` ADD CONSTRAINT `FK_CLASSES_TO_REGIST_CLASSES_1` FOREIGN KEY (
	`classId`
)
REFERENCES `CLASSES` (
	`classId`
);

ALTER TABLE `REVIEW_TRAINER` ADD CONSTRAINT `FK_USERS_TO_REVIEW_TRAINER_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `REVIEW_TRAINER` ADD CONSTRAINT `FK_TRAINER_TO_REVIEW_TRAINER_1` FOREIGN KEY (
	`trainerId`
)
REFERENCES `TRAINER` (
	`trainerId`
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

ALTER TABLE `CLASSES` ADD CONSTRAINT `FK_GYM_TO_CLASSES_1` FOREIGN KEY (
	`gymId`
)
REFERENCES `GYM` (
	`gymId`
);

ALTER TABLE `CLASSES` ADD CONSTRAINT `FK_TRAINER_TO_CLASSES_1` FOREIGN KEY (
	`trainerId`
)
REFERENCES `TRAINER` (
	`trainerId`
);

ALTER TABLE `TRAINER` ADD CONSTRAINT `FK_USERS_TO_TRAINER_1` FOREIGN KEY (
	`userId`
)
REFERENCES `USERS` (
	`userId`
);

ALTER TABLE `TRAINER` ADD CONSTRAINT `FK_GYM_TO_TRAINER_1` FOREIGN KEY (
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