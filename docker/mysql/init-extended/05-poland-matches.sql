-- Mecze Polski w kwalifikacjach do Mistrzostw Świata 2026
-- Z typowaniami i punktami dla wszystkich użytkowników
-- Extended dataset: 31 users (11 original + 20 new)

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

-- 4. Dodaj mecze (wszystkie zakończone, status FINISHED)
-- Mecz 73: 2025-03-21 - Polska - Litwa 1:0
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (73, '2025-03-21 20:45:00', 'FINISHED', @group_f_id, 49, 50, 1, 0);

-- Mecz 74: 2025-03-24 - Polska - Malta 2:0
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (74, '2025-03-24 20:45:00', 'FINISHED', @group_f_id, 49, 51, 2, 0);

-- Mecz 75: 2025-06-10 - Finlandia - Polska 2:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (75, '2025-06-10 18:00:00', 'FINISHED', @group_f_id, 52, 49, 2, 1);

-- Mecz 76: 2025-09-04 - Holandia - Polska 1:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (76, '2025-09-04 20:45:00', 'FINISHED', @group_f_id, 21, 49, 1, 1);

-- Mecz 77: 2025-09-07 - Polska - Finlandia 3:1
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (77, '2025-09-07 20:45:00', 'FINISHED', @group_f_id, 49, 52, 3, 1);

-- Mecz 78: 2025-10-12 - Litwa - Polska 0:2
INSERT INTO game (id, game_date, game_status, group_id, home_country_id, away_country_id, home_score, away_score)
VALUES (78, '2025-10-12 18:00:00', 'FINISHED', @group_f_id, 50, 49, 0, 2);

-- 5. Dodaj typowania dla wszystkich użytkowników (31 użytkowników)
-- Mecz 73: Polska - Litwa 1:0
-- admin (1) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (1, 1, 73, 2, 1);
-- ola (2) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (2, 2, 73, 1, 2);
-- ania (3) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (3, 3, 73, 2, 1);
-- janek (4) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (4, 4, 73, 1, 2);
-- kuba (5) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (5, 5, 73, 1, 0);
-- michal (6) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (6, 6, 73, 1, 0);
-- magda (7) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (7, 7, 73, 1, 2);
-- marcin (8) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (8, 8, 73, 1, 0);
-- lukasz (9) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (9, 9, 73, 2, 0);
-- pawel (10) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (10, 10, 73, 2, 0);
-- piotr (11) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (11, 11, 73, 0, 0);
-- Ola Kot (12) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (12, 12, 73, 3, 2);
-- Jan Lis (13) - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (13, 13, 73, 3, 0);
-- Ewa Bąk (14) - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (14, 14, 73, 1, 1);
-- Ada Dąb (15) - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (15, 15, 73, 1, 1);
-- Leo Łuk (16) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (16, 16, 73, 1, 0);
-- Ida Łoś (17) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (17, 17, 73, 0, 0);
-- Mia Pęk (18) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (18, 18, 73, 2, 1);
-- Zosia Róg (19) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (19, 19, 73, 0, 0);
-- Maja Szyk (20) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (20, 20, 73, 1, 2);
-- Nina Żuk (21) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (21, 21, 73, 1, 0);
-- Magdalena Kowalska (22) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (22, 22, 73, 1, 2);
-- Aleksandra Szymańska (23) - typ 3:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (23, 23, 73, 3, 3);
-- Katarzyna Dąbrowska (24) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (24, 24, 73, 0, 0);
-- Sebastian Kozłowski (25) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (25, 25, 73, 1, 2);
-- Małgorzata Jankowska (26) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (26, 26, 73, 1, 0);
-- Krzysztof Wojciechowski (27) - typ 1:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (27, 27, 73, 1, 0);
-- Agnieszka Kwiatkowska (28) - typ 0:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (28, 28, 73, 0, 2);
-- Bartłomiej Kaczmarek (29) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (29, 29, 73, 2, 1);
-- Weronika Pawlak (30) - typ 0:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (30, 30, 73, 0, 0);
-- Przemysław Michalski (31) - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (31, 31, 73, 1, 1);

