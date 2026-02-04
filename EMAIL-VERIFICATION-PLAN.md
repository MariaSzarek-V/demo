# 📧 Plan Implementacji Weryfikacji Email z Gmail SMTP

## 🎯 Cel
Dodanie funkcjonalności weryfikacji email przy rejestracji użytkownika z wykorzystaniem Gmail SMTP.

---

## 📋 Kroki Konfiguracji Gmail SMTP

### 1. Przygotowanie Konta Gmail

#### Opcja A: Hasło Aplikacji (Zalecane)

1. **Zaloguj się do konta Gmail**
   - Przejdź do: https://myaccount.google.com/

2. **Włącz 2-Step Verification (2FA)**
   - Security → 2-Step Verification → Get started
   - Postępuj zgodnie z instrukcjami

3. **Wygeneruj Hasło Aplikacji**
   - Przejdź do: https://myaccount.google.com/apppasswords
   - Lub: Security → 2-Step Verification → App passwords
   - Wybierz "Mail" i "Other (Custom name)"
   - Wpisz nazwę np. "PredictionCup"
   - Kliknij "Generate"
   - **ZAPISZ 16-znakowe hasło** (format: xxxx xxxx xxxx xxxx)

#### Opcja B: Allow Less Secure Apps (Mniej bezpieczne, nie zalecane)

1. Przejdź do: https://myaccount.google.com/lesssecureapps
2. Włącz "Allow less secure apps: ON"
3. Użyj normalnego hasła do konta Gmail

---

## 🔧 Implementacja w Spring Boot

### Krok 1: Dodaj Dependency do `pom.xml`

```xml
<!-- Email Support -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

### Krok 2: Konfiguracja w `application.properties`

```properties
# Gmail SMTP Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=twoj-email@gmail.com
spring.mail.password=twoje-haslo-aplikacji
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000

# Application URL for verification links
app.base.url=http://localhost:8080
```

### Krok 3: Rozszerzenie Encji `User`

Dodaj pola do encji `User`:
```java
@Column(nullable = false)
private boolean enabled = false;  // Czy konto jest zweryfikowane

private String verificationToken;  // Token weryfikacyjny

@Column
private LocalDateTime tokenCreatedDate;  // Data utworzenia tokenu
```

### Krok 4: Utworzenie `VerificationToken` Entity (Opcjonalnie)

Alternatywnie, możesz stworzyć osobną tabelę dla tokenów:

```java
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    
    private LocalDateTime expiryDate;
}
```

### Krok 5: Utworzenie `EmailService`

```java
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${app.base.url}")
    private String baseUrl;
    
    public void sendVerificationEmail(User user, String token) {
        String recipientAddress = user.getEmail();
        String subject = "Email Verification - PredictionCup";
        String confirmationUrl = baseUrl + "/verify?token=" + token;
        
        String message = "Witaj " + user.getUsername() + "!\n\n" +
                        "Kliknij poniższy link, aby zweryfikować swój email:\n" +
                        confirmationUrl + "\n\n" +
                        "Link jest ważny przez 24 godziny.\n\n" +
                        "Pozdrawiamy,\nZespół PredictionCup";
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("noreply@predictioncup.com");
        
        mailSender.send(email);
    }
}
```

### Krok 6: Aktualizacja `UserService`

```java
@Service
public class UserService {
    @Autowired
    private EmailService emailService;
    
    public User registerUser(UserRegistrationDTO dto) {
        // Walidacja i tworzenie użytkownika
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(false);  // Konto nieaktywne do weryfikacji
        
        // Generowanie tokenu weryfikacyjnego
        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setTokenCreatedDate(LocalDateTime.now());
        
        userRepository.save(user);
        
        // Wysyłanie emaila weryfikacyjnego
        emailService.sendVerificationEmail(user, token);
        
        return user;
    }
    
