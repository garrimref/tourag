DROP DATABASE `tourag`;
-- -----------------------------------------------------

CREATE DATABASE  `tourag` DEFAULT CHARACTER SET UTF8MB4;
USE `tourag` ;

-- -----------------------------------------------------

CREATE TABLE  `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------

CREATE TABLE  `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL UNIQUE,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role_id` INT NOT NULL,
  `status` VARCHAR(10),
  PRIMARY KEY (`id`),
  CONSTRAINT `role_id_FK`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
);

-- -----------------------------------------------------

# CREATE TABLE  `category` (
#   `id` INT NOT NULL AUTO_INCREMENT,
#   `name` VARCHAR(255) NOT NULL,
#   PRIMARY KEY (`id`));


-- -----------------------------------------------------

# CREATE TABLE  `hotel_type` (
#   `id` INT NOT NULL AUTO_INCREMENT,
#   `name` VARCHAR(255) NOT NULL,
#   PRIMARY KEY (`id`)
# );


-- -----------------------------------------------------

# CREATE TABLE  `hotel` (
#   `id` INT NOT NULL AUTO_INCREMENT,
#   `name` VARCHAR(255) NOT NULL,
#   `country` VARCHAR(45) NOT NULL,
#   `city` VARCHAR(45) NOT NULL,
#   `street` VARCHAR(45) NOT NULL,
#   `phone` VARCHAR(20) NOT NULL,
#   `stars_amount` INT NOT NULL,
#   `hotel_type_id` INT NOT NULL,
#   PRIMARY KEY (`id`),
#   CONSTRAINT `hotel_type_id_FK`
#     FOREIGN KEY (`hotel_type_id`)
#     REFERENCES `hotel_type` (`id`));


-- -----------------------------------------------------

CREATE TABLE  `tour` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` LONGTEXT NULL,
  `hot` TINYINT NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `hotel_type` VARCHAR(25) NOT NULL,
  `price` INT NOT NULL,
  `people_amount` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `image_path` VARCHAR(255) NOT NULL,
  `date_created` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
#   CONSTRAINT `category_id_FK`
#     FOREIGN KEY (`category_id`)
#     REFERENCES `category` (`id`),
#   CONSTRAINT `hotel_id_FK`
#     FOREIGN KEY (`hotel_type_id`)
#     REFERENCES `hotel_type` (`id`)
);


-- -----------------------------------------------------

CREATE TABLE  `receipt_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

-- -----------------------------------------------------

CREATE TABLE  `receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date_created` TIMESTAMP DEFAULT now(),
  `receipt_status_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `user_id_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
  CONSTRAINT `receipt_status_id_FK`
    FOREIGN KEY (`receipt_status_id`)
    REFERENCES `receipt_status` (`id`)
);

-- -----------------------------------------------------

CREATE TABLE `receipt_has_tour` (
  `receipt_id` INT NOT NULL,
  `tour_id` INT NOT NULL,
  CONSTRAINT `receipt_id_FK`
    FOREIGN KEY (`receipt_id`)
    REFERENCES `receipt` (`id`),
  CONSTRAINT `tour_id_FK`
    FOREIGN KEY (`tour_id`)
    REFERENCES `tour` (`id`)
);