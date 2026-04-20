# MySQL Init Folder - Dane Inicjalizacyjne

Ten katalog zawiera skrypty inicjalizacyjne dla bazy MySQL.

## 📁 Struktura

### `init/`
Pełny zestaw demo z danymi o Mistrzostwach Świata:
- Kraje i grupy MŚ 2026
- Wszystkie mecze eliminacyjne i finałowe
- Przykładowi użytkownicy
- Typy i predykcje
- Posty, komentarze, czat
- Historia rankingu

## 🔄 Jak Działa Inicjalizacja

**Pliki SQL są wykonywane TYLKO przy pierwszym uruchomieniu kontenera MySQL**, gdy baza jest pusta:

1. Kontener MySQL automatycznie wykonuje wszystkie pliki z katalogu `init/`
2. Pliki są przetwarzane w **kolejności alfabetycznej** (dlatego numerujemy: 00-, 01-, 02-, etc.)
3. Po wykonaniu skryptów, dane są zapisywane w **volume** (`mysql_data`)
4. Przy kolejnych uruchomieniach kontenera, skrypty **NIE są wykonywane ponownie**

## ⚠️ Ważne Uwagi

1. **Zawsze używaj `docker-compose down -v`** przed przeładowaniem danych
   - Flaga `-v` usuwa volumes, co pozwala na załadowanie nowych danych
   - Bez tej flagi stare dane pozostaną w bazie

2. **Dane są ładowane tylko przy pierwszym uruchomieniu**
   - Jeśli kontener już istnieje z danymi, nowe skrypty SQL NIE zostaną wykonane

3. **Pliki SQL są wykonywane alfabetycznie**
   - Używamy prefiksów numerycznych (00-, 01-, 02-, etc.)

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
file docker/mysql/init/*.sql
```

### Kontener MySQL nie startuje
```bash
# Sprawdź logi
docker logs prediction-mysql

# Sprawdź czy port 3306 nie jest zajęty
lsof -i :3306
```

## 📚 Więcej Informacji

Szczegółowe instrukcje zarządzania bazą danych: [DATABASE-MANAGEMENT.md](../../DATABASE-MANAGEMENT.md)
