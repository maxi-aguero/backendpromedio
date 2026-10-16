package com.apress.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
public class SecurityConfig {

    // Clave secreta compartida con el backend de auth/login
    private static final String SECRET_KEY = "miClaveSecretaSuperSegura1234567890123456";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ✅ habilita CORS (usa configuración global si tenés CorsConfig)
                .cors(cors -> {})
                // 🔒 reglas de autorización
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**").permitAll()   // rutas públicas
                        .requestMatchers("/api/**").authenticated() // cursos requieren token
                        .requestMatchers("/admin/**").hasRole("ADMIN") // admin requiere rol
                        .anyRequest().authenticated()
                )
                // ❌ deshabilitamos CSRF porque es API REST
                .csrf(csrf -> csrf.disable())
                // 🔑 activamos validación JWT
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
                // 🚫 sin sesiones, todo es stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    // JwtDecoder que valida tokens HS256 con tu clave secreta
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256")
        ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
