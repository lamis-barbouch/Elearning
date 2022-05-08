package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.QuizRepo;
import tn.esprit.spring.dao.SubmitQuizRepo;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.entity.SubmitQuestion;
import tn.esprit.spring.entity.SubmitQuiz;



@Service
public class QuizService {
    private final QuizRepo quizRepo;
    private final SubmitQuizRepo submitQuizRepo;

    public QuizService(QuizRepo quizRepo, SubmitQuizRepo submitQuizRepo) {
        this.quizRepo = quizRepo;
        this.submitQuizRepo = submitQuizRepo;
    }

    /*------------------------------------------------------*/

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    public Quiz getQuiz(Long quizId) {
        return quizRepo.getById(quizId);
    }

    public Quiz getQuizByCode(String code) {
        return quizRepo.findByCode(code);
    }

    public List<Quiz> getQuizByUsername(String username) {
        return quizRepo.findAllByCreatedBy(username);
    }

    public void deleteQuiz(Long quizId) {
        Quiz quiz = quizRepo.getById(quizId);
        quizRepo.delete(quiz);
    }

    public void disableQuiz(Long quizId) {
        Quiz quiz = quizRepo.getById(quizId);
        if (!quiz.getIsPublic()) {
            quiz.setIsEnable(false);
            quizRepo.save(quiz);
        }
    }

    public List<Quiz> getAllActiveQuizzes() {
        return quizRepo.findAllByIsEnableAndIsPublic(true, true);
    }

    public SubmitQuiz submitQuiz(SubmitQuiz submitQuiz) {
        submitQuiz.setTotalCorrectAnswer(this.getCorrectQuestions(submitQuiz.getSubmitQuestionList()));
        return this.submitQuizRepo.save(submitQuiz);
    }

    public SubmitQuiz getSubmitQuiz(Long submitQuizId) {
        return this.submitQuizRepo.getById(submitQuizId);
    }

    public List<SubmitQuiz> getQuizParticipantsResult(Long quizId) {
        return submitQuizRepo.findAllByQuizId(quizId);
    }

    /*--------------------HELPER METHOD-----------------------*/
    private int getCorrectQuestions(List<SubmitQuestion> submitQuestionList) {
        int correctAnswer = 0;
        for (SubmitQuestion submitQuestion : submitQuestionList) {
            if (submitQuestion.getAnswer().equals(submitQuestion.getGivenAnswer())) {
                correctAnswer++;
            }
        }
        return correctAnswer;
    }



}
