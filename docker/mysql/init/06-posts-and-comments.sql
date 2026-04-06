-- Czyszczenie starych danych
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE post_comment_reactions;
TRUNCATE TABLE post_reactions;
TRUNCATE TABLE post_comment;
TRUNCATE TABLE post;
SET FOREIGN_KEY_CHECKS = 1;

-- Posty (po 2 dla każdego użytkownika)
INSERT INTO post (title, content, image_url, created_at, updated_at, user_id) VALUES
-- admin (id=1)
('Najlepszy gol sezonu!', 'Ten gol Lewandowskiego z przewrotki był absolutnie genialny! Nie mogę przestać tego oglądać. Co za technika! Bramkarz nie miał szans. To pokazuje różnicę między dobrym a wybitnym piłkarzem.', NULL, '2026-04-01 10:00:00', '2026-04-01 10:00:00', 1),
('Analiza taktyczna meczu Polska-Hiszpania', 'Dzisiejszy mecz pokazał jak ważne jest posiadanie piłki. 65% posiadania przełożyło się na zwycięstwo 3-0. Nasza defensywa była solidna, a atak skuteczny. Zieliński kontrolował środek pola jak maestro.', NULL, '2026-04-02 14:30:00', '2026-04-02 14:30:00', 1),

-- ola (id=2)
('Mój pierwszy mecz na stadionie! 🏟️', 'Nie mogę uwierzyć! Wreszcie byłam na prawdziwym meczu! Atmosfera niesamowita, kibice śpiewali cały czas! Emocje na żywo to zupełnie co innego niż w telewizji. Polecam wszystkim takie doświadczenie!', NULL, '2026-04-01 11:00:00', '2026-04-01 11:00:00', 2),
('Moja kolekcja koszulek piłkarskich 👕', 'Pokazuję moją kolekcję - 15 koszulek z różnych klubów i reprezentacji. Moja ulubiona to Bayern Monachium z podpisem Lewandowskiego! Którą klub macie wy?', NULL, '2026-04-02 15:00:00', '2026-04-02 15:00:00', 2),

-- ania (id=3)
('Polska awansowała do półfinału! 🇵🇱', 'Historyczny moment! Polska awansowała do półfinału! Nie spałam całą noc ze szczęścia! Byliśmy niesamowici! Każdy zawodnik dał z siebie wszystko. To są wspomnienia na całe życie!', NULL, '2026-04-01 12:00:00', '2026-04-01 12:00:00', 3),
('Mój trening przed weekendowym meczem ⚽💪', 'Dzisiaj intensywny trening - bieganie, podania, strzały. Jestem gotowa na weekend! Gram w amatorskiej lidze i mamy ważny mecz. Trzymajcie kciuki!', NULL, '2026-04-02 16:00:00', '2026-04-02 16:00:00', 3),

-- janek (id=4)
('Ranking najlepszych drużyn w historii futbolu 🏆', 'Barcelona 2009-2011 to najlepsza drużyna w historii futbolu. Messi, Xavi, Iniesta - magia! Tiki-taka w najczystszej formie. Żadna drużyna nie grała tak pięknie. Change my mind!', NULL, '2026-04-01 13:00:00', '2026-04-01 13:00:00', 4),
('Statystyki Lewandowskiego w tym sezonie 📈', 'Lewandowski 30 goli w sezonie! Najlepszy napastnik w Europie bez dwóch zdań! Konsekwencja, profesjonalizm i talent. Duma Polski!', NULL, '2026-04-02 17:00:00', '2026-04-02 17:00:00', 4),

-- kuba (id=5)
('Derby miasta - najwięcej emocji w piłce! 🔴🔵', 'Najbardziej emocjonujący mecz roku! Derby miasta zawsze dostarczają niesamowitych emocji! Rivalstwo, atmosfera, pasja - to jest istota futbolu!', NULL, '2026-04-01 14:00:00', '2026-04-01 14:00:00', 5),
('VAR - za czy przeciw? Wasza opinia? 🤔', 'VAR poprawia sprawiedliwość, ale zabija spontaniczne emocje. Co myślicie? Czy warto poświęcić emocje dla sprawiedliwości? Ciekaw jestem waszych opinii!', NULL, '2026-04-02 18:00:00', '2026-04-02 18:00:00', 5),

