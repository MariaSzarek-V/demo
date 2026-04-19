-- Initialize users for Prediction Cup application
-- This script creates test users
-- Extended dataset: 31 users (11 original + 20 new with Polish names)

-- Set UTF-8 encoding
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- USERS (30 users + 1 admin = 31 total)
-- ==============================================
-- Password for all users: 123qweasd
-- BCrypt hash: $2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.

-- Admin user
INSERT IGNORE INTO user (id, username, email, password, user_role)
VALUES (1, 'admin', 'admin@admin.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'ADMIN');

-- Regular users (original 10)
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

-- New users with Polish first and last names (20 users)
INSERT IGNORE INTO user (id, username, email, password, user_role)
VALUES
(12, 'Ola Kot', 'ola.kot@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(13, 'Jan Lis', 'jan.lis@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(14, 'Ewa Bąk', 'ewa.bak@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(15, 'Ada Dąb', 'ada.dab@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(16, 'Leo Łuk', 'leo.luk@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(17, 'Ida Łoś', 'ida.los@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(18, 'Mia Pęk', 'mia.pek@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(19, 'Zosia Róg', 'zosia.rog@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(20, 'Maja Szyk', 'maja.szyk@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(21, 'Nina Żuk', 'nina.zuk@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(22, 'Magdalena Kowalska', 'magdalena.kowalska@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(23, 'Aleksandra Szymańska', 'aleksandra.szymanska@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(24, 'Katarzyna Dąbrowska', 'katarzyna.dabrowska@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(25, 'Sebastian Kozłowski', 'sebastian.kozlowski@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(26, 'Małgorzata Jankowska', 'malgorzata.jankowska@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(27, 'Krzysztof Wojciechowski', 'krzysztof.wojciechowski@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(28, 'Agnieszka Kwiatkowska', 'agnieszka.kwiatkowska@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(29, 'Bartłomiej Kaczmarek', 'bartlomiej.kaczmarek@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(30, 'Weronika Pawlak', 'weronika.pawlak@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(31, 'Przemysław Michalski', 'przemyslaw.michalski@example.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

COMMIT;
