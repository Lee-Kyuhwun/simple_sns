package com.fastcampus.project.sns.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/*/users/join", "/api/*/users/login").permitAll() // /api/*/users/join으로 시작하는 요청은 모두 허용
                        .requestMatchers("/api/**").authenticated() // /api/**로 시작하는 요청은 모두 인증을 필요로 한다.
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Correct placement
                        .sessionFixation().changeSessionId() // 세션 고정 공격을 방지하기 위해 세션 아이디를 변경한다.
                        .maximumSessions(1) // 최대 세션 수를 1개로 제한한다.
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> response.setStatus(401)) // 인증되지 않은 사용자가 접근하면 401을 반환한다.
                        .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403)) // 인가되지 않은 사용자가 접근하면 403을 반환한다.
                );
                return http.build();
    }




}