    public boolean verifyUser(String token) {
        User user = userRepository.findByVerificationToken(token);
        
        if (user == null) {
            return false;
        }
        
        // Sprawdzenie czy token nie wygasł (24h)
        LocalDateTime tokenDate = user.getTokenCreatedDate();
        if (tokenDate.plusHours(24).isBefore(LocalDateTime.now())) {
            return false;
        }
        
        user.setEnabled(true);
        user.setVerificationToken(null);
        user.setTokenCreatedDate(null);
        userRepository.save(user);
        
        return true;
    }
}
```

### Krok 7: Utworzenie Endpointu Weryfikacyjnego

```java
@Controller
public class VerificationController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/verify")
    public String verifyUser(@RequestParam("token") String token, Model model) {
        boolean isVerified = userService.verifyUser(token);
        
        if (isVerified) {
            model.addAttribute("message", "Twoje konto zostało pomyślnie zweryfikowane! Możesz się teraz zalogować.");
            model.addAttribute("success", true);
        } else {
            model.addAttribute("message", "Link weryfikacyjny jest nieprawidłowy lub wygasł.");
            model.addAttribute("success", false);
        }
        
        return "verification-result";
    }
}
```

### Krok 8: Aktualizacja `SecurityConfig`

Dodaj endpoint `/verify` do publicznych:

```java
http.authorizeHttpRequests(auth -> auth
    .requestMatchers("/register", "/login", "/verify").permitAll()
    .anyRequest().authenticated()
);
```

### Krok 9: Dodanie Metody do `UserRepository`

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User findByVerificationToken(String token);
}
```

### Krok 10: Aktualizacja Migracji Bazy Danych

Dodaj do `00-create-tables.sql`:

```sql
ALTER TABLE user 
ADD COLUMN enabled BOOLEAN DEFAULT FALSE,
ADD COLUMN verification_token VARCHAR(255),
ADD COLUMN token_created_date TIMESTAMP;
```

### Krok 11: Utworzenie Szablonu `verification-result.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Weryfikacja Email</title>
</head>
<body>
    <div th:if="${success}">
        <h2>Sukces!</h2>
        <p th:text="${message}"></p>
        <a href="/login">Przejdź do logowania</a>
    </div>
    <div th:unless="${success}">
        <h2>Błąd weryfikacji</h2>
        <p th:text="${message}"></p>
        <a href="/register">Zarejestruj się ponownie</a>
    </div>
</body>
</html>
```

---

## 🔒 Uwagi Bezpieczeństwa

1. **Nigdy nie commituj prawdziwych danych do repozytorium**
   - Użyj zmiennych środowiskowych lub pliku `.env`
   - Dodaj `application-local.properties` do `.gitignore`

2. **Użyj Hasła Aplikacji zamiast normalnego hasła**

3. **Przykład z zmiennymi środowiskowymi:**

```properties
spring.mail.username=${GMAIL_USERNAME}
spring.mail.password=${GMAIL_APP_PASSWORD}
```

Następnie ustaw w systemie:
```bash
export GMAIL_USERNAME=twoj-email@gmail.com
export GMAIL_APP_PASSWORD=twoje-haslo-aplikacji
```

---

## ✅ Checklist Implementacji

- [ ] Włącz 2FA na koncie Gmail
- [ ] Wygeneruj hasło aplikacji Gmail
- [ ] Dodaj dependency `spring-boot-starter-mail` do `pom.xml`
- [ ] Skonfiguruj `application.properties` z danymi Gmail SMTP
- [ ] Rozszerz encję `User` o pola weryfikacyjne
- [ ] Utwórz `EmailService` do wysyłania emaili
- [ ] Zaktualizuj `UserService` z logiką weryfikacji
- [ ] Dodaj endpoint `/verify` w kontrolerze
- [ ] Zaktualizuj `SecurityConfig`
- [ ] Dodaj metodę `findByVerificationToken` do repozytorium
- [ ] Zaktualizuj schemat bazy danych
- [ ] Utwórz szablon `verification-result.html`
- [ ] Przetestuj rejestrację i weryfikację
- [ ] Zabezpiecz credentials (zmienne środowiskowe)

---

## 🧪 Testowanie

1. Zarejestruj nowego użytkownika z prawdziwym adresem email
2. Sprawdź inbox (również folder spam)
3. Kliknij link weryfikacyjny
4. Sprawdź czy konto zostało aktywowane w bazie danych
5. Spróbuj się zalogować

---

## 📝 Dodatkowe Ulepszenia

1. **Resend Verification Email** - możliwość ponownego wysłania emaila
2. **HTML Email Templates** - ładniejsze emaile z użyciem Thymeleaf
3. **Background Jobs** - wysyłanie emaili asynchronicznie
4. **Email Queuing** - kolejkowanie emaili w przypadku problemów z SMTP
5. **Monitoring** - logowanie wysłanych emaili