-- michal (id=6)
('Real Madryt vs Bayern - klasyk Ligi Mistrzów! ⭐', 'Real Madryt vs Bayern Monachium - mecz na miarę finału! Taka piłka to poezja! Dwa giganty europejskiego futbolu w starciu. Nie mogę się doczekać!', NULL, '2026-04-01 15:00:00', '2026-04-01 15:00:00', 6),
('Młode talenty które warto obserwować 🌟', 'Obserwujcie tego młodego zawodnika z Borussii! Za 2 lata będzie w TOP 10 najlepszych! Talent, szybkość i inteligencja piłkarska. Zapamiętajcie tę nazwę!', NULL, '2026-04-02 19:00:00', '2026-04-02 19:00:00', 6),

-- magda (id=7)
('Kobiecy futbol w Polsce rośnie! 💪⚽', 'Coraz więcej dziewczyn gra w piłkę! To wspaniałe widzieć taki rozwój! Wreszcie kobiecy futbol dostaje uwagę jaką zasługuje. Gratuluję wszystkim piłkarkom!', NULL, '2026-04-01 16:00:00', '2026-04-01 16:00:00', 7),
('Mundial 2026 - kto zwycięży? Prognozy 🏆', 'Kto waszym zdaniem wygra Mundial 2026? Ja stawiam na Brazylię lub Francję! Obie drużyny mają niesamowity skład. A może Polska zaskoczy?', NULL, '2026-04-02 20:00:00', '2026-04-02 20:00:00', 7),

-- marcin (id=8)
('Premier League najlepsza liga świata! 🏴󠁧󠁢󠁥󠁮󠁧󠁿', 'Tempo, intensywność, wyrównanie - Premier League to najlepsza liga świata! Każdy mecz to emocje. Żadna inna liga nie ma takiej jakości od góry do dołu tabeli!', NULL, '2026-04-01 17:00:00', '2026-04-01 17:00:00', 8),
('Manchester United wraca na szczyt! 👹', 'Ten sezon pokazuje że United wraca na szczyt! Old Trafford znów się liczy! Nowe talenty, doświadczeni gracze - idealne połączenie!', NULL, '2026-04-02 21:00:00', '2026-04-02 21:00:00', 8),

-- lukasz (id=9)
('Bundesliga niedoceniana 🇩🇪', 'Bayern dominuje, ale inne kluby też grają świetny futbol! Bundesliga zasługuje na więcej uwagi! Taktyka, technika i atmosfera na stadionach - rewelacja!', NULL, '2026-04-01 18:00:00', '2026-04-01 18:00:00', 9),
('Robert Lewandowski - polska legenda! 👑', 'Najlepszy polski piłkarz wszech czasów! Dumny z naszego kapitana! Rekordy, gole, trofea. Reprezentuje nas na najwyższym poziomie!', NULL, '2026-04-02 22:00:00', '2026-04-02 22:00:00', 9),

-- pawel (id=10)
('Serie A - taktyczna szkoła futbolu 🇮🇹', 'Włoski futbol to szkoła taktyki. Każdy mecz to szachowa partia! Catenaccio, pressing, kontratak - wszystko dopracowane do perfekcji!', NULL, '2026-04-01 19:00:00', '2026-04-01 19:00:00', 10),
('AC Milan powraca do europejskiej elity 🔴⚫', 'Milan wraca do europejskiej elity! Rossoneri znów budzą respekt! Młody skład, świetny trener - przyszłość wygląda obiecująco!', NULL, '2026-04-02 23:00:00', '2026-04-02 23:00:00', 10);

-- Reakcje do postów (około 30%)
INSERT INTO post_reactions (post_id, emoji, username) VALUES
-- Post 1 (admin - gol sezonu) - 5 osób
(1, '⚽', 'ania'),
(1, '🔥', 'janek'),
(1, '😍', 'kuba'),
(1, '👏', 'magda'),
(1, '💯', 'lukasz'),

-- Post 3 (ola - stadion) - 4 osoby
(3, '🏟️', 'admin'),
(3, '🎉', 'ania'),
(3, '❤️', 'janek'),
(3, '👍', 'magda'),

