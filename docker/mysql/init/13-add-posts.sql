-- Add posts, comments and reactions for league 1 (Pierwsza Liga)
-- 5 posts
-- First 3 posts have 3 comments each
-- All posts and comments have reactions

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- POSTS for League 1
-- ==============================================

-- Post 1: admin
INSERT INTO `post` (id, title, content, created_at, user_id, league_id)
VALUES (1, 'Witamy w Pierwszej Lidze! ⚽',
'Witajcie wszystkim w naszej lidze typowania! Życzę powodzenia i dobrej zabawy. Niech wygra najlepszy!

Zasady są proste:
- 5 punktów za dokładny wynik
- 3 punkty za trafienie wyniku (wygrana/remis/przegrana)
- 1 punkt za trafienie różnicy bramek

Powodzenia! 🍀',
'2025-03-20 17:00:00', 1, 1);

-- Post 2: ania
INSERT INTO `post` (id, title, content, created_at, user_id, league_id)
VALUES (2, 'Kto typuje na Polskę? 🇵🇱',
'Jutro wielki mecz Polska - Litwa! Jestem ciekawa jakie są Wasze typy?

Ja obstawiam 2:1 dla Polski. Myślę, że będzie ciężko, ale damy radę!

Co Wy sądzicie?',
'2025-03-20 20:00:00', 2, 1);

-- Post 3: kuba
INSERT INTO `post` (id, title, content, created_at, user_id, league_id)
VALUES (3, 'Analiza wczorajszych meczów 📊',
'Wczoraj mieliśmy 6 meczów i różne wyniki. Sprawdźmy co się działo:

**Grupa G:**
- Polska - Litwa 1:0 ✅
- Malta - Finlandia 0:1 ✅

**Grupa H:**
- Cypr - San Marino 2:0 ✅
- Rumunia - Bośnia i Hercegowina 0:1 😮

**Grupa K:**
- Andora - Łotwa 0:1 ✅
- Anglia - Albania 2:0 ✅

Największą niespodzianką była przegrana Rumunii u siebie! Kto to trafił?',
'2025-03-22 10:30:00', 5, 1);

-- Post 4: magda
INSERT INTO `post` (id, title, content, created_at, user_id, league_id)
VALUES (4, 'Moje strategie typowania 🎯',
'Chciałam się podzielić swoją strategią:

1. Zawsze sprawdzam ostatnie 5 meczów obu drużyn
2. Patrzę na mecze wyjazdowe/domowe
3. Nie ryzykuję dużo przy niespodziewanych wynikach
4. Staram się być konserwatywna w typach

Dzięki temu jestem w TOP 10! 💪

A jak Wy typujecie?',
'2025-03-23 14:00:00', 7, 1);

-- Post 5: Piotr Nowak
INSERT INTO `post` (id, title, content, created_at, user_id, league_id)
VALUES (5, 'Gratulacje dla lidera! 🏆',
'Sprawdziłem ranking i jest naprawdę ostro!

Różnica między pierwszym a piątym miejscem to tylko kilka punktów. Każdy mecz się liczy!

Trzymajcie poziom, bo zamierzam was wszystkich pokonać w następnej kolejce! 😄

Powodzenia!',
'2025-03-24 18:00:00', 11, 1);

-- ==============================================
-- COMMENTS for Posts 1, 2, 3 (3 comments each)
-- ==============================================