-- Mecz 74: Polska - Malta 2:0
-- admin (1) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (32, 1, 74, 3, 1);
-- ola (2) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (33, 2, 74, 2, 2);
-- ania (3) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (34, 3, 74, 2, 3);
-- janek (4) - typ 4:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (35, 4, 74, 4, 0);
-- kuba (5) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (36, 5, 74, 2, 0);
-- michal (6) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (37, 6, 74, 1, 0);
-- magda (7) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (38, 7, 74, 1, 0);
-- marcin (8) - typ 1:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (39, 8, 74, 1, 1);
-- lukasz (9) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (40, 9, 74, 1, 0);
-- pawel (10) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (41, 10, 74, 2, 2);
-- piotr (11) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (42, 11, 74, 2, 0);
-- Ola Kot (12) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (43, 12, 74, 3, 1);
-- Jan Lis (13) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (44, 13, 74, 2, 2);
-- Ewa Bąk (14) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (45, 14, 74, 1, 0);
-- Ada Dąb (15) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (46, 15, 74, 2, 0);
-- Leo Łuk (16) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (47, 16, 74, 1, 3);
-- Ida Łoś (17) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (48, 17, 74, 2, 0);
-- Mia Pęk (18) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (49, 18, 74, 3, 2);
-- Zosia Róg (19) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (50, 19, 74, 3, 1);
-- Maja Szyk (20) - typ 4:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (51, 20, 74, 4, 0);
-- Nina Żuk (21) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (52, 21, 74, 2, 0);
-- Magdalena Kowalska (22) - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (53, 22, 74, 3, 0);
-- Aleksandra Szymańska (23) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (54, 23, 74, 3, 1);
-- Katarzyna Dąbrowska (24) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (55, 24, 74, 2, 2);
-- Sebastian Kozłowski (25) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (56, 25, 74, 3, 1);
-- Małgorzata Jankowska (26) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (57, 26, 74, 1, 0);
-- Krzysztof Wojciechowski (27) - typ 4:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (58, 27, 74, 4, 0);
-- Agnieszka Kwiatkowska (28) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (59, 28, 74, 1, 0);
-- Bartłomiej Kaczmarek (29) - typ 4:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (60, 29, 74, 4, 0);
-- Weronika Pawlak (30) - typ 2:0 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (61, 30, 74, 2, 0);
-- Przemysław Michalski (31) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (62, 31, 74, 1, 0);

-- Mecz 75: Finlandia - Polska 2:1
-- admin (1) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (63, 1, 75, 3, 1);
-- ola (2) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (64, 2, 75, 1, 0);
-- ania (3) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (65, 3, 75, 1, 0);
-- janek (4) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (66, 4, 75, 3, 1);
-- kuba (5) - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (67, 5, 75, 2, 1);
-- michal (6) - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (68, 6, 75, 2, 1);
-- magda (7) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (69, 7, 75, 3, 1);
-- marcin (8) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (70, 8, 75, 4, 1);
-- lukasz (9) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (71, 9, 75, 2, 2);
-- pawel (10) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (72, 10, 75, 4, 1);
-- piotr (11) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (73, 11, 75, 4, 1);
-- Ola Kot (12) - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (74, 12, 75, 2, 1);
-- Jan Lis (13) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (75, 13, 75, 4, 1);
-- Ewa Bąk (14) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (76, 14, 75, 2, 2);
-- Ada Dąb (15) - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (77, 15, 75, 3, 0);
-- Leo Łuk (16) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (78, 16, 75, 1, 0);
-- Ida Łoś (17) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (79, 17, 75, 2, 3);
-- Mia Pęk (18) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (80, 18, 75, 4, 1);
-- Zosia Róg (19) - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (81, 19, 75, 2, 1);
-- Maja Szyk (20) - typ 2:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (82, 20, 75, 2, 1);
-- Nina Żuk (21) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (83, 21, 75, 4, 1);
-- Magdalena Kowalska (22) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (84, 22, 75, 3, 2);
-- Aleksandra Szymańska (23) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (85, 23, 75, 3, 2);
-- Katarzyna Dąbrowska (24) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (86, 24, 75, 4, 1);
-- Sebastian Kozłowski (25) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (87, 25, 75, 2, 3);
-- Małgorzata Jankowska (26) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (88, 26, 75, 3, 2);
-- Krzysztof Wojciechowski (27) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (89, 27, 75, 1, 0);
-- Agnieszka Kwiatkowska (28) - typ 1:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (90, 28, 75, 1, 0);
-- Bartłomiej Kaczmarek (29) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (91, 29, 75, 2, 3);
-- Weronika Pawlak (30) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (92, 30, 75, 2, 2);
-- Przemysław Michalski (31) - typ 3:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (93, 31, 75, 3, 1);

