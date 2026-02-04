# 📊 Propozycje Statystyk dla PredictionCup

## Aktualne Statystyki w Aplikacji

### Dashboard (zaimplementowane)
- ✅ Pozycja w rankingu + zmiana pozycji
- ✅ Suma wszystkich punktów
- ✅ Punkty za ostatni typ
- ✅ Historia pozycji w rankingu (wykres liniowy)
- ✅ Porównanie pozycji wszystkich użytkowników w czasie
- ✅ Statystyki typów: remisy vs zwycięstwa (donut chart)
- ✅ Najbliższe mecze z informacją o typach
- ✅ Ostatnie mecze z wynikami i punktami

---

## 🎯 Propozycje Nowych Statystyk

### 1. **Statystyki Dokładności Predykcji**

#### A. Procent trafień
- **Dokładne wyniki** - procent typów z dokładnym wynikiem (3 pkt)
- **Trafione rozstrzygnięcia** - procent typów z dobrym rozstrzygnięciem (1 lub 3 pkt)
- **Nietrafione** - procent całkowicie błędnych typów (0 pkt)
- **Wizualizacja**: Pie chart lub donut chart z 3 kategoriami

```java
// Przykładowe dane
{
    "exactScores": 15,      // 15% typów z dokładnym wynikiem
    "correctOutcome": 45,   // 45% typów z dobrym rozstrzygnięciem ale złym wynikiem
    "incorrect": 40         // 40% całkowicie błędnych
}
```

#### B. Średnia punktów na mecz
- Całkowita suma punktów / liczba typowanych meczów
- Porównanie ze średnią wszystkich użytkowników
- Trend w czasie (czy się poprawiasz?)
- **Wizualizacja**: Karta ze wskaźnikiem + wykres liniowy trendu

```java
{
    "userAverage": 1.35,
    "globalAverage": 1.12,
    "trend": "UP",  // UP, DOWN, STABLE
    "trendPercentage": 8.5  // +8.5%
}
```

---

### 2. **Statystyki Wyników i Bramek**

#### A. Średnia przewidywanych bramek
- Średnia typowanych bramek gospodarzy
- Średnia typowanych bramek gości
- Średnia łączna bramek w meczu
- Porównanie z rzeczywistymi średnimi
- **Wizualizacja**: Bar chart porównawczy

```java
{
    "predictedHomeGoalsAvg": 1.8,
    "actualHomeGoalsAvg": 1.5,
    "predictedAwayGoalsAvg": 1.2,
    "actualAwayGoalsAvg": 1.1,
    "predictedTotalGoalsAvg": 3.0,
    "actualTotalGoalsAvg": 2.6
}
```

#### B. Najczęściej typowane wyniki
- TOP 5 najczęściej typowanych wyników (np. 2-1, 1-1, 2-0)
- Skuteczność każdego z tych typów
- **Wizualizacja**: Tabela z procentami trafień

```java
{
    "mostCommonPredictions": [
        {
            "score": "2-1",
            "count": 12,
            "successRate": 25.0  // 25% z tych typów było dokładnych
        },
        {
            "score": "1-1",
            "count": 10,
            "successRate": 30.0
        }
    ]
}
```

#### C. Statystyki bramek
- Ile razy typowałeś 0 bramek (dla gospodarzy/gości)
- Najwyższy typowany wynik
- Ile razy typowałeś high-scoring game (ponad 4 bramki łącznie)
- **Wizualizacja**: Zestaw kart informacyjnych

---

### 3. **Analiza Faworytów vs Outsiderów**

#### A. Tendencje w typowaniu
- Procent typów na gospodarzy (wygrana gospodarzy)
- Procent typów na gości (wygrana gości)
- Procent typów na remis
- Skuteczność każdej z kategorii
- **Wizualizacja**: Stacked bar chart

```java
{
    "homeWinsPredicted": 45,      // 45% typów na gospodarzy
    "homeWinsCorrect": 60,        // 60% z nich było trafnych
    "awayWinsPredicted": 30,
    "awayWinsCorrect": 40,
    "drawsPredicted": 25,
    "drawsCorrect": 20
}
```

#### B. Bias Analysis
- Czy typujesz zbyt często na gospodarzy?
- Czy typujesz zbyt ostrożnie (za dużo remisów)?
- Porównanie z rzeczywistością
- **Wizualizacja**: Radar chart porównawczy

---

### 4. **Serie i Rekordy**

