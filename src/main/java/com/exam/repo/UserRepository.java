package com.exam.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
	@Transactional
	void deleteByUsername(String username);
	
	
	
}

