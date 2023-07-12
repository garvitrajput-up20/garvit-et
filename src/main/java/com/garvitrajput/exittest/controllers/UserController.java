package com.garvitrajput.exittest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garvitrajput.exittest.body.LoginBody;
import com.garvitrajput.exittest.body.LoginResponse;
import com.garvitrajput.exittest.entity.UsersStructure;
import com.garvitrajput.exittest.exceptions.UserAlreadyExistsException;
import com.garvitrajput.exittest.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

	private UserServices userServices;

	public UserController(UserServices userServices) {
		this.userServices = userServices;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/register")
	public ResponseEntity registerUser(@Valid @RequestBody UsersStructure usersStructure) {
		try {
			userServices.registerUser(usersStructure);
			return ResponseEntity.status(HttpStatus.OK).body("");
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUserByUsername(@Valid @RequestBody LoginBody loginBody) {
		String jwt = userServices.loginUserByUsername(loginBody);
		UsersStructure usersStructure = new UsersStructure();
		if (jwt == null) {
			LoginResponse errorResponse = new LoginResponse();
			errorResponse.setMessage("Invalid Credentials"); // Set the error message
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} else {
			LoginResponse response = new LoginResponse();
			response.setMessage("Login Success");
			response.setJwt(jwt);
			response.setUsersStructure(usersStructure);
			return ResponseEntity.ok(response); // Set the response status to 200 OK
		}
	}

	@PostMapping("/loginEmail")
	public ResponseEntity<LoginResponse> loginUserByEmail(@Valid @RequestBody LoginBody loginBody) {
		String jwt = userServices.loginUserByEmail(loginBody);
		if (jwt == null) {
			LoginResponse errorResponse = new LoginResponse();
			errorResponse.setMessage("Invalid Credentials"); // Set the error message
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} else {
			LoginResponse response = new LoginResponse();
			response.setMessage("Login Success");
			response.setJwt(jwt);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("/LoggedInUser")
	public UsersStructure getLoggedInUserProfile(@AuthenticationPrincipal UsersStructure user) {
		return user;
	}
}