#### A. Aktualne serie
- **Najdłuższa seria punktowanych typów** (co najmniej 1 pkt)
- **Aktualna seria punktowanych typów**
- **Najdłuższa seria dokładnych trafień** (3 pkt)
- **Najdłuższa seria nietrafień** (0 pkt)
- **Wizualizacja**: Karty z badge'ami "🔥 HOT STREAK"

```java
{
    "currentPointsStreak": 5,        // 5 meczów z rzędu z punktami
    "longestPointsStreak": 8,        // rekord
    "currentExactStreak": 2,
    "longestExactStreak": 3,
    "currentMissStreak": 0,
    "longestMissStreak": 4
}
```

#### B. Rekordy użytkownika
- Najwięcej punktów w jednej kolejce/rundzie
- Największy skok w rankingu (+X miejsc)
- Największy spadek w rankingu (-X miejsc)
- **Wizualizacja**: Timeline z najważniejszymi osiągnięciami

---

### 5. **Statystyki Czasowe i Aktywność**

#### A. Aktywność użytkownika
- Liczba typowanych meczów
- Procent typowanych meczów (z dostępnych)
- Mecze bez typu
- Średni czas do deadline (jak wcześnie typujesz?)
- **Wizualizacja**: Progress bar + informacje

```java
{
    "totalPredictions": 45,
    "totalGamesAvailable": 50,
    "predictionRate": 90.0,  // 90% meczów typowane
    "missedGames": 5
}
```

#### B. Statystyki miesięczne/tygodniowe
- Punkty w tym miesiącu
- Punkty w tym tygodniu
- Porównanie z poprzednim okresem
- **Wizualizacja**: Karty z trendem (↑↓)

---

### 6. **Porównania z Innymi Użytkownikami**

#### A. Head-to-Head
- Porównaj się z wybranym użytkownikiem
- Kto ma lepszy bilans?
- Wspólne mecze - kto więcej punktów?
- **Wizualizacja**: Porównawcza tabela

```java
{
    "user1": "JanKowalski",
    "user2": "AnnaNowak",
    "user1Points": 67,
    "user2Points": 72,
    "commonGames": 45,
    "user1Wins": 18,      // w ilu meczach JanKowalski miał więcej pkt
    "user2Wins": 22,
    "draws": 5            // w ilu meczach mieli tyle samo pkt
}
```

#### B. Pozycja w różnych kategoriach
- Miejsce w rankingu ogólnym
- Miejsce w rankingu dokładności (exact predictions)
- Miejsce w rankingu consistency (najmniej 0 pkt)
- **Wizualizacja**: Tabela z mini rankingami

---

### 7. **Statystyki Zaawansowane**

#### A. Form Guide (Ostatnia forma)
- Punkty z ostatnich 5 meczów
- Punkty z ostatnich 10 meczów
- Wizualna reprezentacja (W-W-L-W-D)
- **Wizualizacja**: Badge timeline (🟢🟢🔴🟢🟡)

```java
{
    "last5Games": [3, 1, 0, 3, 1],    // punkty
    "last5Total": 8,
    "last5Average": 1.6,
    "last10Total": 14,
    "last10Average": 1.4
}
```

#### B. Konsystencja
- Odchylenie standardowe punktów
- Czy jesteś stabilny czy chaotyczny?
- **Wizualizacja**: Wskaźnik z opisem "KONSYSTENTNY" / "ZMIENNY"

```java
{
    "standardDeviation": 1.2,
    "consistency": "HIGH",  // HIGH, MEDIUM, LOW
    "description": "Zdobywasz punkty bardzo regularnie"
}
```

#### C. Momentum Analysis
- Trend ostatnich wyników
- Czy idziesz w górę czy w dół?
- Predykcja kolejnej pozycji
- **Wizualizacja**: Wykres z trendem

---

### 8. **Statystyki Społeczne**

#### A. Ranking przyjaciół
- Stwórz grupę znajomych
- Porównaj wyniki tylko z nimi
- Mini-liga
- **Wizualizacja**: Osobna tabela rankingu

#### B. Najbliżsi rywale
- Użytkownicy +/- 3 miejsca w rankingu
- Różnica punktów do nich
- Jak często ich wyprzedzasz?
- **Wizualizacja**: Compact ranking table

