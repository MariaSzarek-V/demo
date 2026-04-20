-- Upcoming games - World Cup 2026
-- Games with known countries: SCHEDULED
-- Games with unknown countries (TBD/WKS): ADMIN_VIEW

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- FAZA GRUPOWA - 11 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Meksyk'), (SELECT id FROM country WHERE name = 'RPA'), '2026-06-11 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 12 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Korea Południowa'), (SELECT id FROM country WHERE name = 'Czechy'), '2026-06-12 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Kanada'), (SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), '2026-06-12 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 13 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'USA'), (SELECT id FROM country WHERE name = 'Paragwaj'), '2026-06-13 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Katar'), (SELECT id FROM country WHERE name = 'Szwajcaria'), '2026-06-13 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 14 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Brazylia'), (SELECT id FROM country WHERE name = 'Maroko'), '2026-06-14 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Haiti'), (SELECT id FROM country WHERE name = 'Szkocja'), '2026-06-14 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Australia'), (SELECT id FROM country WHERE name = 'Turcja'), '2026-06-14 06:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Niemcy'), (SELECT id FROM country WHERE name = 'Curacao'), '2026-06-14 19:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Holandia'), (SELECT id FROM country WHERE name = 'Japonia'), '2026-06-14 22:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 15 CZERWCA 2026
-- ==============================================
-- WKS - unknown country - ADMIN_VIEW
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'WKS'), (SELECT id FROM country WHERE name = 'Ekwador'), '2026-06-15 01:00:00', 'ADMIN_VIEW');

INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Szwecja'), (SELECT id FROM country WHERE name = 'Tunezja'), '2026-06-15 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Hiszpania'), (SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), '2026-06-15 18:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Belgia'), (SELECT id FROM country WHERE name = 'Egipt'), '2026-06-15 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 16 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Arabia Saudyjska'), (SELECT id FROM country WHERE name = 'Urugwaj'), '2026-06-16 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Iran'), (SELECT id FROM country WHERE name = 'Nowa Zelandia'), '2026-06-16 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Francja'), (SELECT id FROM country WHERE name = 'Senegal'), '2026-06-16 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 17 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Irak'), (SELECT id FROM country WHERE name = 'Norwegia'), '2026-06-17 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Argentyna'), (SELECT id FROM country WHERE name = 'Algieria'), '2026-06-17 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Austria'), (SELECT id FROM country WHERE name = 'Jordania'), '2026-06-17 06:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Portugalia'), (SELECT id FROM country WHERE name = 'DR Konga'), '2026-06-17 19:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Chorwacja'), '2026-06-17 22:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 18 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Ghana'), (SELECT id FROM country WHERE name = 'Panama'), '2026-06-18 01:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Uzbekistan'), (SELECT id FROM country WHERE name = 'Kolumbia'), '2026-06-18 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Czechy'), (SELECT id FROM country WHERE name = 'RPA'), '2026-06-18 18:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Szwajcaria'), (SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), '2026-06-18 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 19 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Kanada'), (SELECT id FROM country WHERE name = 'Katar'), '2026-06-19 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Meksyk'), (SELECT id FROM country WHERE name = 'Korea Południowa'), '2026-06-19 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'USA'), (SELECT id FROM country WHERE name = 'Australia'), '2026-06-19 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 20 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Szkocja'), (SELECT id FROM country WHERE name = 'Maroko'), '2026-06-20 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Brazylia'), (SELECT id FROM country WHERE name = 'Haiti'), '2026-06-20 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Turcja'), (SELECT id FROM country WHERE name = 'Paragwaj'), '2026-06-20 06:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Holandia'), (SELECT id FROM country WHERE name = 'Szwecja'), '2026-06-20 19:00:00', 'SCHEDULED');

-- WKS - unknown country - ADMIN_VIEW
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Niemcy'), (SELECT id FROM country WHERE name = 'WKS'), '2026-06-20 22:00:00', 'ADMIN_VIEW');

