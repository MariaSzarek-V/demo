-- Czyszczenie starych danych z czatu
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE chat_message_reactions;
TRUNCATE TABLE chat_message;
SET FOREIGN_KEY_CHECKS = 1;

-- Wiadomości na czacie (po 3 dla każdego użytkownika)
INSERT INTO chat_message (text, created_at, user_id, parent_message_id, quoted_message_id) VALUES
-- admin (id=1)
('Cześć wszystkim! Kto ogląda dzisiaj mecz? ⚽', '2026-04-05 18:00:00', 1, NULL, NULL),
('Mam nadzieję że Polska wygra dzisiaj! 🇵🇱', '2026-04-05 19:15:00', 1, NULL, NULL),
('Świetny mecz! Bramka w 89 minucie! 🔥', '2026-04-05 21:45:00', 1, NULL, NULL),

-- ola (id=2)
('Oglądam! Mam nadzieję na dobrą grę 😊', '2026-04-05 18:05:00', 2, NULL, NULL),
('Ktoś wie kto dzisiaj sędziuje?', '2026-04-05 19:20:00', 2, NULL, NULL),
('TAK! GOOOOL! Nie mogę uwierzyć! 🎉', '2026-04-05 21:50:00', 2, NULL, NULL),

-- ania (id=3)
('Będę oglądać w pubie z kolegami! 🍺', '2026-04-05 18:10:00', 3, NULL, NULL),
('Atmosfera w pubie niesamowita! Wszyscy kibicują! 📣', '2026-04-05 20:30:00', 3, NULL, NULL),
('Najlepszy wieczór! Polska wygrała! ❤️', '2026-04-05 22:00:00', 3, NULL, NULL),

-- janek (id=4)
('Typuję 2-1 dla Polski! Co wy myślicie?', '2026-04-05 18:15:00', 4, NULL, NULL),
('Polowa czasu 0-0, ale gramy nieźle!', '2026-04-05 19:50:00', 4, NULL, NULL),
('Moje typowanie było prawie dobre! 2-0 dla nas! 👍', '2026-04-05 22:05:00', 4, NULL, NULL),

-- kuba (id=5)
('Kto gra w podstawowym składzie?', '2026-04-05 18:20:00', 5, NULL, NULL),
('Lewandowski w ataku, to dobrze! 💪', '2026-04-05 18:45:00', 5, NULL, NULL),
('Zasłużone zwycięstwo! Kapitalna akcja!', '2026-04-05 22:10:00', 5, NULL, NULL),

-- michal (id=6)
('Oglądam na telewizorze 4K, jakość rewelacyjna! 📺', '2026-04-05 18:25:00', 6, NULL, NULL),
('Pierwsza połowa wyrównana, ciekawie się zapowiada', '2026-04-05 19:55:00', 6, NULL, NULL),
('Wspaniały finisz! To był thriller! 🎬', '2026-04-05 22:15:00', 6, NULL, NULL),

-- magda (id=7)
('Dzieci śpią, mogę w końcu spokojnie oglądać! 😅', '2026-04-05 18:30:00', 7, NULL, NULL),
('Stres! Karny dla Polski! 😰', '2026-04-05 20:15:00', 7, NULL, NULL),
('Nie wierzę co się stało! Heroiczna wygrana! 🏆', '2026-04-05 22:20:00', 7, NULL, NULL),

-- marcin (id=8)
('Premier League czy reprezentacja? Trudny wybór 😄', '2026-04-05 18:35:00', 8, NULL, NULL),
('Reprezentacja! Emocje niesamowite! 🔴⚪', '2026-04-05 20:00:00', 8, NULL, NULL),
('To był mecz roku! Pamiętny wieczór! 🌟', '2026-04-05 22:25:00', 8, NULL, NULL),

-- lukasz (id=9)
('Lewandowski to legenda! Mam jego koszulkę! 👕', '2026-04-05 18:40:00', 9, NULL, NULL),
('Zielinski świetnie dziś gra! Kontroluje środek pola! ⚡', '2026-04-05 20:05:00', 9, NULL, NULL),
('POLSKA! POLSKA! POLSKA! 🇵🇱🇵🇱🇵🇱', '2026-04-05 22:30:00', 9, NULL, NULL),

-- pawel (id=10)
('Ktoś ma link do statystyk meczu?', '2026-04-05 18:45:00', 10, NULL, NULL),
('65% posiadania piłki dla Polski! Dominujemy! 📊', '2026-04-05 20:10:00', 10, NULL, NULL),
('Statystyki potwierdzają - zasłużone zwycięstwo! 💯', '2026-04-05 22:35:00', 10, NULL, NULL),

-- piotr (id=11)
('Gram w FIFA podczas przerwy 😂🎮', '2026-04-05 19:00:00', 11, NULL, NULL),
('Druga połowa ruszała! Wracam przed TV!', '2026-04-05 20:20:00', 11, NULL, NULL),
('Lepsze niż gra komputerowa! Real life jest najlepszy! 🎯', '2026-04-05 22:40:00', 11, NULL, NULL);

-- Reakcje do wiadomości (około 30% wiadomości ma reakcje od kilku osób)
INSERT INTO chat_message_reactions (chat_message_id, emoji, username) VALUES
-- Wiadomość 1 (admin - Kto ogląda) - 4 osoby
(1, '👍', 'ania'),
(1, '⚽', 'janek'),
(1, '🔥', 'kuba'),
(1, '😊', 'magda'),

-- Wiadomość 3 (admin - Bramka!) - 5 osób
(3, '🎉', 'ola'),
(3, '🔥', 'ania'),
(3, '⚽', 'janek'),
(3, '👏', 'michal'),
(3, '😍', 'lukasz'),

-- Wiadomość 6 (ola - GOOOOL!) - 6 osób
(6, '🎉', 'admin'),
(6, '❤️', 'ania'),
(6, '🔥', 'janek'),
(6, '⚽', 'kuba'),
(6, '👍', 'marcin'),
(6, '😍', 'lukasz'),

-- Wiadomość 9 (ania - Najlepszy wieczór) - 4 osoby
(9, '❤️', 'admin'),
(9, '🎉', 'ola'),
(9, '👏', 'janek'),
(9, '🔥', 'magda'),

-- Wiadomość 12 (janek - Typowanie) - 3 osoby
(12, '👍', 'admin'),
(12, '💯', 'kuba'),
(12, '🎯', 'pawel'),

-- Wiadomość 15 (kuba - Zasłużone zwycięstwo) - 5 osób
(15, '👏', 'admin'),
(15, '🔥', 'ola'),
(15, '❤️', 'ania'),
(15, '💪', 'janek'),
(15, '⚽', 'michal'),

-- Wiadomość 21 (magda - Heroiczna wygrana) - 4 osoby
(21, '🏆', 'admin'),
(21, '🎉', 'ania'),
(21, '❤️', 'lukasz'),
(21, '👏', 'piotr'),

-- Wiadomość 24 (marcin - Mecz roku) - 3 osoby
(24, '🌟', 'admin'),
(24, '🔥', 'janek'),
(24, '💯', 'kuba'),

-- Wiadomość 27 (lukasz - POLSKA!) - 6 osób
(27, '🇵🇱', 'admin'),
(27, '❤️', 'ola'),
(27, '🎉', 'ania'),
(27, '🔥', 'janek'),
(27, '⚽', 'kuba'),
(27, '👍', 'magda'),

-- Wiadomość 33 (piotr - Real life) - 4 osoby
(33, '😂', 'admin'),
(33, '👍', 'ania'),
(33, '🎮', 'janek'),
(33, '💯', 'lukasz');
