package tn.esprit.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DatabaseFile;


@Repository
public interface DatabaseFileRepository extends JpaRepository<DatabaseFile, String> {

}