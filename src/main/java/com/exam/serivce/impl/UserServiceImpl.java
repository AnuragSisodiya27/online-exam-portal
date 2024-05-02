package com.exam.serivce.impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoundException;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
 
	//implement class
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception{
		
		
		User local = this.userRepo.findByUsername(user.getUsername());
		
		//checking user is already exist or not
		if(local !=null) {
			System.out.println("User is already present");
			throw new UserFoundException("User already present");
		}else {
			// we create a new user
			
			//first save the role
			for(UserRole ur: userRoles) {
				this.roleRepo.save(ur.getRole());
			}
			
			//
			user.getUserRole().addAll(userRoles);
			local = this.userRepo.save(user);
		}
		
		return local;
		
	}

	//get user by username
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return this.userRepo.findByUsername(username);
	}
	


	//delete user by username
	@Override
	public void deleteByUsername(String username) {
		// TODO Auto-generated method stub
		this.userRepo.deleteByUsername(username);
		
	}

	//delete user by user id
	@Override
	public void deleteUserbyId(Long userId) {
		// TODO Auto-generated method stub
		
		this.userRepo.deleteById(userId);
		
	}
	
	//update user by username
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		User local  = this.userRepo.save(user);
		return local;
	}
	
	
	
	
 
	

}
