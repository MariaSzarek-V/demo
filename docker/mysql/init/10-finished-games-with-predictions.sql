-- Add 9 finished games with predictions and calculated points
-- Using only countries that exist in the database

-- Game 7: Polska vs Anglia (2-1) - Date: 2025-03-21 20:45:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (49, 45, '2025-03-21 20:45:00', 2, 1, 'FINISHED', NULL);

SET @game7_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game7_id, 1, 2, 1, 1), (2, @game7_id, 1, 1, 0, 1), (3, @game7_id, 1, 0, 2, 1);

-- Game 8: Niemcy vs Holandia (2-2) - Date: 2025-03-25 18:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (17, 21, '2025-03-25 18:00:00', 2, 2, 'FINISHED', NULL);

SET @game8_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game8_id, 1, 2, 2, 1), (2, @game8_id, 1, 1, 1, 1), (3, @game8_id, 1, 3, 0, 1);

-- Game 9: Hiszpania vs Portugalia (3-1) - Date: 2025-04-02 21:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (29, 41, '2025-04-02 21:00:00', 3, 1, 'FINISHED', NULL);

SET @game9_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game9_id, 1, 2, 0, 1), (2, @game9_id, 1, 3, 1, 1), (3, @game9_id, 1, 1, 2, 1);

-- Game 10: Belgia vs Francja (1-2) - Date: 2025-04-15 19:45:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (25, 33, '2025-04-15 19:45:00', 1, 2, 'FINISHED', NULL);

SET @game10_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game10_id, 1, 0, 1, 1), (2, @game10_id, 1, 2, 1, 1), (3, @game10_id, 1, 1, 2, 1);

-- Game 11: Chorwacja vs Austria (0-0) - Date: 2025-05-10 17:30:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (46, 39, '2025-05-10 17:30:00', 0, 0, 'FINISHED', NULL);

SET @game11_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game11_id, 1, 0, 0, 1), (2, @game11_id, 1, 1, 1, 1), (3, @game11_id, 1, 2, 0, 1);

-- Game 12: Brazylia vs Argentyna (1-0) - Date: 2025-06-05 22:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (9, 37, '2025-06-05 22:00:00', 1, 0, 'FINISHED', NULL);

SET @game12_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game12_id, 1, 2, 1, 1), (2, @game12_id, 1, 1, 0, 1), (3, @game12_id, 1, 0, 1, 1);

-- Game 13: Norwegia vs Finlandia (3-2) - Date: 2025-06-20 19:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (36, 52, '2025-06-20 19:00:00', 3, 2, 'FINISHED', NULL);

SET @game13_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game13_id, 1, 3, 2, 1), (2, @game13_id, 1, 2, 1, 1), (3, @game13_id, 1, 1, 3, 1);

-- Game 14: Litwa vs Malta (2-0) - Date: 2025-07-10 18:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (50, 51, '2025-07-10 18:00:00', 2, 0, 'FINISHED', NULL);

SET @game14_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game14_id, 1, 1, 0, 1), (2, @game14_id, 1, 2, 0, 1), (3, @game14_id, 1, 0, 1, 1);

-- Game 15: Urugwaj vs Kolumbia (1-1) - Date: 2025-08-15 20:00:00
INSERT INTO game (home_country_id, away_country_id, game_date, home_score, away_score, game_status, group_id)
VALUES (32, 44, '2025-08-15 20:00:00', 1, 1, 'FINISHED', NULL);

SET @game15_id = LAST_INSERT_ID();

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
VALUES (1, @game15_id, 1, 1, 1, 1), (2, @game15_id, 1, 0, 0, 1), (3, @game15_id, 1, 2, 0, 1);
