# Zarządzanie Bazą Danych w Docker Compose

Ten przewodnik opisuje jak zarządzać bazą danych MySQL w kontenerze Docker.

## 📋 Spis Treści
- [Jak Działa Inicjalizacja Bazy](#jak-działa-inicjalizacja-bazy)
- [Dodawanie Nowych Plików SQL](#dodawanie-nowych-plików-sql)
- [Modyfikacja Istniejących Danych](#modyfikacja-istniejących-danych)
- [Backup i Restore](#backup-i-restore)
- [Rozwiązywanie Problemów](#rozwiązywanie-problemów)

---

## 🔄 Jak Działa Inicjalizacja Bazy

### Mechanizm Docker MySQL

Kontener MySQL automatycznie wykonuje pliki SQL **tylko przy pierwszym uruchomieniu**, gdy baza jest pusta:

1. Pliki z katalogu `docker/mysql/init` są wykonywane
2. Pliki są przetwarzane w **kolejności alfabetycznej**
3. Po wykonaniu skryptów, dane są zapisywane w **volume** (`mysql_data`)
4. Przy kolejnych uruchomieniach kontenera, skrypty **NIE są wykonywane ponownie**

### Konfiguracja w docker-compose.yml

```yaml
mysql:
  volumes:
    - mysql_data:/var/lib/mysql  # Trwałe dane
    - ./docker/mysql/init:/docker-entrypoint-initdb.d  # Skrypty SQL
```

- `mysql_data` - volume z trwałymi danymi bazy
- `./docker/mysql/init` - folder ze skryptami inicjalizacyjnymi

---

## ➕ Dodawanie Nowych Plików SQL

### Krok 1: Utwórz nowy plik SQL
```bash
# Przejdź do katalogu init
cd docker/mysql/init

# Utwórz nowy plik (zwróć uwagę na numerację!)
nano 16-additional-data.sql
```

### Przykład zawartości:
```sql
-- =====================================================
-- Additional Data: Extra users and predictions
-- =====================================================

SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

USE betsdb;

-- Dodaj nowych użytkowników
INSERT INTO `user` (`username`, `email`, `password`, `user_role`) VALUES
('Nowy User', 'nowy@example.com', '$2a$10$8R0VHZ5YqJ5Y5YqJ5Y5Y5u0VHZ5YqJ5Y5YqJ5Y5Y5u0VHZ5YqJ5Y5', 'USER');

COMMIT;
```

### Krok 2: Przeładuj bazę
```bash
# Wróć do głównego katalogu
cd /Users/mszarek/workspace/coderslab/demo

# Restart z usunięciem volume
docker-compose down -v
docker-compose up -d
```

---

## ✏️ Modyfikacja Istniejących Danych

### ⚠️ WAŻNE: Nie modyfikuj plików init po pierwszym uruchomieniu!

**Pliki SQL w katalogu init są wykonywane TYLKO przy pierwszym uruchomieniu kontenera.**

### Jeśli chcesz zmienić dane w działającej bazie:

#### Opcja 1: Bezpośrednie zapytanie SQL
```bash
# Zaloguj się do bazy
docker exec -it prediction-mysql mysql -uroot -proot betsdb

# Wykonaj zapytanie
UPDATE user SET username = 'Nowa Nazwa' WHERE id = 1;
```

#### Opcja 2: Plik SQL + przeładowanie
```bash
# 1. Zmodyfikuj plik SQL (np. 05-users.sql)
nano docker/mysql/init/05-users.sql

# 2. Restart z usunięciem volume
docker-compose down -v
docker-compose up -d
```

---

## 💾 Backup i Restore

### Backup całej bazy

```bash
# Eksport całej bazy do pliku
docker exec prediction-mysql mysqldump -uroot -proot betsdb > backup_$(date +%Y%m%d).sql

# Backup z kompresją
docker exec prediction-mysql mysqldump -uroot -proot betsdb | gzip > backup_$(date +%Y%m%d).sql.gz
```

### Restore z backup

```bash
# Restore z pliku SQL
docker exec -i prediction-mysql mysql -uroot -proot betsdb < backup_20260418.sql

# Restore z pliku skompresowanego
gunzip < backup_20260418.sql.gz | docker exec -i prediction-mysql mysql -uroot -proot betsdb
```

### Backup tylko struktury (bez danych)

```bash
docker exec prediction-mysql mysqldump -uroot -proot --no-data betsdb > schema_only.sql
```

### Backup tylko danych (bez struktury)

```bash
docker exec prediction-mysql mysqldump -uroot -proot --no-create-info betsdb > data_only.sql
```

---

## 🔧 Rozwiązywanie Problemów

### Problem 1: Dane się nie załadowały po restarcie

**Przyczyna:** Volume z danymi nie został usunięty.

**Rozwiązanie:**
```bash
# Zatrzymaj kontenery
docker-compose down

# Usuń volume ręcznie
docker volume rm demo_mysql_data

# Uruchom ponownie
docker-compose up -d
```

### Problem 2: Błędy w logach MySQL podczas inicjalizacji

**Diagnoza:**
```bash
# Zobacz logi kontenera
docker logs prediction-mysql

# Zobacz ostatnie 50 linii
docker logs --tail 50 prediction-mysql
```

**Najczęstsze błędy:**

#### Błąd: "Duplicate entry"
```
ERROR 1062 (23000): Duplicate entry 'username' for key 'user.username'
```

**Rozwiązanie:** Sprawdź czy nie masz duplikatów w plikach SQL

#### Błąd: "Unknown column"
```
ERROR 1054 (42S22): Unknown column 'field_name'
```

**Rozwiązanie:** Sprawdź kolejność plików (struktura musi być przed danymi)

#### Błąd kodowania UTF-8
```
ERROR: Invalid utf8mb4 character string
```

**Rozwiązanie:**
```bash
# Sprawdź kodowanie pliku
file docker/mysql/init/05-users.sql

# Jeśli nie UTF-8, przekonwertuj:
iconv -f ISO-8859-2 -t UTF-8 05-users.sql > 05-users-utf8.sql
```

### Problem 3: Port 3306 zajęty

**Diagnoza:**
```bash
lsof -i :3306
```

**Rozwiązanie:**
```bash
# Zatrzymaj lokalny MySQL (jeśli działa)
sudo systemctl stop mysql

# Lub zmień port w docker-compose.yml
ports:
  - "3307:3306"
```

### Problem 4: Kontener MySQL nie startuje

**Diagnoza:**
```bash
# Sprawdź status
docker ps -a | grep mysql

# Sprawdź szczegółowe logi
docker logs prediction-mysql
```

**Rozwiązanie:**
```bash
# Usuń kontener i volume
docker-compose down -v
docker volume prune

# Uruchom ponownie
docker-compose up -d
```

---

## 📝 Checklist przed zmianą bazy

- [ ] Backup obecnych danych (jeśli potrzebne)
- [ ] Sprawdzenie kodowania UTF-8 w nowych plikach SQL
- [ ] Numeracja plików SQL (00-, 01-, 02-, ...)
- [ ] Wykonanie `docker-compose down -v`
- [ ] Uruchomienie `docker-compose up -d`
- [ ] Weryfikacja danych po załadowaniu

---

## 🎯 Szybkie Komendy

```bash
# Backup bazy
docker exec prediction-mysql mysqldump -uroot -proot betsdb > backup.sql

# Restore bazy
docker exec -i prediction-mysql mysql -uroot -proot betsdb < backup.sql

# Sprawdzenie liczby rekordów
docker exec -i prediction-mysql mysql -uroot -proot betsdb -e "SELECT 'Users:' as '', COUNT(*) FROM user UNION ALL SELECT 'Games:', COUNT(*) FROM game UNION ALL SELECT 'Predictions:', COUNT(*) FROM prediction;"

# Restart z czystą bazą
docker-compose down -v && docker-compose up -d

# Zobacz logi MySQL
docker logs -f prediction-mysql
```

---

## 📚 Dodatkowe Zasoby

- [Docker MySQL Documentation](https://hub.docker.com/_mysql)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

---

**Ostatnia aktualizacja:** 2026-04-19
**Wersja:** 2.0
