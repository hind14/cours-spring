package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor //permet de créer un new user
@NoArgsConstructor  //si pas ca , spring et hibernate ne fonctionnera pas car ile en ont besoin
@RequiredArgsConstructor  //en plus tt sauf la clé primaire - c'est pr le save pr éviter de mettre null pr l'id et autres agrs, il faut obligatoirement un NONNULL
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@NonNull
	String titre;
	
	
}