-- Mecz 76: Holandia - Polska 1:1
-- admin (1) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (94, 1, 76, 2, 2);
-- ola (2) - typ 0:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (95, 2, 76, 0, 1);
-- ania (3) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (96, 3, 76, 2, 2);
-- janek (4) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (97, 4, 76, 1, 3);
-- kuba (5) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (98, 5, 76, 1, 3);
-- michal (6) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (99, 6, 76, 2, 2);
-- magda (7) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (100, 7, 76, 3, 1);
-- marcin (8) - typ 1:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (101, 8, 76, 1, 2);
-- lukasz (9) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (102, 9, 76, 2, 2);
-- pawel (10) - typ 0:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (103, 10, 76, 0, 0);
-- piotr (11) - typ 0:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (104, 11, 76, 0, 0);
-- Ola Kot (12) - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (105, 12, 76, 1, 1);
-- Jan Lis (13) - typ 0:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (106, 13, 76, 0, 1);
-- Ewa Bąk (14) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (107, 14, 76, 1, 3);
-- Ada Dąb (15) - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (108, 15, 76, 1, 1);
-- Leo Łuk (16) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (109, 16, 76, 3, 1);
-- Ida Łoś (17) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (110, 17, 76, 3, 1);
-- Mia Pęk (18) - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (111, 18, 76, 1, 1);
-- Zosia Róg (19) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (112, 19, 76, 3, 1);
-- Maja Szyk (20) - typ 1:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (113, 20, 76, 1, 1);
-- Nina Żuk (21) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (114, 21, 76, 3, 1);
-- Magdalena Kowalska (22) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (115, 22, 76, 3, 1);
-- Aleksandra Szymańska (23) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (116, 23, 76, 1, 3);
-- Katarzyna Dąbrowska (24) - typ 2:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (117, 24, 76, 2, 0);
-- Sebastian Kozłowski (25) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (118, 25, 76, 2, 2);
-- Małgorzata Jankowska (26) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (119, 26, 76, 2, 2);
-- Krzysztof Wojciechowski (27) - typ 1:0 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (120, 27, 76, 1, 0);
-- Agnieszka Kwiatkowska (28) - typ 0:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (121, 28, 76, 0, 1);
-- Bartłomiej Kaczmarek (29) - typ 2:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (122, 29, 76, 2, 1);
-- Weronika Pawlak (30) - typ 0:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (123, 30, 76, 0, 0);
-- Przemysław Michalski (31) - typ 2:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (124, 31, 76, 2, 2);

-- Mecz 77: Polska - Finlandia 3:1
-- admin (1) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (125, 1, 77, 2, 0);
-- ola (2) - typ 5:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (126, 2, 77, 5, 1);
-- ania (3) - typ 5:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (127, 3, 77, 5, 1);
-- janek (4) - typ 3:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (128, 4, 77, 3, 3);
-- kuba (5) - typ 3:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (129, 5, 77, 3, 3);
-- michal (6) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (130, 6, 77, 2, 3);
-- magda (7) - typ 4:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (131, 7, 77, 4, 2);
-- marcin (8) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (132, 8, 77, 3, 1);
-- lukasz (9) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (133, 9, 77, 3, 1);
-- pawel (10) - typ 4:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (134, 10, 77, 4, 2);
-- piotr (11) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (135, 11, 77, 2, 0);
-- Ola Kot (12) - typ 4:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (136, 12, 77, 4, 2);
-- Jan Lis (13) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (137, 13, 77, 3, 1);
-- Ewa Bąk (14) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (138, 14, 77, 3, 1);
-- Ada Dąb (15) - typ 4:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (139, 15, 77, 4, 2);
-- Leo Łuk (16) - typ 5:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (140, 16, 77, 5, 1);
-- Ida Łoś (17) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (141, 17, 77, 2, 2);
-- Mia Pęk (18) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (142, 18, 77, 3, 1);
-- Zosia Róg (19) - typ 3:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (143, 19, 77, 3, 0);
-- Maja Szyk (20) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (144, 20, 77, 2, 0);
-- Nina Żuk (21) - typ 5:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (145, 21, 77, 5, 1);
-- Magdalena Kowalska (22) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (146, 22, 77, 2, 0);
-- Aleksandra Szymańska (23) - typ 2:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (147, 23, 77, 2, 1);
-- Katarzyna Dąbrowska (24) - typ 2:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (148, 24, 77, 2, 3);
-- Sebastian Kozłowski (25) - typ 2:0 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (149, 25, 77, 2, 0);
-- Małgorzata Jankowska (26) - typ 0:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (150, 26, 77, 0, 3);
-- Krzysztof Wojciechowski (27) - typ 3:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (151, 27, 77, 3, 2);
-- Agnieszka Kwiatkowska (28) - typ 4:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (152, 28, 77, 4, 1);
-- Bartłomiej Kaczmarek (29) - typ 1:3 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (153, 29, 77, 1, 3);
-- Weronika Pawlak (30) - typ 3:1 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (154, 30, 77, 3, 1);
-- Przemysław Michalski (31) - typ 4:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (155, 31, 77, 4, 2);

