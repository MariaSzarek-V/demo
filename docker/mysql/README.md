# MySQL Init Folders - Zestawy Danych

Ten katalog zawiera różne zestawy danych inicjalizacyjnych dla bazy MySQL.

## 📁 Dostępne Zestawy

### 1. `init/` (domyślny)
Pełny zestaw demo z danymi o Mistrzostwach Świata:
- Kraje i grupy MŚ 2026
- Wszystkie mecze eliminacyjne i finałowe
- Przykładowi użytkownicy
- Typy i predykcje
- Posty, komentarze, czat
- Historia rankingu

**Kiedy używać:** Dla pełnej demonstracji aplikacji

### 2. `init-extended/` (rozbudowany)
Rozbudowany zestaw testowy:
- **40 użytkowników** (krótkie i długie polskie imiona z polskimi znakami)
- **20 zakończonych meczów** z wynikami
- **800 typów** (40 × 20) z różnorodnymi scenariuszami:
  - Dokładne trafienia (3 pkt)
  - Trafione wyniki (1 pkt)
  - Nietrafione (0 pkt)
- Liga główna ze wszystkimi użytkownikami

**Kiedy używać:**
- Testowanie rankingu z dużą liczbą użytkowników
- Testowanie kapitalizacji polskich znaków
- Testowanie różnych scenariuszy typowania
- Testy wydajnościowe

Szczegóły: [init-extended/README.md](init-extended/README.md)

## 🚀 Jak Przełączać Zestawy

### Metoda 1: Zmienna środowiskowa (ZALECANE)

1. Edytuj plik `.env` w głównym katalogu projektu:
```bash
MYSQL_INIT_FOLDER=init-extended
```

2. Restart kontenerów z usunięciem volume:
```bash
docker-compose down -v
docker-compose up -d
```

### Metoda 2: Bezpośrednia zmiana w docker-compose.yml

Zmień linię w sekcji `mysql.volumes`:
```yaml
- ./docker/mysql/init-extended:/docker-entrypoint-initdb.d
```

### Metoda 3: Kopiowanie plików

```bash
cp docker/mysql/init-extended/* docker/mysql/init/
docker-compose down -v
docker-compose up -d
```

## ⚠️ Ważne Uwagi

1. **Zawsze używaj `docker-compose down -v`** przed zmianą zestawu danych
   - Flaga `-v` usuwa volumes, co pozwala na załadowanie nowych danych
   - Bez tej flagi stare dane pozostaną w bazie

2. **Dane są ładowane tylko przy pierwszym uruchomieniu kontenera MySQL**
   - Jeśli kontener już istnieje z danymi, nowe skrypty SQL NIE zostaną wykonane

3. **Pliki SQL są wykonywane w kolejności alfabetycznej**
   - Dlatego używamy prefiksów numerycznych (00-, 01-, 02-, etc.)

## 📝 Tworzenie Własnego Zestawu

1. Utwórz nowy katalog, np. `init-custom/`
2. Skopiuj strukturę z `init-extended/`:
   ```bash
   cp docker/mysql/init-extended/00-create-tables.sql docker/mysql/init-custom/
   ```
3. Dodaj własne pliki SQL z danymi (pamiętaj o numeracji!)
4. Ustaw w `.env`:
   ```
   MYSQL_INIT_FOLDER=init-custom
   ```

## 🔍 Weryfikacja Załadowanych Danych

Po uruchomieniu kontenerów, sprawdź dane:

```bash
# Zaloguj się do MySQL
docker exec -it prediction-mysql mysql -uroot -proot betsdb

# Sprawdź liczbę użytkowników
SELECT COUNT(*) FROM user;

# Sprawdź liczbę meczy
SELECT COUNT(*) FROM game;

# Sprawdź liczbę typów
SELECT COUNT(*) FROM prediction;
```

## 🆘 Rozwiązywanie Problemów

### Dane się nie załadowały
```bash
# Usuń volume i spróbuj ponownie
docker-compose down -v
docker volume rm demo_mysql_data
docker-compose up -d
```

### Błędy SQL podczas ładowania
```bash
# Zobacz logi kontenera MySQL
docker logs prediction-mysql

# Sprawdź czy wszystkie pliki SQL mają prawidłowe kodowanie UTF-8
file docker/mysql/init-extended/*.sql
```

### Kontener MySQL nie startuje
```bash
# Sprawdź logi
docker logs prediction-mysql

# Sprawdź czy port 3306 nie jest zajęty
lsof -i :3306
```
