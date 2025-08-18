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
        // csrf 토큰 default 설정은 활성화 -> 테스트 위해 비활성화
        http.csrf((csrf) -> csrf.disable());
        // 접근 페이지 권한 설정
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry.requestMatchers(WHITE_LIST).permitAll();
            authorizationManagerRequestMatcherRegistry.requestMatchers(WHITE_LIST);
            authorizationManagerRequestMatcherRegistry.requestMatchers(ADMIN_LIST).hasRole("ADMIN");
            authorizationManagerRequestMatcherRegistry.requestMatchers(MANAGER_LIST).hasRole("MANAGER");
            authorizationManagerRequestMatcherRegistry.requestMatchers(USER_LIST).hasRole("USER");
            authorizationManagerRequestMatcherRegistry.anyRequest().authenticated(); // 나머지 로그인 후 접속 가능
        });
        // 로그인 설정
        http.formLogin(httpSecurityFormLoginConfigurer -> {
            httpSecurityFormLoginConfigurer.loginPage("/auth/login")
                    .usernameParameter("mid")
                    .passwordParameter("mpassword")
                    .defaultSuccessUrl("/auth/loginSuccess")
                    .permitAll();
        });
        // 로그아웃 설정
        http.logout(httpSecurityLogoutConfigurer -> {
            httpSecurityLogoutConfigurer.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                    .logoutSuccessUrl("/auth/login")
                    .invalidateHttpSession(true);
        });
        // 403 오류(접근불가) 처리
        http.exceptionHandling(exceptionHandlingContext -> {
            exceptionHandlingContext.accessDeniedPage("/auth/accessDenined");
        });

        return http.build();
    }
}
