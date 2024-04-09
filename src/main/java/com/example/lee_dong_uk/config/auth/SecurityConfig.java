package com.example.lee_dong_uk.config.auth;

import com.example.lee_dong_uk.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        return http
                // csrf
                .csrf(AbstractHttpConfigurer::disable)
                // h2 브라우저에서 접근 가능토록
                .headers(httpSecurityHeadersConfigurer -> {
                    httpSecurityHeadersConfigurer
                            .frameOptions(frameOptionsConfig ->
                                    frameOptionsConfig.disable());
                })
                // 인증 및 권한 설정
                .authorizeHttpRequests((requestMatcherRegistry -> {
                    requestMatcherRegistry
                            .requestMatchers("/", "/css/**", "/images/**",
                            "/js/**", "/h2-console/**").permitAll()
                            .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                            .anyRequest().authenticated();

                }))
                // logout 시 페이지 이동
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/"))
                // oauth2 로그인시
                .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)))

                .build();
    }

}
