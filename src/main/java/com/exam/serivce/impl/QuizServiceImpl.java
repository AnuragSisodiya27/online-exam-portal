package com.exam.serivce.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.exam.Category;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepo;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return this.quizRepo.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizzes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(this.quizRepo.findAll());
	}

	@Override
	public Quiz getQuiz(Long QuizId) {
		// TODO Auto-generated method stub

		return this.quizRepo.findById(QuizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		// TODO Auto-generated method stub
		// two way to delete
		// one
		this.quizRepo.deleteById(quizId);

		// second -- by delete object
//		Quiz quiz =  new Quiz();
//		quiz.setQId(quizId);
//		this.quizRepo.delete(quiz);

	}

	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategory(category);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizRepo.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizOfCategory(Category c) {
		// TODO Auto-generated method stub
		return this.quizRepo.findByCategoryAndActive(c, true);
	}

}
