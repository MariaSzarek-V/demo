-- Calculate and insert user_points for new games (79, 81-88)
-- This script calculates points based on prediction accuracy

INSERT INTO user_points (user_id, prediction_id, league_id, points)
SELECT
    p.user_id,
    p.id as prediction_id,
    p.league_id,
    CASE
        -- Exact score = 3 points
        WHEN p.predicted_home_score = g.home_score AND p.predicted_away_score = g.away_score THEN 3
        -- Correct result (winner/draw) but wrong score = 1 point
        WHEN (p.predicted_home_score > p.predicted_away_score AND g.home_score > g.away_score)
          OR (p.predicted_home_score < p.predicted_away_score AND g.home_score < g.away_score)
          OR (p.predicted_home_score = p.predicted_away_score AND g.home_score = g.away_score) THEN 1
        -- Wrong prediction = 0 points
        ELSE 0
    END as points
FROM prediction p
JOIN game g ON p.game_id = g.id
WHERE g.id IN (79, 81, 82, 83, 84, 85, 86, 87, 88)
  AND g.game_status = 'FINISHED'
  AND NOT EXISTS (
      SELECT 1 FROM user_points up WHERE up.prediction_id = p.id
  );

-- Mark all predictions as calculated
UPDATE prediction
SET calculated = 1
WHERE game_id IN (79, 81, 82, 83, 84, 85, 86, 87, 88);
