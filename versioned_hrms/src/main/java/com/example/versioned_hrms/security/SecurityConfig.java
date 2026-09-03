package com.example.versioned_hrms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/actuator/health"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/employees/**"
                        ).hasAnyRole(
                                "EMPLOYEE",
                                "MANAGER",
                                "ADMIN"
                        )

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/employees/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/employees/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/employees/**"
                        ).hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()
                )
                //.httpBasic(Customizer.withDefaults());
                .formLogin(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
