//package pl.xxx.demo;
//
//// WAŻNE: Upewnij się, że importy są z org.apache.http, a NIE z org.apache.hc.client5
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.TrustStrategy;
//import org.apache.http.ssl.SSLContexts; // Poprawny import dla v4
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import javax.net.ssl.SSLContext;
//import java.security.cert.X509Certificate;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class ApiGamesData {
//
//    // Arka API
////    private static final String API_KEY
////            = "eee5028bd4f1a9645f0de3b18aa4c17c11a0eedd815aeaacf2cae4d5801e8969";
////    private static final String API_URL
////            = "https://apiv3.apifootball.com/api/?action=get_countries&APIkey={apiKey}";
//
//    //MOJE API
//    private static final String RAPIDAPI_KEY = "3a263e195713aaef25d730cc3cbca2f9";
//    private static final String RAPIDAPI_HOST = "v3.football.api-sports.io";
//
//    //private static final String URL = "https://v3.football.api-sports.io/status"; // odpowiednik "get_countries"
////    private static final String URL = "https://v3.football.api-sports.io/fixtures?id=215662"; // odpowiednik "get_countries"
//
//    private static final String URL = "https://v3.football.api-sports.io/fixtures?league=1&season=2022"; // odpowiednik "get_countries"
//
//    public static void main(String[] args) throws Exception {
//        // Kod tworzący "ufający wszystkim" RestTemplate dla HttpClient 4.x
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//
//        SSLContext sslContext = SSLContexts.custom()
//                .loadTrustMaterial(null, acceptingTrustStrategy)
//                .build();
//
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(csf)
//                .build();
//
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(httpClient); // Teraz to zadziała!
//
//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//
////        ARKA PRZYKLAD// Reszta kodu bez zmian
////        try {
////            CountryDTO[] countries = restTemplate.getForObject(API_URL,
////                    CountryDTO[].class, API_KEY);
////
////            if (countries != null) {
////                Arrays.stream(countries).forEach(System.out::println);
////            } else {
////                System.out.println("Otrzymano pustą odpowiedź (null).");
////            }
////        } catch (Exception e) {
////            System.err.println("Wystąpił błąd podczas wywoływania API:");
////            e.printStackTrace();
////        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Key", RAPIDAPI_KEY);
//        headers.set("X-RapidAPI-Host", RAPIDAPI_HOST);
//
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<String> response = restTemplate.exchange(
//                    URL, HttpMethod.GET, entity, String.class);
//
//            System.out.println("HTTP " + response.getStatusCodeValue());
//            System.out.println(response.getBody());
//
//            //parsowanie json
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode root = objectMapper.readTree(response.getBody()); // moj json
//            JsonNode matches = root.path("response");
////
//            String jdbcUrl = "jdbc:mysql://localhost:3306/betsdb?useSSL=false&serverTimezone=UTC";
//            String user = "root";
//            String password = "";
////
//            try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
//
//                String insertSQl = "INSERT INTO game(home_team, away_team, home_score, away_score, game_date) VALUES (?, ?, ?, ?, ?)";
//                PreparedStatement ps = conn.prepareStatement(insertSQl);
//                for (JsonNode match : matches) {
//                    String homeTeam = match.path("teams").path("home").path("name").asText();
//                    String awayTeam = match.path("teams").path("away").path("name").asText();
//                    String homeGoals = match.path("goals").path("home").asText();
//                    String awayGoals = match.path("goals").path("away").asText();
//                    String matchDate = match.path("fixture").path("date").asText();
//
//                    ps.setString(1, homeTeam);
//                    ps.setString(2, awayTeam);
//                    ps.setString(3, homeGoals);
//                    ps.setString(4, awayGoals);
//                    ps.setString(5, matchDate);
//                    ps.addBatch();
//                }
//                ps.executeBatch();
//                System.out.println("Zapisano mecze");
//            }
//
//        } catch (Exception e) {
//            System.err.println("Błąd wywołania API:");
//            e.printStackTrace();
//        }
//    }
//}
//
//// END GENAI