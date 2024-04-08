package com.example.lee_dong_uk.config.auth;

import com.example.lee_dong_uk.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers ->
                        headers
                                .frameOptions(frameOptions-> frameOptions.disable())
                );
        http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/", "/css/**", "/images/**",
                                        "/js/**", "/h2-console/**").permitAll()
                                .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                                .anyRequest().authenticated()
                        );
        http
                .logout(logout -> logout.logoutSuccessUrl("/"));
        http
                .oauth2Login(oauth ->
                        oauth
                                .userInfoEndpoint(customOAuth2UserService)
                );

        return http.build();
    }

}
