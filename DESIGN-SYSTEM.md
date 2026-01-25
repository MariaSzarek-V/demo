# 🎨 PredictionCup - Design System Documentation

## 📋 Spis treści
1. [Wprowadzenie](#wprowadzenie)
2. [Typografia](#typografia)
3. [Kolory](#kolory)
4. [Spacing (Odstępy)](#spacing)
5. [Komponenty](#komponenty)
6. [Responsive Design](#responsive-design)
7. [Accessibility](#accessibility)
8. [Przykłady użycia](#przykłady-użycia)

---

## 🎯 Wprowadzenie

Design System PredictionCup jest oparty na sprawdzonych standardach:
- **Material Design Typography Scale** - spójne rozmiary czcionek
- **8pt Grid System** - konsystentne odstępy
- **WCAG 2.1 AA** - dostępność dla wszystkich użytkowników

### Główne zasady:
✅ **Spójność** - te same elementy wyglądają tak samo w całej aplikacji  
✅ **Czytelność** - hierarchia wizualna prowadzi użytkownika  
✅ **Dostępność** - łatwe w użyciu dla wszystkich  
✅ **Responsywność** - działa na wszystkich urządzeniach

---

## 📝 Typografia

### Skala typograficzna (Perfect Fourth: 1.333)

Używamy modularnej skali opartej na proporcjach matematycznych:

| Poziom | Rozmiar | Zastosowanie | Przykład |
|--------|---------|--------------|----------|
| **3xl** | 50.5px | Hero headings | Landing pages |
| **2xl** | 37.9px | `<h1>` - Główne nagłówki | "Moje mecze" |
| **xl**  | 28.4px | `<h2>` - Sekcje | "Lista meczy" |
| **lg**  | 21.3px | `<h3>` - Podsekcje | Nazwy kart |
| **md**  | 18px   | `<h4>` - Wyróżnienia | |
| **base**| 16px   | **BAZOWY** - Body text | Tekst standardowy |
| **sm**  | 14px   | `small` - Drobne info | Metadane, daty |
| **xs**  | 12px   | Labele, badges | Status, tagi |

### Użycie w HTML:

```html
<!-- Nagłówki -->
<h1>Moje mecze</h1>          <!-- 37.9px, bold -->
<h2>Ranking graczy</h2>       <!-- 28.4px, bold -->
<h3>Top 10</h3>               <!-- 21.3px, semibold -->

<!-- Tekst -->
<p>Standardowy tekst</p>      <!-- 16px -->
<p class="text-lg">Większy tekst</p>  <!-- 18px -->
<small>Drobny tekst</small>   <!-- 14px -->
```

### Font Weights (Grubości):

```css
.font-light      /* 300 - rzadko używane */
.font-normal     /* 400 - standardowy tekst */
.font-medium     /* 500 - przyciski, labele */
.font-semibold   /* 600 - podtytuły */
.font-bold       /* 700 - nagłówki */
```

---

## 🎨 Kolory

### Paleta główna:
```css
/* Primary - Niebieski */
--primary: #4e73df
--primary-dark: #2e59d9
--primary-light: #6f8ff5

/* Grayscale */
--gray-50: #f9fafb
--gray-100: #f3f4f6
--gray-200: #e5e7eb
--gray-300: #d1d5db
--gray-600: #6b7280
--gray-900: #374151

/* Semantic colors */
--success: #1cc88a   /* Zielony */
--danger: #e74a3b    /* Czerwony */
--warning: #f6c23e   /* Żółty */
--info: #36b9cc      /* Cyjan */
```

---

## 📏 Spacing (Odstępy) - 8pt Grid

Wszystkie odstępy są wielokrotnością 8px:

| Klasa | Wartość | Pixele | Użycie |
|-------|---------|--------|--------|
| `space-1` | 0.5rem | 8px | Małe odstępy, gap |
| `space-2` | 1rem | 16px | **Standardowe** |
| `space-3` | 1.5rem | 24px | Między sekcjami |
| `space-4` | 2rem | 32px | Duże odstępy |
| `space-5` | 2.5rem | 40px | Bardzo duże |
| `space-6` | 3rem | 48px | Główne sekcje |
| `space-8` | 4rem | 64px | Hero sections |

### Utility classes:

```html
<!-- Margin -->
<div class="mt-4">Margines top: 32px</div>
<div class="mb-3">Margines bottom: 24px</div>

<!-- Padding -->
<div class="p-4">Padding: 32px</div>
<div class="p-3">Padding: 24px</div>
```

---

## 🧩 Komponenty

### Przyciski

```html
<!-- Rozmiary -->
<button class="btn btn-primary">Standardowy (44px)</button>
<button class="btn btn-lg btn-primary">Duży (48px)</button>
<button class="btn btn-sm btn-primary">Mały (36px)</button>
<button class="btn btn-xs btn-primary">Mini (32px)</button>

<!-- Warianty -->
<button class="btn btn-primary">Primary</button>
<button class="btn btn-outline-primary">Outline</button>
<button class="btn btn-danger">Danger</button>
<button class="btn btn-success">Success</button>
```

**Zasady:**
- Minimum 44px wysokości (WCAG touch target)
- Padding: 16px góra/dół, 24px lewo/prawo
- Font: 16px, medium weight
- Border radius: 8px

### Formularze

```html
<!-- Input -->
<label class="form-label">Email</label>
<input type="email" class="form-control" placeholder="twoj@email.pl">

<!-- Select -->
<select class="form-select">
  <option>Wybierz opcję</option>
</select>

<!-- Rozmiary -->
<input class="form-control form-control-lg">  <!-- 48px -->
<input class="form-control">                   <!-- 44px -->
<input class="form-control form-control-sm">   <!-- 36px -->
```

**Zasady:**
- Minimum 44px wysokości
- Padding: 16px
- Border: 1px solid #d1d5db
- Focus: niebieski border + cień

### Karty (Cards)

```html
<div class="card">
  <div class="card-header">
    <h3 class="card-title">Tytuł karty</h3>
  </div>
  <div class="card-body">
    <p>Treść karty...</p>
  </div>
</div>
```

**Zasady:**
- Border radius: 12px
- Padding: 32px w body
- Cień: subtleny shadow-sm
- Margines dolny: 32px

### Tabele

```html
<table class="table table-bordered">
  <thead>
    <tr>
      <th>Nagłówek</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Komórka</td>
    </tr>
  </tbody>
</table>
```

**Zasady:**
- Font size: 14px
- Nagłówki: uppercase, semibold, 14px
- Padding: 16px
- Hover: lekkie tło #f9fafb

---

## 📱 Responsive Design

### Breakpointy:

```css
/* Mobile First! */
< 768px     /* Mobile */
768-991px   /* Tablet */
> 992px     /* Desktop */
```

### Zmiany na mobile:

```css
/* Mobile (< 768px) */
- h1: 32px → mniejsze
- Przyciski: 48px wysokość (większe touch target)
- Przyciski: 100% szerokość
- Padding kart: 24px
- Font tables: 12px
```

**Przykład responsive utility:**

```html
<!-- Ukryj na mobile -->
<div class="d-none d-md-block">Tylko desktop</div>

<!-- Pełna szerokość na mobile -->
<button class="btn btn-primary w-100 w-md-auto">Przycisk</button>
```

---

## ♿ Accessibility (WCAG 2.1 AA)

### Minimalne wymagania:

✅ **Touch Targets:** min. 44x44px (przyciski, linki, inputy)  
✅ **Kontrast:** min. 4.5:1 dla tekstu  
✅ **Focus:** widoczny outline 2px  
✅ **Font Size:** min. 16px dla body  
✅ **Line Height:** min. 1.5 dla body tekstu

### Implementacja:

```html
<!-- Accessible button -->
<button 
  class="btn btn-primary" 
  aria-label="Typuj wynik meczu">
  Typuj
</button>

<!-- Accessible form -->
<label for="email" class="form-label">Email</label>
<input 
  type="email" 
  id="email" 
  class="form-control"
  aria-describedby="email-help"
  required>
<small id="email-help">Podaj swój email</small>
```

---

## 💡 Przykłady użycia

### Przykład 1: Karta z meczem

```html
<div class="card">
  <div class="card-body">
    <h3 class="h4 mb-3">Real Madrid vs Barcelona</h3>
    <p class="text-sm text-gray-600 mb-3">15 stycznia 2026, 20:00</p>
    
    <div class="d-flex gap-2">
      <button class="btn btn-primary">
        <i class="fas fa-futbol"></i>
        Typuj wynik
      </button>
      <button class="btn btn-outline-primary btn-sm">
        Zobacz szczegóły
      </button>
    </div>
  </div>
</div>
```

### Przykład 2: Formularz typowania

```html
<form class="p-4">
  <h2 class="h3 mb-4">Typuj wynik</h2>
  
  <div class="mb-3">
    <label for="home-score" class="form-label">Wynik gospodarzy</label>
    <input 
      type="number" 
      id="home-score" 
      class="form-control"
      min="0"
      required>
  </div>
  
  <div class="mb-3">
    <label for="away-score" class="form-label">Wynik gości</label>
    <input 
      type="number" 
      id="away-score" 
      class="form-control"
      min="0"
      required>
  </div>
  
  <button type="submit" class="btn btn-primary btn-lg w-100">
    Zapisz typ
  </button>
</form>
```

### Przykład 3: Ranking

```html
<div class="card">
  <div class="card-header">
    <h2 class="h3 mb-0">Ranking graczy</h2>
  </div>
  <div class="card-body">
    <table class="table">
      <thead>
        <tr>
          <th>Miejsce</th>
          <th>Gracz</th>
          <th>Punkty</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td class="font-semibold">1</td>
          <td>Jan Kowalski</td>
          <td><span class="badge bg-success">250 pkt</span></td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
```

---

## 🚀 Najlepsze praktyki

### ✅ DO:

1. **Używaj zmiennych CSS** zamiast hardcoded wartości
   ```css
   /* ✅ Dobrze */
   padding: var(--space-3);
   
   /* ❌ Źle */
   padding: 24px;
   ```

2. **Konsekwentna hierarchia**
   ```html
   <!-- ✅ Dobrze -->
   <h1>Strona główna</h1>
   <h2>Sekcja meczy</h2>
   <h3>Real Madrid vs Barcelona</h3>
   
   <!-- ❌ Źle - pominięto h2 -->
   <h1>Strona główna</h1>
   <h3>Real Madrid vs Barcelona</h3>
   ```

3. **Mobile first**
   ```css
   /* ✅ Dobrze */
   .btn { width: 100%; }
   @media (min-width: 768px) {
     .btn { width: auto; }
   }
   ```

### ❌ DON'T:

1. ❌ Nie używaj inline styles
2. ❌ Nie łam hierarchii nagłówków
3. ❌ Nie twórz własnych rozmiarów czcionek
4. ❌ Nie używaj odstępów poza 8pt grid
5. ❌ Nie pomijaj labelek w formularzach

---

## 📚 Referencje

- [Material Design](https://material.io/design/typography/the-type-system.html)
- [WCAG 2.1](https://www.w3.org/WAI/WCAG21/quickref/)
- [8pt Grid System](https://spec.fm/specifics/8-pt-grid)
- [Type Scale Calculator](https://type-scale.com/)

---

## 📞 Wsparcie

Pytania? Problemy z Design System?
- Sprawdź plik: `src/main/resources/static/css/design-system.css`
- Zobacz komentarze w kodzie CSS

---

**Wersja:** 1.0  
**Data:** 15 stycznia 2026  
**Autor:** PredictionCup Team
