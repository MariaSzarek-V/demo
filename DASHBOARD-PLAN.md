# 📊 Dashboard Użytkownika - Szczegółowy Plan

Plan implementacji dashboardu użytkownika dla aplikacji PredictionCup z wykresami i statystykami.

---

## 🎨 Layout Dashboardu

```
┌─────────────────────────────────────────────────────────────┐
│  HEADER - Witaj, [Username]!                                │
├─────────────────────────────────────────────────────────────┤
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────┐        │
│  │   Pozycja    │ │ Suma Punktów │ │ Ostatni Typ  │        │
│  │   w Ranku    │ │              │ │              │        │
│  │      #5      │ │     45       │ │    +3       │        │
│  │   (↑ +2)     │ │              │ │              │        │
│  └──────────────┘ └──────────────┘ └──────────────┘        │
├─────────────────────────────────────────────────────────────┤
│  WYKRES: Punkty w czasie (linia)                           │
├─────────────────────────────────────────────────────────────┤
│  ┌─────────────────────────┐ ┌───────────────────────────┐ │
│  │ WYKRES: Rozkład typów   │ │ WYKRES: Pozycja w rankingu│ │
│  │        (donut)          │ │        w czasie (linia)   │ │
│  └─────────────────────────┘ └───────────────────────────┘ │
├─────────────────────────────────────────────────────────────┤
│  Statystyki szczegółowe (karty)                            │
├─────────────────────────────────────────────────────────────┤
│  Najbliższe mecze do typowania (lista)                     │
├─────────────────────────────────────────────────────────────┤
│  Ostatnie wyniki (tabela)                                  │
└─────────────────────────────────────────────────────────────┘
```

---

## 📈 Wykresy do Implementacji

### 1. **Wykres Punktów w Czasie** (Główny - Line Chart)
**Typ**: Line Chart (wykres liniowy)  
**Pozycja**: Górna część, pełna szerokość  
**Dane**: Historia punktów po każdym meczu

```javascript
// Przykładowe dane
{
  labels: ['Mecz 1', 'Mecz 2', 'Mecz 3', 'Mecz 4', 'Mecz 5'],
  datasets: [{
    label: 'Twoje punkty',
    data: [0, 3, 3, 6, 9],
    borderColor: '#4e73df',
    backgroundColor: 'rgba(78, 115, 223, 0.05)'
  }, {
    label: 'Średnia grupy',
    data: [0, 2, 3, 4, 6],
    borderColor: '#858796',
    borderDash: [5, 5]
  }]
}
```

**Co pokazuje**:
- Skumulowane punkty w czasie
- Możliwość porównania ze średnią grupy (linia przerywana)
- Możliwość porównania z liderem

**Użyteczność**: ⭐⭐⭐⭐⭐ (MUST HAVE)

---

### 2. **Rozkład Typów** (Donut Chart)
**Typ**: Donut/Pie Chart  
**Pozycja**: Lewa strona pod głównym wykresem  
**Dane**: Statystyki trafności typów

```javascript
// Przykładowe dane
{
  labels: ['3 punkty (dokładny wynik)', '1 punkt (wynik meczu)', '0 punktów'],
  datasets: [{
    data: [5, 8, 7], // liczba typów
    backgroundColor: ['#1cc88a', '#f6c23e', '#e74a3b'],
    hoverBackgroundColor: ['#17a673', '#f4b619', '#e02d1b']
  }]
}
```

**Wyświetlane informacje**:
- 5 typów = 3 pkt (25% trafności dokładnej)
- 8 typów = 1 pkt (40% trafności wyniku)
- 7 typów = 0 pkt (35% nietrafione)

**W środku donut**: Celność % (np. "65% trafności")

**Użyteczność**: ⭐⭐⭐⭐⭐ (MUST HAVE)

---

### 3. **Historia Pozycji w Rankingu** (Line Chart)
**Typ**: Line Chart z odwróconą osią Y  
**Pozycja**: Prawa strona pod głównym wykresem  
**Dane**: Zmiana pozycji w rankingu po każdym meczu

```javascript
// Przykładowe dane
{
  labels: ['Po meczu 1', 'Po meczu 2', 'Po meczu 3', 'Po meczu 4'],
  datasets: [{
    label: 'Pozycja w rankingu',
    data: [8, 7, 5, 5], // pozycje (niższy = lepszy)
    borderColor: '#36b9cc',
    fill: true,
    backgroundColor: 'rgba(54, 185, 204, 0.1)'
  }]
}
```

