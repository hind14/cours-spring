package com.example.demo.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dao.AdresseRepository;
import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;

import lombok.AllArgsConstructor;
// @Service = @Component
@Service
@AllArgsConstructor
public class PersonneService {
    private PersonneRepository personneRepository;
    private AdresseRepository adresseRepository;
    public List<Personne> findAll() {
        return personneRepository.findAll();
    }
    public Personne findById(int id) {
        return personneRepository.findById(id).orElse(null);
    }
    public Personne save(Personne personne) {
        adresseRepository.saveAll(personne.getAdresses());
        return personneRepository.save(personne);
    }
    public void deleteById(int id) {
        personneRepository.deleteById(id);
    }
    public Personne update(Personne personne) {
        return personneRepository.save(personne);
    }
}