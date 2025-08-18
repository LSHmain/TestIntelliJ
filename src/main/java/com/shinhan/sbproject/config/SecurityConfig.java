package com.shinhan.sbproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 시큐리티 설정파일임을 의미
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    final String[] WHITE_LIST = {"/security/all", "/auth/**", "/board/**", "/css/**", "/images/**", "/js/**"};
    final String[] ADMIN_LIST = {"/security/admin"};
    final String[] MANAGER_LIST = {"/security/manager"};
    final String[] USER_LIST = {"/security/user"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("securityFilterChain start");

        // CSRF 비활성화 (테스트용)
        http.csrf(csrf -> csrf.disable());

        // 권한 설정
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers(ADMIN_LIST).hasRole("ADMIN")
                .requestMatchers(MANAGER_LIST).hasRole("MANAGER")
                .requestMatchers(USER_LIST).hasRole("USER")
                .anyRequest().authenticated()
        );

        // 로그인 설정
        http.formLogin(login -> login
                .loginPage("/auth/login")
                .usernameParameter("mid")
                .passwordParameter("mpassword")
                // .defaultSuccessUrl("/auth/loginSuccess") // 생략하고 successHandler 사용
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/auth/loginSuccess");
                })
                .failureHandler((request, response, exception) -> {
                    request.getSession().setAttribute("loginError", "로그인 실패");
                    response.sendRedirect("/auth/login");
                })
                .permitAll()
        );

        // 로그아웃 설정
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/auth/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        );

        // 접근 거부 (403)
        http.exceptionHandling(ex -> ex
                .accessDeniedPage("/auth/accessDenined")
        );

        return http.build();
    }
}


