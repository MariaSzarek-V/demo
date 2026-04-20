-- Initialize users for Prediction Cup application
-- Total: 30 users (1 admin + 1 ania + 8 with first name only + 20 with first and last name)

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- USERS
-- ==============================================
-- Password for all users: 123qweasd
-- BCrypt hash: $2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.

-- 1. Admin user
INSERT IGNORE INTO `user` (id, username, email, password, user_role)
VALUES (1, 'admin', 'admin@admin.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'ADMIN');

-- 2. Ania
INSERT IGNORE INTO `user` (id, username, email, password, user_role)
VALUES (2, 'ania', 'ania@ania.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

-- 3-10. Users with first name only (8 users)
INSERT IGNORE INTO `user` (id, username, email, password, user_role)
VALUES
(3, 'ola', 'ola@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(4, 'janek', 'janek@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(5, 'kuba', 'kuba@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(6, 'michal', 'michal@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(7, 'magda', 'magda@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(8, 'marcin', 'marcin@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(9, 'lukasz', 'lukasz@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(10, 'pawel', 'pawel@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

-- 11-30. Users with first name and last name (20 users)
INSERT IGNORE INTO `user` (id, username, email, password, user_role)
VALUES
(11, 'Piotr Nowak', 'piotr.nowak@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(12, 'Katarzyna Kowalska', 'katarzyna.kowalska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(13, 'Tomasz Wiśniewski', 'tomasz.wisniewski@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(14, 'Anna Wójcik', 'anna.wojcik@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(15, 'Krzysztof Kamiński', 'krzysztof.kaminski@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(16, 'Monika Lewandowska', 'monika.lewandowska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(17, 'Jakub Zieliński', 'jakub.zielinski@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(18, 'Agnieszka Szymańska', 'agnieszka.szymanska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(19, 'Marek Woźniak', 'marek.wozniak@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(20, 'Joanna Dąbrowska', 'joanna.dabrowska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(21, 'Paweł Kozłowski', 'pawel.kozlowski@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(22, 'Małgorzata Jankowska', 'malgorzata.jankowska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(23, 'Rafał Mazur', 'rafal.mazur@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(24, 'Ewa Krawczyk', 'ewa.krawczyk@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(25, 'Grzegorz Piotrowski', 'grzegorz.piotrowski@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(26, 'Beata Grabowska', 'beata.grabowska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(27, 'Andrzej Pawlak', 'andrzej.pawlak@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(28, 'Iwona Michalska', 'iwona.michalska@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(29, 'Robert Zając', 'robert.zajac@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER'),
(30, 'Dorota Król', 'dorota.krol@test.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

COMMIT;
