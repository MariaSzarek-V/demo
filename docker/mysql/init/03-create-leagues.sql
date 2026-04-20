-- Create two leagues: Pierwsza Liga and Liga VIP
-- Pierwsza Liga: Default league (id = 1)
-- Liga VIP: Premium league (id = 2)

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- Insert Pierwsza Liga (id = 1) - default league
INSERT INTO `league` (`id`, `name`, `description`, `created_at`, `is_active`)
VALUES (1, 'Pierwsza Liga', 'Domyślna liga dla wszystkich użytkowników', NOW(), TRUE)
ON DUPLICATE KEY UPDATE `name` = 'Pierwsza Liga', `description` = 'Domyślna liga dla wszystkich użytkowników';

-- Insert Liga VIP (id = 2)
INSERT INTO `league` (`id`, `name`, `description`, `created_at`, `is_active`)
VALUES (2, 'Liga VIP', 'Liga VIP dla najlepszych graczy', NOW(), TRUE)
ON DUPLICATE KEY UPDATE `name` = 'Liga VIP', `description` = 'Liga VIP dla najlepszych graczy';

COMMIT;
