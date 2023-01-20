package com.example.demo.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa") //grâce à la dependance sb config processor- permet de ne pas écrire @value 2 fois
public record RsaKeyProperties(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
	
}
