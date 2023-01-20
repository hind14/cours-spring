package com.example.demo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity  //table ds bd dc = repository (dao)
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NonNull
	String nom;
	@NonNull
	String prenom;
	@NonNull
	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JsonIgnoreProperties(value = "personnes") //Pr éviter la ref circulaire = boucle infinie
	List<Adresse> adresses;
}
