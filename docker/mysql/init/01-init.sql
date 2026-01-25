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
-- USERS
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
(6, 'michal', 'michal@michal.com', '$2a$10$FlGxFUMpRH/spOdHfCWtHOn3rOVkC8H.aZ4LaU6wvdrLsIZLKOVI.', 'USER');

-- ==============================================
-- GAMES - 10 TOTAL
-- ==============================================

-- 3 FINISHED GAMES (with results)
-- Game 1: Brazil vs Argentina - Brazil won 2:1
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES (1, 'Brazylia', 'Argentyna', '2026-01-10 20:00:00', 'FINISHED', 2, 1);

-- Game 2: Germany vs France - Draw 1:1
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES (2, 'Niemcy', 'Francja', '2026-01-12 18:30:00', 'FINISHED', 1, 1);

-- Game 3: Spain vs Italy - Spain won 3:0
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES (3, 'Hiszpania', 'Włochy', '2026-01-14 21:00:00', 'FINISHED', 3, 0);

-- 7 FUTURE GAMES (scheduled, no results yet)
INSERT IGNORE INTO game (id, home_team, away_team, game_date, game_status, home_score, away_score) 
VALUES 
(4, 'Polska', 'Portugalia', '2026-01-23 19:00:00', 'SCHEDULED', NULL, NULL),
(5, 'Anglia', 'Holandia', '2026-01-25 20:30:00', 'SCHEDULED', NULL, NULL),
(6, 'Belgia', 'Chorwacja', '2026-01-27 18:00:00', 'SCHEDULED', NULL, NULL),
(7, 'Szwecja', 'Norwegia', '2026-01-29 17:00:00', 'SCHEDULED', NULL, NULL),
(8, 'Dania', 'Szwajcaria', '2026-01-31 19:30:00', 'SCHEDULED', NULL, NULL),
(9, 'Turcja', 'Grecja', '2026-02-03 20:00:00', 'SCHEDULED', NULL, NULL),
(10, 'Czechy', 'Austria', '2026-02-06 18:30:00', 'SCHEDULED', NULL, NULL);

-- ==============================================
-- PREDICTIONS FOR FINISHED GAMES (all 5 users)
-- ==============================================

-- Game 1: Brazil 2:1 Argentina (FINISHED)
-- Predictions with points calculation:
-- Exact score (2:1) = 3 points
-- Correct winner (Brazil) = 1 point
-- Wrong = 0 points

INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
-- Ola: predicted 2:1 (EXACT) - 3 points
(1, 2, 1, 2, 1, true),
-- Ania: predicted 2:0 (correct winner) - 1 point
(2, 2, 0, 3, 1, true),
-- Janek: predicted 1:1 (wrong) - 0 points
(3, 1, 1, 4, 1, true),
-- Kuba: predicted 3:1 (correct winner) - 1 point
(4, 3, 1, 5, 1, true),
-- Michał: predicted 1:2 (wrong winner) - 0 points
(5, 1, 2, 6, 1, true);

-- Game 2: Germany 1:1 France (FINISHED - DRAW)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
-- Ola: predicted 1:1 (EXACT) - 3 points
(6, 1, 1, 2, 2, true),
-- Ania: predicted 2:2 (correct draw) - 1 point
(7, 2, 2, 3, 2, true),
-- Janek: predicted 2:1 (wrong) - 0 points
(8, 2, 1, 4, 2, true),
-- Kuba: predicted 0:0 (correct draw) - 1 point
(9, 0, 0, 5, 2, true),
-- Michał: predicted 1:2 (wrong) - 0 points
(10, 1, 2, 6, 2, true);

-- Game 3: Spain 3:0 Italy (FINISHED)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
-- Ola: predicted 2:0 (correct winner) - 1 point
(11, 2, 0, 2, 3, true),
-- Ania: predicted 3:0 (EXACT) - 3 points
(12, 3, 0, 3, 3, true),
-- Janek: predicted 3:1 (correct winner) - 1 point
(13, 3, 1, 4, 3, true),
-- Kuba: predicted 1:0 (correct winner) - 1 point
(14, 1, 0, 5, 3, true),
-- Michał: predicted 3:0 (EXACT) - 3 points
(15, 3, 0, 6, 3, true);

