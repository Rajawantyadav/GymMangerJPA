package com.gymmanager.newgymmanager.jwtconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    private JwtAuthenticationFiler jwtAuthenticationFiler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/addGymOwner").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()  // public endpoints
                        .requestMatchers("/api/**","/actuator/metrics/**").authenticated() // allow unauthenticated access// allow unauthenticated access
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFiler, AuthenticationFilter.class);
        return httpSecurity.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        //authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authenticationProvider.setPasswordEncoder(passwordEncoderConfig.bCryptPasswordEncoder());
        return authenticationProvider;
    }

}
