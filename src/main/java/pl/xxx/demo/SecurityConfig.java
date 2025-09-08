//package pl.xxx.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    @Order(1)
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        System.out.println("ŁADUJE: apiFilterChain");
//        http
//                .securityMatcher("/api/**")
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .addFilterBefore((request, response, chain) -> {
//                    String uri = ((jakarta.servlet.http.HttpServletRequest) request).getRequestURI();
//                    System.out.println("API filter chain obsługuje: " + uri);
//                    chain.doFilter(request, response);
//                }, org.springframework.web.filter.CorsFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    @Order(2)
//    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception {
//        http
//                .securityMatcher("/**")
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/home", "/index").permitAll()
//                        .requestMatchers("/error").permitAll() // <-- DODANE
//                        .requestMatchers("/css/**", "/js/**", "/vendor/**", "/img/**", "/scss/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/user/**", "/app/games**", "/app/prediction-result")
//                        .hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(logout -> logout.permitAll());
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
//        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
//
//    }
//}