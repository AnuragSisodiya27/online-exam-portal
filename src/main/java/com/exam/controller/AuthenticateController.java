package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.User;
import com.exam.helper.JwtUtil;
import com.exam.helper.UserNotFoundException;
import com.exam.impl.JwtRequest;
import com.exam.impl.JwtResponse;
import com.exam.repo.UserRepository;
import com.exam.service.CustomUserDetailService;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
// Jwt Controller
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	//generated token
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			System.out.println(jwtRequest.getUsername()+" "+jwtRequest.getPassword());
			this.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
			
		}catch(UserNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found!!");
		}
		
		//authenticate done
		UserDetails userDetails =  this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}

	private void authenticate(String username, String password) throws Exception {
		System.out.println(username + " ----- "+ password);

		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED");
		}catch(BadCredentialsException e) {
			throw new Exception("Wrong Credentails");
		}
		
	}
	
	//return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
		//System.out.println(principal.getName());
		
		return (User) this.customUserDetailService.loadUserByUsername(principal.getName());
		
	}
	
	
}
