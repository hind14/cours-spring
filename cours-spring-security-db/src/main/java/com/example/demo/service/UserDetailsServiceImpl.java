package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username); // dmd a mon repo de chercher ds db pr trouver le username
		if (user == null) {
			throw new UsernameNotFoundException("Aucun utilisateur trouvé avec la valeur" + username);
		}
		return new UserDetailsImpl(user);
	}

}
//permet d'indiquer à spring où il peut retrouver les utilisateurs