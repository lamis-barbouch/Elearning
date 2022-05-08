package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Quiz;


@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {
    @Query
    List<Quiz> findAllByCreatedBy(String username);

    @Query
    Quiz findByCode(String code);

    @Query
    List<Quiz> findAllByIsEnableAndIsPublic(Boolean isEnable, Boolean isPublic);

}