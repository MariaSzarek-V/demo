package pl.xxx.demo;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts; // Poprawny import dla v4
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main01 {
    private static final String RAPIDAPI_KEY = "3a263e195713aaef25d730cc3cbca2f9";
    private static final String RAPIDAPI_HOST = "v3.football.api-sports.io";
    private static final String URL = "https://v3.football.api-sports.io/fixtures?league=1&season=2022";


    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {


        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient); // Teraz to zadziała!

        RestTemplate restTemplate = new RestTemplate(requestFactory);


        // 1️⃣ Dane do MySQL
        String jdbcUrl = "jdbc:mysql://localhost:3306/betsdb?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {

            // Stworzenie tabeli jeśli nie istnieje
            String createTable = "CREATE TABLE IF NOT EXISTS matches (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "homeTeam VARCHAR(255)," +
                    "awayTeam VARCHAR(255)," +
                    "homeGoals INT," +
                    "awayGoals INT)";
            conn.createStatement().execute(createTable);

            // 2️⃣ Pobranie JSON-a z API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("X-RapidAPI-Key", RAPIDAPI_KEY)
                    .header("X-RapidAPI-Host", RAPIDAPI_HOST)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            // 3️⃣ Parsowanie JSON-a
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode matches = root.path("response");

            // 4️⃣ Wstawienie wszystkich meczów do bazy
            String insertSql = "INSERT INTO matches(homeTeam, awayTeam, homeGoals, awayGoals) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertSql);

            for (JsonNode matchNode : matches) {
                String homeTeam = matchNode.path("teams").path("home").path("name").asText();
                String awayTeam = matchNode.path("teams").path("away").path("name").asText();
                int homeGoals = matchNode.path("goals").path("home").asInt();
                int awayGoals = matchNode.path("goals").path("away").asInt();

                stmt.setString(1, homeTeam);
                stmt.setString(2, awayTeam);
                stmt.setInt(3, homeGoals);
                stmt.setInt(4, awayGoals);
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("✅ Zapisano wszystkie mecze do MySQL");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
