-- Ustawienie kodowania UTF-8
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Czyszczenie starych danych
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE post_comment_reactions;
TRUNCATE TABLE post_reactions;
TRUNCATE TABLE post_comment;
TRUNCATE TABLE post;
SET FOREIGN_KEY_CHECKS = 1;

-- Posty (6 postów o meczach międzynarodowych i zawodnikach)
INSERT INTO post (title, content, image_url, created_at, updated_at, user_id) VALUES
-- Post 1: POLSKA NIE JEDZIE NA MUNDIAL
('POLSKA NIE JEDZIE NA MUNDIAL 2026! 😢🇵🇱', 'To się nie mogło stać... Po remisie z Holandią 1:1 i przegranej z Finlandią 2:1 nasza reprezentacja nie zakwalifikowała się do Mistrzostw Świata 2026. Mimo zwycięstw z Litwą i Maltą, zabrakło nam punktów. Lewandowski walczył do końca, Zieliński tworzył szanse, ale to nie wystarczyło. Brak nas w tym turnieju to ogromna strata dla polskiego futbolu. Teraz musimy się odbudować i skupić na kolejnych eliminacjach. Trzymajmy kciuki za naszą reprezentację! 💔', NULL, '2025-10-15 22:30:00', '2025-10-15 22:30:00', 3),

-- Post 2: Mecz Brazylia - Hiszpania
('Brazylia vs Hiszpania - hit Mundialu 2026! ⚽🔥', 'To będzie mecz marzeń! Brazylia z jej legendarnymi napastnikami kontra hiszpańska tiki-taka. W grupie C Brazylia rozbiła Maroko 3:1, a Hiszpania w grupie H pokazała klasę wygrywając z Urugwajem 2:0. Jeśli obie drużyny awansują, możemy zobaczyć je w 1/8 finału. Neymar kontra Pedri, Vinicius kontra Gavi - to będą pojedynki na najwyższym poziomie!', NULL, '2026-04-01 10:00:00', '2026-04-01 10:00:00', 1),

-- Post 3: Niemcy wrócą po tytuł
('Niemcy wrócą po tytuł! 🇩🇪⚽', 'Die Mannschaft jest w świetnej formie przed Mundialem 2026! W grupie E zmierzą się z Ekwadorem, Wybrzeżem Kości Słoniowej i Curacao. Müller kończy karierę po tym turnieju - to jego ostatnia szansa na kolejny mundial. Młodzi: Musiala, Wirtz, Havertz tworzą niesamowitą ofensywę. Niemcy zawsze są groźni na wielkich turniejach!', NULL, '2026-03-28 14:00:00', '2026-03-28 14:00:00', 4),

-- Post 4: Francja faworyt
('Francja znowu faworytem! 🇫🇷👑', 'Les Bleus jadą po kolejny tytuł! Mbappé w szczytowej formie, Griezmann nadal magia, młodzi jak Camavinga i Tchouameni kontrolują środek pola. W grupie I z Senegalem i Norwegią nie powinni mieć problemów. Po triumfie w 2018 i finale w 2022, teraz czas na potwierdzenie dominacji! Deschamps wie jak wygrywać wielkie turnieje.', NULL, '2026-04-02 16:30:00', '2026-04-02 16:30:00', 7),

-- Post 5: Lewandowski legenda
('Robert Lewandowski - legenda pomimo braku Mundialu! 👑⚽', 'Mimo że Polska nie awansowała na Mundial 2026, Lewandowski pozostaje absolutną legendą! 35 goli w eliminacjach, 80+ goli w reprezentacji, kapitan który zawsze dawał z siebie wszystko. W meczu z Finlandią strzelił pięknego gola, ale defensywa zawiodła. Lewy, dziękujemy za wszystko! Twoja klasa światowa to duma każdego Polaka. 🇵🇱', NULL, '2025-10-20 19:00:00', '2025-10-20 19:00:00', 9),

-- Post 6: Holandia - Japonia zapowiedź
('Holandia - Japonia: Klasyk mundialu! 🇳🇱⚽🇯🇵', 'Mecz otwarcia grupy F zapowiada się rewelacyjnie! Holandia po remisie z Polską 1:1 w eliminacjach pokazała solidną grę. Van Dijk wrócił do formy, Gakpo strzela gole. Japonia zawsze jest niewygodnym rywalem - szybka, techniczna, zorganizowana. Pamiętacie ich zwycięstwo nad Niemcami w 2022? To będzie emocjonujące starcie 14 czerwca 2026!', NULL, '2026-04-05 11:00:00', '2026-04-05 11:00:00', 6);

-- Reakcje do postów (około 30%)
INSERT INTO post_reactions (post_id, emoji, username) VALUES
-- Post 1 (Polska nie jedzie na mundial) - 8 osób
(1, '😢', 'admin'),
(1, '💔', 'ola'),
(1, '😭', 'janek'),
(1, '🇵🇱', 'kuba'),
(1, '😔', 'michal'),
(1, '💪', 'magda'),
(1, '😥', 'marcin'),
(1, '😢', 'lukasz'),

