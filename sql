CREATE TABLE IF NOT EXISTS `patients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `given_name` VARCHAR(255) NOT NULL,
  `family_name` VARCHAR(255) NOT NULL,
  `birth_date` DATE NOT NULL,
  `gender` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255),
  `phone_number` VARCHAR(255),
  PRIMARY KEY (`id`)
);