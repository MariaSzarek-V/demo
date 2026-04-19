-- Ustawienie kodowania UTF-8
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Ranking history dla ostatniego meczu (game_id=78)
INSERT INTO ranking_history (game_id, user_id, total_points, position, position_change) VALUES
(78, 1, 13, 1, 0),
(78, 3, 12, 2, 0),
(78, 10, 12, 3, 0),
(78, 4, 11, 4, 0),
(78, 8, 11, 5, 0),
(78, 9, 9, 6, 0),
(78, 6, 8, 7, 0),
(78, 7, 7, 8, 0),
(78, 2, 6, 9, 0),
(78, 5, 3, 10, 0),
(78, 11, 0, 11, 0);
