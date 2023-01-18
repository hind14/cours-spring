package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Bean    
    InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.builder().username("admin").password("{noop}admin").roles("ADMIN", "USER").build(),
                User.builder().username("user").password("{noop}user").roles("USER").build());
    }
    
    @Bean    
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http 
        		.csrf().disable()
        		.authorizeHttpRequests((auth) -> auth                    
        		.requestMatchers(HttpMethod.GET, "/personnes").hasRole("USER")   
                .requestMatchers(HttpMethod.POST, "/personnes").hasRole("ADMIN")
                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}