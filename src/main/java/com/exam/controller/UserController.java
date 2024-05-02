 package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.helper.UserNotFoundException;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		if(user.getProfile()==null) {
			user.setProfile("default.png");
		}
		
		//encoding password with BCryptPassword Encoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Set<UserRole> userRoleSet = new HashSet<>();

		Role role = new Role();
		role.setRoleId(28L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleSet.add(userRole);

		return this.userService.createUser(user, userRoleSet);

	}

	// getting a user by id
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {

		return this.userService.getUser(username);
	}

	// delete the user by id
	@DeleteMapping("/userId/{userId}")
	public void deleteUserbyId(@PathVariable("userId") Long userId) {
		this.userService.deleteUserbyId(userId);
	}

	// delete the user by id
	@DeleteMapping("/username/{username}")
	public void deleteUserbyUsername(@PathVariable("username") String username) {
		this.userService.deleteByUsername(username);
	}
	
	//update user
//	@PutMapping("/update/{username}")
//	public User updateUser(@PathVariable("username") String username) {
//		User local  = this.
//		return this.userService.updateUser(user);
//	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return ResponseEntity.status(HttpStatus.FOUND).body(ex);
	}
}
