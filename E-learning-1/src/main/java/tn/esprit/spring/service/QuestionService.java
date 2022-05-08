package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.QuestionRepo;
import tn.esprit.spring.dao.SubmitQuestionRepo;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.SubmitQuestion;

@Service
public class QuestionService {
    private final QuestionRepo questionRepo;
    private final SubmitQuestionRepo submitQuestionRepo;

    public QuestionService(QuestionRepo questionRepo, SubmitQuestionRepo submitQuestionRepo) {
        this.questionRepo = questionRepo;
        this.submitQuestionRepo = submitQuestionRepo;
    }

    /*-----------------------------------------------------------------------------------*/

    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }

    public Question getQuestion(Long questionId) {
        return questionRepo.getById(questionId);
    }

    public void deleteQuestion(Long questionId) {
        Question question = this.questionRepo.getById(questionId);
        this.questionRepo.delete(question);
    }

    public List<SubmitQuestion> saveSubmitQuestionList(List<SubmitQuestion> submitQuestionList) {
        for (SubmitQuestion submitQuestion:submitQuestionList) {
            submitQuestion.setAnswer(getQuestion(submitQuestion.getQuestionId()).getAnswer());
        }
        return submitQuestionRepo.saveAll(submitQuestionList);
    }
}
