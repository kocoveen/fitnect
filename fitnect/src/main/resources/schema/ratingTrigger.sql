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