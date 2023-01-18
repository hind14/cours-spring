package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;

@SpringBootApplication
public class CoursSpringDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursSpringDataRestApplication.class, args);
	}

	@Bean
	CommandLineRunner start(PersonneRepository rep) {
		return args -> {
			rep.save(Personne.builder().nom("john").prenom("whick").build());
			rep.save(Personne.builder().nom("dalton").prenom("jack").build());
		};
	}
}
