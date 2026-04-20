-- Mecze Polski w kwalifikacjach do Mistrzostw Świata 2026
-- Z typowaniami i punktami dla wszystkich użytkowników

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
USE betsdb;

-- 1. Pobierz ID grupy F
SET @group_f_id = (SELECT id FROM `group` WHERE name = 'F');

-- 2. Dodaj kraje do grupy F (jeśli nie istnieją)
INSERT IGNORE INTO country (id, name, code, group_id) VALUES
(49, 'Polska', 'pl', @group_f_id),
(50, 'Litwa', 'lt', @group_f_id),
(51, 'Malta', 'mt', @group_f_id),
(52, 'Finlandia', 'fi', @group_f_id);

-- 3. Holandia już istnieje (ID 21), zaktualizuj jej grupę
UPDATE country SET group_id = @group_f_id WHERE id = 21;

-- 3. Dodaj mecze (wszystkie zakończone, status FINISHED)
-- Mecz 1: 21 marca 2025 - Polska - Litwa 1:0
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (73, '2025-03-21 20:45:00', 'FINISHED', @group_f_id, 49, 50, 1, 0);

-- Mecz 2: 24 marca 2025 - Polska - Malta 2:0
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (74, '2025-03-24 20:45:00', 'FINISHED', @group_f_id, 49, 51, 2, 0);

-- Mecz 3: 10 czerwca 2025 - Finlandia - Polska 2:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (75, '2025-06-10 18:00:00', 'FINISHED', @group_f_id, 52, 49, 2, 1);

-- Mecz 4: 4 września 2025 - Holandia - Polska 1:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (76, '2025-09-04 20:45:00', 'FINISHED', @group_f_id, 21, 49, 1, 1);

-- Mecz 5: 7 września 2025 - Polska - Finlandia 3:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (77, '2025-09-07 20:45:00', 'FINISHED', @group_f_id, 49, 52, 3, 1);

-- Mecz 6: 12 października 2025 - Litwa - Polska 0:2
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (78, '2025-10-12 18:00:00', 'FINISHED', @group_f_id, 50, 49, 0, 2);

-- 3. Dodaj typowania dla wszystkich użytkowników (11 użytkowników)
-- Mecz 73: Polska - Litwa 1:0
-- admin (1) - typ 1:0 (3 pkt - dokładny)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (1, 1, 73, 1, 0);
-- ola (2) - typ 2:0 (1 pkt - trafiony zwycięzca)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (2, 2, 73, 2, 0);
-- ania (3) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (3, 3, 73, 1, 0);
-- janek (4) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (4, 4, 73, 2, 1);
-- kuba (5) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (5, 5, 73, 0, 0);
-- michal (6) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (6, 6, 73, 1, 0);
-- magda (7) - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (7, 7, 73, 3, 0);
-- marcin (8) - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (8, 8, 73, 1, 1);
-- lukasz (9) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (9, 9, 73, 2, 0);
-- pawel (10) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (10, 10, 73, 1, 0);
-- piotr (11) - typ 0:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (11, 11, 73, 0, 1);

-- Mecz 74: Polska - Malta 2:0
-- admin - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (12, 1, 74, 3, 0);
-- ola - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (13, 2, 74, 2, 0);
-- ania - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (14, 3, 74, 2, 0);
-- janek - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (15, 4, 74, 1, 0);
-- kuba - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (16, 5, 74, 2, 1);
-- michal - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (17, 6, 74, 2, 0);
-- magda - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (18, 7, 74, 3, 0);
-- marcin - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (19, 8, 74, 2, 0);
-- lukasz - typ 4:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (20, 9, 74, 4, 0);
-- pawel - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (21, 10, 74, 2, 0);
-- piotr - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (22, 11, 74, 1, 1);

-- Mecz 75: Finlandia - Polska 2:1
-- admin - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (23, 1, 75, 1, 1);
-- ola - typ 0:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (24, 2, 75, 0, 2);
-- ania - typ 1:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (25, 3, 75, 1, 2);
-- janek - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (26, 4, 75, 2, 1);
-- kuba - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (27, 5, 75, 1, 1);
-- michal - typ 0:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (28, 6, 75, 0, 1);
-- magda - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (29, 7, 75, 2, 2);
-- marcin - typ 1:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (30, 8, 75, 1, 2);
-- lukasz - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (31, 9, 75, 2, 1);
-- pawel - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (32, 10, 75, 1, 0);
-- piotr - typ 0:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (33, 11, 75, 0, 2);

-- Mecz 76: Holandia - Polska 1:1
-- admin - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (34, 1, 76, 1, 1);
-- ola - typ 2:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (35, 2, 76, 2, 1);
-- ania - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (36, 3, 76, 1, 1);
-- janek - typ 2:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (37, 4, 76, 2, 0);
-- kuba - typ 0:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (38, 5, 76, 0, 0);
-- michal - typ 1:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (39, 6, 76, 1, 0);
-- magda - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (40, 7, 76, 2, 2);
-- marcin - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (41, 8, 76, 1, 1);
-- lukasz - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (42, 9, 76, 1, 2);
-- pawel - typ 0:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (43, 10, 76, 0, 0);
-- piotr - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (44, 11, 76, 3, 1);