```java
{
    "rivals": [
        {
            "position": 8,
            "username": "UserA",
            "points": 72,
            "pointsDifference": 5  // o ile punktów wyprzedza Cię
        },
        // twoja pozycja: 10, 67 pkt
        {
            "position": 11,
            "username": "UserB",
            "points": 65,
            "pointsDifference": -2  // o ile punktów ty go wyprzedzasz
        }
    ]
}
```

---

### 9. **Wizualizacje i Wykresy**

#### A. Heat Map
- Godziny/dni kiedy typujesz mecze
- Skuteczność w różnych dniach tygodnia
- **Wizualizacja**: Calendar heatmap

#### B. Scatter Plot
- Przewidywane vs rzeczywiste wyniki
- Czy przewidujesz defensywne czy ofensywne mecze?
- **Wizualizacja**: Scatter plot z linią trendu

#### C. Box Plot
- Rozkład Twoich punktów vs innych użytkowników
- Outliers - najlepsze i najgorsze typy
- **Wizualizacja**: Box plot

---

### 10. **Gamification i Achievements**

#### A. Odznaki i osiągnięcia
- 🎯 **Snajper** - 5 dokładnych typów z rzędu
- 🔥 **Hot Streak** - 10 meczów z punktami
- 📈 **Rising Star** - +5 miejsc w rankingu
- 🏆 **Lider** - 1. miejsce w rankingu
- 👑 **Król Remisów** - najwięcej trafionych remisów
- ⚽ **Goal Machine** - najlepiej typujesz high-scoring games
- 🎓 **Ekspert** - 80%+ trafień rozstrzygnięć
- **Wizualizacja**: Grid z odznaczkami

```java
{
    "achievements": [
        {
            "id": "sniper",
            "name": "Snajper",
            "description": "5 dokładnych typów z rzędu",
            "unlocked": true,
            "unlockedDate": "2026-01-15",
            "progress": 5,
            "target": 5
        },
        {
            "id": "hot_streak",
            "name": "Hot Streak",
            "description": "10 meczów z punktami z rzędu",
            "unlocked": false,
            "progress": 7,
            "target": 10
        }
    ]
}
```

#### B. Progress do następnego poziomu
- System poziomów (Level 1-100)
- Doświadczenie za typowanie i punkty
- **Wizualizacja**: Progress bar z levelem

---

## 🎨 Rekomendacje Implementacji

### Priorytet 1 (Must-Have) - Podstawowe rozszerzenia
1. **Statystyki dokładności** - procent trafień (exact/correct/miss)
2. **Średnia punktów na mecz** - z trendem
3. **Aktualne serie** - hot streaks
4. **Aktywność** - ile meczów typowane
5. **Form guide** - ostatnie 5 meczów

### Priorytet 2 (Should-Have) - Średnio zaawansowane
6. **Najczęściej typowane wyniki**
7. **Bias analysis** - gospodarze vs goście vs remis
8. **Rekordy osobiste**
9. **Najbliżsi rywale w rankingu**
10. **Średnie bramek** - predicted vs actual

### Priorytet 3 (Nice-to-Have) - Zaawansowane
11. **Head-to-Head porównania**
12. **Konsystencja i momentum**
13. **Statystyki miesięczne/tygodniowe**
14. **Achievements system**
15. **Heat mapy i advanced visualizations**

---

## 📋 Przykładowy Layout Dashboard z Nowymi Statystykami

