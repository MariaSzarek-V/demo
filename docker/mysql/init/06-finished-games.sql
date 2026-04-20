-- Finished games with results
-- Qualifiers matches from March and June 2025

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- KWALIFIKACJE - PIĄTEK 21.03.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Polska'), (SELECT id FROM country WHERE name = 'Litwa'), 1, 0, '2025-03-21 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Malta'), (SELECT id FROM country WHERE name = 'Finlandia'), 0, 1, '2025-03-21 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Cypr'), (SELECT id FROM country WHERE name = 'San Marino'), 2, 0, '2025-03-21 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Rumunia'), (SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), 0, 1, '2025-03-21 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Andora'), (SELECT id FROM country WHERE name = 'Łotwa'), 0, 1, '2025-03-21 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Albania'), 2, 0, '2025-03-21 20:45:00', 'FINISHED');

-- ==============================================
-- KWALIFIKACJE - SOBOTA 22.03.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Mołdawia'), (SELECT id FROM country WHERE name = 'Norwegia'), 0, 5, '2025-03-22 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Izrael'), (SELECT id FROM country WHERE name = 'Estonia'), 2, 1, '2025-03-22 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Liechtenstein'), (SELECT id FROM country WHERE name = 'Macedonia Północna'), 0, 3, '2025-03-22 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Walia'), (SELECT id FROM country WHERE name = 'Kazachstan'), 3, 1, '2025-03-22 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Czarnogóra'), (SELECT id FROM country WHERE name = 'Gibraltar'), 3, 1, '2025-03-22 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Czechy'), (SELECT id FROM country WHERE name = 'Wyspy Owcze'), 2, 1, '2025-03-22 18:00:00', 'FINISHED');

-- ==============================================
-- KWALIFIKACJE - PONIEDZIAŁEK 24.03.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Polska'), (SELECT id FROM country WHERE name = 'Malta'), 2, 0, '2025-03-24 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Litwa'), (SELECT id FROM country WHERE name = 'Finlandia'), 2, 2, '2025-03-24 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'San Marino'), (SELECT id FROM country WHERE name = 'Rumunia'), 1, 5, '2025-03-24 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), (SELECT id FROM country WHERE name = 'Cypr'), 2, 1, '2025-03-24 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Albania'), (SELECT id FROM country WHERE name = 'Andora'), 3, 0, '2025-03-24 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Anglia'), (SELECT id FROM country WHERE name = 'Łotwa'), 3, 0, '2025-03-24 20:45:00', 'FINISHED');

-- ==============================================
-- KWALIFIKACJE - WTOREK 25.03.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Mołdawia'), (SELECT id FROM country WHERE name = 'Estonia'), 2, 3, '2025-03-25 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Izrael'), (SELECT id FROM country WHERE name = 'Norwegia'), 2, 4, '2025-03-25 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Liechtenstein'), (SELECT id FROM country WHERE name = 'Kazachstan'), 0, 2, '2025-03-25 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Macedonia Północna'), (SELECT id FROM country WHERE name = 'Walia'), 1, 1, '2025-03-25 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Czarnogóra'), (SELECT id FROM country WHERE name = 'Wyspy Owcze'), 1, 0, '2025-03-25 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Gibraltar'), (SELECT id FROM country WHERE name = 'Czechy'), 0, 4, '2025-03-25 18:00:00', 'FINISHED');

-- ==============================================
-- KWALIFIKACJE - PIĄTEK 6.06.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Estonia'), (SELECT id FROM country WHERE name = 'Izrael'), 1, 3, '2025-06-06 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Norwegia'), (SELECT id FROM country WHERE name = 'Włochy'), 3, 0, '2025-06-06 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Walia'), (SELECT id FROM country WHERE name = 'Liechtenstein'), 3, 0, '2025-06-06 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Macedonia Północna'), (SELECT id FROM country WHERE name = 'Belgia'), 0, 0, '2025-06-06 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Czechy'), (SELECT id FROM country WHERE name = 'Czarnogóra'), 2, 0, '2025-06-06 20:45:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Gibraltar'), (SELECT id FROM country WHERE name = 'Chorwacja'), 0, 7, '2025-06-06 20:45:00', 'FINISHED');

-- ==============================================
-- KWALIFIKACJE - SOBOTA 7.06.2025
-- ==============================================
INSERT INTO `game` (home_country_id, away_country_id, home_score, away_score, game_date, game_status) VALUES
((SELECT id FROM country WHERE name = 'Malta'), (SELECT id FROM country WHERE name = 'Litwa'), 0, 0, '2025-06-07 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Finlandia'), (SELECT id FROM country WHERE name = 'Holandia'), 0, 2, '2025-06-07 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Bośnia i Hercegowina'), (SELECT id FROM country WHERE name = 'San Marino'), 1, 0, '2025-06-07 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Austria'), (SELECT id FROM country WHERE name = 'Rumunia'), 2, 1, '2025-06-07 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Andora'), (SELECT id FROM country WHERE name = 'Anglia'), 0, 1, '2025-06-07 18:00:00', 'FINISHED'),
((SELECT id FROM country WHERE name = 'Albania'), (SELECT id FROM country WHERE name = 'Serbia'), 0, 0, '2025-06-07 18:00:00', 'FINISHED');

COMMIT;