-- ==============================================
-- PREDICTIONS FOR FUTURE GAMES (4 out of 7 games)
-- No points yet (calculated = false)
-- ==============================================

-- Game 4: Poland vs Portugal (FUTURE)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
(16, 2, 1, 2, 4, false),  -- Ola
(17, 1, 2, 3, 4, false),  -- Ania
(18, 1, 1, 4, 4, false),  -- Janek
(19, 2, 0, 5, 4, false);  -- Kuba

-- Game 5: England vs Netherlands (FUTURE)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
(20, 2, 2, 2, 5, false),  -- Ola
(21, 3, 1, 4, 5, false),  -- Janek
(22, 1, 0, 6, 5, false);  -- Michał

-- Game 6: Belgium vs Croatia (FUTURE)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
(23, 2, 1, 3, 6, false),  -- Ania
(24, 1, 1, 5, 6, false),  -- Kuba
(25, 2, 0, 6, 6, false);  -- Michał

-- Game 7: Sweden vs Norway (FUTURE)
INSERT IGNORE INTO prediction (id, predicted_home_score, predicted_away_score, user_id, game_id, calculated) 
VALUES 
(26, 1, 0, 2, 7, false),  -- Ola
(27, 2, 1, 3, 7, false),  -- Ania
(28, 1, 1, 4, 7, false),  -- Janek
(29, 3, 0, 5, 7, false);  -- Kuba

-- ==============================================
-- USER POINTS (based on finished games)
-- Each record represents points earned for a specific prediction
-- ==============================================

INSERT IGNORE INTO user_points (id, user_id, prediction_id, points) 
VALUES 
-- Game 1 points
(1, 2, 1, 3),   -- Ola: prediction 1 (Game 1) - 3 points
(2, 3, 2, 1),   -- Ania: prediction 2 (Game 1) - 1 point
(3, 4, 3, 0),   -- Janek: prediction 3 (Game 1) - 0 points
(4, 5, 4, 1),   -- Kuba: prediction 4 (Game 1) - 1 point
(5, 6, 5, 0),   -- Michał: prediction 5 (Game 1) - 0 points

-- Game 2 points
(6, 2, 6, 3),   -- Ola: prediction 6 (Game 2) - 3 points
(7, 3, 7, 1),   -- Ania: prediction 7 (Game 2) - 1 point
(8, 4, 8, 0),   -- Janek: prediction 8 (Game 2) - 0 points
(9, 5, 9, 1),   -- Kuba: prediction 9 (Game 2) - 1 point
(10, 6, 10, 0), -- Michał: prediction 10 (Game 2) - 0 points

-- Game 3 points
(11, 2, 11, 1), -- Ola: prediction 11 (Game 3) - 1 point
(12, 3, 12, 3), -- Ania: prediction 12 (Game 3) - 3 points
(13, 4, 13, 1), -- Janek: prediction 13 (Game 3) - 1 point
(14, 5, 14, 1), -- Kuba: prediction 14 (Game 3) - 1 point
(15, 6, 15, 3); -- Michał: prediction 15 (Game 3) - 3 points

-- ==============================================
-- RANKING
-- ==============================================

INSERT IGNORE INTO ranking (id, user_id, total_points, position, position_change) 
VALUES 
(1, 2, 7, 1, 0),   -- Ola - 1st place
(2, 3, 5, 2, 0),   -- Ania - 2nd place
(3, 5, 3, 3, 0),   -- Kuba - 3rd place (tie with Michał)
(4, 6, 3, 3, 0),   -- Michał - 3rd place (tie with Kuba)
(5, 4, 1, 5, 0);   -- Janek - 5th place

-- ==============================================
-- GAME PREDICTION RESULTS (for finished games)
-- ==============================================

INSERT IGNORE INTO game_prediction_result (id, game_id, user_id, points_earned, prediction_id) 
VALUES 
-- Game 1 results
(1, 1, 2, 3, 1),   -- Ola: 3 points
(2, 1, 3, 1, 2),   -- Ania: 1 point
(3, 1, 4, 0, 3),   -- Janek: 0 points
(4, 1, 5, 1, 4),   -- Kuba: 1 point
(5, 1, 6, 0, 5),   -- Michał: 0 points

