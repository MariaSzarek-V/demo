# Strategia Testowania - Prediction Cup

## Spis treści
1. [Wprowadzenie](#wprowadzenie)
2. [Piramida testów](#piramida-testów)
3. [Typy testów](#typy-testów)
4. [Narzędzia](#narzędzia)
5. [Komponenty do przetestowania](#komponenty-do-przetestowania)
6. [Struktura testów](#struktura-testów)

## Wprowadzenie

Aplikacja Prediction Cup wymaga kompleksowego podejścia do testowania obejmującego:
- **Unit Tests** - testy jednostkowe logiki biznesowej
- **Integration Tests** - testy integracyjne z bazą danych i Spring Context
- **API Tests** - testy REST API z użyciem Karate Framework

## Piramida testów

```
           /\
          /  \
         / E2E \        Karate API Tests (10-15%)
        /--------\
       /          \
      / Integration \   Spring Boot Tests + Testcontainers (25-30%)
     /--------------\
    /                \
   /   Unit Tests     \ JUnit + Mockito (55-65%)
  /____________________\
```

## Typy testów

### 1. Unit Tests (JUnit 5 + Mockito)
**Cel:** Testowanie izolowanej logiki biznesowej

**Zakres:**
- Serwisy (UserService, DashboardService, PredictionService, RankingService, etc.)
- Mapery DTO
- Walidatory
- Utility classes

**Przykłady testów:**
- `UserService`:
  - ✅ Dodawanie użytkownika z unikalnymi danymi
  - ✅ Weryfikacja duplikatów username/email
  - ✅ Zmiana hasła z poprawnymi danymi
  - ✅ Obsługa błędów (user not found, invalid password)
  
- `DashboardService`:
  - ✅ Obliczanie statystyk użytkownika
  - ✅ Generowanie historii rankingu
  - ✅ Pobieranie nadchodzących meczów
  - ✅ Logika mini-rankingu

### 2. Integration Tests (Spring Boot Test)
**Cel:** Testowanie integracji komponentów z bazą danych i Spring Context

**Zakres:**
- Repository tests z prawdziwą bazą (Testcontainers MySQL)
- Controller tests (MockMvc)
- Security tests (authentication, authorization)
- Transaction management

**Przykłady testów:**
- `UserRepository`:
  - ✅ CRUD operations
  - ✅ Custom queries (findByUsername, existsUsersByEmail)
  - ✅ Cascade operations
  
- `UserController`:
  - ✅ REST endpoints (GET, POST, PUT, DELETE)
  - ✅ Validation errors
  - ✅ Security (authorized/unauthorized access)
  
- `SecurityConfig`:
  - ✅ Authentication flows
  - ✅ Password encoding
  - ✅ Role-based access control

### 3. API Tests (Karate Framework)
**Cel:** Testowanie REST API end-to-end z perspektywy użytkownika

**Zakres:**
- User registration i login flows
- CRUD operations na zasobach
- Authorization scenarios
- Error handling
- Response validation

**Przykłady testów:**
- User Scenarios:
  - ✅ Rejestracja użytkownika
  - ✅ Login i otrzymanie tokena/sesji
  - ✅ Zmiana hasła
  - ✅ Próba dostępu bez autoryzacji
  
- Prediction Scenarios:
  - ✅ Tworzenie predykcji
  - ✅ Edycja predykcji przed rozpoczęciem meczu
  - ✅ Próba edycji po rozpoczęciu meczu (should fail)
  
- Ranking Scenarios:
  - ✅ Pobieranie aktualnego rankingu
  - ✅ Pobieranie historii rankingu

## Narzędzia

### Główne frameworki testowe:
```xml
<!-- JUnit 5 - domyślnie w spring-boot-starter-test -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>

<!-- Mockito - domyślnie w spring-boot-starter-test -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>test</scope>
</dependency>

<!-- AssertJ - lepsze asercje -->
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <scope>test</scope>
</dependency>

<!-- Testcontainers - testy z prawdziwą bazą MySQL -->
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>testcontainers</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>mysql</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>

<!-- Karate - API testing -->
<dependency>
    <groupId>com.intuit.karate</groupId>
    <artifactId>karate-junit5</artifactId>
    <scope>test</scope>
</dependency>

<!-- Spring Security Test -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

## Komponenty do przetestowania

### Priority 1 - Krytyczne
1. **UserService** - zarządzanie użytkownikami, security
2. **PredictionService** - logika typowania
3. **RankingService** - obliczanie rankingu
4. **GameService** - zarządzanie meczami
5. **UserPointsService** - przyznawanie punktów

### Priority 2 - Ważne
6. **DashboardService** - statystyki (kompleksowa logika)
7. **CommentService** - komentarze
8. **SecurityConfig** - konfiguracja bezpieczeństwa

### Priority 3 - Nice to have
9. **Controllers** - wszystkie REST endpoints
10. **Repositories** - custom queries
11. **DTOs & Mappers** - konwersje

## Struktura testów

```
src/test/java/pl/xxx/demo/
├── unit/                           # Unit tests
│   ├── User/
│   │   ├── UserServiceTest.java
│   │   ├── UserRequestDTOMapperTest.java
│   │   └── UserResponseDTOMapperTest.java
│   ├── Dashboard/
│   │   └── DashboardServiceTest.java
│   ├── Prediction/
│   │   └── PredictionServiceTest.java
│   ├── Ranking/
│   │   └── RankingServiceTest.java
│   └── Game/
│       └── GameServiceTest.java
│
├── integration/                    # Integration tests
│   ├── User/
│   │   ├── UserRepositoryIntegrationTest.java
│   │   └── UserControllerIntegrationTest.java
│   ├── Prediction/
│   │   └── PredictionControllerIntegrationTest.java
│   ├── Security/
│   │   └── SecurityIntegrationTest.java
│   └── config/
│       └── TestContainersConfiguration.java
│
└── karate/                         # Karate API tests
    ├── user/
    │   ├── user-registration.feature
    │   ├── user-login.feature
    │   └── user-password-change.feature
    ├── prediction/
    │   ├── create-prediction.feature
    │   ├── edit-prediction.feature
    │   └── prediction-validations.feature
    ├── ranking/
    │   └── ranking.feature
    ├── game/
    │   └── games.feature
    └── karate-config.js
```

## Najlepsze praktyki

### Unit Tests
- **AAA Pattern** - Arrange, Act, Assert
- **Given-When-Then** dla czytelności
- Mock wszystkie zależności
- Testuj jeden scenariusz per test
- Używaj `@ExtendWith(MockitoExtension.class)`
- Używaj `@Mock`, `@InjectMocks`

### Integration Tests
- Używaj `@SpringBootTest` dla pełnego kontekstu
- Używaj `@DataJpaTest` dla repository tests
- Używaj `@WebMvcTest` dla controller tests
- Używaj Testcontainers dla prawdziwej bazy danych
- Używaj `@Transactional` dla rollback po testach
- Używaj `@TestPropertySource` dla test properties

### Karate Tests
- Jeden feature file = jeden flow/scenariusz
- Używaj Background dla setup
- Używaj Scenario Outline dla parametryzacji
- Waliduj response schema
- Waliduj response status codes
- Używaj reusable functions

## Coverage Target

- **Overall**: minimum 80%
- **Services**: minimum 90%
- **Controllers**: minimum 80%
- **Repositories**: minimum 70%

## CI/CD Integration

```yaml
# .github/workflows/test.yml przykład
test:
  runs-on: ubuntu-latest
  steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
    - name: Run Unit Tests
      run: mvn test
    - name: Run Integration Tests
      run: mvn verify -P integration-tests
    - name: Run Karate Tests
      run: mvn test -P karate-tests
    - name: Generate Coverage Report
      run: mvn jacoco:report
```

## Następne kroki

1. ✅ Dodać zależności do pom.xml
2. ✅ Utworzyć konfigurację Testcontainers
3. ✅ Utworzyć przykładowe unit testy dla UserService
4. ✅ Utworzyć przykładowe integration testy dla UserRepository
5. ✅ Utworzyć przykładowe testy Karate dla User API
6. ⬜ Rozszerzyć testy na pozostałe komponenty
7. ⬜ Skonfigurować Jacoco dla code coverage
8. ⬜ Dodać testy do CI/CD pipeline

## Przykłady

### Unit Test Pattern
```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    @DisplayName("Should successfully add new user with unique credentials")
    void shouldAddNewUserWhenCredentialsAreUnique() {
        // Given
        UserRequestDTO dto = new UserRequestDTO("john", "john@example.com", "password");
        when(userRepository.existsUsersByEmail(anyString())).thenReturn(false);
        when(userRepository.existsUsersByUsername(anyString())).thenReturn(false);
        
        // When
        UserResponseDTO result = userService.add(dto);
        
        // Then
        assertThat(result).isNotNull();
        verify(userRepository).save(any(User.class));
    }
}
```

### Integration Test Pattern
```java
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");
    
    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"test\",\"email\":\"test@test.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("test"));
    }
}
```

### Karate Test Pattern
```gherkin
Feature: User Registration

Background:
  * url baseUrl
  * def newUser = { username: 'testuser', email: 'test@example.com', password: 'Test123!' }

Scenario: Successful user registration
  Given path '/api/users/register'
  And request newUser
  When method POST
  Then status 201
  And match response.username == 'testuser'
  And match response.email == 'test@example.com'
```

## Metryki sukcesu

- [ ] Wszystkie krytyczne serwisy pokryte testami (>90%)
- [ ] Wszystkie endpointy API pokryte testami Karate
- [ ] Testy przechodzą w CI/CD
- [ ] Code coverage >80%
- [ ] Czas wykonania testów <5 min (unit + integration)
- [ ] Zero flaky tests