-- Mecz 78: Litwa - Polska 0:2
-- admin (1) - typ 1:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (156, 1, 78, 1, 3);
-- ola (2) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (157, 2, 78, 0, 2);
-- ania (3) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (158, 3, 78, 0, 2);
-- janek (4) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (159, 4, 78, 0, 2);
-- kuba (5) - typ 0:4 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (160, 5, 78, 0, 4);
-- michal (6) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (161, 6, 78, 0, 1);
-- magda (7) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (162, 7, 78, 0, 1);
-- marcin (8) - typ 1:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (163, 8, 78, 1, 2);
-- lukasz (9) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (164, 9, 78, 0, 1);
-- pawel (10) - typ 1:2 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (165, 10, 78, 1, 2);
-- piotr (11) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (166, 11, 78, 0, 1);
-- Ola Kot (12) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (167, 12, 78, 0, 1);
-- Jan Lis (13) - typ 1:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (168, 13, 78, 1, 3);
-- Ewa Bąk (14) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (169, 14, 78, 0, 2);
-- Ada Dąb (15) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (170, 15, 78, 2, 2);
-- Leo Łuk (16) - typ 0:4 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (171, 16, 78, 0, 4);
-- Ida Łoś (17) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (172, 17, 78, 0, 2);
-- Mia Pęk (18) - typ 2:2 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (173, 18, 78, 2, 2);
-- Zosia Róg (19) - typ 0:4 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (174, 19, 78, 0, 4);
-- Maja Szyk (20) - typ 2:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (175, 20, 78, 2, 3);
-- Nina Żuk (21) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (176, 21, 78, 0, 1);
-- Magdalena Kowalska (22) - typ 0:2 (3 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (177, 22, 78, 0, 2);
-- Aleksandra Szymańska (23) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (178, 23, 78, 0, 1);
-- Katarzyna Dąbrowska (24) - typ 1:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (179, 24, 78, 1, 3);
-- Sebastian Kozłowski (25) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (180, 25, 78, 0, 1);
-- Małgorzata Jankowska (26) - typ 2:3 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (181, 26, 78, 2, 3);
-- Krzysztof Wojciechowski (27) - typ 0:4 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (182, 27, 78, 0, 4);
-- Agnieszka Kwiatkowska (28) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (183, 28, 78, 0, 1);
-- Bartłomiej Kaczmarek (29) - typ 0:1 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (184, 29, 78, 0, 1);
-- Weronika Pawlak (30) - typ 3:1 (0 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (185, 30, 78, 3, 1);
-- Przemysław Michalski (31) - typ 0:4 (1 pkt)
INSERT INTO prediction (id, user_id, game_id, predicted_home_score, predicted_away_score) VALUES (186, 31, 78, 0, 4);

-- 6. Dodaj punkty dla wszystkich typowań

-- Mecz 73: Polska - Litwa 1:0
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 1, 1);  -- admin: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 2, 0);  -- ola: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 3, 1);  -- ania: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 4, 0);  -- janek: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 5, 3);  -- kuba: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 6, 3);  -- michal: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 7, 0);  -- magda: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 8, 3);  -- marcin: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 9, 1);  -- lukasz: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 10, 1);  -- pawel: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 11, 0);  -- piotr: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 12, 1);  -- Ola Kot: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 13, 1);  -- Jan Lis: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 14, 0);  -- Ewa Bąk: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 15, 0);  -- Ada Dąb: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 16, 3);  -- Leo Łuk: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 17, 0);  -- Ida Łoś: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 18, 1);  -- Mia Pęk: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 19, 0);  -- Zosia Róg: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 20, 0);  -- Maja Szyk: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 21, 3);  -- Nina Żuk: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 22, 0);  -- Magdalena Kowalska: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 23, 0);  -- Aleksandra Szymańska: 3:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 24, 0);  -- Katarzyna Dąbrowska: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 25, 0);  -- Sebastian Kozłowski: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 26, 3);  -- Małgorzata Jankowska: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 27, 3);  -- Krzysztof Wojciechowski: 1:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 28, 0);  -- Agnieszka Kwiatkowska: 0:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 29, 1);  -- Bartłomiej Kaczmarek: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 30, 0);  -- Weronika Pawlak: 0:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 31, 0);  -- Przemysław Michalski: 1:1 = 0 pkt

