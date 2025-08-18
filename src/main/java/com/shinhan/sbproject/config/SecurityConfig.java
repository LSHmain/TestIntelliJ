package com.shinhan.sbproject.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// ✅ MVC용 CORS import (reactive 아님!)
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    final String[] WHITE_LIST = {
            "/security/all", "/auth/**", "/board/**",
            "/css/**", "/images/**", "/js/**", "/upload/**", "/api/**"
    };
    final String[] ADMIN_LIST = {"/security/admin"};
    final String[] MANAGER_LIST = {"/security/manager"};
    final String[] USER_LIST = {"/security/user"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // 프리플라이트 허용(안전장치)
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(ADMIN_LIST).hasRole("ADMIN")
                        .requestMatchers(MANAGER_LIST).hasRole("MANAGER")
                        .requestMatchers(USER_LIST).hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .usernameParameter("mid")
                        .passwordParameter("mpassword")
                        // 지금은 리다이렉트 방식 유지
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/auth/loginSuccess");
                        })
                        .failureHandler((request, response, exception) -> {
                            request.getSession().setAttribute("loginError", "로그인 실패");
                            response.sendRedirect("/auth/login");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex
                        // ✅ 오타 수정: accessDenied
                        .accessDeniedPage("/auth/accessDenied")
                );

        return http.build();
    }

    // ✅ MVC용 CORS Bean (모든 경로에 적용)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // withCredentials=true 쓸 때 필수

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
