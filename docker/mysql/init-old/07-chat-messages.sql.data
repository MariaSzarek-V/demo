-- Ustawienie kodowania UTF-8
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Czyszczenie starych danych z czatu
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE chat_message_reactions;
TRUNCATE TABLE chat_message;
SET FOREIGN_KEY_CHECKS = 1;

-- 10 wiadomości na czacie od różnych użytkowników
INSERT INTO chat_message (text, created_at, user_id) VALUES
('Cześć wszystkim! Kto ogląda dzisiaj mecz? ⚽', '2026-04-05 18:00:00', 1),
('Będę oglądać w pubie z kolegami! 🍺', '2026-04-05 18:10:00', 3),
('Typuję 2-1 dla Polski! Co wy myślicie?', '2026-04-05 18:15:00', 4),
('Dzisiaj koniecznie muszę zobaczyć ten mecz!', '2026-04-05 18:25:00', 6),
('Mam nadzieję że Polska wygra dzisiaj! 🇵🇱', '2026-04-05 19:15:00', 1),
('Ktoś wie kto dzisiaj sędziuje?', '2026-04-05 19:20:00', 2),
('Polowa czasu 0-0, ale gramy nieźle!', '2026-04-05 19:50:00', 4),
('Obrona gra dziś świetnie!', '2026-04-05 20:15:00', 7),
('Świetny mecz! Bramka w 89 minucie! 🔥', '2026-04-05 21:45:00', 1),
('TAK! GOOOOL! Nie mogę uwierzyć! 🎉', '2026-04-05 21:50:00', 2);