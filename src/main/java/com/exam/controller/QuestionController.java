package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.Map;

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

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// update question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get all question of quiz ID
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable Long quizId) {

		// this way in which we get all the question of a particular quiz
//		Quiz quiz = new Quiz();
//		quiz.setQId(quizId);
//		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//		return ResponseEntity.ok(questionOfQuiz);

		// one more field in database called number of Question
		// using number of question field we get the specific question as list or set

		Quiz quiz = this.quizService.getQuiz(quizId);

		Set<Question> questions = quiz.getQuestions();

		List<Question> list = new ArrayList<>(questions);
		System.out.println("list " + list.size());
		System.out.println("start");
		String s = quiz.getNumberOfQuestions();
		System.out.println("end");
		int num = Integer.parseInt(s.trim());
		System.out.println("number of question : " + num);

		if (list.size() >= Integer.parseInt(quiz.getNumberOfQuestions())) {

			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));

		}
		
		list.forEach((q)->{
			q.setAnswer("");
		});

		Collections.shuffle(list);

		return ResponseEntity.ok(list);
	}

	// get all question on the basic of quiz id
	@GetMapping("/quiz/questions/{quizId}")
	public ResponseEntity<?> getAllQuestionOfQuiz(@PathVariable Long quizId) {

		// this way in which we get all the question of a particular quiz
		Quiz quiz = new Quiz();
		quiz.setQId(quizId);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);

	}

	// get single question
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {

		return this.questionService.getQuestion(quesId);
	}

	// delete question
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}
	
	//evalute quiz -- in this method we can fetch data from front end and calculate marks and other detail and send a object to front end
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		System.out.println(questions);
		
		double marksGot =0;
		int correctAnswer =0;
		int attempted = 0;
		int unattempted = 0;
		int wrongAnswer = 0;

				
		
		for(Question q : questions) {
			//single question
			Question ques = this.questionService.getQuestion(q.getQueId());
			
			if(ques.getAnswer().trim().equals(q.getGivenAnswer().trim())) {
				//correct
				correctAnswer++;
				
				double singleMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot += singleMarks;
				
			}

			if(q.getGivenAnswer().trim() != "") {
				attempted++;
			}
			
			
		}
		
		unattempted += questions.size()-attempted;
		wrongAnswer += attempted-correctAnswer;
		
		Map<String,Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted,"unattempted",unattempted,"wrongAnswer",wrongAnswer);
		
		return ResponseEntity.ok(map);
	}
	
}
