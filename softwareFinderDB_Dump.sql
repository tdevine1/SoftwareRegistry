SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `softwareFinder` ;
CREATE SCHEMA IF NOT EXISTS `softwareFinder` DEFAULT CHARACTER SET big5 ;
USE `softwareFinder` ;

-- -----------------------------------------------------
-- Table `softwareFinder`.`Software`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `softwareFinder`.`Software` ;

CREATE TABLE IF NOT EXISTS `softwareFinder`.`Software` (
  `id_software` INT NOT NULL,
  `software_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id_software`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `softwareFinder`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `softwareFinder`.`Location` ;

CREATE TABLE IF NOT EXISTS `softwareFinder`.`Location` (
  `id_location` INT NOT NULL,
  `building` VARCHAR(45) NULL,
  `room` VARCHAR(45) NULL,
  PRIMARY KEY (`id_location`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `softwareFinder`.`Requests`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `softwareFinder`.`Requests` ;

CREATE TABLE IF NOT EXISTS `softwareFinder`.`Requests` (
  `software_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `request_count` INT NULL,
  INDEX `software_id_idx` (`software_id` ASC),
  INDEX `location_id_idx` (`location_id` ASC),
  PRIMARY KEY (`software_id`, `location_id`),
  CONSTRAINT `software_id`
    FOREIGN KEY (`software_id`)
    REFERENCES `softwareFinder`.`Software` (`id_software`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `softwareFinder`.`Location` (`id_location`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `softwareFinder`.`Located_in`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `softwareFinder`.`Located_in` ;

CREATE TABLE IF NOT EXISTS `softwareFinder`.`Located_in` (
  `soft_id` INT NOT NULL,
  `loc_id` INT NOT NULL,
  PRIMARY KEY (`soft_id`, `loc_id`),
  INDEX `loc_id_idx` (`loc_id` ASC),
  INDEX `soft_id_idx` (`soft_id` ASC),
  CONSTRAINT `soft_id`
    FOREIGN KEY (`soft_id`)
    REFERENCES `softwareFinder`.`Software` (`id_software`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `loc_id`
    FOREIGN KEY (`loc_id`)
    REFERENCES `softwareFinder`.`Location` (`id_location`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
