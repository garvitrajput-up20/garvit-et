package com.garvitrajput.exittest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.garvitrajput.exittest.body.LoginBody;
import com.garvitrajput.exittest.dao.UserDAO;
import com.garvitrajput.exittest.entity.UsersStructure;
import com.garvitrajput.exittest.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;

@Service
public class UserServices {

	private EncryptionService encryptionService;
	private JWTService jwtService;

	public UserServices(EncryptionService encryptionService, UserDAO userDAO, JWTService jwtService) {
		super();
		this.encryptionService = encryptionService;
		this.userDAO = userDAO;
		this.jwtService = jwtService;
	}

	@Autowired
	UserDAO userDAO;

	public UsersStructure registerUser(UsersStructure usersStructure) throws UserAlreadyExistsException {
		if (userDAO.findByEmailIgnoreCase(usersStructure.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException("User Already Exists With Given");
		}else if(userDAO.findByusernameIgnoreCase(usersStructure.getUsername()).isPresent()) {
			throw new UserAlreadyExistsException("User Already Exists With Given Username");
		}
		UsersStructure user = new UsersStructure();
		user.setEmail(usersStructure.getEmail());
		user.setName(usersStructure.getName());
		user.setUsername(usersStructure.getUsername());
		user.setPassword(encryptionService.encryptPassword(usersStructure.getPassword()));
		return userDAO.save(user);
	}

	public String loginUserByUsername(@Valid LoginBody loginBody) {
		Optional<UsersStructure> localUser = userDAO.findByusernameIgnoreCase(loginBody.getUsername());
		if (localUser.isPresent()) {
			UsersStructure user = localUser.get();
			if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
	}

	public String loginUserByEmail(@Valid LoginBody loginBody) {
		Optional<UsersStructure> localUser = userDAO.findByEmailIgnoreCase(loginBody.getEmail());
		if (localUser.isPresent()) {
			UsersStructure user = localUser.get();
			if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
	}
}