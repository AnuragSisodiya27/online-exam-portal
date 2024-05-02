package com.exam.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	//getting quiz data on the basis of category
	public List<Quiz> findByCategory(Category category);
	
	//return only active quiz
	List<Quiz> findByActive(boolean b);
	
	//return only active quiz on the basis of category
	List<Quiz> findByCategoryAndActive(Category c, boolean b);
	
}
