package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	
	@Autowired
	private UserService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code");
		
//		User user=  new User();
//		user.setFirstName("Anurag");
//		user.setLastName("Sisodiya");
//		user.setEmail("anuragsinghsisodiya27@gmail.com");
//		user.setUsername("anurag0827");
//		user.setPassword(this.bCryptPasswordEncoder.encode("Anurag@123"));
//		user.setPhone("8770377021");
//		user.setProfile("default.png");
//		
//		Role role = new Role();
//		role.setRoleId(1L);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		
//		userRoleSet.add(userRole);
//		
//		User user1 = this.service.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
		
	}

}
