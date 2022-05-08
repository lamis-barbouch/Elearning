package tn.esprit.spring.dao;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.*;


@Repository
public interface SubjectForumRepository extends CrudRepository<SubjectForum,Integer>{

	public List<SubjectForum> findByTitle(String title);
 
	
	
//	@Query("select s from Subject s where CURRENT_DATE - s.date_subject >=:maxdate")
//	 public List<SubjectForum> subList(@Param("maxdate") Date mydate);
//	
//	@Query("Select s From SubjectForum s "
//			+ "JOIN  s.getComment() c"
//			+ " where s.id=:id_subject") 
//	public List<String> commentairesList(@Param("id_subject")int id_subject);
}