**Ważne**: Oś Y odwrócona (1 na górze, najgorsze na dole)

**Dodatkowo pokazuj**:
- Strzałki ↑↓ przy zmianach
- Kolor zielony gdy pozycja rośnie (w górę)
- Kolor czerwony gdy pozycja spada

**Użyteczność**: ⭐⭐⭐⭐⭐ (MUST HAVE)

---

### 4. **Wykres Słupkowy - Porównanie z Innymi** (Bar Chart)
**Typ**: Horizontal Bar Chart  
**Pozycja**: Sekcja "Porównaj się z innymi"  
**Dane**: Top 5 użytkowników + Ty

```javascript
// Przykładowe dane
{
  labels: ['Jan (1)', 'Anna (2)', 'Piotr (3)', 'Ty (5)', 'Karol (4)'],
  datasets: [{
    label: 'Punkty',
    data: [52, 48, 45, 42, 44],
    backgroundColor: [
      '#f6c23e', // złoto - 1 miejsce
      '#858796', // srebro - 2 miejsce
      '#cd7f32', // brąz - 3 miejsce
      '#4e73df', // twój kolor - wyróżniony
      '#858796'
    ]
  }]
}
```

**Użyteczność**: ⭐⭐⭐⭐ (HIGH)

---

### 5. **Streak Counter** (Wizualizacja serii)
**Typ**: Custom component z wizualizacją  
**Pozycja**: Osobna karta  
**Dane**: Aktualna seria trafnych typów

```
┌─────────────────────────────────┐
│  🔥 STREAK - SERIA TRAFIEŃ     │
│                                 │
│  ✅ ✅ ✅ ❌ ✅ ✅             │
│                                 │
│  Aktualna seria: 2 mecze       │
│  Najdłuższa seria: 5 meczów    │
└─────────────────────────────────┘
```

**Użyteczność**: ⭐⭐⭐⭐ (HIGH)

---

### 6. **Wykres Kołowy - Typy Według Wyników** (Pie Chart)
**Typ**: Pie Chart  
**Pozycja**: Karta statystyk  
**Dane**: Rozkład typowanych wyników

```javascript
// Przykładowe dane
{
  labels: ['Wygrana gospodarzy', 'Remis', 'Wygrana gości'],
  datasets: [{
    data: [12, 5, 8], // ile razy typowałeś każdy wynik
    backgroundColor: ['#1cc88a', '#f6c23e', '#e74a3b']
  }]
}
```

**Dodatkowo**: Pokazuje czy gracz ma tendencję do typowania gospodarzy/gości

**Użyteczność**: ⭐⭐⭐ (MEDIUM)

---

### 7. **Heatmap Kalendarza** (Calendar Heatmap)
**Typ**: Calendar/Heatmap  
**Pozycja**: Sekcja aktywności  
**Dane**: Aktywność w typowaniu w ciągu ostatnich miesięcy

```
Styczeń
Po Wt Śr Cz Pt So Nd
                🟢 🟢
🟢 ⚪ 🟢 🟢 🟡 ⚪ 🟢
🟢 🟢 🟢 ⚪ 🟢 🟢 🔴

🟢 = 3 pkt
🟡 = 1 pkt
🔴 = 0 pkt
⚪ = brak meczu/typu
```

**Użyteczność**: ⭐⭐⭐ (MEDIUM - fajne wizualnie)

---

### 8. **Wykres Radarowy - Analiza Umiejętności** (Radar Chart)
**Typ**: Radar/Spider Chart  
**Pozycja**: Zaawansowane statystyki  
**Dane**: Umiejętności w różnych aspektach

```javascript
// Przykładowe dane
{
  labels: [
    'Dokładny wynik',
    'Wygrana gospodarzy',
    'Wygrana gości',
    'Remisy',
    'Wysokie wyniki (>3)',
    'Niskie wyniki (<2)'
  ],
  datasets: [{
    label: 'Twoja celność %',
    data: [25, 60, 45, 10, 70, 55],
    backgroundColor: 'rgba(78, 115, 223, 0.2)',
    borderColor: '#4e73df'
  }, {
    label: 'Średnia grupy',
    data: [20, 50, 50, 15, 55, 60],
    backgroundColor: 'rgba(133, 135, 150, 0.2)',
    borderColor: '#858796'
  }]
}
```

