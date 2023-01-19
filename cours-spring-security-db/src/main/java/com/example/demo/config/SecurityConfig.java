package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig {
	private UserDetailsService userDetailsService;
	
	@Bean
	NoOpPasswordEncoder encoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();    //pas bonne méthode car mdp pas crypté
	}
	
	//juste pr tester le mdp/en dur
    
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(encoder())
				.and()
				.build();
	}
	
    @Bean    
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http 
        		.csrf().disable()
        		.authorizeHttpRequests((auth) -> auth                    
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}