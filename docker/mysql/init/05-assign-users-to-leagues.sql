-- Assign users to leagues
-- All users should be members of at least one league

USE betsdb;

-- Add all 30 users to "Pierwsza Liga" (league_id = 1)
INSERT INTO `user_league` (user_id, league_id, joined_at)
SELECT
    u.id,
    1 as league_id,
    NOW() - INTERVAL FLOOR(RAND() * 30) DAY as joined_at
FROM `user` u
WHERE u.id BETWEEN 1 AND 30;

-- Add first 10 users to "Liga VIP" (league_id = 2)
-- These are admin and first users who will compete in VIP league
-- (Ranking will be calculated later based on their predictions)
INSERT INTO `user_league` (user_id, league_id, joined_at)
SELECT
    u.id,
    2 as league_id,
    NOW() - INTERVAL FLOOR(RAND() * 15) DAY as joined_at
FROM `user` u
WHERE u.id BETWEEN 1 AND 10;

COMMIT;