-- Post 2 (Brazylia vs Hiszpania) - 6 osób
(2, '🔥', 'ania'),
(2, '⚽', 'janek'),
(2, '😍', 'kuba'),
(2, '👏', 'magda'),
(2, '💯', 'lukasz'),
(2, '🏆', 'pawel'),

-- Post 3 (Niemcy) - 5 osób
(3, '🇩🇪', 'admin'),
(3, '⚽', 'ola'),
(3, '🔥', 'ania'),
(3, '👍', 'michal'),
(3, '💪', 'lukasz'),

-- Post 4 (Francja) - 7 osób
(4, '🇫🇷', 'admin'),
(4, '🏆', 'ola'),
(4, '⚽', 'janek'),
(4, '🔥', 'kuba'),
(4, '👑', 'michal'),
(4, '😍', 'marcin'),
(4, '💯', 'pawel'),

-- Post 5 (Lewandowski legenda) - 9 osób
(5, '👑', 'admin'),
(5, '❤️', 'ola'),
(5, '🇵🇱', 'ania'),
(5, '⚽', 'janek'),
(5, '💪', 'kuba'),
(5, '😍', 'michal'),
(5, '👏', 'magda'),
(5, '🔥', 'marcin'),
(5, '💯', 'pawel'),

-- Post 6 (Holandia - Japonia) - 5 osób
(6, '🇳🇱', 'admin'),
(6, '🇯🇵', 'ania'),
(6, '⚽', 'janek'),
(6, '🔥', 'magda'),
(6, '👍', 'lukasz');

-- Komentarze do postów
INSERT INTO post_comment (text, created_at, user_id, post_id, parent_comment_id, quoted_comment_id) VALUES
-- Komentarze do posta 1 (Polska nie jedzie na mundial)
('To jest tragedia dla polskiego futbolu 😢', '2025-10-15 22:45:00', 1, 1, NULL, NULL),
('Nie mogę w to uwierzyć... Tak bardzo chciałem zobaczyć Lewego na mundialu 💔', '2025-10-15 23:00:00', 4, 1, NULL, NULL),
('Defensywa zawiodła w kluczowych momentach. Musimy to naprawić!', '2025-10-15 23:15:00', 8, 1, NULL, NULL),
('Ja też płakałam... Ale wierzę że wrócimy silniejsi! 🇵🇱💪', '2025-10-15 23:30:00', 2, 1, 2, NULL),

-- Komentarze do posta 2 (Brazylia vs Hiszpania)
('To będzie mecz stulecia! Neymar vs Pedri 🔥', '2026-04-01 10:30:00', 3, 2, NULL, NULL),
('Stawiam na Brazylię! Mają zbyt silny atak', '2026-04-01 11:00:00', 5, 2, NULL, NULL),
('Hiszpania pokaże tiki-taka i wygra 2:1', '2026-04-01 11:30:00', 10, 2, NULL, NULL),

-- Komentarze do posta 4 (Francja)
('Mbappé w takiej formie jest nie do zatrzymania! 🚀', '2026-04-02 16:45:00', 1, 4, NULL, NULL),
('Francja ma najlepszy skład na mundialu. Będą w finale!', '2026-04-02 17:00:00', 6, 4, NULL, NULL),
('Deschamps to trener mistrzów! 🏆', '2026-04-02 17:15:00', 9, 4, NULL, NULL),

-- Komentarze do posta 5 (Lewandowski legenda)
('Lewandowski to najlepszy polski piłkarz w historii! 👑', '2025-10-20 19:30:00', 1, 5, NULL, NULL),
('Bez niego byłoby jeszcze gorzej. Dziękujemy Kapitanie! ❤️', '2025-10-20 19:45:00', 3, 5, NULL, NULL),
('80 goli w reprezentacji to niesamowite osiągnięcie!', '2025-10-20 20:00:00', 4, 5, NULL, NULL),
('Szacunek za walkę do końca! 🇵🇱⚽', '2025-10-20 20:15:00', 7, 5, NULL, NULL);

-- Reakcje do komentarzy
INSERT INTO post_comment_reactions (post_comment_id, emoji, username) VALUES
-- Komentarz 1 (tragedia dla futbolu)
(1, '😢', 'ola'),
(1, '💔', 'ania'),
(1, '😭', 'janek'),

-- Komentarz 2 (Lewy na mundialu)
(2, '😢', 'admin'),
(2, '❤️', 'ania'),
(2, '🇵🇱', 'michal'),

-- Komentarz 5 (mecz stulecia)
(5, '🔥', 'admin'),
(5, '⚽', 'janek'),
(5, '😍', 'lukasz'),

-- Komentarz 8 (Mbappé)
(8, '🚀', 'ania'),
(8, '⚽', 'kuba'),
(8, '💯', 'lukasz'),

-- Komentarz 11 (Lewandowski najlepszy)
(11, '👑', 'ola'),
(11, '❤️', 'ania'),
(11, '🇵🇱', 'janek'),
(11, '💪', 'magda');
