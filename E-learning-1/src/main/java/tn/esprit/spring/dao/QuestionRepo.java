package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Question;


@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
}

