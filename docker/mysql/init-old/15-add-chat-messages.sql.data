-- Dodaj wiadomości na czacie dla każdej ligi z reakcjami od użytkowników
-- Liga Główna (id=1), Liga VIP (id=2), Pierwsza Liga (id=3)

-- ============================================
-- LIGA GŁÓWNA (id=1) - 5 wiadomości
-- ============================================

-- Wiadomość 1: admin (id=1)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Witam wszystkich! Niech wygra najlepszy 🏆', '2026-04-18 10:30:00', 1, 1, NULL, NULL);

SET @msg1_league1 = LAST_INSERT_ID();

-- Reakcje do wiadomości 1
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg1_league1, '👍', 'ania'),
(@msg1_league1, '👍', 'ola'),
(@msg1_league1, '🔥', 'kuba'),
(@msg1_league1, '💪', 'magda'),
(@msg1_league1, '🎉', 'pawel');

-- Wiadomość 2: kuba (id=5)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Kto widział wczorajszy mecz? Niesamowite! ⚽', '2026-04-18 14:15:00', 5, 1, NULL, NULL);

SET @msg2_league1 = LAST_INSERT_ID();

-- Reakcje do wiadomości 2
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg2_league1, '⚽', 'admin'),
(@msg2_league1, '😍', 'ania'),
(@msg2_league1, '👏', 'michal'),
(@msg2_league1, '🔥', 'lukasz');

-- Wiadomość 3: magda (id=7)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Moje typy na dziś są bardzo ryzykowne 😅', '2026-04-18 16:45:00', 7, 1, NULL, NULL);

SET @msg3_league1 = LAST_INSERT_ID();

-- Reakcje do wiadomości 3
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg3_league1, '😂', 'kuba'),
(@msg3_league1, '🎲', 'ania'),
(@msg3_league1, '😱', 'ola'),
(@msg3_league1, '🤞', 'admin'),
(@msg3_league1, '💯', 'pawel'),
(@msg3_league1, '🍀', 'marcin');

-- Wiadomość 4: ania (id=3)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Gratuluję dla wszystkich na podium! 🥇🥈🥉', '2026-04-18 18:20:00', 3, 1, NULL, NULL);

SET @msg4_league1 = LAST_INSERT_ID();

-- Reakcje do wiadomości 4
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg4_league1, '🏆', 'admin'),
(@msg4_league1, '👏', 'magda'),
(@msg4_league1, '🎊', 'kuba'),
(@msg4_league1, '💪', 'michal');

-- Wiadomość 5: pawel (id=10)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Świetna atmosfera w naszej lidze! Powodzenia wszystkim 💪', '2026-04-18 20:00:00', 10, 1, NULL, NULL);

SET @msg5_league1 = LAST_INSERT_ID();

-- Reakcje do wiadomości 5
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg5_league1, '❤️', 'ania'),
(@msg5_league1, '👍', 'ola'),
(@msg5_league1, '🔥', 'admin'),
(@msg5_league1, '💯', 'kuba'),
(@msg5_league1, '🙌', 'magda');

-- ============================================
-- LIGA VIP (id=2) - 5 wiadomości
-- ============================================

-- Wiadomość 1: admin (id=1)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Witajcie w Lidze VIP! Tu gra się o wszystko! 🌟', '2026-04-18 09:00:00', 1, 2, NULL, NULL);

SET @msg1_league2 = LAST_INSERT_ID();

-- Reakcje do wiadomości 1
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg1_league2, '🔥', 'ania'),
(@msg1_league2, '💎', 'kuba'),
(@msg1_league2, '⭐', 'magda'),
(@msg1_league2, '👑', 'pawel');

-- Wiadomość 2: ania (id=3)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Poziom tutaj jest naprawdę wysoki! Trzeba się starać 💪', '2026-04-18 11:30:00', 3, 2, NULL, NULL);

SET @msg2_league2 = LAST_INSERT_ID();

-- Reakcje do wiadomości 2
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg2_league2, '💯', 'admin'),
(@msg2_league2, '🎯', 'kuba'),
(@msg2_league2, '🔥', 'michal');

-- Wiadomość 3: kuba (id=5)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Kto ma pewniaki na dziś? Dzielmy się wiedzą 🧠', '2026-04-18 13:45:00', 5, 2, NULL, NULL);

SET @msg3_league2 = LAST_INSERT_ID();

-- Reakcje do wiadomości 3
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg3_league2, '🤔', 'admin'),
(@msg3_league2, '💡', 'ania'),
(@msg3_league2, '📊', 'magda'),
(@msg3_league2, '🎲', 'pawel'),
(@msg3_league2, '🤝', 'marcin');

