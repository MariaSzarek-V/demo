-- Add chat messages for league 1 (Pierwsza Liga)
-- 5 messages with reactions
-- 1 message with quote

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- ==============================================
-- CHAT MESSAGES for League 1
-- ==============================================

-- Message 1: admin
INSERT INTO `chat_message` (id, text, created_at, user_id, league_id)
VALUES (1, 'Witam wszystkich w lidze! Powodzenia w typowaniu! ⚽', '2025-03-20 18:00:00', 1, 1);

-- Message 2: ania
INSERT INTO `chat_message` (id, text, created_at, user_id, league_id)
VALUES (2, 'Cześć! Myślicie, że Polska wygra z Litwą jutro?', '2025-03-20 19:30:00', 2, 1);

-- Message 3: kuba
INSERT INTO `chat_message` (id, text, created_at, user_id, league_id)
VALUES (3, 'Oczywiście! Wytypowałem 2:0 dla Polski 🇵🇱', '2025-03-20 20:15:00', 5, 1);

-- Message 4: magda - QUOTING message 2
INSERT INTO `chat_message` (id, text, created_at, user_id, league_id, quoted_message_id)
VALUES (4, 'Tak! Polska musi wygrać ten mecz. Jestem pewna że zdobędziemy 3 punkty!', '2025-03-21 10:00:00', 7, 1, 2);

-- Message 5: Piotr Nowak
INSERT INTO `chat_message` (id, text, created_at, user_id, league_id)
VALUES (5, 'Gratulacje dla wszystkich, kto trafił wczorajsze mecze! 🎉', '2025-03-22 09:00:00', 11, 1);

-- ==============================================
-- REACTIONS for chat messages
-- Each message has 3 reactions from different users
-- ==============================================

-- Reactions for Message 1 (admin's welcome message)
INSERT INTO `chat_message_reactions` (chat_message_id, emoji, username) VALUES
(1, '👍', 'ania'),
(1, '⚽', 'kuba'),
(1, '🔥', 'magda');

-- Reactions for Message 2 (ania's question)
INSERT INTO `chat_message_reactions` (chat_message_id, emoji, username) VALUES
(2, '🤔', 'admin'),
(2, '👍', 'janek'),
(2, '🇵🇱', 'lukasz');

-- Reactions for Message 3 (kuba's prediction)
INSERT INTO `chat_message_reactions` (chat_message_id, emoji, username) VALUES
(3, '💪', 'ania'),
(3, '🔥', 'marcin'),
(3, '⚽', 'pawel');

-- Reactions for Message 4 (magda's quoted reply)
INSERT INTO `chat_message_reactions` (chat_message_id, emoji, username) VALUES
(4, '👍', 'admin'),
(4, '🇵🇱', 'ola'),
(4, '⚽', 'michal');

-- Reactions for Message 5 (Piotr Nowak's congratulations)
INSERT INTO `chat_message_reactions` (chat_message_id, emoji, username) VALUES
(5, '🎉', 'ania'),
(5, '👏', 'kuba'),
(5, '💯', 'magda');

COMMIT;