**Użyteczność**: ⭐⭐⭐⭐ (HIGH - świetne do analizy)

---

### 9. **Progress Bar - Postęp do Osiągnięć**
**Typ**: Progress bars / Gauge charts  
**Pozycja**: Sekcja osiągnięć  
**Dane**: Postęp w achievementach

```
┌─────────────────────────────────────────┐
│  🎯 Mistrz Typerów                      │
│  ████████░░░░░░░░ 8/10 dokładnych typów│
│                                         │
│  🔥 Seria Mistrza                       │
│  ██████░░░░░░░░░░ 3/5 serii trafień    │
│                                         │
│  📊 Aktywny Typer                       │
│  ████████████░░░░ 20/25 typów          │
└─────────────────────────────────────────┘
```

**Użyteczność**: ⭐⭐⭐⭐ (HIGH - gamification)

---

## 📋 Dodatkowe Komponenty Dashboardu

### Statystyki w Kartach (Cards)

```
┌──────────────┬──────────────┬──────────────┬──────────────┐
│  Pozycja     │ Suma Punktów │  Celność %   │ Ostatni Typ  │
│    #5        │     45       │    65%       │     +3       │
│  (↑ +2)      │              │              │              │
└──────────────┴──────────────┴──────────────┴──────────────┘

┌──────────────┬──────────────┬──────────────┬──────────────┐
│ Typów ogółem │ Dokładnych   │  Seria       │ Najdł. seria │
│     20       │      5       │     2        │      5       │
└──────────────┴──────────────┴──────────────┴──────────────┘
```

---

### Lista "Najbliższe Mecze"

```
┌─────────────────────────────────────────────────────────┐
│  🕐 Najbliższe mecze do typowania                       │
├─────────────────────────────────────────────────────────┤
│  ⚽ Polska - Niemcy          │  Za 2 dni     │ [TYP]    │
│  ⚽ Hiszpania - Francja      │  Za 3 dni     │ [TYP]    │
│  ⚽ Anglia - Włochy          │  Za 5 dni     │ [TYP]    │
└─────────────────────────────────────────────────────────┘
```

---

### Ostatnie Wyniki

```
┌─────────────────────────────────────────────────────────┐
│  📊 Ostatnie wyniki twoich typów                        │
├─────────────────────────────────────────────────────────┤
│  ✅ Polska 2:1 Niemcy    │ Typ: 2:1  │ +3 pkt │ Wczoraj│
│  ⚠️  Hiszpania 1:1 Francja│ Typ: 2:0  │ +0 pkt │ 2 dni  │
│  ✅ Anglia 3:1 Włochy    │ Typ: 2:1  │ +1 pkt │ 4 dni  │
└─────────────────────────────────────────────────────────┘
```

---

## 🛠️ Technologie do Wykresów

### Rekomendowane Biblioteki:

1. **Chart.js** (POLECAM! ⭐)
   - Pros: Łatwy w użyciu, darmowy, responsywny
   - Cons: Podstawowe wykresy
   - Link: https://www.chartjs.org/

2. **ApexCharts** (Najlepszy! ⭐⭐⭐)
   - Pros: Nowoczesny, interaktywny, dużo opcji
   - Cons: Trochę większy
   - Link: https://apexcharts.com/

3. **Recharts** (dla React, na przyszłość)
   - Pros: Komponentowy, deklaratywny
   - Cons: Wymaga React

---

## 📊 Priorytety Implementacji

### FAZA 1 - MVP (Must Have) ✅
1. ✅ Karty ze statystykami (pozycja, punkty, celność)
2. ✅ Wykres punktów w czasie (line chart)
3. ✅ Wykres rozkładu typów (donut chart)
4. ✅ Lista najbliższych meczów
5. ✅ Ostatnie wyniki

### FAZA 2 - Rozbudowa 📈
6. Historia pozycji w rankingu (line chart)
7. Streak counter
8. Porównanie z innymi (bar chart)

### FAZA 3 - Zaawansowane 🚀
9. Wykres radarowy (analiza umiejętności)
10. Calendar heatmap
11. Progress bars dla osiągnięć

---

## 💻 Przykład Integracji (Chart.js + Thymeleaf)

### 1. Dodaj Chart.js do projektu

W `header.html` lub `sneat-layout.html`:
```html
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
```

### 2. Stwórz Controller