-- Comments for Post 1 (admin's welcome)
INSERT INTO `post_comment` (id, text, created_at, user_id, post_id)
VALUES
(1, 'Dzięki za organizację! Super że mamy taką ligę 👍', '2025-03-20 17:30:00', 2, 1),
(2, 'Jestem gotowy do walki! Niech wygra najlepszy! ⚽', '2025-03-20 18:00:00', 5, 1),
(3, 'Będzie ciekawie, powodzenia wszystkim! 🍀', '2025-03-20 19:00:00', 7, 1);

-- Comments for Post 2 (ania's Poland question)
INSERT INTO `post_comment` (id, text, created_at, user_id, post_id)
VALUES
(4, 'Ja też wierzę w Polskę! Ale daję 1:0, będzie defensywnie 🇵🇱', '2025-03-20 20:30:00', 4, 2),
(5, 'Typuję 2:0 dla nas! Musimy wygrać wysoko! 💪', '2025-03-20 21:00:00', 6, 2),
(6, 'Ostrożnie, Litwa potrafi zaskoczyć. Daję 1:1 remis', '2025-03-20 21:30:00', 9, 2);

-- Comments for Post 3 (kuba's analysis)
INSERT INTO `post_comment` (id, text, created_at, user_id, post_id)
VALUES
(7, 'Ja trafiłem Rumunię! Wiedziałem że Bośnia jest silna 😎', '2025-03-22 11:00:00', 3, 3),
(8, 'Super analiza! Też byłem zaskoczony wynikiem Rumunii', '2025-03-22 12:00:00', 8, 3),
(9, 'Dzięki za podsumowanie! Czekam na więcej takich postów 📊', '2025-03-22 13:00:00', 11, 3);

-- ==============================================
-- REACTIONS for Posts
-- ==============================================

-- Reactions for Post 1 (admin's welcome) - 5 reactions
INSERT INTO `post_reactions` (post_id, emoji, username) VALUES
(1, '👍', 'ania'),
(1, '⚽', 'kuba'),
(1, '🔥', 'magda'),
(1, '💯', 'janek'),
(1, '🎉', 'ola');

-- Reactions for Post 2 (ania's Poland question) - 6 reactions
INSERT INTO `post_reactions` (post_id, emoji, username) VALUES
(2, '🇵🇱', 'admin'),
(2, '🇵🇱', 'kuba'),
(2, '💪', 'michal'),
(2, '⚽', 'marcin'),
(2, '🔥', 'lukasz'),
(2, '👍', 'pawel');

-- Reactions for Post 3 (kuba's analysis) - 7 reactions
INSERT INTO `post_reactions` (post_id, emoji, username) VALUES
(3, '👍', 'admin'),
(3, '👍', 'ania'),
(3, '📊', 'magda'),
(3, '💯', 'ola'),
(3, '🔥', 'janek'),
(3, '👏', 'michal'),
(3, '🎯', 'marcin');

-- Reactions for Post 4 (magda's strategy) - 4 reactions
INSERT INTO `post_reactions` (post_id, emoji, username) VALUES
(4, '👍', 'admin'),
(4, '🎯', 'kuba'),
(4, '💡', 'ania'),
(4, '🔥', 'pawel');

-- Reactions for Post 5 (Piotr Nowak's congratulations) - 5 reactions
INSERT INTO `post_reactions` (post_id, emoji, username) VALUES
(5, '🏆', 'admin'),
(5, '👏', 'ania'),
(5, '💪', 'kuba'),
(5, '🔥', 'magda'),
(5, '⚽', 'lukasz');

-- ==============================================
-- REACTIONS for Comments
-- ==============================================

-- Reactions for comments on Post 1
INSERT INTO `post_comment_reactions` (post_comment_id, emoji, username) VALUES
(1, '👍', 'admin'),
(1, '💯', 'kuba'),
(2, '⚽', 'ania'),
(2, '🔥', 'magda'),
(3, '🍀', 'admin'),
(3, '👍', 'kuba');

-- Reactions for comments on Post 2
INSERT INTO `post_comment_reactions` (post_comment_id, emoji, username) VALUES
(4, '🇵🇱', 'ania'),
(4, '👍', 'kuba'),
(5, '💪', 'ania'),
(5, '🔥', 'magda'),
(6, '🤔', 'ania'),
(6, '👍', 'kuba');

-- Reactions for comments on Post 3
INSERT INTO `post_comment_reactions` (post_comment_id, emoji, username) VALUES
(7, '🎯', 'kuba'),
(7, '👏', 'admin'),
(8, '👍', 'kuba'),
(8, '💯', 'ania'),
(9, '📊', 'kuba'),
(9, '🔥', 'admin');

COMMIT;
