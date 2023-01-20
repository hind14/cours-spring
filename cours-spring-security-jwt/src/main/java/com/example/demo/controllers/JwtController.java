package com.example.demo.controllers;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.JwtService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class JwtController {
	private JwtService jwtService;
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public Map<String, String> getToken(@RequestBody UserDto user) {
		if (user.getGrantType().equals("password")) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			String roles = jwtService.getRoles(authentication.getAuthorities());
			log.info("Génération de token pour {}", user.getUsername());
			return jwtService.generateTokens(authentication.getName(), roles);
		} else if (user.getGrantType().equals("refreshToken") || user.getGrantType().equals("refresh-Token")) {
			log.info("Raffraichissement de token pour {}", user.getRefreshToken());
			return jwtService.generateFromRefreshToken(user);
		} else {
			log.error("Utilisateur non identiufié : {}", user);
			return null;
		}
	}
}