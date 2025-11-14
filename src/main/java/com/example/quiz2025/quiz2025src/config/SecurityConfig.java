package com.example.quiz2025.quiz2025src.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // CSRF는 API 서버에서는 비활성화
                .csrf(csrf -> csrf.disable())

                // 인증 규칙 정의
                .authorizeHttpRequests(auth -> auth
                        // 로그인, 회원가입, 토큰 재발급은 모두 허용
                        .requestMatchers(
                                "/api/user",          // 회원가입
                                "/api/auth/login",    // 로그인
                                "/api/auth/reissue",  // 토큰 재발급
                                "/api/auth/logout"    // 로그아웃
                        ).permitAll()

                        // 나머지 요청은 인증 필요
                        .anyRequest().authenticated()
                )

                // 세션을 사용하지 않음 (JWT 기반이므로)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