```java
@Controller
public class DashboardController {
    
    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        
        // Statystyki
        DashboardStats stats = dashboardService.getUserStats(user.getId());
        model.addAttribute("stats", stats);
        
        // Dane do wykresów
        model.addAttribute("pointsHistory", stats.getPointsHistory());
        model.addAttribute("predictionBreakdown", stats.getPredictionBreakdown());
        
        return "dashboard";
    }
}
```

### 3. Stwórz Service

```java
@Service
public class DashboardService {
    
    public DashboardStats getUserStats(Long userId) {
        DashboardStats stats = new DashboardStats();
        
        // Pobierz wszystkie typy użytkownika
        List<Prediction> predictions = predictionRepository
            .findByUserIdOrderByGameDateAsc(userId);
        
        // Oblicz statystyki
        int total = predictions.size();
        int exact = (int) predictions.stream()
            .filter(p -> p.getPoints() == 3).count();
        int result = (int) predictions.stream()
            .filter(p -> p.getPoints() == 1).count();
        int wrong = total - exact - result;
        
        stats.setTotalPredictions(total);
        stats.setExactScores(exact);
        stats.setCorrectResults(result);
        stats.setWrong(wrong);
        stats.setAccuracy((exact + result) * 100.0 / total);
        
        // Historia punktów
        List<Integer> pointsHistory = new ArrayList<>();
        int cumulative = 0;
        for (Prediction p : predictions) {
            cumulative += p.getPoints();
            pointsHistory.add(cumulative);
        }
        stats.setPointsHistory(pointsHistory);
        
        return stats;
    }
}
```

### 4. Stwórz HTML Template (dashboard.html)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="container">
        <h1>Twój Dashboard</h1>
        
        <!-- Karty statystyk -->
        <div class="row">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-body">
                        <h5>Pozycja w rankingu</h5>
                        <h2 th:text="${stats.position}"></h2>
                    </div>
                </div>
            </div>
            <!-- więcej kart... -->
        </div>
        
        <!-- Wykres punktów -->
        <div class="row mt-4">
            <div class="col-12">
                <canvas id="pointsChart"></canvas>
            </div>
        </div>
        
        <!-- Wykres rozkładu -->
        <div class="row mt-4">
            <div class="col-md-6">
                <canvas id="breakdownChart"></canvas>
            </div>
        </div>
    </div>
    
    <script th:inline="javascript">
        // Wykres punktów w czasie
        const pointsCtx = document.getElementById('pointsChart');
        const pointsData = /*[[${stats.pointsHistory}]]*/ [];
        
        new Chart(pointsCtx, {
            type: 'line',
            data: {
                labels: pointsData.map((_, i) => 'Mecz ' + (i+1)),
                datasets: [{
                    label: 'Punkty',
                    data: pointsData,
                    borderColor: '#4e73df',
                    backgroundColor: 'rgba(78, 115, 223, 0.05)',
                    tension: 0.4
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Twoje punkty w czasie'
                    }
                }
            }
        });
        
        // Wykres donut - rozkład typów
        const breakdownCtx = document.getElementById('breakdownChart');
        new Chart(breakdownCtx, {
            type: 'doughnut',
            data: {
                labels: ['3 punkty', '1 punkt', '0 punktów'],
                datasets: [{
                    data: [
                        /*[[${stats.exactScores}]]*/,
                        /*[[${stats.correctResults}]]*/,
                        /*[[${stats.wrong}]]*/
                    ],
                    backgroundColor: ['#1cc88a', '#f6c23e', '#e74a3b']
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Rozkład twoich typów'
                    }
                }
            }
        });
    </script>
</body>
</html>
```

---

## 🎯 Podsumowanie

### Must-Have Wykresy (Start):
1. ⭐⭐⭐⭐⭐ **Punkty w czasie** (line) - pokazuje progres
2. ⭐⭐⭐⭐⭐ **Rozkład typów** (donut) - pokazuje skuteczność
3. ⭐⭐⭐⭐⭐ **Pozycja w rankingu** (line) - pokazuje rozwój

### Nice-to-Have (Później):
4. ⭐⭐⭐⭐ **Porównanie z innymi** (bar)
5. ⭐⭐⭐⭐ **Streak counter** (custom)
6. ⭐⭐⭐⭐ **Radar analiza** (radar)
7. ⭐⭐⭐ **Calendar heatmap** (visual)

---

**Ostatnia aktualizacja**: 2026-01-25  
**Autor**: Plan dla aplikacji PredictionCup
