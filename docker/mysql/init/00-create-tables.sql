-- Create database schema for Prediction Cup application
-- Set UTF-8 encoding
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- Drop existing tables if they exist (in correct order to handle foreign keys)
DROP TABLE IF EXISTS `chat_message_reactions`;
DROP TABLE IF EXISTS `chat_message`;
DROP TABLE IF EXISTS `ranking_history`;
DROP TABLE IF EXISTS `ranking`;
DROP TABLE IF EXISTS `user_points`;
DROP TABLE IF EXISTS `game_prediction_result`;
DROP TABLE IF EXISTS `prediction`;
DROP TABLE IF EXISTS `game`;
DROP TABLE IF EXISTS `country`;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS `user`;

-- Create User table
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `user_role` VARCHAR(20) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (`id`),
    INDEX `idx_username` (`username`),
    INDEX `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Group table
CREATE TABLE `group` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY (`id`),
    INDEX `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Country table
CREATE TABLE `country` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL UNIQUE,
    `code` VARCHAR(10) NULL,
    `group_id` BIGINT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`group_id`) REFERENCES `group`(`id`) ON DELETE SET NULL,
    INDEX `idx_name` (`name`),
    INDEX `idx_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Game table
CREATE TABLE `game` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `home_country_id` BIGINT NOT NULL,
    `away_country_id` BIGINT NOT NULL,
    `home_score` INT NULL,
    `away_score` INT NULL,
    `game_date` DATETIME NOT NULL,
    `game_status` VARCHAR(20) NOT NULL DEFAULT 'ADMIN_VIEW',
    `group_id` BIGINT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`home_country_id`) REFERENCES `country`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`away_country_id`) REFERENCES `country`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`group_id`) REFERENCES `group`(`id`) ON DELETE SET NULL,
    INDEX `idx_game_date` (`game_date`),
    INDEX `idx_game_status` (`game_status`),
    INDEX `idx_group_id` (`group_id`),
    INDEX `idx_home_country_id` (`home_country_id`),
    INDEX `idx_away_country_id` (`away_country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Prediction table
CREATE TABLE `prediction` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `predicted_home_score` INT NOT NULL,
    `predicted_away_score` INT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `game_id` BIGINT NOT NULL,
    `calculated` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`game_id`) REFERENCES `game`(`id`) ON DELETE CASCADE,
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_game_id` (`game_id`),
    UNIQUE KEY `unique_user_game` (`user_id`, `game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Chat Message table
CREATE TABLE `chat_message` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `content` TEXT,
    `text` TEXT,
    `username` VARCHAR(50),
    `created_at` DATETIME,
    `user_id` BIGINT NULL,
    `game_id` BIGINT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE SET NULL,
    FOREIGN KEY (`game_id`) REFERENCES `game`(`id`) ON DELETE SET NULL,
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Chat Message Reactions table
CREATE TABLE `chat_message_reactions` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `chat_message_id` BIGINT NOT NULL,
    `emoji` VARCHAR(10) NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`chat_message_id`) REFERENCES `chat_message`(`id`) ON DELETE CASCADE,
    INDEX `idx_chat_message_id` (`chat_message_id`),
    UNIQUE KEY `unique_message_user_emoji` (`chat_message_id`, `username`, `emoji`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create User Points table
CREATE TABLE `user_points` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `prediction_id` BIGINT NULL,
    `points` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`prediction_id`) REFERENCES `prediction`(`id`) ON DELETE CASCADE,
    INDEX `idx_prediction_id` (`prediction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Ranking table
CREATE TABLE `ranking` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `total_points` INT NOT NULL DEFAULT 0,
    `position` INT NOT NULL,
    `position_change` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    UNIQUE KEY `unique_user_ranking` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Ranking History table
CREATE TABLE `ranking_history` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `game_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `total_points` INT NOT NULL,
    `position` INT NOT NULL,
    `position_change` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`game_id`) REFERENCES `game`(`id`) ON DELETE CASCADE,
    UNIQUE KEY `unique_game_user` (`game_id`, `user_id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Game Prediction Result table
CREATE TABLE `game_prediction_result` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `game_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `points_earned` INT NOT NULL DEFAULT 0,
    `prediction_id` BIGINT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`game_id`) REFERENCES `game`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`prediction_id`) REFERENCES `prediction`(`id`) ON DELETE SET NULL,
    INDEX `idx_game_id` (`game_id`),
    INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

COMMIT;
