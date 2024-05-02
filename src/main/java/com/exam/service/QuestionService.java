package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;

@Service
public interface QuestionService {

	// add question
	Question addQuestion(Question question);

	// update question
	Question updateQuestion(Question question);
	
	//getting all question
	
	Set<Question> getQuestions();
	
	//get question by id
	Question getQuestion(Long quesId);
	
	//list of question on the basis of quiz
	Set<Question> getQuestionOfQuiz(Quiz quiz);
	
	//delete question
	void deleteQuestion(Long quesId);
	

}
