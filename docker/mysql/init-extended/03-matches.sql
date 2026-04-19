-- World Cup 2026 - Group Stage Matches
-- 12 groups (A-L) with 4 teams each = 72 matches
-- Dates: June 11 - June 26, 2026

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
USE betsdb;

-- ==============================================
-- GROUP A: Meksyk, RPA, Korea Południowa, TBD_A
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Meksyk'), (SELECT id FROM country WHERE name = 'RPA'), '2026-06-11 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A')),
((SELECT id FROM country WHERE name = 'Korea Południowa'), (SELECT id FROM country WHERE name = 'TBD_A'), '2026-06-12 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A')),
((SELECT id FROM country WHERE name = 'Meksyk'), (SELECT id FROM country WHERE name = 'Korea Południowa'), '2026-06-16 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A')),
((SELECT id FROM country WHERE name = 'RPA'), (SELECT id FROM country WHERE name = 'TBD_A'), '2026-06-16 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A')),
((SELECT id FROM country WHERE name = 'Meksyk'), (SELECT id FROM country WHERE name = 'TBD_A'), '2026-06-20 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A')),
((SELECT id FROM country WHERE name = 'RPA'), (SELECT id FROM country WHERE name = 'Korea Południowa'), '2026-06-20 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'A'));

-- ==============================================
-- GROUP B: Kanada, TBD_B, Katar, Szwajcaria
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Kanada'), (SELECT id FROM country WHERE name = 'TBD_B'), '2026-06-12 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B')),
((SELECT id FROM country WHERE name = 'Katar'), (SELECT id FROM country WHERE name = 'Szwajcaria'), '2026-06-12 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B')),
((SELECT id FROM country WHERE name = 'Kanada'), (SELECT id FROM country WHERE name = 'Katar'), '2026-06-17 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B')),
((SELECT id FROM country WHERE name = 'TBD_B'), (SELECT id FROM country WHERE name = 'Szwajcaria'), '2026-06-17 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B')),
((SELECT id FROM country WHERE name = 'Kanada'), (SELECT id FROM country WHERE name = 'Szwajcaria'), '2026-06-21 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B')),
((SELECT id FROM country WHERE name = 'TBD_B'), (SELECT id FROM country WHERE name = 'Katar'), '2026-06-21 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'B'));

-- ==============================================
-- GROUP C: Brazylia, Maroko, Haiti, Szkocja
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Brazylia'), (SELECT id FROM country WHERE name = 'Maroko'), '2026-06-13 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C')),
((SELECT id FROM country WHERE name = 'Haiti'), (SELECT id FROM country WHERE name = 'Szkocja'), '2026-06-13 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C')),
((SELECT id FROM country WHERE name = 'Brazylia'), (SELECT id FROM country WHERE name = 'Haiti'), '2026-06-18 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C')),
((SELECT id FROM country WHERE name = 'Maroko'), (SELECT id FROM country WHERE name = 'Szkocja'), '2026-06-18 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C')),
((SELECT id FROM country WHERE name = 'Brazylia'), (SELECT id FROM country WHERE name = 'Szkocja'), '2026-06-22 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C')),
((SELECT id FROM country WHERE name = 'Maroko'), (SELECT id FROM country WHERE name = 'Haiti'), '2026-06-22 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'C'));

-- ==============================================
-- GROUP D: USA, Paragwaj, Australia, TBD_D
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'USA'), (SELECT id FROM country WHERE name = 'Paragwaj'), '2026-06-13 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D')),
((SELECT id FROM country WHERE name = 'Australia'), (SELECT id FROM country WHERE name = 'TBD_D'), '2026-06-13 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D')),
((SELECT id FROM country WHERE name = 'USA'), (SELECT id FROM country WHERE name = 'Australia'), '2026-06-19 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D')),
((SELECT id FROM country WHERE name = 'Paragwaj'), (SELECT id FROM country WHERE name = 'TBD_D'), '2026-06-19 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D')),
((SELECT id FROM country WHERE name = 'USA'), (SELECT id FROM country WHERE name = 'TBD_D'), '2026-06-23 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D')),
((SELECT id FROM country WHERE name = 'Paragwaj'), (SELECT id FROM country WHERE name = 'Australia'), '2026-06-23 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'D'));

-- ==============================================
-- GROUP E: Niemcy, Curacao, Wybrzeże Kości Słoniowej, Ekwador
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Niemcy'), (SELECT id FROM country WHERE name = 'Curacao'), '2026-06-14 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E')),
((SELECT id FROM country WHERE name = 'Wybrzeże Kości Słoniowej'), (SELECT id FROM country WHERE name = 'Ekwador'), '2026-06-14 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E')),
((SELECT id FROM country WHERE name = 'Niemcy'), (SELECT id FROM country WHERE name = 'Wybrzeże Kości Słoniowej'), '2026-06-19 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E')),
((SELECT id FROM country WHERE name = 'Curacao'), (SELECT id FROM country WHERE name = 'Ekwador'), '2026-06-19 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E')),
((SELECT id FROM country WHERE name = 'Niemcy'), (SELECT id FROM country WHERE name = 'Ekwador'), '2026-06-23 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E')),
((SELECT id FROM country WHERE name = 'Curacao'), (SELECT id FROM country WHERE name = 'Wybrzeże Kości Słoniowej'), '2026-06-23 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'E'));

-- ==============================================
-- GROUP F: Holandia, Japonia, TBD_F, Tunezja
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Holandia'), (SELECT id FROM country WHERE name = 'Japonia'), '2026-06-14 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F')),
((SELECT id FROM country WHERE name = 'TBD_F'), (SELECT id FROM country WHERE name = 'Tunezja'), '2026-06-15 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F')),
((SELECT id FROM country WHERE name = 'Holandia'), (SELECT id FROM country WHERE name = 'TBD_F'), '2026-06-20 19:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F')),
((SELECT id FROM country WHERE name = 'Japonia'), (SELECT id FROM country WHERE name = 'Tunezja'), '2026-06-20 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F')),
((SELECT id FROM country WHERE name = 'Holandia'), (SELECT id FROM country WHERE name = 'Tunezja'), '2026-06-24 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F')),
((SELECT id FROM country WHERE name = 'Japonia'), (SELECT id FROM country WHERE name = 'TBD_F'), '2026-06-24 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'F'));

-- ==============================================
-- GROUP G: Belgia, Egipt, Iran, Nowa Zelandia
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Belgia'), (SELECT id FROM country WHERE name = 'Egipt'), '2026-06-15 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G')),
((SELECT id FROM country WHERE name = 'Iran'), (SELECT id FROM country WHERE name = 'Nowa Zelandia'), '2026-06-15 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G')),
((SELECT id FROM country WHERE name = 'Belgia'), (SELECT id FROM country WHERE name = 'Iran'), '2026-06-21 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G')),
((SELECT id FROM country WHERE name = 'Egipt'), (SELECT id FROM country WHERE name = 'Nowa Zelandia'), '2026-06-21 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G')),
((SELECT id FROM country WHERE name = 'Belgia'), (SELECT id FROM country WHERE name = 'Nowa Zelandia'), '2026-06-25 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G')),
((SELECT id FROM country WHERE name = 'Egipt'), (SELECT id FROM country WHERE name = 'Iran'), '2026-06-25 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'G'));

-- ==============================================
-- GROUP H: Hiszpania, Republika Zielonego Przylądka, Arabia Saudyjska, Urugwaj
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Hiszpania'), (SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), '2026-06-15 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H')),
((SELECT id FROM country WHERE name = 'Arabia Saudyjska'), (SELECT id FROM country WHERE name = 'Urugwaj'), '2026-06-15 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H')),
((SELECT id FROM country WHERE name = 'Hiszpania'), (SELECT id FROM country WHERE name = 'Arabia Saudyjska'), '2026-06-22 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H')),
((SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), (SELECT id FROM country WHERE name = 'Urugwaj'), '2026-06-22 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H')),
((SELECT id FROM country WHERE name = 'Hiszpania'), (SELECT id FROM country WHERE name = 'Urugwaj'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H')),
((SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), (SELECT id FROM country WHERE name = 'Arabia Saudyjska'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'H'));

-- ==============================================
-- GROUP I: Francja, Senegal, TBD_I, Norwegia
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Francja'), (SELECT id FROM country WHERE name = 'Senegal'), '2026-06-16 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I')),
((SELECT id FROM country WHERE name = 'TBD_I'), (SELECT id FROM country WHERE name = 'Norwegia'), '2026-06-16 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I')),
((SELECT id FROM country WHERE name = 'Francja'), (SELECT id FROM country WHERE name = 'TBD_I'), '2026-06-22 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I')),
((SELECT id FROM country WHERE name = 'Senegal'), (SELECT id FROM country WHERE name = 'Norwegia'), '2026-06-22 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I')),
((SELECT id FROM country WHERE name = 'Francja'), (SELECT id FROM country WHERE name = 'Norwegia'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I')),
((SELECT id FROM country WHERE name = 'Senegal'), (SELECT id FROM country WHERE name = 'TBD_I'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'I'));

-- ==============================================
-- GROUP J: Argentyna, Algieria, Austria, Jordania
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Argentyna'), (SELECT id FROM country WHERE name = 'Algieria'), '2026-06-17 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J')),
((SELECT id FROM country WHERE name = 'Austria'), (SELECT id FROM country WHERE name = 'Jordania'), '2026-06-17 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J')),
((SELECT id FROM country WHERE name = 'Argentyna'), (SELECT id FROM country WHERE name = 'Austria'), '2026-06-23 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J')),
((SELECT id FROM country WHERE name = 'Algieria'), (SELECT id FROM country WHERE name = 'Jordania'), '2026-06-23 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J')),
((SELECT id FROM country WHERE name = 'Argentyna'), (SELECT id FROM country WHERE name = 'Jordania'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J')),
((SELECT id FROM country WHERE name = 'Algieria'), (SELECT id FROM country WHERE name = 'Austria'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'J'));

-- ==============================================
-- GROUP K: Portugalia, TBD_K, Uzbekistan, Kolumbia
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Portugalia'), (SELECT id FROM country WHERE name = 'TBD_K'), '2026-06-17 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K')),
((SELECT id FROM country WHERE name = 'Uzbekistan'), (SELECT id FROM country WHERE name = 'Kolumbia'), '2026-06-17 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K')),
((SELECT id FROM country WHERE name = 'Portugalia'), (SELECT id FROM country WHERE name = 'Uzbekistan'), '2026-06-24 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K')),
((SELECT id FROM country WHERE name = 'TBD_K'), (SELECT id FROM country WHERE name = 'Kolumbia'), '2026-06-24 15:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K')),
((SELECT id FROM country WHERE name = 'Portugalia'), (SELECT id FROM country WHERE name = 'Kolumbia'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K')),
((SELECT id FROM country WHERE name = 'TBD_K'), (SELECT id FROM country WHERE name = 'Uzbekistan'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'K'));

-- ==============================================
-- GROUP L: Anglia, Chorwacja, Ghana, Panama
-- ==============================================
INSERT INTO game (home_country_id, away_country_id, game_date, game_status, group_id) VALUES
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Chorwacja'), '2026-06-18 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L')),
((SELECT id FROM country WHERE name = 'Ghana'), (SELECT id FROM country WHERE name = 'Panama'), '2026-06-18 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L')),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Ghana'), '2026-06-24 21:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L')),
((SELECT id FROM country WHERE name = 'Chorwacja'), (SELECT id FROM country WHERE name = 'Panama'), '2026-06-24 18:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L')),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Panama'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L')),
((SELECT id FROM country WHERE name = 'Chorwacja'), (SELECT id FROM country WHERE name = 'Ghana'), '2026-06-26 20:00:00', 'SCHEDULED', (SELECT id FROM `group` WHERE name = 'L'));

COMMIT;
