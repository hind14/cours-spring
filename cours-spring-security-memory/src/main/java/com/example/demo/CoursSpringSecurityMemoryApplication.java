package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.models.Adresse;
import com.example.demo.models.Personne;
import com.example.demo.service.PersonneService;

@SpringBootApplication
public class CoursSpringSecurityMemoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringSecurityMemoryApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(PersonneService personneService) {
		return args -> {
			//personneService.save(new Personne("Mercury", "Freddie", List.of(new Adresse("paradis", "13060", "Marseille")) ));
			//personneService.save(new Personne("Wick", "John", List.of(new Adresse("paradis", "13006", "Marseille"))));
            //personneService.save(new Personne("Dalton", "Jack", List.of(new Adresse("plantes", "75014", "Paris"))));
		};
	}

}
