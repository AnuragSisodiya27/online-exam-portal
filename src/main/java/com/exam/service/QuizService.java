package com.exam.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;

@Service
public interface QuizService {

	//create quiz
	Quiz addQuiz(Quiz quiz);
	
	//update Quiz
	Quiz updateQuiz(Quiz quiz);
	
	//get all quiz
	Set<Quiz> getQuizzes();
	
	//get by id
	Quiz getQuiz(Long QuizId);
	
	//delete quiz by id
	void deleteQuiz(Long quizId);
	
	//get quiz on the basis of category
	List<Quiz> getQuizzesOfCategory(Category category);
	
	//get active quiz
	List<Quiz> getActiveQuizzes();
	
	//get active quiz on the basis of category
	List<Quiz> getActiveQuizOfCategory(Category c);
	
}
