# Extended Dataset - Rozbudowany Zestaw Testowy

Ten katalog zawiera rozbudowany zestaw danych testowych dla aplikacji PredictionCup.

## 📊 Zawartość

### Pliki SQL (wykonywane w kolejności numerycznej):
1. **00-create-tables.sql** - Struktura bazy danych (tabele, indeksy, klucze obce)
2. **01-users.sql** - 40 użytkowników z polskimi imionami i nazwiskami
3. **02-countries-groups.sql** - Kraje i grupy z eliminacji MŚ
4. **03-matches.sql** - 20 zakończonych meczów z wynikami
5. **04-predictions.sql** - 800 typów (40 użytkowników × 20 meczy)
6. **05-leagues.sql** - Liga główna i przypisanie użytkowników

## 👥 Użytkownicy (40)

### Krótkie imiona i nazwiska:
- Ola Kot, Jan Lis, Ewa Bąk, Ada Dąb, Leo Łuk
- Ida Łoś, Mia Pęk, Zosia Róg, Maja Szyk, Nina Żuk

### Średnie imiona i nazwiska:
- Anna Nowak, Piotr Wiśniewski, Kasia Wójcik, Tomasz Mazur
- Magda Piotrowska, Michał Zieliński, Julia Krawczyk, i inne...

### Długie imiona i nazwiska (z polskimi znakami):
- Magdalena Kowalska, Aleksandra Szymańska, Katarzyna Dąbrowska
- Sebastian Kozłowski, Małgorzata Jankowska, Krzysztof Wojciechowski
- Agnieszka Kwiatkowska, Bartłomiej Kaczmarek, i inne...

**Hasło dla wszystkich:** `123qweasd`

## ⚽ Mecze (20)

### Wybrane mecze z eliminacji MŚ 2026:
- **Grupa I**: Estonia vs Izrael (1:3), Norwegia vs Włochy (3:0), Włochy vs Mołdawia (2:0), Estonia vs Norwegia (0:1), Włochy vs Estonia (5:0)
- **Grupa J**: Walia vs Liechtenstein (3:0), Macedonia Północna vs Belgia (0:0)
- **Grupa L**: Chorwacja vs Czechy (5:1)
- **Grupa G**: Malta vs Litwa (0:0), Finlandia vs Holandia (0:2), Finlandia vs Polska (2:1), Holandia vs Malta (8:0), Holandia vs Polska (1:1), Polska vs Finlandia (3:1)
- **Grupa H**: Bośnia i Hercegowina vs San Marino (1:0), Austria vs Rumunia (2:1)
- **Grupa A**: Słowacja vs Niemcy (2:0)
- **Grupa E**: Bułgaria vs Hiszpania (0:3), Turcja vs Hiszpania (0:6)
- **Grupa D**: Ukraina vs Francja (0:2)

## 🎯 Typy (800 predykcji)

Każdy z 40 użytkowników ma wytypowane wszystkie 20 meczy. Scenariusze obejmują:

### Dokładne trafienia (3 punkty):
- Użytkownicy trafili dokładny wynik meczu
- Przykład: Estonia 1:3 Izrael - użytkownicy 1, 2, 3 wytypowali dokładnie 1:3

### Trafiony wynik bez dokładnego wyniku (1 punkt):
- Użytkownicy trafili zwycięzcę lub remis, ale nie dokładny wynik
- Przykład: Estonia 1:3 Izrael - użytkownicy 4, 5, 6 wytypowali 0:2, 2:4, 0:1

### Nietrafione (0 punktów):
- Użytkownicy nie trafili nawet zwycięzcy
- Różnorodne błędne typy dla testowania scenariuszy

## 🏆 Liga

- **Liga Główna** - wszystkich 40 użytkowników należy do jednej ligi
- Możliwość testowania rankingu z dużą liczbą uczestników

## 🚀 Jak używać

### Opcja 1: Zmienna środowiskowa (zalecane)
```bash
cd /Users/mszarek/workspace/coderslab/demo
echo "MYSQL_INIT_FOLDER=init-extended" > .env
docker-compose down -v
docker-compose up -d
```

### Opcja 2: Bezpośrednia zmiana w docker-compose.yml
```yaml
volumes:
  - ./docker/mysql/init-extended:/docker-entrypoint-initdb.d
```

### Opcja 3: Kopiowanie plików
```bash
cp docker/mysql/init-extended/* docker/mysql/init/
docker-compose down -v
docker-compose up -d
```

## 📝 Uwagi

- **WAŻNE**: Użyj `docker-compose down -v` aby usunąć stary volume przed załadowaniem nowych danych
- Wszystkie nazwy z polskimi znakami (ą, ć, ę, ł, ń, ó, ś, ź, ż) są prawidłowo zakodowane w UTF-8
- Hasła są zaszyfrowane BCrypt
- Dataset zawiera różnorodne scenariusze typowania dla kompleksowych testów

## 🎭 Scenariusze testowe

Ten zestaw pozwala przetestować:
- ✅ Ranking z dużą liczbą użytkowników (40)
- ✅ Krótkie i długie nazwy użytkowników
- ✅ Polskie znaki w nazwach (kapitalizacja)
- ✅ Różne wyniki typowania (dokładne, częściowe, błędne)
- ✅ Remisy i wysokie wyniki
- ✅ Porównywanie użytkowników
- ✅ Filtry i sortowanie
- ✅ Paginację danych
