package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query
    Category findByTitle(String title);
}