-- Wiadomość 4: magda (id=7)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Walka o pierwsze miejsce będzie zacięta! 🥊', '2026-04-18 17:10:00', 7, 2, NULL, NULL);

SET @msg4_league2 = LAST_INSERT_ID();

-- Reakcje do wiadomości 4
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg4_league2, '💪', 'admin'),
(@msg4_league2, '🔥', 'ania'),
(@msg4_league2, '⚔️', 'kuba'),
(@msg4_league2, '🏆', 'michal');

-- Wiadomość 5: pawel (id=10)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Elita typuje! Brawo za poziom 👏', '2026-04-18 19:30:00', 10, 2, NULL, NULL);

SET @msg5_league2 = LAST_INSERT_ID();

-- Reakcje do wiadomości 5
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg5_league2, '🌟', 'admin'),
(@msg5_league2, '👍', 'ania'),
(@msg5_league2, '💎', 'kuba'),
(@msg5_league2, '🎖️', 'magda');

-- ============================================
-- PIERWSZA LIGA (id=3) - 5 wiadomości
-- ============================================

-- Wiadomość 1: admin (id=1)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Witam w Pierwszej Lidze! Damy radę! ⚽', '2026-04-18 08:30:00', 1, 3, NULL, NULL);

SET @msg1_league3 = LAST_INSERT_ID();

-- Reakcje do wiadomości 1
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg1_league3, '👋', 'ania'),
(@msg1_league3, '💪', 'Jan Lis'),
(@msg1_league3, '🎉', 'Ola Kot'),
(@msg1_league3, '⚽', 'Nina Żuk');

-- Wiadomość 2: Jan Lis (id=13)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Dzisiaj będzie trudna kolejka do typowania 🤔', '2026-04-18 12:00:00', 13, 3, NULL, NULL);

SET @msg2_league3 = LAST_INSERT_ID();

-- Reakcje do wiadomości 2
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg2_league3, '😅', 'admin'),
(@msg2_league3, '🎯', 'ania'),
(@msg2_league3, '💭', 'Ola Kot'),
(@msg2_league3, '📊', 'Magdalena Kowalska'),
(@msg2_league3, '🤷', 'Nina Żuk');

-- Wiadomość 3: Ola Kot (id=12)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Moje typy na weekend są już gotowe! Oby trafione 🍀', '2026-04-18 15:20:00', 12, 3, NULL, NULL);

SET @msg3_league3 = LAST_INSERT_ID();

-- Reakcje do wiadomości 3
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg3_league3, '🤞', 'admin'),
(@msg3_league3, '🍀', 'ania'),
(@msg3_league3, '💯', 'Jan Lis'),
(@msg3_league3, '🎲', 'Maja Szyk');

-- Wiadomość 4: Magdalena Kowalska (id=22)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Kto jest na czele? Zobaczymy po weekendzie! 📈', '2026-04-18 16:50:00', 22, 3, NULL, NULL);

SET @msg4_league3 = LAST_INSERT_ID();

-- Reakcje do wiadomości 4
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg4_league3, '👀', 'admin'),
(@msg4_league3, '📊', 'ania'),
(@msg4_league3, '🏆', 'Jan Lis'),
(@msg4_league3, '⚡', 'Ola Kot'),
(@msg4_league3, '🔥', 'Nina Żuk');

-- Wiadomość 5: Nina Żuk (id=21)
INSERT INTO chat_message (text, created_at, user_id, league_id, parent_message_id, quoted_message_id)
VALUES ('Świetna zabawa z typowaniem! Powodzenia wszystkim! 🎊', '2026-04-18 18:40:00', 21, 3, NULL, NULL);

SET @msg5_league3 = LAST_INSERT_ID();

-- Reakcje do wiadomości 5
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
(@msg5_league3, '❤️', 'admin'),
(@msg5_league3, '🙏', 'ania'),
(@msg5_league3, '🎉', 'Jan Lis'),
(@msg5_league3, '💪', 'Ola Kot'),
(@msg5_league3, '🌟', 'Magdalena Kowalska'),
(@msg5_league3, '👏', 'Maja Szyk');

-- Podsumowanie
SELECT
    l.name AS Liga,
    COUNT(DISTINCT cm.id) AS 'Liczba wiadomości',
    COUNT(cmr.id) AS 'Liczba reakcji'
FROM league l
LEFT JOIN chat_message cm ON l.id = cm.league_id
LEFT JOIN chat_message_reactions cmr ON cm.id = cmr.chat_message_id
WHERE l.id IN (1, 2, 3)
GROUP BY l.id, l.name
ORDER BY l.id;
