package com.annie.bookclub.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginUser {
	
	@NotEmpty(message="email is reuqired!")
	@Email(message="Please enter a valid email!")
	private String email;
	
	@NotEmpty(message="password is reuqired!")
	@Size(min=8, max=128, message="password must be between 8 and 128 characters")
	private String password;
	
	// Constructor
	
	public LoginUser() {
		
	}
	
	// Getters and Setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
