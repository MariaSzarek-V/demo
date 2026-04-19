-- Create two new leagues: Liga VIP and Pierwsza Liga
-- Liga VIP: users with single-word names (first names only) + Admin, Ania, Ewa Bąk, Mia Pęk, Ada Dąb
-- Pierwsza Liga: users with two-word names (first + last name) + Admin, Ania, Ewa Bąk, Mia Pęk, Ada Dąb

-- Insert Liga VIP (id = 2)
INSERT INTO league (id, name, description, is_active)
VALUES (2, 'Liga VIP', 'Liga VIP dla najlepszych graczy', 1)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Insert Pierwsza Liga (id = 3)
INSERT INTO league (id, name, description, is_active)
VALUES (3, 'Pierwsza Liga', 'Liga dla wszystkich graczy z pełnym imieniem i nazwiskiem', 1)
ON DUPLICATE KEY UPDATE name = VALUES(name), description = VALUES(description);

-- Clear existing assignments for these leagues (but keep Liga Główna assignments)
DELETE FROM user_league WHERE league_id IN (2, 3);

-- Liga VIP members: single-word usernames + specific users
-- Single-word usernames: admin, ania, janek, kuba, lukasz, magda, marcin, michal, ola, pawel, piotr
-- Plus specific users: Admin (1), Ania (3), Ewa Bąk (14), Mia Pęk (18), Ada Dąb (15)

INSERT INTO user_league (user_id, league_id)
SELECT id, 2 FROM user
WHERE
  -- Single-word names (no space in username)
  username NOT LIKE '% %'
  OR
  -- Specific users to include regardless
  id IN (1, 3, 14, 15, 18)
ON DUPLICATE KEY UPDATE league_id = VALUES(league_id);

-- Pierwsza Liga members: two-word usernames (name + surname) + specific users
-- Two-word usernames: all users with space in name
-- Plus specific users: Admin (1), Ania (3), Ewa Bąk (14), Mia Pęk (18), Ada Dąb (15)

INSERT INTO user_league (user_id, league_id)
SELECT id, 3 FROM user
WHERE
  -- Two-word names (has space in username)
  username LIKE '% %'
  OR
  -- Specific users to include regardless
  id IN (1, 3, 14, 15, 18)
ON DUPLICATE KEY UPDATE league_id = VALUES(league_id);

-- Verify the assignments
SELECT
  l.name AS league_name,
  COUNT(ul.user_id) AS user_count
FROM league l
LEFT JOIN user_league ul ON l.id = ul.league_id
WHERE l.id IN (2, 3)
GROUP BY l.id, l.name
ORDER BY l.id;