-- Post 5 (ania - awans) - 6 osób
(5, '🇵🇱', 'admin'),
(5, '🎉', 'ola'),
(5, '🔥', 'janek'),
(5, '❤️', 'kuba'),
(5, '👏', 'michal'),
(5, '😍', 'lukasz'),

-- Post 7 (janek - Barcelona) - 4 osoby
(7, '🏆', 'admin'),
(7, '😍', 'ola'),
(7, '⚽', 'lukasz'),
(7, '💯', 'pawel'),

-- Post 10 (kuba - VAR) - 5 osób
(10, '🤔', 'admin'),
(10, '👍', 'ania'),
(10, '👎', 'magda'),
(10, '❓', 'marcin'),
(10, '💭', 'piotr'),

-- Post 13 (magda - kobiecy futbol) - 4 osoby
(13, '💪', 'admin'),
(13, '❤️', 'ola'),
(13, '👏', 'ania'),
(13, '🔥', 'janek'),

-- Post 16 (marcin - Premier League) - 5 osób
(16, '🏴󠁧󠁢󠁥󠁮󠁧󠁿', 'admin'),
(16, '⚽', 'janek'),
(16, '🔥', 'kuba'),
(16, '👍', 'lukasz'),
(16, '💯', 'pawel');

-- Komentarze do postów
INSERT INTO post_comment (text, created_at, user_id, post_id, parent_comment_id, quoted_comment_id) VALUES
-- Komentarze do posta 1 (gol sezonu)
('Zgadzam się w 100%! Ten gol był niesamowity! 🔥', '2026-04-01 10:30:00', 2, 1, NULL, NULL),
('Lewandowski to prawdziwa legenda polskiego futbolu! 👑', '2026-04-01 11:00:00', 4, 1, NULL, NULL),
('Widziałem to na żywo na stadionie! Cały stadion oszalał!', '2026-04-01 11:30:00', 5, 1, NULL, NULL),
('Dokładnie! Ja też tam byłam! Niesamowite przeżycie! 🏟️', '2026-04-01 12:00:00', 2, 1, 3, NULL),

-- Komentarze do posta 5 (awans Polski)
('Nie spałam całą noc! Jakie emocje! 🇵🇱', '2026-04-01 12:30:00', 1, 5, NULL, NULL),
('Zasłużony awans! Grali wspaniale!', '2026-04-01 13:00:00', 4, 5, NULL, NULL),
('Zieliński był MVP tego meczu! ⚡', '2026-04-01 13:30:00', 9, 5, NULL, NULL),

-- Komentarze do posta 7 (Barcelona)
('Messi w tamtej Barcelonie to była magia! ✨', '2026-04-01 13:30:00', 2, 7, NULL, NULL),
('Xavi i Iniesta w środku pola - mistrzostwo świata!', '2026-04-01 14:00:00', 6, 7, NULL, NULL),
('Ta Barcelona zmieniła futbol na zawsze', '2026-04-01 14:30:00', 10, 7, NULL, NULL),

-- Komentarze do posta 10 (VAR)
('Jestem za VAR! Mniej błędów sędziowskich!', '2026-04-02 18:30:00', 1, 10, NULL, NULL),
('Ale zabija emocje... Trudna decyzja', '2026-04-02 19:00:00', 3, 10, NULL, NULL),
('Sprawiedliwość ważniejsza niż emocje', '2026-04-02 19:30:00', 6, 10, NULL, NULL),
('Nie zgadzam się. Emocje to istota futbolu!', '2026-04-02 20:00:00', 7, 10, 12, NULL);

-- Reakcje do komentarzy
INSERT INTO post_comment_reactions (post_comment_id, emoji, username) VALUES
-- Komentarz 1 (o golu)
(1, '👍', 'admin'),
(1, '🔥', 'janek'),
(1, '⚽', 'kuba'),

-- Komentarz 5 (nie spałam)
(5, '😂', 'ola'),
(5, '❤️', 'ania'),
(5, '🎉', 'janek'),

-- Komentarz 8 (Messi magia)
(8, '✨', 'admin'),
(8, '😍', 'ania'),
(8, '⚽', 'lukasz'),

-- Komentarz 11 (VAR za)
(11, '👍', 'janek'),
(11, '💯', 'michal'),

-- Komentarz 12 (VAR zabija emocje)
(12, '❤️', 'magda'),
(12, '👏', 'marcin');