-- Mecz 74: Polska - Malta 2:0
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 32, 1);  -- admin: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 33, 0);  -- ola: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 34, 0);  -- ania: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 35, 1);  -- janek: 4:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 36, 3);  -- kuba: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 37, 1);  -- michal: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 38, 1);  -- magda: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 39, 0);  -- marcin: 1:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 40, 1);  -- lukasz: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 41, 0);  -- pawel: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 42, 3);  -- piotr: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 43, 1);  -- Ola Kot: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 44, 0);  -- Jan Lis: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 45, 1);  -- Ewa Bąk: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 46, 3);  -- Ada Dąb: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 47, 0);  -- Leo Łuk: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 48, 3);  -- Ida Łoś: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 49, 1);  -- Mia Pęk: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 50, 1);  -- Zosia Róg: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 51, 1);  -- Maja Szyk: 4:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 52, 3);  -- Nina Żuk: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 53, 1);  -- Magdalena Kowalska: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 54, 1);  -- Aleksandra Szymańska: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 55, 0);  -- Katarzyna Dąbrowska: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 56, 1);  -- Sebastian Kozłowski: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 57, 1);  -- Małgorzata Jankowska: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 58, 1);  -- Krzysztof Wojciechowski: 4:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 59, 1);  -- Agnieszka Kwiatkowska: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 60, 1);  -- Bartłomiej Kaczmarek: 4:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 61, 3);  -- Weronika Pawlak: 2:0 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 62, 1);  -- Przemysław Michalski: 1:0 = 1 pkt

-- Mecz 75: Finlandia - Polska 2:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 63, 1);  -- admin: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 64, 1);  -- ola: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 65, 1);  -- ania: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 66, 1);  -- janek: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 67, 3);  -- kuba: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 68, 3);  -- michal: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 69, 1);  -- magda: 3:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 70, 1);  -- marcin: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 71, 0);  -- lukasz: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 72, 1);  -- pawel: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 73, 1);  -- piotr: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 74, 3);  -- Ola Kot: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 75, 1);  -- Jan Lis: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 76, 0);  -- Ewa Bąk: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 77, 1);  -- Ada Dąb: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 78, 1);  -- Leo Łuk: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 79, 0);  -- Ida Łoś: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 80, 1);  -- Mia Pęk: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 81, 3);  -- Zosia Róg: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 82, 3);  -- Maja Szyk: 2:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 83, 1);  -- Nina Żuk: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 84, 1);  -- Magdalena Kowalska: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 85, 1);  -- Aleksandra Szymańska: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 86, 1);  -- Katarzyna Dąbrowska: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 87, 0);  -- Sebastian Kozłowski: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 88, 1);  -- Małgorzata Jankowska: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 89, 1);  -- Krzysztof Wojciechowski: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 90, 1);  -- Agnieszka Kwiatkowska: 1:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 91, 0);  -- Bartłomiej Kaczmarek: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 92, 0);  -- Weronika Pawlak: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 93, 1);  -- Przemysław Michalski: 3:1 = 1 pkt

-- Mecz 76: Holandia - Polska 1:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 94, 1);  -- admin: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 95, 0);  -- ola: 0:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 96, 1);  -- ania: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 97, 0);  -- janek: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 98, 0);  -- kuba: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 99, 1);  -- michal: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 100, 0);  -- magda: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 101, 0);  -- marcin: 1:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 102, 1);  -- lukasz: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 103, 1);  -- pawel: 0:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 104, 1);  -- piotr: 0:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 105, 3);  -- Ola Kot: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 106, 0);  -- Jan Lis: 0:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 107, 0);  -- Ewa Bąk: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 108, 3);  -- Ada Dąb: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 109, 0);  -- Leo Łuk: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 110, 0);  -- Ida Łoś: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 111, 3);  -- Mia Pęk: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 112, 0);  -- Zosia Róg: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 113, 3);  -- Maja Szyk: 1:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 114, 0);  -- Nina Żuk: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 115, 0);  -- Magdalena Kowalska: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 116, 0);  -- Aleksandra Szymańska: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 117, 0);  -- Katarzyna Dąbrowska: 2:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 118, 1);  -- Sebastian Kozłowski: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 119, 1);  -- Małgorzata Jankowska: 2:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 120, 0);  -- Krzysztof Wojciechowski: 1:0 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 121, 0);  -- Agnieszka Kwiatkowska: 0:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 122, 0);  -- Bartłomiej Kaczmarek: 2:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 123, 1);  -- Weronika Pawlak: 0:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 124, 1);  -- Przemysław Michalski: 2:2 = 1 pkt

