package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.AdresseRepository;
import com.example.demo.dao.PersonneRepository;
import com.example.demo.models.Personne;
import com.example.demo.service.PersonneService;

import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("http://127.0.0.1:5500")
@RestController /*Retourne tjrs des données pas de vue*/
@RequestMapping("/personnes")
@Slf4j /* framework de journalisation + écrire dans app.proer ajoute un dossier log == logging.file.name et path*/
@AllArgsConstructor
@Secured("SCOPE_ADMIN")
public class PersonneController {
	private PersonneService personneService;
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Personne> getPersonnes() {
		// log.info("liste de personne recherchée "); /* pour ajouter texte dans le log*/
		return personneService.findAll();
	}

	// Pour récuprer le type d'erreur avec un msg et un statut / EXCEPTION
	@GetMapping("/{id}")
	public ResponseEntity<Personne> getPersonne(@PathVariable int id) {
		var personne =  personneService.findById(id);
		if (personne == null) {
			log.error("personne recherchée avec l'id {} est introuvable ", id);
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, 
					"this personne n'existe pas");		
		}
		return new ResponseEntity<Personne>(personne, HttpStatus.OK);
	}
	
	@PostMapping()
	//sys.out.println afficher objet déjà reçu)
	public Personne postPersonne(@RequestBody Personne personne) {
		log.info("personne envoyée {} ", personne);
		return  personneService.save(personne);
		}
	
    @PutMapping("/{id}")
    public ResponseEntity<?> addPersonne(@PathVariable int id, @RequestBody Personne personne) {
        if (id != personne.getId()) {
            log.error("Requête incohérente {} !=  {} ", id, personne);
            return new ResponseEntity<Personne>(personne, HttpStatus.BAD_REQUEST);
        } else if (personneService.findById(id) == null) {
            log.error("Requête incohérente {} !=  {} ", id, personne);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        log.info("Personne modifiée dans la base de données  {} ", personne);
        return new ResponseEntity<Personne>(personneService.save(personne), HttpStatus.ACCEPTED);
    }
	
	//Retourne un booleen 
	// 204 no content true
	// 404 not found 
	@DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersonne(@PathVariable int id) {
        var personne = personneService.findById(id);
        if (personne == null) {
            log.error("Personne à supprimer avec l'identifiant {} est introuvable", id);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        log.info("Personne supprimée avec l'identifiant {}", id);
        personneService.deleteById(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }
}
