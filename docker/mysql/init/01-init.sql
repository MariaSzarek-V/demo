-- Initialize database for Prediction Cup application
-- This script will be executed when MySQL container starts for the first time

-- Set UTF-8 encoding
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Create database if not exists
CREATE DATABASE IF NOT EXISTS betsdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
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

-- ==============================================
-- GAMES - 17 TOTAL (8 FINISHED + 9 SCHEDULED)
-- ==============================================

-- 8 FINISHED GAMES (with results)
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES 
(1, 'Brazylia', 'Argentyna', '2026-01-10 20:00:00', 'FINISHED', 2, 1),
(2, 'Niemcy', 'Francja', '2026-01-12 18:30:00', 'FINISHED', 1, 1),
(3, 'Hiszpania', 'Włochy', '2026-01-14 21:00:00', 'FINISHED', 3, 0),
(4, 'Anglia', 'Holandia', '2026-01-16 20:30:00', 'FINISHED', 2, 1),
(5, 'Belgia', 'Chorwacja', '2026-01-18 18:00:00', 'FINISHED', 2, 2),
(6, 'Portugalia', 'Polska', '2026-01-20 19:00:00', 'FINISHED', 3, 1),
(7, 'Szwecja', 'Norwegia', '2026-01-22 17:00:00', 'FINISHED', 1, 0),
(8, 'Dania', 'Szwajcaria', '2026-01-24 19:30:00', 'FINISHED', 4, 2);

-- 9 FUTURE GAMES (scheduled, no results yet)
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES 
(9, 'Polska', 'Portugalia', '2026-01-28 19:00:00', 'SCHEDULED', NULL, NULL),
(10, 'Włochy', 'Hiszpania', '2026-01-30 20:30:00', 'SCHEDULED', NULL, NULL),
(11, 'Francja', 'Niemcy', '2026-02-01 18:00:00', 'SCHEDULED', NULL, NULL),
(12, 'Argentyna', 'Brazylia', '2026-02-03 20:00:00', 'SCHEDULED', NULL, NULL),
(13, 'Holandia', 'Anglia', '2026-02-05 19:30:00', 'SCHEDULED', NULL, NULL),
(14, 'Turcja', 'Grecja', '2026-02-08 20:00:00', 'SCHEDULED', NULL, NULL),
(15, 'Czechy', 'Austria', '2026-02-10 18:30:00', 'SCHEDULED', NULL, NULL),
(16, 'Norwegia', 'Szwecja', '2026-02-12 17:00:00', 'SCHEDULED', NULL, NULL),
(17, 'Szwajcaria', 'Dania', '2026-02-14 19:00:00', 'SCHEDULED', NULL, NULL);

-- ==============================================
-- PREDICTIONS FOR ALL 10 USERS FOR 8 FINISHED GAMES
-- ==============================================

INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
-- Game 1: Brazil 2:1 Argentina (FINISHED)
(1, 2, 1, 2, 1, true), (2, 2, 0, 3, 1, true), (3, 1, 1, 4, 1, true), (4, 3, 1, 5, 1, true), (5, 1, 2, 6, 1, true),
(70, 2, 0, 7, 1, true), (71, 2, 1, 8, 1, true), (72, 1, 1, 9, 1, true), (73, 3, 0, 10, 1, true), (74, 1, 0, 11, 1, true),

-- Game 2: Germany 1:1 France (FINISHED - DRAW)
(6, 1, 1, 2, 2, true), (7, 2, 2, 3, 2, true), (8, 2, 1, 4, 2, true), (9, 0, 0, 5, 2, true), (10, 1, 2, 6, 2, true),
(75, 1, 1, 7, 2, true), (76, 0, 0, 8, 2, true), (77, 2, 1, 9, 2, true), (78, 2, 2, 10, 2, true), (79, 1, 2, 11, 2, true),

-- Game 3: Spain 3:0 Italy (FINISHED)
(11, 2, 0, 2, 3, true), (12, 3, 0, 3, 3, true), (13, 3, 1, 4, 3, true), (14, 1, 0, 5, 3, true), (15, 3, 0, 6, 3, true),
(80, 3, 0, 7, 3, true), (81, 2, 0, 8, 3, true), (82, 3, 1, 9, 3, true), (83, 2, 1, 10, 3, true), (84, 3, 0, 11, 3, true),

