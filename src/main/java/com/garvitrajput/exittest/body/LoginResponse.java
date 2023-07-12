package com.garvitrajput.exittest.body;

import com.garvitrajput.exittest.entity.UsersStructure;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;

public class LoginResponse {

	/** The JWT token to be used for authentication. */
	private String jwt;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable
	private UsersStructure usersStructure;
	
	

	public UsersStructure getUsersStructure() {
		return usersStructure;
	}

	public void setUsersStructure(UsersStructure usersStructure) {
		this.usersStructure = usersStructure;
	}

	public String getJwt() {
		return jwt;
	}

	private String message;

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}