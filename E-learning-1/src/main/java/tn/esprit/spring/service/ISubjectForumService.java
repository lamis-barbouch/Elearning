package tn.esprit.spring.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.SubjectForum;

public interface ISubjectForumService {
	
	public List<SubjectForum> viewAllSubject();//voir la liste des sujets
	public int addSubject(SubjectForum subjectforum);//ajouter sujet by client
	public SubjectForum updateSubject(SubjectForum subjectforum);
	public void deleteSubject(int id) ;
	public SubjectForum findbyid(int id);//chercher by id
	public List<SubjectForum> findbytitle(String title);	
	public List<String> displayBestSubjectsByComments();
	public List<String> displayBestSubjectByrating();
	public void affecterRatingASubject(int id_rating, int id_subject);
	public void disaffecterRatingASubject(int id_rating, int id_subject);
	public  List<String>commentairesList (int id_subject);
	public void affecterCommentASubject(int id, int id_subject);
	void autodeleteSubject() ;
	//public List<SubjectForum> subList(@Param("mydate") Double mydate);



	//public List<Comment> commentaireList(int idSubject,int idClient);
	  //List<Long> notcommented() ;
	// void autodeleteSubject() ;
	// void addrate(int value,long id);
	// int maxrate(long id) ;
	// int minrate(long id);
}