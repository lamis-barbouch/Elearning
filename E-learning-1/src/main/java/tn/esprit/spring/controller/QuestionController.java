package tn.esprit.spring.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.ResponseDto;
import tn.esprit.spring.service.QuestionService;
import tn.esprit.spring.service.QuizService;


@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
    private final QuizService quizService;
    private final QuestionService questionService;

    public QuestionController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    /*-------------------------------------------------*/
    
    @PostMapping("/add/{quizId}")
    @ResponseBody
    public Question addQuestion1(@RequestBody Question question,@PathVariable(value = "quizId") long quizId)
    {
    	question.setQuiz(this.quizService.getQuiz(quizId));
    	
		return questionService.saveQuestion(question);
    	
    	
    }
    	
    

    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        question.setQuiz(this.quizService.getQuiz(question.getQuiz().getQuizId()));
        Question savedQuestion = this.questionService.saveQuestion(question);

        if (savedQuestion != null) {
            return ResponseEntity.ok(new ResponseDto("Save"));
        } else {
            return ResponseEntity.ok(new ResponseDto("Fail"));
        }
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(new ResponseDto("Delete"));
    }

}
