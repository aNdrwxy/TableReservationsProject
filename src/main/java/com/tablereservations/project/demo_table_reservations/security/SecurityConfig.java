package com.tablereservations.project.demo_table_reservations.security;

import com.tablereservations.project.demo_table_reservations.service.UserDetailsImpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsImpService userDetailsImpService;

    public SecurityConfig(UserDetailsImpService userDetailsImpService) {
        this.userDetailsImpService = userDetailsImpService;
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity httpSecurity)
            throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(
                                                "/auth/login",
                                                "/auth/register",
                                                "/resources/**",
                                                "/static/**",
                                                "/css/**",
                                                "/userStyle/**",
                                                "/imgs/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                ).formLogin(
                        login ->
                                login.loginPage("/auth/login")
                                        .defaultSuccessUrl("/auth/login-success")
                                        .usernameParameter("correo")
                                        .passwordParameter("password")
                ).logout(
                        logout ->
                                logout.logoutSuccessUrl("/auth/login")
                                        .invalidateHttpSession(true)
                ).authenticationProvider(getAuthenticationProvider());
        return httpSecurity.build();

    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(
                new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(
                userDetailsImpService);
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

