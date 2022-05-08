package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.SubmitQuestion;


@Repository
public interface SubmitQuestionRepo extends JpaRepository<SubmitQuestion,Long> {
}
