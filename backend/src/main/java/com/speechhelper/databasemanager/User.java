package com.speechhelper.databasemanager;

import java.util.ArrayList;
import com.speechhelper.speechtotext.Speech;

public class User {
	private String username;
	private String hashedPassword;
	private String firstName;
	private String lastName;
	private String email;
	private ArrayList<Speech> speeches;
	
	public User(String username, String password, String firstName, String lastName, String email) {
		this.username  = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.speeches = new ArrayList<Speech>();
		//Uses bcrypt to hash password
		hashPassword(password);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Speech> getSpeeches() {
		return speeches;
	}
	public void setSpeeches(ArrayList<Speech> speeches) {
		this.speeches = speeches;
	}
	
	private void hashPassword(String password) {
		String hashed = "";
		//BCrypt.gensalt();
		this.hashedPassword = hashed;
	}
	
	
}
