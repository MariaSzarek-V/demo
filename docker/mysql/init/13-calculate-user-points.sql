-- Calculate and populate user_points for all finished games
-- This table stores the points earned by each user for each prediction

USE betsdb;

-- Insert points for all predictions of finished games
INSERT INTO user_points (user_id, prediction_id, league_id, points)
SELECT
    p.user_id,
    p.id as prediction_id,
    p.league_id,
    CASE
        -- Exact score: 3 points
        WHEN p.predicted_home_score = g.home_score
         AND p.predicted_away_score = g.away_score
        THEN 3

        -- Correct result (win/draw/loss): 1 point
        WHEN ((p.predicted_home_score - p.predicted_away_score) > 0 AND (g.home_score - g.away_score) > 0)  -- Both home win
          OR ((p.predicted_home_score - p.predicted_away_score) = 0 AND (g.home_score - g.away_score) = 0)  -- Both draw
          OR ((p.predicted_home_score - p.predicted_away_score) < 0 AND (g.home_score - g.away_score) < 0)  -- Both away win
        THEN 1

        -- No match: 0 points
        ELSE 0
    END as points
FROM prediction p
JOIN game g ON p.game_id = g.id
WHERE g.game_status = 'FINISHED'
  AND g.home_score IS NOT NULL
  AND g.away_score IS NOT NULL;

COMMIT;
