-- Calculate ranking history for all finished games
-- Points system:
-- 3 points: exact score match
-- 1 point: correct result (win/draw/loss) but not exact score
-- 0 points: nothing correct
--
-- IMPORTANT: Predictions are shared across all leagues (one prediction per user/game),
-- but ranking_history is calculated separately for each league based on league membership.

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- Populate ranking_history for ALL LEAGUES
-- ==============================================
-- For each league, for each finished game, calculate points for each user's prediction
-- Users only appear in rankings for leagues they are members of (via user_league table)

INSERT INTO `ranking_history` (game_id, user_id, league_id, total_points, position, position_change)
SELECT
    g.id as game_id,
    ul.user_id as user_id,
    ul.league_id as league_id,
    -- Calculate cumulative points up to this game for this league
    (
        SELECT COALESCE(SUM(
            CASE
                -- Exact score: 3 points
                WHEN p2.predicted_home_score = g2.home_score
                     AND p2.predicted_away_score = g2.away_score
                THEN 3
                -- Correct result (win/draw/loss): 1 point
                WHEN (p2.predicted_home_score > p2.predicted_away_score AND g2.home_score > g2.away_score)
                     OR (p2.predicted_home_score = p2.predicted_away_score AND g2.home_score = g2.away_score)
                     OR (p2.predicted_home_score < p2.predicted_away_score AND g2.home_score < g2.away_score)
                THEN 1
                -- Nothing correct: 0 points
                ELSE 0
            END
        ), 0)
        FROM `prediction` p2
        JOIN `game` g2 ON p2.game_id = g2.id
        WHERE p2.user_id = ul.user_id
          AND g2.game_status = 'FINISHED'
          AND g2.id <= g.id
    ) as total_points,
    -- Calculate position (will be updated with ranking logic below)
    0 as position,
    0 as position_change
FROM `user_league` ul
CROSS JOIN `game` g
WHERE g.game_status = 'FINISHED'
  AND g.id BETWEEN 1 AND 36
  AND EXISTS (
      -- User must have a prediction for this game
      SELECT 1 FROM `prediction` p
      WHERE p.user_id = ul.user_id
        AND p.game_id = g.id
  )
ORDER BY ul.league_id, g.id, total_points DESC, ul.user_id;

-- ==============================================
-- Update positions for EACH LEAGUE separately
-- ==============================================
-- For each league, for each game, rank users by total_points (descending), breaking ties by user_id (ascending)
-- IMPORTANT: Position must reset to 1 for each new (league_id, game_id) combination

-- Process each league separately to ensure correct ranking
SET @leagues = (SELECT GROUP_CONCAT(DISTINCT id ORDER BY id) FROM `league` WHERE is_active = 1);

-- Liga 1: Pierwsza Liga
SET @prev_game_id = 0;
SET @rank = 0;
SET @prev_points = -1;
SET @actual_rank = 0;

UPDATE `ranking_history` rh
JOIN (
    SELECT
        rh2.game_id,
        rh2.user_id,
        rh2.league_id,
        rh2.total_points,
        -- Reset @actual_rank to 0 when game_id changes, then increment
        @actual_rank := IF(@prev_game_id != rh2.game_id, 1, @actual_rank + 1) as row_num,
        -- Assign rank: if game changed OR points changed, use current row_num, else keep previous rank (for ties)
        @rank := IF(@prev_game_id != rh2.game_id, 1, IF(@prev_points != rh2.total_points, @actual_rank, @rank)) as position,
        @prev_game_id := rh2.game_id,
        @prev_points := rh2.total_points
    FROM `ranking_history` rh2
    CROSS JOIN (SELECT @prev_game_id := 0, @rank := 0, @prev_points := -1, @actual_rank := 0) vars
    WHERE rh2.league_id = 1
    ORDER BY rh2.game_id, rh2.total_points DESC, rh2.user_id
) ranked ON rh.game_id = ranked.game_id AND rh.user_id = ranked.user_id AND rh.league_id = ranked.league_id
SET rh.position = ranked.position
WHERE rh.league_id = 1;

-- Liga 2: Liga VIP
SET @prev_game_id = 0;
SET @rank = 0;
SET @prev_points = -1;
SET @actual_rank = 0;

UPDATE `ranking_history` rh
JOIN (
    SELECT
        rh2.game_id,
        rh2.user_id,
        rh2.league_id,
        rh2.total_points,
        -- Reset @actual_rank to 0 when game_id changes, then increment
        @actual_rank := IF(@prev_game_id != rh2.game_id, 1, @actual_rank + 1) as row_num,
        -- Assign rank: if game changed OR points changed, use current row_num, else keep previous rank (for ties)
        @rank := IF(@prev_game_id != rh2.game_id, 1, IF(@prev_points != rh2.total_points, @actual_rank, @rank)) as position,
        @prev_game_id := rh2.game_id,
        @prev_points := rh2.total_points
    FROM `ranking_history` rh2
    CROSS JOIN (SELECT @prev_game_id := 0, @rank := 0, @prev_points := -1, @actual_rank := 0) vars
    WHERE rh2.league_id = 2
    ORDER BY rh2.game_id, rh2.total_points DESC, rh2.user_id
) ranked ON rh.game_id = ranked.game_id AND rh.user_id = ranked.user_id AND rh.league_id = ranked.league_id
SET rh.position = ranked.position
WHERE rh.league_id = 2;

-- ==============================================
-- Calculate position changes for ALL LEAGUES
-- ==============================================
-- Position change = previous position - current position (positive means moved up)

UPDATE `ranking_history` rh
JOIN (
    SELECT
        rh2.id,
        COALESCE((
            SELECT (prev.position - rh2.position)
            FROM `ranking_history` prev
            WHERE prev.user_id = rh2.user_id
              AND prev.league_id = rh2.league_id
              AND prev.game_id = (
                  SELECT MAX(g.id)
                  FROM `game` g
                  WHERE g.id < rh2.game_id
                    AND g.game_status = 'FINISHED'
              )
        ), 0) as calc_position_change
    FROM `ranking_history` rh2
) changes ON rh.id = changes.id
SET rh.position_change = changes.calc_position_change;

COMMIT;
