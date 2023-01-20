package com.example.demo;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.Adresse;
import com.example.demo.models.Personne;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.PersonneService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class CoursSpringSecurityDbApplication {
	private PasswordEncoder encoder;
    public static void main(String[] args) {
        SpringApplication.run(CoursSpringSecurityDbApplication.class, args);
    }
    
    
    @Bean
    CommandLineRunner start(PersonneService personneService, UserRepository userRepository) {
        return args -> {
            personneService.save(new Personne("Wick", "John", List.of(new Adresse("paradis", "13006", "Marseille"))));
            personneService.save(new Personne("Dalton", "Jack", List.of(new Adresse("plantes", "75014", "Paris"))));
            userRepository.save(new User("user", encoder.encode("user"), List.of(new Role("USER"))));
            userRepository.save(new User("admin", encoder.encode("admin"), List.of(new Role("ADMIN"))));
        };
    }
}