-- Game 2 results
(6, 2, 2, 3, 6),   -- Ola: 3 points
(7, 2, 3, 1, 7),   -- Ania: 1 point
(8, 2, 4, 0, 8),   -- Janek: 0 points
(9, 2, 5, 1, 9),   -- Kuba: 1 point
(10, 2, 6, 0, 10), -- Michał: 0 points

-- Game 3 results
(11, 3, 2, 1, 11), -- Ola: 1 point
(12, 3, 3, 3, 12), -- Ania: 3 points
(13, 3, 4, 1, 13), -- Janek: 1 point
(14, 3, 5, 1, 14), -- Kuba: 1 point
(15, 3, 6, 3, 15); -- Michał: 3 points

-- ==============================================
-- RANKING HISTORY (after each finished game)
-- ==============================================

-- After Game 1 (Brazil 2:1 Argentina)
INSERT IGNORE INTO ranking_history (id, game_id, user_id, total_points, position, position_change) 
VALUES 
(1, 1, 2, 3, 1, 0),   -- Ola: 3 points, 1st place
(2, 1, 5, 1, 2, 0),   -- Kuba: 1 point, 2nd place
(3, 1, 3, 1, 2, 0),   -- Ania: 1 point, 2nd place (tie)
(4, 1, 4, 0, 4, 0),   -- Janek: 0 points, 4th place
(5, 1, 6, 0, 4, 0);   -- Michał: 0 points, 4th place (tie)

-- After Game 2 (Germany 1:1 France)
INSERT IGNORE INTO ranking_history (id, game_id, user_id, total_points, position, position_change) 
VALUES 
(6, 2, 2, 6, 1, 0),   -- Ola: 6 points, 1st place, no change
(7, 2, 3, 2, 2, 0),   -- Ania: 2 points, 2nd place, no change
(8, 2, 5, 2, 2, 0),   -- Kuba: 2 points, 2nd place (tie), no change
(9, 2, 4, 0, 4, 0),   -- Janek: 0 points, 4th place, no change
(10, 2, 6, 0, 4, 0);  -- Michał: 0 points, 4th place (tie), no change

-- After Game 3 (Spain 3:0 Italy) - FINAL STANDINGS
INSERT IGNORE INTO ranking_history (id, game_id, user_id, total_points, position, position_change) 
VALUES 
(11, 3, 2, 7, 1, 0),   -- Ola: 7 points, 1st place, no change
(12, 3, 3, 5, 2, 0),   -- Ania: 5 points, 2nd place, no change
(13, 3, 5, 3, 3, -1),  -- Kuba: 3 points, 3rd place, down 1
(14, 3, 6, 3, 3, 1),   -- Michał: 3 points, 3rd place (tie), up 1
(15, 3, 4, 1, 5, -1);  -- Janek: 1 point, 5th place, down 1

-- ==============================================
-- COMMENTS (1 per user including admin)
-- ==============================================

INSERT IGNORE INTO comment (id, text, username, created_at, user_id, game_id) 
VALUES 
(1, 'Świetny mecz! Brazylia zasłużenie wygrała!', 'ola', '2026-01-10 22:30:00', 2, 1),
(2, 'Remis był sprawiedliwy, obie drużyny grały dobrze.', 'ania', '2026-01-12 20:45:00', 3, 2),
(3, 'Hiszpania pokazała klasę! Dominacja na boisku.', 'janek', '2026-01-14 23:15:00', 4, 3),
(4, 'Czekam na mecz Polski z Portugalią, będzie ciekawie!', 'kuba', '2026-01-16 14:20:00', 5, 4),
(5, 'Anglia vs Holandia to będzie hit! Nie mogę się doczekać.', 'michal', '2026-01-17 18:00:00', 6, 5),
(6, 'Witamy wszystkich w typowaniu! Powodzenia!', 'admin', '2026-01-09 12:00:00', 1, 1);

-- Flush privileges
FLUSH PRIVILEGES;

-- Set auto increment values to avoid conflicts
ALTER TABLE user AUTO_INCREMENT = 100;
ALTER TABLE game AUTO_INCREMENT = 100;
ALTER TABLE prediction AUTO_INCREMENT = 100;
ALTER TABLE comment AUTO_INCREMENT = 100;
ALTER TABLE user_points AUTO_INCREMENT = 100;
ALTER TABLE ranking AUTO_INCREMENT = 100;
ALTER TABLE game_prediction_result AUTO_INCREMENT = 100;

COMMIT;
