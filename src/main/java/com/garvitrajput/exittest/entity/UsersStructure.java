package com.garvitrajput.exittest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class UsersStructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Email
	@NotBlank
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Size(min = 0)
	@Size(max = 20)
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false, length = 255)
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsersStructure(int id, String email, String username, String name, String password) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.password = password;
	}

	public UsersStructure() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsersStructure [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name
				+ ", password=" + password + "]";
	}
	
	
	
}
