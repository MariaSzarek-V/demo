-- Generate realistic predictions based on actual game results
-- Users are grouped by skill level which affects their prediction accuracy

USE betsdb;

-- Delete any existing predictions
DELETE FROM prediction;

-- Strategy: For each user-game pair, determine prediction type based on deterministic hash
-- prediction_type = (user_id * 100 + game_id) MOD 100
-- Expert users (1-5): 0-24 = exact (25%), 25-69 = correct result (45%), 70-99 = wrong (30%)
-- Good users (6-15): 0-14 = exact (15%), 15-59 = correct result (45%), 60-99 = wrong (40%)
-- Average users (16-30): 0-9 = exact (10%), 10-49 = correct result (40%), 50-99 = wrong (50%)

INSERT INTO prediction (user_id, game_id, league_id, predicted_home_score, predicted_away_score, calculated)
SELECT
    u.id as user_id,
    g.id as game_id,
    1 as league_id,
    -- Predicted home score
    COALESCE(CASE
        -- EXACT SCORE PREDICTION (3 points)
        WHEN (
            (u.id BETWEEN 1 AND 5 AND (u.id * 73 + g.id * 17) MOD 100 < 25) OR
            (u.id BETWEEN 6 AND 15 AND (u.id * 73 + g.id * 17) MOD 100 < 15) OR
            (u.id BETWEEN 16 AND 30 AND (u.id * 73 + g.id * 17) MOD 100 < 10)
        ) THEN g.home_score

        -- CORRECT RESULT PREDICTION (1 point) - same winner/draw but wrong score
        WHEN (
            (u.id BETWEEN 1 AND 5 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 25 AND 69) OR
            (u.id BETWEEN 6 AND 15 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 15 AND 59) OR
            (u.id BETWEEN 16 AND 30 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 10 AND 49)
        ) THEN
            CASE
                -- Home win: predict higher home score
                WHEN g.home_score > g.away_score THEN
                    CASE
                        WHEN g.home_score >= 2 THEN g.home_score - 1
                        ELSE g.home_score + 1
                    END
                -- Away win: predict lower home score
                WHEN g.home_score < g.away_score THEN
                    CASE
                        WHEN g.home_score >= 1 THEN g.home_score - 1
                        ELSE g.home_score
                    END
                -- Draw: keep score but vary it slightly
                ELSE g.home_score
            END

        -- WRONG PREDICTION (0 points) - completely off
        ELSE
            CASE (g.id + u.id) MOD 4
                WHEN 0 THEN 0
                WHEN 1 THEN 1
                WHEN 2 THEN 2
                ELSE 3
            END
    END, 0) as predicted_home_score,

    -- Predicted away score
    COALESCE(CASE
        -- EXACT SCORE PREDICTION (3 points)
        WHEN (
            (u.id BETWEEN 1 AND 5 AND (u.id * 73 + g.id * 17) MOD 100 < 25) OR
            (u.id BETWEEN 6 AND 15 AND (u.id * 73 + g.id * 17) MOD 100 < 15) OR
            (u.id BETWEEN 16 AND 30 AND (u.id * 73 + g.id * 17) MOD 100 < 10)
        ) THEN g.away_score

        -- CORRECT RESULT PREDICTION (1 point)
        WHEN (
            (u.id BETWEEN 1 AND 5 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 25 AND 69) OR
            (u.id BETWEEN 6 AND 15 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 15 AND 59) OR
            (u.id BETWEEN 16 AND 30 AND (u.id * 73 + g.id * 17) MOD 100 BETWEEN 10 AND 49)
        ) THEN
            CASE
                -- Home win: predict lower away score
                WHEN g.home_score > g.away_score THEN
                    CASE
                        WHEN g.away_score >= 1 THEN g.away_score - 1
                        ELSE g.away_score
                    END
                -- Away win: predict higher away score
                WHEN g.home_score < g.away_score THEN
                    CASE
                        WHEN g.away_score >= 2 THEN g.away_score - 1
                        ELSE g.away_score + 1
                    END
                -- Draw: keep score
                ELSE g.away_score
            END

        -- WRONG PREDICTION (0 points)
        ELSE
            CASE (g.id * 2 + u.id) MOD 4
                WHEN 0 THEN 0
                WHEN 1 THEN 1
                WHEN 2 THEN 2
                ELSE 3
            END
    END, 0) as predicted_away_score,

    CASE WHEN g.id <= 36 THEN TRUE ELSE FALSE END as calculated
FROM user u
CROSS JOIN game g
WHERE u.id BETWEEN 1 AND 30
  AND g.id BETWEEN 1 AND 46;

COMMIT;
