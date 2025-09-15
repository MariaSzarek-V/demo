ADMIN
- CRUD dla game skonczony
- walidacja daty meczu dziala scheduled/finished
- zmiana statusu meczu na finished wywołuje:
  - podliczenie punktów za typ
  - zapis rankignu do bazy danych 
- admin veiw meczu - dostepne tylko dla admina dla innch tylko schedule 
- 

RANKING
- bezstanowy, aktualizuje sie przy każdym get

RANKING HISTORY
- historia rankignu, zmian pozycji userow, spadkow i awansow
- 

MECZ
- musi miec status na etapi kazdym

SPRAWDZIC 
- CZY MZNA DODAC WYNIK BEZ ZAKONCZENIA MECZU?


POST 
-przyjmuje niepelne requesty i nie nadpisuje nulli


// START GENAI
# 🏆 PredictionCup

PredictionCup to aplikacja webowa do **typowania wyników meczów**, z systemem punktacji i rankingiem graczy.  
Pozwala użytkownikom przewidywać wyniki, zdobywać punkty i rywalizować w rankingu.  
Administrator ma możliwość zarządzania meczami i rozliczania wyników.

---

## 📌 Funkcjonalności

### 🔹 Użytkownik
- Rejestracja za pomocą **adresu e‑mail** i **hasła** (Spring Security, walidacja).
- Logowanie i uwierzytelnianie.
- Możliwość **zmiany hasła** (aktualizacja tylko pola `password`).
- Przeglądanie listy dostępnych meczów.
- Typowanie wyniku danego meczu (przed jego rozpoczęciem).
- Zdobywanie punktów po zakończeniu meczu:
  - **3 punkty** – trafiony wynik dokładny.
  - **1 punkt** – trafiony zwycięzca/remis, ale zły wynik.
  - **0 punktów** – błędne typowanie.
- Podgląd **rankingu** z informacją o:
  - aktualnej pozycji,
  - zmianie miejsca względem poprzedniej kolejki.

---

### 🔹 Administrator
- Dodawanie nowych meczów.
- Edytowanie istniejących meczów.
- Usuwanie meczów (jeśli nie rozpoczęte lub przy spełnieniu warunków).
- Oznaczanie meczu jako **zakończony** — uruchamia naliczanie punktów dla wszystkich użytkowników.

---

## 🛠️ Technologie

- **Backend**:
  - [Spring Boot](https://spring.io/projects/spring-boot) `3.5.5`
  - Spring Data JPA
  - Spring Security (logowanie, role: USER / ADMIN)
  - Spring Validation
  - Lombok
  - MySQL (baza danych)
  - Springdoc OpenAPI (Swagger UI)

- **Frontend**:
  - [Thymeleaf](https://www.thymeleaf.org/)
  - Bootstrap 5
  - thymeleaf-extras-springsecurity6 (wyświetlanie treści zależnie od roli użytkownika)

---

## 📂 Struktura aplikacji

- `/` – Strona główna (lista meczów, możliwość typowania)
- `/login` – Logowanie
- `/register` – Rejestracja
- `/ranking` – Ranking użytkowników
- `/admin/matches` – Panel zarządzania meczami (ADMIN)
- `/users/{id}/password` – Zmiana hasła użytkownika

---

## ⚙️ Wymagania

- Java 17+
- Maven 3+
- MySQL 8+
- Przeglądarka internetowa

---

## 🚀 Uruchomienie projektu

1. **Sklonuj repozytorium**
   ```bash
   git clone https://github.com/twoje-repo/PredictionCup.git
   cd PredictionCup

// END GENAI

http://localhost:8080/swagger-ui.html

📅 Roadmap / Planowane funkcje
Historia typów użytkownika
Powiadomienia e‑mail o wynikach
Integracja z zewnętrznym API do pobierania wyników meczów
Tryb turniejowy
