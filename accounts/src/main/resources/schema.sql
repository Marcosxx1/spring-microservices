CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` INT AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `mobile_number` VARCHAR(255) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `updated_at` DATE DEFAULT NULL,
    `updated_by` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
    );

CREATE TABLE IF NOT EXISTS `account` (
    `account_number` INT AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `account_type` VARCHAR(255) NOT NULL,
    `branch_address` VARCHAR(255) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `updated_at` DATE DEFAULT NULL,
    `updated_by` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`account_number`),
    FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
    );
