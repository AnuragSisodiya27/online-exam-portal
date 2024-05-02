package com.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.repo.UserRepository;
import com.exam.entity.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
    private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		User user = this.userRepo.findByUsername(username);

        if(user == null){
        	System.out.println("User Not Found!");
            throw new UsernameNotFoundException("User Not Found!!");
        }else{
            return user;
        }
		
		
		
	}

}