-- Mecz 77: Polska - Finlandia 3:1
-- admin - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (45, 1, 77, 3, 1);
-- ola - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (46, 2, 77, 2, 0);
-- ania - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (47, 3, 77, 2, 1);
-- janek - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (48, 4, 77, 3, 1);
-- kuba - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (49, 5, 77, 1, 0);
-- michal - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (50, 6, 77, 2, 0);
-- magda - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (51, 7, 77, 3, 0);
-- marcin - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (52, 8, 77, 3, 1);
-- lukasz - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (53, 9, 77, 2, 1);
-- pawel - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (54, 10, 77, 3, 1);
-- piotr - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (55, 11, 77, 1, 1);

-- Mecz 78: Litwa - Polska 0:2
-- admin - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (56, 1, 78, 0, 2);
-- ola - typ 1:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (57, 2, 78, 1, 2);
-- ania - typ 0:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (58, 3, 78, 0, 3);
-- janek - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (59, 4, 78, 0, 2);
-- kuba - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (60, 5, 78, 1, 1);
-- michal - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (61, 6, 78, 0, 1);
-- magda - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (62, 7, 78, 0, 2);
-- marcin - typ 1:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (63, 8, 78, 1, 3);
-- lukasz - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (64, 9, 78, 0, 2);
-- pawel - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (65, 10, 78, 0, 1);
-- piotr - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (66, 11, 78, 2, 2);

-- 4. Dodaj punkty dla każdego typowania
-- Mecz 73: Polska - Litwa 1:0
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 1, 3);  -- admin: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 2, 1);  -- ola: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 3, 3);  -- ania: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 4, 1);  -- janek: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 5, 0);  -- kuba: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 6, 3);  -- michal: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 7, 1);  -- magda: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 8, 0);  -- marcin: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 9, 1);  -- lukasz: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 10, 3); -- pawel: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 11, 0); -- piotr: 0:1 = 0 pkt

-- Mecz 74: Polska - Malta 2:0
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 12, 1);  -- admin: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 13, 3);  -- ola: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 14, 3);  -- ania: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 15, 1);  -- janek: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 16, 1);  -- kuba: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 17, 3);  -- michal: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 18, 1);  -- magda: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 19, 3);  -- marcin: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 20, 1);  -- lukasz: 4:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 21, 3); -- pawel: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 22, 0); -- piotr: 1:1 = 0 pkt

-- Mecz 75: Finlandia - Polska 2:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 23, 0);  -- admin: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 24, 0);  -- ola: 0:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 25, 1);  -- ania: 1:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 26, 3);  -- janek: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 27, 0);  -- kuba: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 28, 0);  -- michal: 0:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 29, 0);  -- magda: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 30, 1);  -- marcin: 1:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 31, 3);  -- lukasz: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 32, 1); -- pawel: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 33, 0); -- piotr: 0:2 = 0 pkt

-- Mecz 76: Holandia - Polska 1:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 34, 3);  -- admin: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 35, 0);  -- ola: 2:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 36, 3);  -- ania: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 37, 0);  -- janek: 2:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 38, 1);  -- kuba: 0:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 39, 0);  -- michal: 1:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 40, 1);  -- magda: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 41, 3);  -- marcin: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 42, 0);  -- lukasz: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 43, 1); -- pawel: 0:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 44, 0); -- piotr: 3:1 = 0 pkt

-- Mecz 77: Polska - Finlandia 3:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 45, 3);  -- admin: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 46, 1);  -- ola: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 47, 1);  -- ania: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 48, 3);  -- janek: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 49, 1);  -- kuba: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 50, 1);  -- michal: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 51, 1);  -- magda: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 52, 3);  -- marcin: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 53, 1);  -- lukasz: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 54, 3); -- pawel: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 55, 0); -- piotr: 1:1 = 0 pkt

-- Mecz 78: Litwa - Polska 0:2
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 56, 3);  -- admin: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 57, 1);  -- ola: 1:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 58, 1);  -- ania: 0:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 59, 3);  -- janek: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 60, 0);  -- kuba: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 61, 1);  -- michal: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 62, 3);  -- magda: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 63, 1);  -- marcin: 1:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 64, 3);  -- lukasz: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 65, 1); -- pawel: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 66, 0); -- piotr: 2:2 = 0 pkt

COMMIT;

-- Podsumowanie punktów:
-- admin (1): 3+1+0+3+3+3 = 13 pkt
-- ola (2): 1+3+0+0+1+1 = 6 pkt
-- ania (3): 3+3+1+3+1+1 = 12 pkt
-- janek (4): 1+1+3+0+3+3 = 11 pkt
-- kuba (5): 0+1+0+1+1+0 = 3 pkt
-- michal (6): 3+3+0+0+1+1 = 8 pkt
-- magda (7): 1+1+0+1+1+3 = 7 pkt
-- marcin (8): 0+3+1+3+3+1 = 11 pkt
-- lukasz (9): 1+1+3+0+1+3 = 9 pkt
-- pawel (10): 3+3+1+1+3+1 = 12 pkt
-- piotr (11): 0+0+0+0+0+0 = 0 pkt
