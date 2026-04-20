package pl.xxx.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins:}")
    private String additionalOrigins;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Bazowe dozwolone origin (lokalne developerskie)
        List<String> baseOrigins = Arrays.asList(
            "http://localhost:5173",
            "http://localhost:5174",
            "http://localhost:5175",
            "http://localhost:3000",
            "http://127.0.0.1:3000",
            "http://host.docker.internal:3000",
            // Wildcards for local development - allows any local IP (192.168.x.x, 10.x.x.x, etc.)
            "*"
        );

        // Dodaj dodatkowe origin z konfiguracji (np. z docker-compose)
        List<String> allOrigins = baseOrigins;
        if (additionalOrigins != null && !additionalOrigins.isEmpty()) {
            List<String> extraOrigins = Arrays.stream(additionalOrigins.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            allOrigins = Stream.concat(baseOrigins.stream(), extraOrigins.stream())
                    .collect(Collectors.toList());
        }

        configuration.setAllowedOrigins(allOrigins);

        // Dozwolone HTTP metody
        configuration.setAllowedMethods(Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        // Dozwolone nagłówki
        configuration.setAllowedHeaders(Arrays.asList("*"));

        // Pozwól na credentials (cookies, authorization headers)
        configuration.setAllowCredentials(true);

        // Czas cache dla preflight requests
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
