-- fitnectdb --
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
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now()
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
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now()
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
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now()
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
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now()
);

DROP TABLE IF EXISTS `FAV_GYM`;

CREATE TABLE `FAV_GYM` (
	`favId`	bigint(20) AUTO_INCREMENT	PRIMARY KEY,
	`userId`	bigint(20)	NOT NULL,
	`gymId`	bigint(20)	NOT NULL,
	`createdDate`	timestamp	NOT NULL	DEFAULT now(),
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now()
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
	`modifiedDate`	timestamp	NOT NULL	DEFAULT now() ON UPDATE now(),
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



-- gymAndTrainerTrigger
DELIMITER //

CREATE TRIGGER calculate_gym_rating_after_review_change AFTER INSERT ON `fitnectdb`.`review_gym`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the gym
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_gym`
    WHERE `gymId` = NEW.`gymId`;

    -- Update gym rating
    UPDATE `fitnectdb`.`gym`
    SET `rating` = total_rating / num_reviews
    WHERE `gymId` = NEW.`gymId`;
END;
//

CREATE TRIGGER recalculate_gym_rating_after_review_deletion AFTER DELETE ON `fitnectdb`.`review_gym`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the gym
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_gym`
    WHERE `gymId` = OLD.`gymId`;

    -- Update gym rating
    UPDATE `fitnectdb`.`gym`
    SET `rating` = total_rating / num_reviews
    WHERE `gymId` = OLD.`gymId`;
END;
//

CREATE TRIGGER update_gym_rating_after_review_update AFTER UPDATE ON `fitnectdb`.`review_gym`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the gym
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_gym`
    WHERE `gymId` = OLD.`gymId`;

    -- Update gym rating
    UPDATE `fitnectdb`.`gym`
    SET `rating` = total_rating / num_reviews
    WHERE `gymId` = OLD.`gymId`;
END;
//

DELIMITER ;




DELIMITER //

CREATE TRIGGER calculate_trainer_rating_after_review_change AFTER INSERT ON `fitnectdb`.`review_trainer`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the trainer
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_trainer`
    WHERE `trainerId` = NEW.`trainerId`;

    -- Update trainer rating
    UPDATE `fitnectdb`.`trainer`
    SET `rating` = total_rating / num_reviews
    WHERE `trainerId` = NEW.`trainerId`;
END;
//

CREATE TRIGGER recalculate_trainer_rating_after_review_deletion AFTER DELETE ON `fitnectdb`.`review_trainer`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the trainer
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_trainer`
    WHERE `trainerId` = OLD.`trainerId`;

    -- Update trainer rating
    UPDATE `fitnectdb`.`trainer`
    SET `rating` = total_rating / num_reviews
    WHERE `trainerId` = OLD.`trainerId`;
END;
//

CREATE TRIGGER update_trainer_rating_after_review_update AFTER UPDATE ON `fitnectdb`.`review_trainer`
FOR EACH ROW
BEGIN
    DECLARE total_rating DECIMAL(5, 2);
    DECLARE num_reviews INT;

    -- Calculate total rating and number of reviews for the trainer
    SELECT SUM(`rating`), COUNT(*) INTO total_rating, num_reviews
    FROM `fitnectdb`.`review_trainer`
    WHERE `trainerId` = OLD.`trainerId`;

    -- Update trainer rating
    UPDATE `fitnectdb`.`trainer`
    SET `rating` = total_rating / num_reviews
    WHERE `trainerId` = OLD.`trainerId`;
END;
//

DELIMITER ;

-- gym --
-- 수영
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('웨이브워터 강남', '서울특별시 강남구 학동로 426(삼성동, 강남구청내 본관1층로비)', 127.0470598, 37.51796816, '010-1234-5678', '수영', 100, 0),
    ('블루워터 강남', '서울특별시 강남구 학동로20길 25(논현동)', 127.0285345, 37.5115038, '010-2345-6789', '수영', 80, 0),
    ('실버워터 강남', '서울특별시 강남구 강남대로 396(역삼동, 2호선 강남역1번,12번 출구 중간)', 127.0283079, 37.49816465, '010-3456-7890', '수영', 120, 0),
    ('액티브워터 강남', '서울특별시 강남구 테헤란로 538(역삼동, 지하1층 역무실앞)', 127.063073, 37.508862, '010-4567-8901', '수영', 90, 0),
    ('플라잉워터 강남', '서울특별시 강남구 도곡로77길 23(대치동)', 127.0578495, 37.49972974, '010-5678-9012', '수영', 110, 0),
    ('피쉬워터 역삼1동', '서울특별시 강남구 역삼로7길 16(역삼동, 역삼1동주민센터 1층 로비)', 127.0332838, 37.49537321, '010-6789-0123', '수영', 70, 0),
    ('디럭스워터 삼성', '서울특별시 강남구 봉은사로 616(삼성동)', 127.062501, 37.5143323, '010-7890-1234', '수영', 150, 0),
    ('미러워터 강남', '서울특별시 강남구 학동로 425(청담동)', 127.0469585, 37.51871603, '010-8901-2345', '수영', 85, 0),
    ('파워워터 일원', '서울특별시 강남구 일원로 81(일원동, 별관입구 수납창구 뒤)', 127.0895902, 37.49034451, '010-9012-3456', '수영', 95, 0),
    ('헬스워터 삼성2동', '서울특별시 강남구 봉은사로 419(삼성동)', 127.0459769, 37.51120727, '010-0123-4567', '수영', 130, 0);


-- 헬스
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('피트니스 강남센터', '서울특별시 강남구 테헤란로 114(역삼동)', 127.0301032, 37.49828846, '010-1234-5678', '헬스', 200, 0),
    ('스트롱헬스 강남', '서울특별시 강남구 광평로 301-4(수서동)', 127.1049911, 37.4889031, '010-2345-6789', '헬스', 150, 0),
    ('파워헬스 역삼', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-3456-7890', '헬스', 180, 0),
    ('다이나믹헬스 대치', '서울특별시 강남구 도곡로77길 23(대치동)', 127.0578495, 37.49972974, '010-4567-8901', '헬스', 220, 0),
    ('스포티헬스 청담', '서울특별시 강남구 학동로 426(삼성동, 강남구청내 본관1층 로비)', 127.0470598, 37.51796816, '010-5678-9012', '헬스', 170, 0),
    ('엑티브헬스 삼성', '서울특별시 강남구 봉은사로 616(삼성동)', 127.062501, 37.5143323, '010-6789-0123', '헬스', 190, 0),
    ('히어로헬스 삼성2동', '서울특별시 강남구 봉은사로 419(삼성동)', 127.0459769, 37.51120727, '010-7890-1234', '헬스', 210, 0),
    ('플라잉헬스 청담', '서울특별시 강남구 학동로 426(삼성동, 강남구청내 본관 1층 로비)', 127.0470598, 37.51796816, '010-8901-2345', '헬스', 140, 0),
    ('스마트헬스 신사', '서울특별시 강남구 압구정로 172(압구정동, 지하1층4번출구)', 127.0281956, 37.52682814, '010-9012-3456', '헬스', 230, 0),
    ('챌린지헬스 세곡', '서울특별시 강남구 밤고개로 286, 세곡동주민센터 (율현동)', 127.1069125, 37.46904926, '010-0123-4567', '헬스', 160, 0);

-- 농구
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('슬램덩크 강남', '서울특별시 강남구 언주로 211(도곡동)', 127.0463104, 37.49280599, '010-1234-5678', '농구', 50, 0),
    ('핫숏 강남', '서울특별시 강남구 일원로 81(일원동, 별관입구 수납창구 뒤)', 127.0895902, 37.49034451, '010-2345-6789', '농구', 40, 0),
    ('프리썬 강남', '서울특별시 강남구 봉은사로 419(삼성동)', 127.0459769, 37.51120727, '010-3456-7890', '농구', 60, 0),
    ('플라이하이 역삼', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-4567-8901', '농구', 45, 0),
    ('다이빙 대치', '서울특별시 강남구 도곡로77길 23(대치동)', 127.0578495, 37.49972974, '010-5678-9012', '농구', 55, 0),
    ('슈팅스타 율현', '서울특별시 강남구 밤고개로 286, 세곡동주민센터 (율현동)', 127.1069125, 37.46904926, '010-6789-0123', '농구', 35, 0),
    ('알레이유 역삼2동', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-7890-1234', '농구', 65, 0),
    ('버킷리스트 개포', '서울특별시 강남구 개포로38길 12(개포동)', 127.0516585, 37.47881995, '010-8901-2345', '농구', 75, 0),
    ('스카이블루 청담', '서울특별시 강남구 학동로43길 17(논현동)', 127.0372042, 37.51728313, '010-9012-3456', '농구', 85, 0),
    ('플라잉워터 압구정', '서울특별시 강남구 압구정로33길 48(압구정동)', 127.0306522, 37.53061724, '010-0123-4567', '농구', 95, 0);

-- 필라테스
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('플라잉필라테스 강남점', '서울특별시 강남구 테헤란로 156(역삼동, 역삼역 지하1층 5번출구)', 127.0366182, 37.50071627, '010-2345-6789', '필라테스', 30, 0),
    ('스피닝스튜디오', '서울특별시 강남구 테헤란로 340(삼성동, 선릉역 지하 3번출구)', 127.0489425, 37.50450288, '010-3456-7890', '필라테스', 25, 0),
    ('Air Pilates Studio', '서울특별시 강남구 테헤란로 114(역삼동)', 127.0301032, 37.49828846, '010-4567-8901', '필라테스', 20, 0),
    ('래나필라테스', '서울특별시 강남구 학동로20길 25(논현동)', 127.0285345, 37.5115038, '010-5678-9012', '필라테스', 35, 0),
    ('스마일필라테스클럽', '서울특별시 강남구 언주로 211(도곡동)', 127.0463104, 37.49280599, '010-6789-0123', '필라테스', 40, 0);

-- 클라이밍
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('서울 클라이밍 센터', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-7890-1234', '클라이밍', 20, 0),
    ('강남 클라이밍 센터', '서울특별시 강남구 압구정로 172(압구정동)', 127.0281956, 37.52682814, '010-8901-2345', '클라이밍', 15, 0),
    ('클라이밍 스튜디오', '서울특별시 강남구 논현로 566, 강남차병원 (역삼동)', 127.0347144, 37.50703244, '010-9012-3456', '클라이밍', 25, 0),
    ('강남클라이밍센터', '서울특별시 강남구 선릉로 668, 강남구보건소,강남구립국제교육원 (삼성동)', 127.0423035, 37.51631743, '010-0123-4567', '클라이밍', 30, 0),
    ('강남클라이밍클럽', '서울특별시 강남구 영동대로65길 24, 대치2동주민센터 (대치동)', 127.0641877, 37.50230433, '010-1234-5678', '클라이밍', 18, 0);

-- 복싱
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('강남복싱클럽', '서울특별시 강남구 학동로 426(삼성동, 강남구청내 본관 1층 로비)', 127.0470598, 37.51796816, '010-2345-6789', '복싱', 50, 0),
    ('복싱 피트니스', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-3456-7890', '복싱', 40, 0),
    ('싸이클링클럽', '서울특별시 강남구 도곡로77길 23, 대치4동주민센터 (대치동)', 127.0578495, 37.49972974, '010-4567-8901', '복싱', 60, 0),
    ('남성복싱', '서울특별시 강남구 일원로 81(일원동, 별관입구 수납창구 뒤)', 127.0895902, 37.49034451, '010-5678-9012', '복싱', 45, 0),
    ('여성복싱', '서울특별시 강남구 봉은사로 419(삼성동)', 127.0459769, 37.51120727, '010-6789-0123', '복싱', 55, 0);

-- 배드민턴
INSERT INTO `GYM` (`name`, `address`, `longitude`, `latitude`, `phone`, `type`, `capacity`, `rating`)
VALUES
    ('강남배드민턴클럽', '서울특별시 강남구 역삼로7길 16(역삼동)', 127.0332838, 37.49537321, '010-7890-1234', '배드민턴', 30, 0),
    ('배드민턴 피트니스', '서울특별시 강남구 테헤란로 340(삼성동, 선릉역 지하 3번출구)', 127.0489425, 37.50450288, '010-8901-2345', '배드민턴', 25, 0);


-- prices
INSERT INTO `prices` (`gymId`, `name`, `price`, `text`, `days`)
VALUES 
    (1, "3개월권", 200000, "3개월권", 90),
    (1, "6개월권", 350000, "6개월권", 180),
    (2, "3개월권", 200000, "3개월권", 90),
    (2, "6개월권", 350000, "6개월권", 180),
    (3, "3개월권", 200000, "3개월권", 90),
    (3, "6개월권", 350000, "6개월권", 180),
    (4, "3개월권", 200000, "3개월권", 90),
    (4, "6개월권", 350000, "6개월권", 180),
    (5, "3개월권", 200000, "3개월권", 90),
    (5, "6개월권", 350000, "6개월권", 180),
    (6, "3개월권", 200000, "3개월권", 90),
    (6, "6개월권", 350000, "6개월권", 180),
    (7, "3개월권", 200000, "3개월권", 90),
    (7, "6개월권", 350000, "6개월권", 180),
    (8, "3개월권", 200000, "3개월권", 90),
    (8, "6개월권", 350000, "6개월권", 180),
    (9, "3개월권", 200000, "3개월권", 90),
    (9, "6개월권", 350000, "6개월권", 180),
    (10, "3개월권", 200000, "3개월권", 90),
    (10, "6개월권", 350000, "6개월권", 180),
    (11, "3개월권", 200000, "3개월권", 90),
    (11, "6개월권", 350000, "6개월권", 180),
    (12, "3개월권", 200000, "3개월권", 90),
    (12, "6개월권", 350000, "6개월권", 180),
    (13, "3개월권", 200000, "3개월권", 90),
    (13, "6개월권", 350000, "6개월권", 180),
    (14, "3개월권", 200000, "3개월권", 90),
    (14, "6개월권", 350000, "6개월권", 180),
    (15, "3개월권", 200000, "3개월권", 90),
    (15, "6개월권", 350000, "6개월권", 180),
    (16, "3개월권", 200000, "3개월권", 90),
    (16, "6개월권", 350000, "6개월권", 180),
    (17, "3개월권", 200000, "3개월권", 90),
    (17, "6개월권", 350000, "6개월권", 180),
    (18, "3개월권", 200000, "3개월권", 90),
    (18, "6개월권", 350000, "6개월권", 180),
    (19, "3개월권", 200000, "3개월권", 90),
    (19, "6개월권", 350000, "6개월권", 180),
    (20, "3개월권", 200000, "3개월권", 90),
    (20, "6개월권", 350000, "6개월권", 180),
    (21, "3개월권", 200000, "3개월권", 90),
    (21, "6개월권", 350000, "6개월권", 180),
    (22, "3개월권", 200000, "3개월권", 90),
    (22, "6개월권", 350000, "6개월권", 180),
    (23, "3개월권", 200000, "3개월권", 90),
    (23, "6개월권", 350000, "6개월권", 180),
    (24, "3개월권", 200000, "3개월권", 90),
    (24, "6개월권", 350000, "6개월권", 180),
    (25, "3개월권", 200000, "3개월권", 90),
    (25, "6개월권", 350000, "6개월권", 180),
    (26, "3개월권", 200000, "3개월권", 90),
    (26, "6개월권", 350000, "6개월권", 180),
    (27, "3개월권", 200000, "3개월권", 90),
    (27, "6개월권", 350000, "6개월권", 180),
    (28, "3개월권", 200000, "3개월권", 90),
    (28, "6개월권", 350000, "6개월권", 180),
    (29, "3개월권", 200000, "3개월권", 90),
    (29, "6개월권", 350000, "6개월권", 180),
    (30, "3개월권", 200000, "3개월권", 90),
    (30, "6개월권", 350000, "6개월권", 180),
    (31, "3개월권", 200000, "3개월권", 90),
    (31, "6개월권", 350000, "6개월권", 180),
    (32, "3개월권", 200000, "3개월권", 90),
    (32, "6개월권", 350000, "6개월권", 180),
    (33, "3개월권", 200000, "3개월권", 90),
    (33, "6개월권", 350000, "6개월권", 180),
    (34, "3개월권", 200000, "3개월권", 90),
    (34, "6개월권", 350000, "6개월권", 180),
    (35, "3개월권", 200000, "3개월권", 90),
    (35, "6개월권", 350000, "6개월권", 180),
    (36, "3개월권", 200000, "3개월권", 90),
    (36, "6개월권", 350000, "6개월권", 180),
    (37, "3개월권", 200000, "3개월권", 90),
    (37, "6개월권", 350000, "6개월권", 180),
    (38, "3개월권", 200000, "3개월권", 90),
    (38, "6개월권", 350000, "6개월권", 180),
    (39, "3개월권", 200000, "3개월권", 90),
    (39, "6개월권", 350000, "6개월권", 180),
    (40, "3개월권", 200000, "3개월권", 90),
    (40, "6개월권", 350000, "6개월권", 180),
    (41, "3개월권", 200000, "3개월권", 90),
    (41, "6개월권", 350000, "6개월권", 180),
    (42, "3개월권", 200000, "3개월권", 90),
    (42, "6개월권", 350000, "6개월권", 180),
    (43, "3개월권", 200000, "3개월권", 90),
    (43, "6개월권", 350000, "6개월권", 180),
    (44, "3개월권", 200000, "3개월권", 90),
    (44, "6개월권", 350000, "6개월권", 180),
    (45, "3개월권", 200000, "3개월권", 90),
    (45, "6개월권", 350000, "6개월권", 180),
    (46, "3개월권", 200000, "3개월권", 90),
    (46, "6개월권", 350000, "6개월권", 180),
    (47, "3개월권", 200000, "3개월권", 90),
    (47, "6개월권", 350000, "6개월권", 180);
    
    
-- amenity --
INSERT INTO `AMENITY` (`gymId`, `freeParking`, `paidParking`, `allDay`, `showerFacilities`, `Sportswear`, `sharedLocker`, `personalLocker`, `unmanded`)
SELECT 
    gymIds.gymId,
    ROUND(RAND()) AS freeParking,
    ROUND(RAND()) AS paidParking,
    ROUND(RAND()) AS allDay,
    ROUND(RAND()) AS showerFacilities,
    ROUND(RAND()) AS Sportswear,
    ROUND(RAND()) AS sharedLocker,
    ROUND(RAND()) AS personalLocker,
    ROUND(RAND()) AS unmanded
FROM
    (SELECT 1 AS gymId UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
    UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
    UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
    UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20
    UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL SELECT 25
    UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30
    UNION ALL SELECT 31 UNION ALL SELECT 32 UNION ALL SELECT 33 UNION ALL SELECT 34 UNION ALL SELECT 35
    UNION ALL SELECT 36 UNION ALL SELECT 37 UNION ALL SELECT 38 UNION ALL SELECT 39 UNION ALL SELECT 40
    UNION ALL SELECT 41 UNION ALL SELECT 42 UNION ALL SELECT 43 UNION ALL SELECT 44 UNION ALL SELECT 45
    UNION ALL SELECT 46 UNION ALL SELECT 47) AS gymIds;


-- gymMachine
INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (1, FLOOR(RAND() * 8) + 3, '수영모'),
    (1, FLOOR(RAND() * 3) + 4, '수경'),
    (2, FLOOR(RAND() * 8) + 3, '수영모'),
    (2, FLOOR(RAND() * 3) + 4, '수경'),
    (3, FLOOR(RAND() * 8) + 3, '수영모'),
    (3, FLOOR(RAND() * 3) + 4, '수경'),
    (4, FLOOR(RAND() * 8) + 3, '수영모'),
    (4, FLOOR(RAND() * 3) + 4, '수경'),
    (5, FLOOR(RAND() * 8) + 3, '수영모'),
    (5, FLOOR(RAND() * 3) + 4, '수경'),
    (6, FLOOR(RAND() * 8) + 3, '수영모'),
    (6, FLOOR(RAND() * 3) + 4, '수경'),
    (7, FLOOR(RAND() * 8) + 3, '수영모'),
    (7, FLOOR(RAND() * 3) + 4, '수경'),
    (8, FLOOR(RAND() * 8) + 3, '수영모'),
    (8, FLOOR(RAND() * 3) + 4, '수경'),
    (9, FLOOR(RAND() * 8) + 3, '수영모'),
    (9, FLOOR(RAND() * 3) + 4, '수경'),
    (10, FLOOR(RAND() * 8) + 3, '수영모'),
    (10, FLOOR(RAND() * 3) + 4, '수경');

INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (11, FLOOR(RAND() * 8) + 3, '덤벨'),
    (11, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (12, FLOOR(RAND() * 8) + 3, '덤벨'),
    (12, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (13, FLOOR(RAND() * 8) + 3, '덤벨'),
    (13, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (14, FLOOR(RAND() * 8) + 3, '덤벨'),
    (14, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (15, FLOOR(RAND() * 8) + 3, '덤벨'),
    (15, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (16, FLOOR(RAND() * 8) + 3, '덤벨'),
    (16, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (17, FLOOR(RAND() * 8) + 3, '덤벨'),
    (17, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (18, FLOOR(RAND() * 8) + 3, '덤벨'),
    (18, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (19, FLOOR(RAND() * 8) + 3, '덤벨'),
    (19, FLOOR(RAND() * 3) + 4, '스미스머신'),
    (20, FLOOR(RAND() * 8) + 3, '덤벨'),
    (20, FLOOR(RAND() * 3) + 4, '스미스머신');
    
INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (21, FLOOR(RAND() * 8) + 3, '농구공'),
    (21, FLOOR(RAND() * 3) + 4, '농구골대'),
    (22, FLOOR(RAND() * 8) + 3, '농구공'),
    (22, FLOOR(RAND() * 3) + 4, '농구골대'),
    (23, FLOOR(RAND() * 8) + 3, '농구공'),
    (23, FLOOR(RAND() * 3) + 4, '농구골대'),
    (24, FLOOR(RAND() * 8) + 3, '농구공'),
    (24, FLOOR(RAND() * 3) + 4, '농구골대'),
    (25, FLOOR(RAND() * 8) + 3, '농구공'),
    (25, FLOOR(RAND() * 3) + 4, '농구골대'),
    (26, FLOOR(RAND() * 8) + 3, '농구공'),
    (26, FLOOR(RAND() * 3) + 4, '농구골대'),
    (27, FLOOR(RAND() * 8) + 3, '농구공'),
    (27, FLOOR(RAND() * 3) + 4, '농구골대'),
    (28, FLOOR(RAND() * 8) + 3, '농구공'),
    (28, FLOOR(RAND() * 3) + 4, '농구골대'),
    (29, FLOOR(RAND() * 8) + 3, '농구공'),
    (29, FLOOR(RAND() * 3) + 4, '농구골대'),
    (30, FLOOR(RAND() * 8) + 3, '농구공'),
    (30, FLOOR(RAND() * 3) + 4, '농구골대');
    
INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (31, FLOOR(RAND() * 8) + 3, '필라테스 매트'),
    (31, FLOOR(RAND() * 3) + 4, '필라테스 볼'),
    (32, FLOOR(RAND() * 8) + 3, '필라테스 매트'),
    (32, FLOOR(RAND() * 3) + 4, '필라테스 볼'),
    (33, FLOOR(RAND() * 8) + 3, '필라테스 매트'),
    (33, FLOOR(RAND() * 3) + 4, '필라테스 볼'),
    (34, FLOOR(RAND() * 8) + 3, '필라테스 매트'),
    (34, FLOOR(RAND() * 3) + 4, '필라테스 볼'),
    (35, FLOOR(RAND() * 8) + 3, '필라테스 매트'),
    (35, FLOOR(RAND() * 3) + 4, '필라테스 볼');
    
INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (36, FLOOR(RAND() * 8) + 3, '클라이밍화'),
    (36, FLOOR(RAND() * 3) + 4, '클라이밍 로프'),
    (37, FLOOR(RAND() * 8) + 3, '클라이밍화'),
    (37, FLOOR(RAND() * 3) + 4, '클라이밍 로프'),
    (38, FLOOR(RAND() * 8) + 3, '클라이밍화'),
    (38, FLOOR(RAND() * 3) + 4, '클라이밍 로프'),
    (39, FLOOR(RAND() * 8) + 3, '클라이밍화'),
    (39, FLOOR(RAND() * 3) + 4, '클라이밍 로프'),
    (40, FLOOR(RAND() * 8) + 3, '클라이밍화'),
    (40, FLOOR(RAND() * 3) + 4, '클라이밍 로프');
    
INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (41, FLOOR(RAND() * 8) + 3, '복싱 글러브'),
    (41, FLOOR(RAND() * 3) + 4, '복싱 펀치백'),
    (42, FLOOR(RAND() * 8) + 3, '복싱 글러브'),
    (42, FLOOR(RAND() * 3) + 4, '복싱 펀치백'),
    (43, FLOOR(RAND() * 8) + 3, '복싱 글러브'),
    (43, FLOOR(RAND() * 3) + 4, '복싱 펀치백'),
    (44, FLOOR(RAND() * 8) + 3, '복싱 글러브'),
    (44, FLOOR(RAND() * 3) + 4, '복싱 펀치백'),
    (45, FLOOR(RAND() * 8) + 3, '복싱 글러브'),
    (45, FLOOR(RAND() * 3) + 4, '복싱 펀치백');

INSERT INTO GYM_MACHINE (gymId, machineCount, machineName)
VALUES 
    (43, FLOOR(RAND() * 8) + 3, '배드민턴 라켓'),
    (43, FLOOR(RAND() * 3) + 4, '배드민턴 넷'),
    (44, FLOOR(RAND() * 8) + 3, '배드민턴 라켓'),
    (44, FLOOR(RAND() * 3) + 4, '배드민턴 넷');


-- users --
-- 20개의 임의의 유저 데이터 삽입 쿼리
-- 20개의 임의의 유저 데이터 삽입 쿼리
INSERT INTO `USERS` (`email`, `password`, `phone`, `name`, `address`, `longitude`, `latitude`, `profileImgUrl`, `height`, `weight`, `auth`)
VALUES
('admin@ssafy.com', '$2a$10$QwTQdqJUeUugHWXEiXH2Ye54tV4KICMo/bejjpPLZyq3.70RbRi3a', '010-0000-0000', 'admin', '대한민국', 0.0, 0.0, NULL, NULL, NULL, 'admin'),
('trainer1@example.com', '$2a$10$EMfAFExQxF5UfeIBnCDlNuaC.k3ZcNADEGYvIhCwrztuuaU3Cb/ky', '010-1234-5678', '김민지', '서울 강남구 역삼동', 37.504062, 127.036209, NULL, 160, 50, 'trainer'),
('trainer2@example.com', '$2a$10$o3XEszF8ytlINbbNaOeaVuEBbf144KpGcPCV0p7eorGPVMs9lbK86', '010-2345-6789', '이민준', '서울 강남구 논현동', 37.516066, 127.035760, NULL, 170, 60, 'trainer'),
('user1@example.com', '$2a$10$B54QP0PVWMfZZ/tSHvA7qOYKTTrsWCwPN5MspUuT4hHlEJ5kQumqS', '010-3456-7890', '박서연', '서울 강남구 대치동', 37.501704, 127.058740, NULL, 175, 65, 'user'),
('user2@example.com', '$2a$10$gntqMiMzp1Iq0Rbm6VG5/.NPQKfYlO20Ke/TBB54dvI/trJmOkYou', '010-4567-8901', '최지훈', '서울 강남구 삼성동', 37.510571, 127.060315, NULL, 165, 55, 'user'),
('user3@example.com', '$2a$10$tpv2DkUpn6cRLvte4X2bU.C85vO0rmhn2EMhtaDEI0cJ.3g5sN5F2', '010-5678-9012', '정예진', '서울 강남구 역삼동', 37.498255, 127.039721, NULL, 180, 70, 'user'),
('user4@example.com', '$2a$10$nuIeTb7/47CqBMJo.xYMqONQFYhvwZo6PaWaQm5e754Vaec2BLulG', '010-6789-0123', '강지우', '서울 강남구 역삼동', 37.502194, 127.039634, NULL, 165, 55, 'user'),
('user5@example.com', '$2a$10$73.Uyzk8gjphoyV6H58bB.6rGWbg6bn103EiZsGDGzUiO6F0mBmfi', '010-7890-1234', '손민지', '서울 강남구 역삼동', 37.497974, 127.038981, NULL, 170, 60, 'user'),
('user6@example.com', '$2a$10$iv5iey/.xsxVVVPy80bVkOeHDIjqATCTsi4LumKJiakiMG7bvWaf.', '010-8901-2345', '윤서연', '서울 강남구 역삼동', 37.500649, 127.042879, NULL, 175, 65, 'user'),
('user7@example.com', '$2a$10$R99a6YoBl1bJAjqcGhQYHeTdAglImLyHnPEYZbZo8BeDcpt.8kNRK', '010-9012-3456', '임지훈', '서울 강남구 역삼동', 37.499939, 127.035914, NULL, 170, 60, 'user'),
('user8@example.com', '$2a$10$dvjnSa.Gad0XTKMQMTMtXuE026mkcabnWkUH3lQn5sI1vjJ7eTCnG', '010-0123-4567', '장예진', '서울 강남구 역삼동', 37.502901, 127.037615, NULL, 160, 50, 'user'),
('user9@example.com', '$2a$10$7h14eno0.ANs0eDFuKg56OtCkJ7nU1H8vNI8xCWn9ewuA3Gts12dW', '010-1111-1111', '김태연', '서울 강남구 역삼동', 37.503170, 127.036722, NULL, NULL, NULL, 'user'),
('user10@example.com', '$2a$10$/PAhmlybO/L.lwTL.5aV0OIYQAn6CDFXqjGPIVJ/kVUT7ySAF2bKK', '010-2222-2222', '이서연', '서울 강남구 역삼동', 37.504019, 127.035682, NULL, NULL, NULL, 'user'),
('user11@example.com', '$2a$10$Pdv4FS/YdrpKCkOxyoynAuik5ag.U6CSVxCJ.iLwYSnk3u47nP1Ie', '010-3333-3333', '박민지', '서울 강남구 역삼동', 37.502497, 127.036539, NULL, NULL, NULL, 'user'),
('user12@example.com', '$2a$10$EEr7q.K9hgS.KR.d7sbTgu91IyTBMnhUMFqYBcTn5r6JUrBMunOCO', '010-4444-4444', '최지훈', '서울 강남구 역삼동', 37.503349, 127.036525, NULL, NULL, NULL, 'user'),
('user13@example.com', '$2a$10$xzdkzfcrklKf4NSmorBTOexjckyrTUYH7n8CnUNMwP2fRuyBGfE6q', '010-5555-5555', '정예진', '서울 강남구 역삼동', 37.502864, 127.036124, NULL, NULL, NULL, 'user'),
('user14@example.com', '$2a$10$ycjGU/.TxCexTY1n92vUBefDDkYIWakDTJCN0Q4Z8zApOPn9fHZUu', '010-6666-6666', '강지우', '서울 강남구 역삼동', 37.503878, 127.037178, NULL, NULL, NULL, 'user'),
('user15@example.com', '$2a$10$DB3lMIHfDYUkMoORnhvjDew5Kdgh9f3cd6fkwnUVI0C2kDmSpjde.', '010-7777-7777', '손민지', '서울 강남구 역삼동', 37.502937, 127.036466, NULL, NULL, NULL, 'user'),
('user16@example.com', '$2a$10$SamIUA3XFynMZ5Lv7sXRROfXAjfKOV0xt/WlY2k6gobbS0pFcXywu', '010-8888-8888', '윤서연', '서울 강남구 역삼동', 37.503768, 127.036856, NULL, NULL, NULL, 'user'),
('user17@example.com', '$2a$10$7saoRe1SNTt/BCRQojF1C.UnP9QF9OXRlRVRfLen3TTl5YzTE1XeG', '010-9999-9999', '임지훈', '서울 강남구 역삼동', 37.503204, 127.036267, NULL, NULL, NULL, 'user'),
('user18@example.com', '$2a$10$VxRDhegQIaHOHhgijSEWY.9LGBI7IxJWJCtm1AjziJC9q1WujW1TS', '010-0000-0000', '장예진', '서울 강남구 역삼동', 37.504177, 127.037266, NULL, NULL, NULL, 'user');

INSERT INTO `TRAINER` (`userId`, `gymId`, `career`, `major`)
VALUES
(2, 1, "국가대표 수영 코치", "자유영"),
(3, 1, "XXX 대회 금상", "접영");


-- classes
INSERT INTO `CLASSES` (`gymId`, `trainerId`, `name`, `startDate`, `endDate`, `minimum`, `current`, `maximum`, `classPrice`)
VALUES 
(1, 1, '기초 수영 강좌', '2024-05-25', '2024-05-31', 5, 0, 10, 20000),
(1, 2, '수영의 정석', '2024-05-03', '2024-05-06', 5, 7, 10, 20000),
(1, 2, '수영의 정석(심화)', '2024-05-25', '2024-05-31', 5, 10, 10, 30000);