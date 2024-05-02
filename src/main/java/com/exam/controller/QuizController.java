package com.exam.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;

	// add quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}

	// update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {

		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	// get all quiz
	@GetMapping("/")
	public ResponseEntity<?> quizzes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}

	// get single quiz
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable Long quizId) {
		return this.quizService.getQuiz(quizId);
	}
	
	//delete quiz
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable Long quizId) {
		this.quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/category/{cId}")
	public List<Quiz> getQuizOfCategory(@PathVariable Long cId ){
		
		Category category = new Category();
		category.setId(cId);
						
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	
	//get active quizzes
	@GetMapping("/category/active/{cId}")
	public List<Quiz> getActiveQuizOfCategory(@PathVariable Long cId ){
		
		Category category = new Category();
		category.setId(cId);
						
		return this.quizService.getActiveQuizOfCategory(category);
	}
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuiz(){

		return this.quizService.getActiveQuizzes();
	}
	

}
