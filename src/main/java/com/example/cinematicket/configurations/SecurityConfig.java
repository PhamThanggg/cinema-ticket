package com.example.cinematicket.configurations;

import com.example.cinematicket.services.securityService.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/v1/auth/token", "/api/v1/users/register", "/api/v1/auth/introspect",
            "/api/v1/auth/logout",  "/api/v1/login/**", "/api/v1/auth/oauth2/google",

    };

    @Autowired
    private SecurityService securityService;

    private final String[] PUBLIC_GET_ENDPOINTS = {
            "/api/v1/area", "/api/v1/cinema/**",
            "/api/v1/cinema_room/**", "/api/v1/movie/comment/**",
            "/api/v1/genre/**", "/api/v1/movie/**","/api/v1/movie/show/**",
            "/api/v1/moviePeople/**", "/api/v1/schedule/**",
            "/api/v1/cinema_seat", "/api/v1/seat_reservation/**",
            "/api/v1/item/**", "/api/v1/payment/vnpay-callback",
            "api/v1/cinema_seat/room/**",  "api/v1/room_type/**",
            "api/v1/cinema_seat/seatBought/**"
    };

    @Autowired
    @Lazy
    private CustomJwtDecoder customJwtDecoder;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(customizer -> {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("http://localhost:3000"); // Cho phép origin của ứng dụng React
            config.addAllowedHeader("*"); // Cho phép tất cả các header
            config.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP
            config.setAllowCredentials(true); // Nếu bạn cần gửi cookie hoặc thông tin xác thực
            source.registerCorsConfiguration("/**", config); // Áp dụng cho tất cả các endpoint
            customizer.configurationSource(source);
        });

        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                .requestMatchers("/ws/**").permitAll()
                .anyRequest()
                .authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder(10);
    }

}
