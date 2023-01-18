package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;

@SpringBootApplication
public class CoursSpringRestApplication  implements ApplicationRunner{

	@Autowired
	private PersonneRepository personneRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CoursSpringRestApplication.class, args);
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		personneRepository.save(new Personne("Whick", "John"));
//		personneRepository.save(new Personne("Dalton", "Jack" ));
	}

}
