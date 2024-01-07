package com.iksadnorth.bookmanage.config;

import com.iksadnorth.bookmanage.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/members")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/auth/login")).permitAll()

                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/books")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/api/books/*")).hasRole("ADMIN")

                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/books/*/histories")).permitAll()
                        .requestMatchers(antMatcher(HttpMethod.POST, "/api/books/*/histories")).authenticated()

                        .anyRequest().authenticated()
        );

        return http.build();
    }
}
