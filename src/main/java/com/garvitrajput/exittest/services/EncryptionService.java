package com.garvitrajput.exittest.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EncryptionService {
	@Value("${encryption.salt.rounds}")
	private int saltrounds;
	private String salt;
	
	@PostConstruct
	public void postConstruct() {
		salt = BCrypt.gensalt(saltrounds);
	}
	
	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, salt);
	}
	
	public Boolean verifyPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
