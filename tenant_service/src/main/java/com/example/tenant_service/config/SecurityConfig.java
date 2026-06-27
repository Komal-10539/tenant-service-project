package com.example.tenant_service.config;

import com.example.tenant_service.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/tenant/onboarding/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/tenant/document/view/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/tenant/document/upload").permitAll()
                        .requestMatchers("/tenant-service/api/v1/tenant/document/onboarding").permitAll()
                        .requestMatchers("/tenant-service/api/manager/complaint/document/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/record/update").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/record/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/record/update /**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/approval/request/update").permitAll()
                        .requestMatchers("/tenant-service/api/v1/tenant/rent/proof/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/document/onboarding/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/document/upload/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/complaint/update/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/document/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/room/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/record/update/**").permitAll()
                        //.requestMatchers("/tenant-service/api/v1/manager/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/room/list/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/collection/status/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/rent/history/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/complaint/list/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/user/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/tenant/invite/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/tenant/list/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/food/menu/**").permitAll()
                        .requestMatchers("/tenant-service/api/v1/manager/tenant/details/**").permitAll()
                        .requestMatchers("/tenant-service/api/tenant/document/**").permitAll()
                        .requestMatchers("/tenant-service/api/tenant/complaint/raise").permitAll()
                        .requestMatchers("/tenant-service/api/tenant/complaint/document/**").permitAll()
                        //.requestMatchers("/tenant-service/api/v1/tenant/self/onboarding/details").permitAll()

                        .requestMatchers("/tenant-service/api/v1/manager/**").hasRole("MANAGER")
                        .requestMatchers("/tenant-service/api/v1/tenant/**").hasRole("TENANT")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new UsernameNotFoundException("User not found");
        };
    }
}