-- Mecz 77: Polska - Finlandia 3:1
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 125, 1);  -- admin: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 126, 1);  -- ola: 5:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 127, 1);  -- ania: 5:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 128, 0);  -- janek: 3:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 129, 0);  -- kuba: 3:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 130, 0);  -- michal: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 131, 1);  -- magda: 4:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 132, 3);  -- marcin: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 133, 3);  -- lukasz: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 134, 1);  -- pawel: 4:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 135, 1);  -- piotr: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 136, 1);  -- Ola Kot: 4:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 137, 3);  -- Jan Lis: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 138, 3);  -- Ewa Bąk: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 139, 1);  -- Ada Dąb: 4:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 140, 1);  -- Leo Łuk: 5:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 141, 0);  -- Ida Łoś: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 142, 3);  -- Mia Pęk: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 143, 1);  -- Zosia Róg: 3:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 144, 1);  -- Maja Szyk: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 145, 1);  -- Nina Żuk: 5:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 146, 1);  -- Magdalena Kowalska: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 147, 1);  -- Aleksandra Szymańska: 2:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 148, 0);  -- Katarzyna Dąbrowska: 2:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 149, 1);  -- Sebastian Kozłowski: 2:0 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 150, 0);  -- Małgorzata Jankowska: 0:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 151, 1);  -- Krzysztof Wojciechowski: 3:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 152, 1);  -- Agnieszka Kwiatkowska: 4:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 153, 0);  -- Bartłomiej Kaczmarek: 1:3 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 154, 3);  -- Weronika Pawlak: 3:1 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 155, 1);  -- Przemysław Michalski: 4:2 = 1 pkt

-- Mecz 78: Litwa - Polska 0:2
INSERT INTO user_points (user_id, prediction_id, points) VALUES (1, 156, 1);  -- admin: 1:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (2, 157, 3);  -- ola: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (3, 158, 3);  -- ania: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (4, 159, 3);  -- janek: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (5, 160, 1);  -- kuba: 0:4 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (6, 161, 1);  -- michal: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (7, 162, 1);  -- magda: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (8, 163, 1);  -- marcin: 1:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (9, 164, 1);  -- lukasz: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (10, 165, 1);  -- pawel: 1:2 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (11, 166, 1);  -- piotr: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (12, 167, 1);  -- Ola Kot: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (13, 168, 1);  -- Jan Lis: 1:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (14, 169, 3);  -- Ewa Bąk: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (15, 170, 0);  -- Ada Dąb: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (16, 171, 1);  -- Leo Łuk: 0:4 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (17, 172, 3);  -- Ida Łoś: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (18, 173, 0);  -- Mia Pęk: 2:2 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (19, 174, 1);  -- Zosia Róg: 0:4 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (20, 175, 1);  -- Maja Szyk: 2:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (21, 176, 1);  -- Nina Żuk: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (22, 177, 3);  -- Magdalena Kowalska: 0:2 = 3 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (23, 178, 1);  -- Aleksandra Szymańska: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (24, 179, 1);  -- Katarzyna Dąbrowska: 1:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (25, 180, 1);  -- Sebastian Kozłowski: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (26, 181, 1);  -- Małgorzata Jankowska: 2:3 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (27, 182, 1);  -- Krzysztof Wojciechowski: 0:4 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (28, 183, 1);  -- Agnieszka Kwiatkowska: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (29, 184, 1);  -- Bartłomiej Kaczmarek: 0:1 = 1 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (30, 185, 0);  -- Weronika Pawlak: 3:1 = 0 pkt
INSERT INTO user_points (user_id, prediction_id, points) VALUES (31, 186, 1);  -- Przemysław Michalski: 0:4 = 1 pkt

COMMIT;

