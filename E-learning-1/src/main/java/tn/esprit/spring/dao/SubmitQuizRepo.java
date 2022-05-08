package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.SubmitQuiz;


@Repository
public interface SubmitQuizRepo extends JpaRepository<SubmitQuiz,Long> {
    @Query
    List<SubmitQuiz> findAllByQuizId(long quizId);
}
