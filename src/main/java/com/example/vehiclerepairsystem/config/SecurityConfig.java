package com.example.vehiclerepairsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ✅ 放行注册、登录、错误路径
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/error").permitAll()
                        .requestMatchers("api/test/user/details", "api/test/user/vehicles").permitAll()
                        .requestMatchers("/api/repair-orders/report","/api/repair-orders/repairs",
                                "/api/repair-orders/submit-feedback","/api/repair-orders/worker/tasks").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