-- Game 4: England 2:1 Netherlands (FINISHED)
(30, 2, 1, 2, 4, true), (31, 2, 0, 3, 4, true), (32, 1, 2, 4, 4, true), (33, 3, 1, 5, 4, true), (34, 1, 1, 6, 4, true),
(85, 1, 0, 7, 4, true), (86, 2, 1, 8, 4, true), (87, 2, 0, 9, 4, true), (88, 1, 1, 10, 4, true), (89, 0, 2, 11, 4, true),

-- Game 5: Belgium 2:2 Croatia (FINISHED - DRAW)
(35, 1, 1, 2, 5, true), (36, 2, 2, 3, 5, true), (37, 2, 1, 4, 5, true), (38, 3, 3, 5, 5, true), (39, 2, 2, 6, 5, true),
(90, 2, 2, 7, 5, true), (91, 1, 1, 8, 5, true), (92, 2, 1, 9, 5, true), (93, 3, 3, 10, 5, true), (94, 2, 2, 11, 5, true),

-- Game 6: Portugal 3:1 Poland (FINISHED)
(40, 3, 1, 2, 6, true), (41, 2, 1, 3, 6, true), (42, 1, 1, 4, 6, true), (43, 2, 0, 5, 6, true), (44, 3, 0, 6, 6, true),
(95, 2, 0, 7, 6, true), (96, 3, 1, 8, 6, true), (97, 2, 1, 9, 6, true), (98, 1, 1, 10, 6, true), (99, 3, 0, 11, 6, true),

-- Game 7: Sweden 1:0 Norway (FINISHED)
(45, 1, 0, 2, 7, true), (46, 2, 0, 3, 7, true), (47, 1, 1, 4, 7, true), (48, 1, 0, 5, 7, true), (49, 2, 1, 6, 7, true),
(100, 1, 0, 7, 7, true), (101, 2, 0, 8, 7, true), (102, 1, 0, 9, 7, true), (103, 0, 0, 10, 7, true), (104, 2, 1, 11, 7, true),

-- Game 8: Denmark 4:2 Switzerland (FINISHED)
(50, 3, 1, 2, 8, true), (51, 2, 1, 3, 8, true), (52, 4, 2, 4, 8, true), (53, 2, 2, 5, 8, true), (54, 3, 2, 6, 8, true),
(105, 3, 1, 7, 8, true), (106, 4, 2, 8, 8, true), (107, 3, 2, 9, 8, true), (108, 2, 2, 10, 8, true), (109, 4, 1, 11, 8, true);

-- ==============================================
-- PREDICTIONS FOR FUTURE GAMES
-- ==============================================

INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
-- Game 9 (some users)
(55, 2, 1, 2, 9, false), (56, 1, 2, 3, 9, false), (57, 1, 0, 4, 9, false),
-- Game 10 (some users)
(58, 1, 1, 2, 10, false), (59, 2, 1, 5, 10, false), (60, 0, 2, 6, 10, false),
-- Game 11 (some users)
(61, 2, 0, 3, 11, false), (62, 1, 1, 4, 11, false), (63, 3, 1, 5, 11, false),
-- Game 12 (some users)
(64, 1, 2, 2, 12, false), (65, 2, 2, 3, 12, false), (66, 1, 1, 6, 12, false),
-- Game 13 (some users)
(67, 2, 1, 4, 13, false), (68, 1, 2, 5, 13, false), (69, 2, 0, 6, 13, false);

-- ==============================================
-- USER POINTS (all 10 users for 8 finished games)
-- ==============================================

INSERT IGNORE INTO user_points (id, user_id, prediction_id, points) 
VALUES 
-- Old users (2-6)
(1,2,1,3),(2,3,2,1),(3,4,3,0),(4,5,4,1),(5,6,5,0),
(6,2,6,3),(7,3,7,1),(8,4,8,0),(9,5,9,1),(10,6,10,0),
(11,2,11,1),(12,3,12,3),(13,4,13,1),(14,5,14,1),(15,6,15,3),
(16,2,30,3),(17,3,31,1),(18,4,32,0),(19,5,33,1),(20,6,34,0),
(21,2,35,1),(22,3,36,3),(23,4,37,0),(24,5,38,1),(25,6,39,3),
(26,2,40,3),(27,3,41,1),(28,4,42,0),(29,5,43,1),(30,6,44,1),
(31,2,45,3),(32,3,46,1),(33,4,47,0),(34,5,48,3),(35,6,49,1),
(36,2,50,1),(37,3,51,1),(38,4,52,3),(39,5,53,0),(40,6,54,1),
-- New users (7-11)
(41,7,70,1),(42,7,75,3),(43,7,80,3),(44,7,85,1),(45,7,90,3),(46,7,95,1),(47,7,100,3),(48,7,105,1),
(49,8,71,3),(50,8,76,1),(51,8,81,1),(52,8,86,3),(53,8,91,1),(54,8,96,3),(55,8,101,1),(56,8,106,3),
(57,9,72,0),(58,9,77,0),(59,9,82,1),(60,9,87,1),(61,9,92,0),(62,9,97,1),(63,9,102,3),(64,9,107,1),
(65,10,73,1),(66,10,78,1),(67,10,83,1),(68,10,88,0),(69,10,93,1),(70,10,98,0),(71,10,103,0),(72,10,108,0),
(73,11,74,1),(74,11,79,0),(75,11,84,3),(76,11,89,0),(77,11,94,3),(78,11,99,1),(79,11,104,1),(80,11,109,1);

