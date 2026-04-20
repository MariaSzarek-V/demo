-- Initialize users for Prediction Cup application
-- This script creates test users

-- Set UTF-8 encoding
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- USERS (10 users + 1 admin = 11 total)
-- ==============================================
-- Password for all users: 123qweasd
-- BCrypt hash: $2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.

-- Admin user
INSERT IGNORE INTO user (id, username, email, password, user_role)
VALUES (1, 'admin', 'admin@admin.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'ADMIN');

-- Regular users
INSERT IGNORE INTO user (id, username, email, password, user_role)
VALUES
(2, 'ola', 'ola@ola.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(3, 'ania', 'ania@ania.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(4, 'janek', 'janek@janek.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(5, 'kuba', 'kuba@kuba.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(6, 'michal', 'michal@michal.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(7, 'magda', 'magda@magda.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(8, 'marcin', 'marcin@marcin.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(9, 'lukasz', 'lukasz@lukasz.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(10, 'pawel', 'pawel@pawel.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(11, 'piotr', 'piotr@piotr.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

COMMIT;
