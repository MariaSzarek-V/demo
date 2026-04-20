-- Add 9 more finished games to reach 15 total (games 73-78 already exist from 05-poland-matches.sql)
-- Using explicit IDs (79-87) so other scripts can reference them
-- Using only countries that exist in the database

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Game 79: Polska vs Anglia (2-1) - Date: 2025-11-21 20:45:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (79, 49, 45, '2025-11-21 20:45:00', 2, 1, 'FINISHED', NULL);

-- Game 80: Niemcy vs Holandia (2-2) - Date: 2025-11-25 18:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (80, 17, 21, '2025-11-25 18:00:00', 2, 2, 'FINISHED', NULL);

-- Game 81: Hiszpania vs Portugalia (3-1) - Date: 2025-12-02 21:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (81, 29, 41, '2025-12-02 21:00:00', 3, 1, 'FINISHED', NULL);

-- Game 82: Belgia vs Francja (1-2) - Date: 2025-12-15 19:45:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (82, 25, 33, '2025-12-15 19:45:00', 1, 2, 'FINISHED', NULL);

-- Game 83: Chorwacja vs Austria (0-0) - Date: 2026-01-10 17:30:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (83, 46, 39, '2026-01-10 17:30:00', 0, 0, 'FINISHED', NULL);

-- Game 84: Brazylia vs Argentyna (1-0) - Date: 2026-01-25 22:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (84, 9, 37, '2026-01-25 22:00:00', 1, 0, 'FINISHED', NULL);

-- Game 85: Norwegia vs Finlandia (3-2) - Date: 2026-02-10 19:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (85, 36, 52, '2026-02-10 19:00:00', 3, 2, 'FINISHED', NULL);

-- Game 86: Litwa vs Malta (2-0) - Date: 2026-03-01 18:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (86, 50, 51, '2026-03-01 18:00:00', 2, 0, 'FINISHED', NULL);

-- Game 87: Urugwaj vs Kolumbia (1-1) - Date: 2026-03-20 20:00:00
INSERT INTO game (id, home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (87, 32, 44, '2026-03-20 20:00:00', 1, 1, 'FINISHED', NULL);