-- ==============================================
-- GAME PREDICTION RESULTS (all 10 users for 8 finished games)
-- ==============================================

INSERT IGNORE INTO game_prediction_result (id, game_id, user_id, points_earned, prediction_id) 
VALUES 
-- Old users (2-6)
(1,1,2,3,1),(2,1,3,1,2),(3,1,4,0,3),(4,1,5,1,4),(5,1,6,0,5),
(6,2,2,3,6),(7,2,3,1,7),(8,2,4,0,8),(9,2,5,1,9),(10,2,6,0,10),
(11,3,2,1,11),(12,3,3,3,12),(13,3,4,1,13),(14,3,5,1,14),(15,3,6,3,15),
(16,4,2,3,30),(17,4,3,1,31),(18,4,4,0,32),(19,4,5,1,33),(20,4,6,0,34),
(21,5,2,1,35),(22,5,3,3,36),(23,5,4,0,37),(24,5,5,1,38),(25,5,6,3,39),
(26,6,2,3,40),(27,6,3,1,41),(28,6,4,0,42),(29,6,5,1,43),(30,6,6,1,44),
(31,7,2,3,45),(32,7,3,1,46),(33,7,4,0,47),(34,7,5,3,48),(35,7,6,1,49),
(36,8,2,1,50),(37,8,3,1,51),(38,8,4,3,52),(39,8,5,0,53),(40,8,6,1,54),
-- New users (7-11)
(41,1,7,1,70),(42,2,7,3,75),(43,3,7,3,80),(44,4,7,1,85),(45,5,7,3,90),(46,6,7,1,95),(47,7,7,3,100),(48,8,7,1,105),
(49,1,8,3,71),(50,2,8,1,76),(51,3,8,1,81),(52,4,8,3,86),(53,5,8,1,91),(54,6,8,3,96),(55,7,8,1,101),(56,8,8,3,106),
(57,1,9,0,72),(58,2,9,0,77),(59,3,9,1,82),(60,4,9,1,87),(61,5,9,0,92),(62,6,9,1,97),(63,7,9,3,102),(64,8,9,1,107),
(65,1,10,1,73),(66,2,10,1,78),(67,3,10,1,83),(68,4,10,0,88),(69,5,10,1,93),(70,6,10,0,98),(71,7,10,0,103),(72,8,10,0,108),
(73,1,11,1,74),(74,2,11,0,79),(75,3,11,3,84),(76,4,11,0,89),(77,5,11,3,94),(78,6,11,1,99),(79,7,11,1,104),(80,8,11,1,109);

-- ==============================================
-- RANKING (after 8 games - 10 users)
-- ==============================================
-- Ola:18, Marcin:16, Magda:16, Ania:12, Michał:11, Kuba:9, Łukasz:7, Piotr:10, Paweł:5, Janek:4

INSERT IGNORE INTO ranking (id, user_id, total_points, position, position_change) 
VALUES 
(1, 2, 18, 1, 0),   -- Ola - 1st (18 pts)
(2, 8, 16, 2, 0),   -- Marcin - 2nd (16 pts) 
(3, 7, 16, 2, 0),   -- Magda - 2nd tie (16 pts)
(4, 3, 12, 4, 0),   -- Ania - 4th (12 pts)
(5, 6, 11, 5, 0),   -- Michał - 5th (11 pts)
(6, 11, 10, 6, 0),  -- Piotr - 6th (10 pts)
(7, 5, 9, 7, 0),    -- Kuba - 7th (9 pts)
(8, 9, 7, 8, 0),    -- Łukasz - 8th (7 pts)
(9, 10, 5, 9, 0),   -- Paweł - 9th (5 pts)
(10, 4, 4, 10, 0);  -- Janek - 10th (4 pts)

-- ==============================================
-- RANKING HISTORY (after each game for all 10 users)
-- ==============================================