-- ==============================================
-- FAZA GRUPOWA - 21 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Ekwador'), (SELECT id FROM country WHERE name = 'Curacao'), '2026-06-21 02:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Tunezja'), (SELECT id FROM country WHERE name = 'Japonia'), '2026-06-21 06:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Hiszpania'), (SELECT id FROM country WHERE name = 'Arabia Saudyjska'), '2026-06-21 18:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Belgia'), (SELECT id FROM country WHERE name = 'Iran'), '2026-06-21 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 22 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Urugwaj'), (SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), '2026-06-22 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Nowa Zelandia'), (SELECT id FROM country WHERE name = 'Egipt'), '2026-06-22 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Argentyna'), (SELECT id FROM country WHERE name = 'Austria'), '2026-06-22 19:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Francja'), (SELECT id FROM country WHERE name = 'Irak'), '2026-06-22 23:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 23 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Norwegia'), (SELECT id FROM country WHERE name = 'Senegal'), '2026-06-23 02:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Jordania'), (SELECT id FROM country WHERE name = 'Algieria'), '2026-06-23 05:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Portugalia'), (SELECT id FROM country WHERE name = 'Uzbekistan'), '2026-06-23 19:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Ghana'), '2026-06-23 22:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 24 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Panama'), (SELECT id FROM country WHERE name = 'Chorwacja'), '2026-06-24 01:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Kolumbia'), (SELECT id FROM country WHERE name = 'DR Konga'), '2026-06-24 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Szwajcaria'), (SELECT id FROM country WHERE name = 'Kanada'), '2026-06-24 21:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), (SELECT id FROM country WHERE name = 'Katar'), '2026-06-24 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 25 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Maroko'), (SELECT id FROM country WHERE name = 'Haiti'), '2026-06-25 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Szkocja'), (SELECT id FROM country WHERE name = 'Brazylia'), '2026-06-25 00:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'RPA'), (SELECT id FROM country WHERE name = 'Korea Południowa'), '2026-06-25 03:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Czechy'), (SELECT id FROM country WHERE name = 'Meksyk'), '2026-06-25 03:00:00', 'SCHEDULED');

-- WKS - unknown country - ADMIN_VIEW
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Curacao'), (SELECT id FROM country WHERE name = 'WKS'), '2026-06-25 22:00:00', 'ADMIN_VIEW'),
((SELECT id FROM country WHERE name = 'Ekwador'), (SELECT id FROM country WHERE name = 'Niemcy'), '2026-06-25 22:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 26 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Japonia'), (SELECT id FROM country WHERE name = 'Szwecja'), '2026-06-26 01:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Tunezja'), (SELECT id FROM country WHERE name = 'Holandia'), '2026-06-26 01:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Paragwaj'), (SELECT id FROM country WHERE name = 'Australia'), '2026-06-26 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Turcja'), (SELECT id FROM country WHERE name = 'USA'), '2026-06-26 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Norwegia'), (SELECT id FROM country WHERE name = 'Francja'), '2026-06-26 21:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Senegal'), (SELECT id FROM country WHERE name = 'Irak'), '2026-06-26 21:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 27 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Republika Zielonego Przylądka'), (SELECT id FROM country WHERE name = 'Arabia Saudyjska'), '2026-06-27 02:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Urugwaj'), (SELECT id FROM country WHERE name = 'Hiszpania'), '2026-06-27 02:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Egipt'), (SELECT id FROM country WHERE name = 'Iran'), '2026-06-27 05:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Nowa Zelandia'), (SELECT id FROM country WHERE name = 'Belgia'), '2026-06-27 05:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Chorwacja'), (SELECT id FROM country WHERE name = 'Ghana'), '2026-06-27 23:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Panama'), (SELECT id FROM country WHERE name = 'Anglia'), '2026-06-27 23:00:00', 'SCHEDULED');

-- ==============================================
-- FAZA GRUPOWA - 28 CZERWCA 2026
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'DR Konga'), (SELECT id FROM country WHERE name = 'Uzbekistan'), '2026-06-28 01:30:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Kolumbia'), (SELECT id FROM country WHERE name = 'Portugalia'), '2026-06-28 01:30:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Algieria'), (SELECT id FROM country WHERE name = 'Austria'), '2026-06-28 04:00:00', 'SCHEDULED'),
((SELECT id FROM country WHERE name = 'Jordania'), (SELECT id FROM country WHERE name = 'Argentyna'), '2026-06-28 04:00:00', 'SCHEDULED');

COMMIT;
