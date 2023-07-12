package com.garvitrajput.exittest.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.garvitrajput.exittest.entity.UsersStructure;

public interface UserDAO extends CrudRepository<UsersStructure, Long>{
	Optional<UsersStructure> findByEmailIgnoreCase(String email);
	Optional<UsersStructure> findByusernameIgnoreCase(String username);

}