INSERT IGNORE INTO ranking_history (id, game_id, user_id, total_points, position, position_change) 
VALUES 
-- After Game 1
(1,1,2,3,1,0),(2,1,8,3,1,0),(3,1,3,1,3,0),(4,1,5,1,3,0),(5,1,7,1,3,0),(6,1,10,1,3,0),(7,1,11,1,3,0),(8,1,4,0,8,0),(9,1,6,0,8,0),(10,1,9,0,8,0),
-- After Game 2
(11,2,2,6,1,0),(12,2,8,4,2,1),(13,2,7,4,2,1),(14,2,3,2,4,-1),(15,2,5,2,4,-1),(16,2,10,2,4,-1),(17,2,11,1,7,-4),(18,2,4,0,8,0),(19,2,6,0,8,0),(20,2,9,0,8,0),
-- After Game 3
(21,3,2,7,1,0),(22,3,7,7,1,0),(23,3,8,5,3,-1),(24,3,3,5,3,1),(25,3,11,4,5,2),(26,3,6,3,6,2),(27,3,5,3,6,2),(28,3,10,3,6,2),(29,3,9,1,9,-2),(30,3,4,1,9,-1),
-- After Game 4
(31,4,2,10,1,0),(32,4,8,8,2,1),(33,4,7,8,2,1),(34,4,3,6,4,-1),(35,4,11,4,5,0),(36,4,5,4,5,1),(37,4,6,3,7,-1),(38,4,10,3,7,-1),(39,4,9,1,9,0),(40,4,4,1,9,0),
-- After Game 5
(41,5,2,11,1,0),(42,5,7,11,1,0),(43,5,8,9,3,-1),(44,5,3,9,3,1),(45,5,11,7,5,0),(46,5,6,6,6,1),(47,5,5,5,7,-2),(48,5,10,4,8,-1),(49,5,9,1,9,0),(50,5,4,1,9,0),
-- After Game 6
(51,6,2,14,1,0),(52,6,8,12,2,1),(53,6,7,12,2,1),(54,6,3,10,4,-1),(55,6,11,8,5,0),(56,6,6,7,6,0),(57,6,9,7,6,0),(58,6,5,6,8,-1),(59,6,10,4,9,-1),(60,6,4,1,10,-1),
-- After Game 7
(61,7,2,17,1,0),(62,7,7,15,2,0),(63,7,8,13,3,-1),(64,7,3,11,4,0),(65,7,9,10,5,1),(66,7,11,9,6,-1),(67,7,5,9,6,2),(68,7,6,8,8,-2),(69,7,10,4,9,0),(70,7,4,1,10,0),
-- After Game 8 - CURRENT
(71,8,2,18,1,0),(72,8,8,16,2,1),(73,8,7,16,2,1),(74,8,3,12,4,0),(75,8,6,11,5,3),(76,8,11,10,6,0),(77,8,5,9,7,-1),(78,8,9,7,8,-3),(79,8,10,4,9,0),(80,8,4,4,10,0);

-- ==============================================
-- COMMENTS
-- ==============================================

INSERT IGNORE INTO comment (id, text, username, created_at, user_id, game_id) 
VALUES 
(1, 'Świetny mecz! Brazylia zasłużenie wygrała!', 'ola', '2026-01-10 22:30:00', 2, 1),
(2, 'Remis był sprawiedliwy, obie drużyny grały dobrze.', 'ania', '2026-01-12 20:45:00', 3, 2),
(3, 'Hiszpania pokazała klasę! Dominacja na boisku.', 'janek', '2026-01-14 23:15:00', 4, 3),
(4, 'Czekam na mecz Polski z Portugalią!', 'kuba', '2026-01-16 14:20:00', 5, 4),
(5, 'Anglia wygrała zasłużenie!', 'michal', '2026-01-17 18:00:00', 6, 5),
(6, 'Witamy wszystkich w typowaniu!', 'admin', '2026-01-09 12:00:00', 1, 1);

-- Flush privileges
FLUSH PRIVILEGES;

-- Set auto increment values to avoid conflicts
ALTER TABLE user AUTO_INCREMENT = 100;
ALTER TABLE game AUTO_INCREMENT = 200;
ALTER TABLE prediction AUTO_INCREMENT = 200;
ALTER TABLE comment AUTO_INCREMENT = 100;
ALTER TABLE user_points AUTO_INCREMENT = 200;
ALTER TABLE ranking AUTO_INCREMENT = 100;
ALTER TABLE game_prediction_result AUTO_INCREMENT = 200;
ALTER TABLE ranking_history AUTO_INCREMENT = 200;

COMMIT;
