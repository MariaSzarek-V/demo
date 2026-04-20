-- =====================================================
-- Add League Support
-- Description: Adds league functionality where users can
--              belong to multiple leagues and have separate
--              predictions, rankings, chats, and posts per league
-- =====================================================

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- 1. Create league table
CREATE TABLE IF NOT EXISTS `league` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL UNIQUE,
    `description` TEXT,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_active` BOOLEAN DEFAULT TRUE,
    `created_by_user_id` BIGINT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`created_by_user_id`) REFERENCES `user`(`id`) ON DELETE SET NULL,
    INDEX `idx_league_active` (`is_active`),
    INDEX `idx_league_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Create user_league join table (many-to-many relationship)
CREATE TABLE IF NOT EXISTS `user_league` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `league_id` BIGINT NOT NULL,
    `joined_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `is_admin` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE,
    UNIQUE KEY `uk_user_league` (`user_id`, `league_id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_league_id` (`league_id`),
    INDEX `idx_is_admin` (`is_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Add league_id to prediction table
ALTER TABLE `prediction`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `game_id`,
    ADD CONSTRAINT `fk_prediction_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE;

-- Update unique constraint to include league_id
ALTER TABLE `prediction`
    DROP INDEX `unique_user_game`,
    ADD UNIQUE KEY `uk_user_game_league` (`user_id`, `game_id`, `league_id`);

-- 4. Add league_id to ranking table
ALTER TABLE `ranking`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `user_id`,
    ADD CONSTRAINT `fk_ranking_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE;

-- Update unique constraint to include league_id
ALTER TABLE `ranking`
    DROP INDEX `unique_user_ranking`,
    ADD UNIQUE KEY `uk_user_league_ranking` (`user_id`, `league_id`);

-- 5. Add league_id to ranking_history table
ALTER TABLE `ranking_history`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `game_id`,
    ADD CONSTRAINT `fk_ranking_history_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE;

-- Update unique constraint and add index
ALTER TABLE `ranking_history`
    DROP INDEX `unique_game_user`,
    ADD UNIQUE KEY `uk_game_user_league` (`game_id`, `user_id`, `league_id`),
    ADD INDEX `idx_league_game` (`league_id`, `game_id`);

-- 6. Add league_id to user_points table
ALTER TABLE `user_points`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `prediction_id`,
    ADD CONSTRAINT `fk_user_points_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE,
    ADD INDEX `idx_user_league_points` (`user_id`, `league_id`);

-- 7. Add league_id to chat_message table
ALTER TABLE `chat_message`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `user_id`,
    ADD CONSTRAINT `fk_chat_message_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE,
    ADD INDEX `idx_league_created` (`league_id`, `created_at`);

-- 8. Add league_id to post table
ALTER TABLE `post`
    ADD COLUMN `league_id` BIGINT NOT NULL DEFAULT 1 AFTER `user_id`,
    ADD CONSTRAINT `fk_post_league` FOREIGN KEY (`league_id`) REFERENCES `league`(`id`) ON DELETE CASCADE,
    ADD INDEX `idx_league_created` (`league_id`, `created_at`);

-- 9. Add all existing users to default league (Pierwsza Liga - id=1)
INSERT INTO `user_league` (`user_id`, `league_id`, `joined_at`, `is_admin`)
SELECT `id`, 1, NOW(), FALSE
FROM `user`
WHERE NOT EXISTS (
    SELECT 1 FROM `user_league` WHERE `user_league`.`user_id` = `user`.`id` AND `user_league`.`league_id` = 1
);

-- 10. Make admin user an admin in default league
UPDATE `user_league`
SET `is_admin` = TRUE
WHERE `user_id` = 1 AND `league_id` = 1;

COMMIT;