```
┌─────────────────────────────────────────────────────────────┐
│  PODSUMOWANIE                                               │
│  ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐          │
│  │ Poz: #3 │ │ 67 pkt  │ │ Avg:1.4 │ │ 🔥 5    │          │
│  │ ↑ +2    │ │ +3 pkt  │ │ (+0.2)  │ │ streak  │          │
│  └─────────┘ └─────────┘ └─────────┘ └─────────┘          │
└─────────────────────────────────────────────────────────────┘

┌──────────────────────────┐  ┌───────────────────────────────┐
│  DOKŁADNOŚĆ TYPÓW        │  │  OSTATNIA FORMA (Last 5)      │
│  ┌────────────────────┐  │  │  🟢 🟢 🔴 🟢 🟡               │
│  │  Donut Chart:      │  │  │  3pt 3pt 0pt 3pt 1pt          │
│  │  Exact: 15%        │  │  │  Total: 10 / 15 points        │
│  │  Correct: 45%      │  │  │  Average: 2.0 per game        │
│  │  Miss: 40%         │  │  └───────────────────────────────┘
│  └────────────────────┘  │
└──────────────────────────┘  ┌───────────────────────────────┐
                              │  NAJBLIŻSI RYWALE             │
┌──────────────────────────┐  │  #2 UserA    72 pkt (+5)     │
│  WYKRES POZYCJI          │  │  #3 TY       67 pkt          │
│  [Wykres liniowy]        │  │  #4 UserB    65 pkt (-2)     │
└──────────────────────────┘  └───────────────────────────────┘

┌──────────────────────────┐  ┌───────────────────────────────┐
│  STATYSTYKI TYPÓW        │  │  OSIĄGNIĘCIA                  │
│  Gospodarze: 45% (60%)   │  │  🎯 Snajper    ✅            │
│  Remisy: 25% (20%)       │  │  🔥 Hot Streak ⏳ 7/10       │
│  Goście: 30% (40%)       │  │  📈 Rising Star ✅           │
│  (% skuteczności)        │  │  👑 Król Remisów ❌          │
└──────────────────────────┘  └───────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│  NAJBLIŻSZE MECZE (istniejąca sekcja)                       │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│  OSTATNIE WYNIKI (istniejąca sekcja)                        │
└─────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Techniczne Szczegóły Implementacji

### Nowe repozytoria queries (przykłady)

```java
// PredictionRepository
@Query("SELECT COUNT(p) FROM Prediction p WHERE p.user.id = :userId")
Long countPredictionsByUserId(@Param("userId") Long userId);

@Query("SELECT p FROM Prediction p WHERE p.user.id = :userId AND p.calculated = true")
List<Prediction> findCalculatedPredictionsByUserId(@Param("userId") Long userId);

// UserPointsRepository  
@Query("SELECT AVG(up.points) FROM UserPoints up WHERE up.user.id = :userId")
Double getAveragePointsByUserId(@Param("userId") Long userId);

@Query("SELECT AVG(up.points) FROM UserPoints up")
Double getGlobalAveragePoints();

@Query("SELECT up FROM UserPoints up WHERE up.user.id = :userId ORDER BY up.id DESC")
List<UserPoints> findLastNByUserId(@Param("userId") Long userId, Pageable pageable);

// GameRepository
@Query("SELECT COUNT(g) FROM Game g WHERE g.gameStatus = :status")
Long countGamesByStatus(@Param("status") GameStatus status);
```

### Nowy DTO dla rozszerzonych statystyk

```java
@Data
@Builder
public class ExtendedStatsDTO {
    // Accuracy stats
    private AccuracyStats accuracyStats;
    
    // Streak stats
    private StreakStats streakStats;
    
    // Average stats
    private AverageStats averageStats;
    
    // Bias stats
    private BiasStats biasStats;
    
    // Activity stats
    private ActivityStats activityStats;
    
    // Form guide
    private FormGuide formGuide;
    
    // Achievements
    private List<Achievement> achievements;
}
```

---

## 📊 Dodatkowe Pomysły

### API Endpoints dla statystyk
- `GET /api/stats/user/{userId}/accuracy` - statystyki dokładności
- `GET /api/stats/user/{userId}/streaks` - serie
- `GET /api/stats/user/{userId}/comparison/{otherUserId}` - head-to-head
- `GET /api/stats/user/{userId}/achievements` - odznaki
- `GET /api/stats/global` - globalne statystyki

### Export danych
- Możliwość eksportu swoich statystyk do CSV/PDF
- Generowanie raportów okresowych
- Share stats - możliwość udostępnienia swoich statystyk

### Notyfikacje
- Powiadomienie gdy zbliżasz się do osiągnięcia
- Alert gdy masz serię (3+ trafień)
- Informacja o dużych zmianach w rankingu

---

## 🎯 Podsumowanie

Aplikacja PredictionCup ma ogromny potencjał do rozbudowy statystyk. Przedstawione propozycje można implementować stopniowo, zaczynając od najprostszych (Priorytet 1), które dają największą wartość użytkownikom.

Kluczowe zalety dodania statystyk:
- 📈 **Większe zaangażowanie** użytkowników
- 🎮 **Gamification** - achievement system
- 🏆 **Konkurencyjność** - porównania z innymi
- 📊 **Insighty** - użytkownicy widzą swoje mocne/słabe strony
- 🎯 **Cel** - dążenie do poprawy statystyk

---

*Dokument stworzony: 2026-01-25*
*Wersja: 1.0*
