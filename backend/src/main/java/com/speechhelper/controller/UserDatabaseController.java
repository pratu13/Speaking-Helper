package com.speechhelper.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.speechhelper.databasemanager.UserEntity;
import com.speechhelper.databasemanager.UserRepository;


@CrossOrigin(origins = "https://speechhelper.herokuapp.com/")
@RestController
public class UserDatabaseController {

	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin
	@Transactional
	@PostMapping(path="/add_user") // Map ONLY POST Requests
	public String addNewUser (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String password, @RequestParam String email ) {
		UserEntity n = new UserEntity();
		n.setFirstName(firstName);
		n.setLastName(lastName);
		n.setUsername(username);
		n.setPassword(password);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}
	
	@CrossOrigin
	@Transactional
	@GetMapping(path="/all/user")
	public Iterable<UserEntity> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return userRepository.findAll();
	}
	
	@CrossOrigin
	@Transactional
	@GetMapping(path="/user_id")
	public UserEntity findByUserId(@RequestParam Long userId) {
		return userRepository.findById(userId).get();
	}
	
	@CrossOrigin
	@Transactional
	@GetMapping(path="/user_name")
	public UserEntity findByUsername(@RequestParam String username) {
		return userRepository.findByUsername(username);
	}
	

	@CrossOrigin
	@Transactional
	@GetMapping(path="/email")
	public UserEntity findByEmail(@RequestParam String email) {
		return userRepository.findByEmail(email);
	}
	
}
