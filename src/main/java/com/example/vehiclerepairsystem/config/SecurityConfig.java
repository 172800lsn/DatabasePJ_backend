//package com.example.vehiclerepairsystem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(Customizer.withDefaults())
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        // ✅ 放行注册、登录、错误路径
//                        .requestMatchers("/api/auth/register", "/api/auth/login", "/error").permitAll()
//                        .requestMatchers("api/test/user/details", "api/test/user/vehicles").permitAll()
//                        .requestMatchers("/api/repair-orders/report","/api/repair-orders/repairs",
//                                "/api/repair-orders/submit-feedback").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}
package com.example.vehiclerepairsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // 启用 CORS 默认配置
                .csrf(csrf -> csrf.disable())   // 禁用 CSRF 防护，用于支持 RESTful API
                .authorizeHttpRequests(auth -> auth
                        // ✅ 放行注册、登录、错误路径
                        .requestMatchers("/api/auth/register", "/api/auth/login", "/error").permitAll()

                        // ✅ 放行部分用户 API
                        .requestMatchers(HttpMethod.POST, "/api/test/user/details", "/api/test/user/vehicles").permitAll()

                        // ✅ 根据角色限制新增的用户管理功能
                        .requestMatchers("/api/test/user/get-all").permitAll() // 获取用户列表
                        // 仅管理员可以访问
                        .requestMatchers("/api/test/user/delete/**").permitAll() // 删除用户
                         // 仅管理员可以访问

                        // ✅ 放行维修订单部分 API
                        .requestMatchers("/api/repair-orders/report",
                                "/api/repair-orders/repairs",
                                "/api/repair-orders/submit-feedback",
                                "/api/repair-orders/worker/tasks").permitAll()

                        // 其他请求需要认证
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
