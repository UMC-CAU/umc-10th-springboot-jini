package com.example.jini_umc10th.global.config;

import com.example.jini_umc10th.global.security.handler.CustomAccessDenied;
import com.example.jini_umc10th.global.security.handler.CustomEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    public final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            // 회원가입, 로그인 허용
            "/auth/**"
    };

    public final String[] publicAPI = {
            // TODO : 추후 추가
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 비활성화
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll() // allowUris는 인증 없이 허용
                        .requestMatchers(publicAPI).permitAll() // publicAPI는 인증 없이 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .formLogin(form -> form // 폼 기반 로그인 활성화 및 설정
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // 로그인 성공시 항상 Swagger UI로 리다이렉트
                        .permitAll() // 로그인 페이지는 누구나 접근 가능
                )
                .logout(logout -> logout // 로그아웃 처리
                        .logoutUrl("/logout") // 이 URL로 POST요청 시 로그아웃 처리
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 후 이동할 URL
                        .permitAll() // 누구나 접근 가능
                )
                .exceptionHandling(exception -> exception // 예외 상황 핸들러
                        .accessDeniedHandler(customAccessDenied())       // 403 - 인증은 됐지만 권한 없음
                        .authenticationEntryPoint(customEntryPoint())    // 401 - 인증 안 됨
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 솔트를 위한 BCrypt를 PasswordEncoder로 설정
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }

}
