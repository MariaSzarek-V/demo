# Automatyczna Inicjalizacja Bazy Danych w Docker

## 🎯 Jak to działa?

Twoja aplikacja jest **już skonfigurowana** do automatycznej inicjalizacji bazy danych! 

### Mechanizm

1. **Przy pierwszym uruchomieniu** kontenera MySQL automatycznie wykonują się skrypty SQL z katalogu `docker/mysql/init/`
2. **Kolejność wykonania**: alfabetyczna (00 → 01 → 02)
3. **Hibernate jest wyłączony**: `ddl-auto=none` - schemat zarządzany jest przez SQL, nie przez JPA

### Struktura Plików

```
docker/mysql/init/
├── 00-create-tables.sql    # Tworzy tabele i schemat
├── 01-init.sql             # Dodaje podstawowe dane (admin, testuser, przykładowe gry)
└── 02-sample-data.sql      # Dodaje dodatkowe dane testowe
```

## 🚀 Uruchomienie

### Metoda 1: Skrypt zarządzający (ZALECANA)

```bash
# Nadaj uprawnienia (pierwszym razem)
chmod +x docker-run.sh

# Uruchom aplikację
./docker-run.sh start
```

### Metoda 2: Docker Compose

```bash
docker-compose up --build -d
```

## 📊 Co zostanie utworzone?

### Baza Danych
- **Nazwa**: `betsdb`
- **Tabele**: user, game, prediction, comment, user_points, ranking, ranking_history, game_prediction_result

### Domyślni Użytkownicy
| Username | Email | Password | Rola |
|----------|-------|----------|------|
| admin | admin@admin.com | 123qweasd | ADMIN |
| ola | ola@ola.com | 123qweasd | USER |
| ania | ania@ania.com | 123qweasd | USER |
| janek | janek@janek.com | 123qweasd | USER |
| kuba | kuba@kuba.com | 123qweasd | USER |
| michal | michal@michal.com | 123qweasd | USER |

### Przykładowe Dane

#### 🏆 Mecze (10 total)
**3 Zakończone mecze z wynikami:**
- Brazylia 2:1 Argentyna (FINISHED)
- Niemcy 1:1 Francja (FINISHED)
- Hiszpania 3:0 Włochy (FINISHED)

**7 Zaplanowanych meczów (przyszłe daty):**
- Polska vs Portugalia
- Anglia vs Holandia
- Belgia vs Chorwacja
- Szwecja vs Norwegia
- Dania vs Szwajcaria
- Turcja vs Grecja
- Czechy vs Austria

#### 🎯 Predykcje
- Wszyscy 5 użytkowników typowali wszystkie 3 zakończone mecze
- 4 z 7 przyszłych meczów mają już typowania (bez punktów, bo wynik nieznany)
- System automatycznie przypisał punkty za zakończone mecze zgodnie z zasadami

#### 📊 Ranking (na podstawie zakończonych meczów)
1. **Ola** - 7 punktów (2x exact score, 1x correct winner)
2. **Ania** - 5 punktów (1x exact score, 2x correct winner/draw)
3. **Kuba** - 3 punkty (3x correct winner/draw)
3. **Michał** - 3 punkty (1x exact score)
5. **Janek** - 1 punkt (1x correct winner)

#### 💬 Komentarze
- 6 komentarzy (po 1 od każdego użytkownika włącznie z adminem)

## 🔄 Reset Bazy Danych

Aby ponownie zainicjalizować bazę od zera:

```bash
# Zatrzymaj i usuń wszystko (łącznie z danymi)
docker-compose down -v

# Uruchom ponownie - baza zostanie utworzona od nowa
./docker-run.sh start
```

## ✅ Weryfikacja

Po uruchomieniu sprawdź:

```bash
# Status kontenerów
./docker-run.sh status

# Logi aplikacji
./docker-run.sh logs app

# Logi bazy danych
./docker-run.sh logs mysql
```

### Dostęp do Usług

- **Aplikacja**: http://localhost:8080
- **phpMyAdmin**: http://localhost:8081 (login: root / root)
- **Swagger API**: http://localhost:8080/swagger-ui.html
- **Health Check**: http://localhost:8080/actuator/health

## 🔍 Podgląd Danych

### Opcja 1: phpMyAdmin
1. Otwórz http://localhost:8081
2. Login: `root`
3. Hasło: `root`
4. Wybierz bazę: `betsdb`

### Opcja 2: MySQL CLI
```bash
# Wejdź do kontenera MySQL
./docker-run.sh shell mysql

# Połącz się z bazą
mysql -u root -proot betsdb

# Sprawdź użytkowników
SELECT id, username, email, user_role FROM user;

# Sprawdź gry
SELECT id, home_team, away_team, game_date, game_status FROM game;
```

## ⚙️ Konfiguracja

### Zmiana Danych Inicjalizacyjnych

Edytuj pliki SQL w `docker/mysql/init/`:
- `00-create-tables.sql` - zmiana struktury tabel
- `01-init.sql` - zmiana podstawowych danych
- `02-sample-data.sql` - zmiana danych testowych

**WAŻNE**: Zmiany będą widoczne tylko po resecie bazy danych (`docker-compose down -v`)!

### Zmiana Ustawień Hibernate

W pliku `docker-compose.yml` lub `application.properties`:
```properties
# none - schemat zarządzany przez SQL (zalecane)
spring.jpa.hibernate.ddl-auto=none

# validate - tylko walidacja (bez zmian)
spring.jpa.hibernate.ddl-auto=validate

# update - automatyczne aktualizacje (niezalecane z SQL init)
spring.jpa.hibernate.ddl-auto=update
```

## 🐛 Rozwiązywanie Problemów

### Baza nie została zainicjalizowana
```bash
# Sprawdź logi MySQL
./docker-run.sh logs mysql

# Szukaj błędów w skryptach SQL
grep -i error
```

### Aplikacja nie łączy się z bazą
```bash
# Sprawdź, czy MySQL jest gotowy
docker-compose ps

# Poczekaj na healthcheck
./docker-run.sh logs mysql | grep "ready for connections"
```

### Port zajęty
```bash
# Sprawdź, co zajmuje port 3306
lsof -i :3306

# Lub zmień port w docker-compose.yml
ports:
  - "3307:3306"  # Użyj portu 3307 zamiast 3306
```

## 📚 Więcej Informacji

Szczegółowa dokumentacja znajduje się w pliku `README-Docker.md`.
