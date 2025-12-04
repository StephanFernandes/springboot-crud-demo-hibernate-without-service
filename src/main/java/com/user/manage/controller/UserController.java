package com.user.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.manage.entity.User;
import com.user.manage.exception.ResourceNotFoundException;
import com.user.manage.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {
	

	@Autowired
	private UserRepository userRepository;
	//get all user
	
	@GetMapping
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+userId));
				
	}
	//.orElseThrow(() -> ResourceNotFoundException("User Not Found with id:"+ userId));
	//create user
	@PostMapping
	public User CreateUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	// update user 
	@PutMapping("/{id}")
	public User UpdateUser(@RequestBody User user, @PathVariable (value = "id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+userId));
		existingUser.setfirstName(user.getfirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
		 
	}
	//dlete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> DeleteUser(@PathVariable (value = "id") long userId) {
		User deleteUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+userId));
		
		this.userRepository.delete(deleteUser);
		return ResponseEntity.ok().build();
		
		
		
		
		
		
	}
	
}
