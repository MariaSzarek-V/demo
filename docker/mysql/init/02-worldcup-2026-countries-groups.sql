-- World Cup 2026 - Countries and Groups
-- 12 groups (A-L) with 4 teams each = 48 countries
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;
USE betsdb;

-- ==============================================
-- INSERT GROUPS
-- ==============================================
INSERT INTO `group` (name) VALUES
('A'), ('B'), ('C'), ('D'), ('E'), ('F'),
('G'), ('H'), ('I'), ('J'), ('K'), ('L');

-- ==============================================
-- INSERT COUNTRIES WITH GROUP ASSIGNMENTS
-- Kody krajów zgodne z ISO 3166-1 alpha-2 (małe litery)
-- ==============================================

-- Group A: Meksyk, RPA, Korea Południowa, TBD_A
INSERT INTO country (name, code, group_id) VALUES
('Meksyk', 'mx', (SELECT id FROM `group` WHERE name = 'A')),
('RPA', 'za', (SELECT id FROM `group` WHERE name = 'A')),
('Korea Południowa', 'kr', (SELECT id FROM `group` WHERE name = 'A')),
('TBD_A', 'TBD_A', (SELECT id FROM `group` WHERE name = 'A'));

-- Group B: Kanada, TBD_B, Katar, Szwajcaria
INSERT INTO country (name, code, group_id) VALUES
('Kanada', 'ca', (SELECT id FROM `group` WHERE name = 'B')),
('TBD_B', 'TBD_B', (SELECT id FROM `group` WHERE name = 'B')),
('Katar', 'qa', (SELECT id FROM `group` WHERE name = 'B')),
('Szwajcaria', 'ch', (SELECT id FROM `group` WHERE name = 'B'));

-- Group C: Brazylia, Maroko, Haiti, Szkocja
INSERT INTO country (name, code, group_id) VALUES
('Brazylia', 'br', (SELECT id FROM `group` WHERE name = 'C')),
('Maroko', 'ma', (SELECT id FROM `group` WHERE name = 'C')),
('Haiti', 'ht', (SELECT id FROM `group` WHERE name = 'C')),
('Szkocja', 'gb-sct', (SELECT id FROM `group` WHERE name = 'C'));

-- Group D: USA, Paragwaj, Australia, TBD_D
INSERT INTO country (name, code, group_id) VALUES
('USA', 'us', (SELECT id FROM `group` WHERE name = 'D')),
('Paragwaj', 'py', (SELECT id FROM `group` WHERE name = 'D')),
('Australia', 'au', (SELECT id FROM `group` WHERE name = 'D')),
('TBD_D', 'TBD_D', (SELECT id FROM `group` WHERE name = 'D'));

-- Group E: Niemcy, Curacao, Wybrzeże Kości Słoniowej, Ekwador
INSERT INTO country (name, code, group_id) VALUES
('Niemcy', 'de', (SELECT id FROM `group` WHERE name = 'E')),
('Curacao', 'cw', (SELECT id FROM `group` WHERE name = 'E')),
('Wybrzeże Kości Słoniowej', 'ci', (SELECT id FROM `group` WHERE name = 'E')),
('Ekwador', 'ec', (SELECT id FROM `group` WHERE name = 'E'));

-- Group F: Holandia, Japonia, TBD_F, Tunezja
INSERT INTO country (name, code, group_id) VALUES
('Holandia', 'nl', (SELECT id FROM `group` WHERE name = 'F')),
('Japonia', 'jp', (SELECT id FROM `group` WHERE name = 'F')),
('TBD_F', 'TBD_F', (SELECT id FROM `group` WHERE name = 'F')),
('Tunezja', 'tn', (SELECT id FROM `group` WHERE name = 'F'));

-- Group G: Belgia, Egipt, Iran, Nowa Zelandia
INSERT INTO country (name, code, group_id) VALUES
('Belgia', 'be', (SELECT id FROM `group` WHERE name = 'G')),
('Egipt', 'eg', (SELECT id FROM `group` WHERE name = 'G')),
('Iran', 'ir', (SELECT id FROM `group` WHERE name = 'G')),
('Nowa Zelandia', 'nz', (SELECT id FROM `group` WHERE name = 'G'));

-- Group H: Hiszpania, Republika Zielonego Przylądka, Arabia Saudyjska, Urugwaj
INSERT INTO country (name, code, group_id) VALUES
('Hiszpania', 'es', (SELECT id FROM `group` WHERE name = 'H')),
('Republika Zielonego Przylądka', 'cv', (SELECT id FROM `group` WHERE name = 'H')),
('Arabia Saudyjska', 'sa', (SELECT id FROM `group` WHERE name = 'H')),
('Urugwaj', 'uy', (SELECT id FROM `group` WHERE name = 'H'));

-- Group I: Francja, Senegal, TBD_I, Norwegia
INSERT INTO country (name, code, group_id) VALUES
('Francja', 'fr', (SELECT id FROM `group` WHERE name = 'I')),
('Senegal', 'sn', (SELECT id FROM `group` WHERE name = 'I')),
('TBD_I', 'TBD_I', (SELECT id FROM `group` WHERE name = 'I')),
('Norwegia', 'no', (SELECT id FROM `group` WHERE name = 'I'));

-- Group J: Argentyna, Algieria, Austria, Jordania
INSERT INTO country (name, code, group_id) VALUES
('Argentyna', 'ar', (SELECT id FROM `group` WHERE name = 'J')),
('Algieria', 'dz', (SELECT id FROM `group` WHERE name = 'J')),
('Austria', 'at', (SELECT id FROM `group` WHERE name = 'J')),
('Jordania', 'jo', (SELECT id FROM `group` WHERE name = 'J'));

-- Group K: Portugalia, TBD_K, Uzbekistan, Kolumbia
INSERT INTO country (name, code, group_id) VALUES
('Portugalia', 'pt', (SELECT id FROM `group` WHERE name = 'K')),
('TBD_K', 'TBD_K', (SELECT id FROM `group` WHERE name = 'K')),
('Uzbekistan', 'uz', (SELECT id FROM `group` WHERE name = 'K')),
('Kolumbia', 'co', (SELECT id FROM `group` WHERE name = 'K'));

-- Group L: Anglia, Chorwacja, Ghana, Panama
INSERT INTO country (name, code, group_id) VALUES
('Anglia', 'gb-eng', (SELECT id FROM `group` WHERE name = 'L')),
('Chorwacja', 'hr', (SELECT id FROM `group` WHERE name = 'L')),
('Ghana', 'gh', (SELECT id FROM `group` WHERE name = 'L')),
('Panama', 'pa', (SELECT id FROM `group` WHERE name = 'L'));

COMMIT;
