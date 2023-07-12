package com.garvitrajput.exittest.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * The body for the login requests.
 */
public class LoginBody {

	/** The username to log in with. */
	private String username;
	/** The password to log in with. */

	private String email;

	@NotNull
	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
