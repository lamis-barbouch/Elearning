package tn.esprit.spring.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.CommentRepository;
import tn.esprit.spring.dao.RatingSubjectRepository;
import tn.esprit.spring.dao.SubjectForumRepository;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.RatingSubject;
import tn.esprit.spring.entity.SubjectForum;

@Service
public class SubjectForumService implements ISubjectForumService {
	private static final Logger logger = LogManager.getLogger(SubjectForumService.class);

	@Autowired
	CommentRepository commentrep;
	@Autowired
	SubjectForumRepository subjectforumrep;
	@Autowired
	RatingSubjectRepository ratingsubjectrep;
	@Override
	public List<SubjectForum> viewAllSubject() {
		List<SubjectForum> subjectForumList=(List<SubjectForum>) subjectforumrep.findAll();
		return subjectForumList;
	}
	@Override
	public int addSubject(SubjectForum subjectforum) {
		subjectforumrep.save(subjectforum);
		return subjectforum.getId_subject();
	}
	@Override
	public SubjectForum updateSubject(SubjectForum subjectforum) {
		return subjectforumrep.save(subjectforum);
	}
	@Override
	public void deleteSubject(int id) {
		subjectforumrep.deleteById(id);
		
	}
	@Override
	public SubjectForum findbyid(int id) {
		
		return subjectforumrep.findById(id).get();
	}
	@Override
	public List<SubjectForum> findbytitle(String title) {
		List<SubjectForum> list=(List<SubjectForum>) subjectforumrep.findByTitle(title);
		return list;
	}
	@Override
	public List<String> displayBestSubjectsByComments() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> lc= new ArrayList<>();
		List<SubjectForum> listSubjectForum = (List<SubjectForum>) subjectforumrep.findAll();
		
		for (SubjectForum sf: listSubjectForum) {
			listId.add(sf.getId_subject());
			lc.add((int)sf.getComment().size());
		}
		
		List<Integer> sortedList = new ArrayList<>(lc);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Integer ind = listId.get(lc.indexOf(max));// prend le collecte et retourne id d'event
			s =(i+1)+" - The subject : "+subjectforumrep.findById(ind).get().getTitle()+" with "+max+"Comments ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lc.set(lc.indexOf(max), -1);
		}
		
		return list;
	}
	@Override
	public List<String> displayBestSubjectByrating() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> lc= new ArrayList<>();
		List<SubjectForum> listSubjectForum = (List<SubjectForum>) subjectforumrep.findAll();
		
		for (SubjectForum sf: listSubjectForum) {
			listId.add(sf.getId_subject());
			lc.add((int)sf.getRatingsubject().size());
		}
		
		List<Integer> sortedList = new ArrayList<>(lc);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Integer ind = listId.get(lc.indexOf(max));// prend le collecte et retourne id d'event
			s =(i+1)+" - The subject : "+subjectforumrep.findById(ind).get().getTitle()+" with "+max+"Ratings ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lc.set(lc.indexOf(max), -1);
		}
		
		return list;
	}

	@Override
	public void affecterRatingASubject(int id_rating, int id_subject) {
		RatingSubject r=ratingsubjectrep.findById(id_rating).orElse(null);
		SubjectForum s=subjectforumrep.findById(id_subject).orElse(null);
		if(r.getRating_value().toString().equals("LIKE"))
		{
			int nv_nbreLike=s.getNumber_LikesSubject()+1;
			s.setNumber_LikesSubject(nv_nbreLike);
		}
		else if(r.getRating_value().toString().equals("DISLIKE"))
		{
			int nv_nbreDislike=s.getNumber_disLikesSubject()+1;
			s.setNumber_LikesSubject(nv_nbreDislike);
		}
		subjectforumrep.save(s);
		
	}
	@Override
	public void disaffecterRatingASubject(int id_rating, int id_subject) {
		RatingSubject r=ratingsubjectrep.findById(id_rating).orElse(null);
		SubjectForum s=subjectforumrep.findById(id_subject).orElse(null);
		if(r.getRating_value().toString().equals("LIKE"))
		{
			int nv_nbreLike=s.getNumber_LikesSubject()+1;
			s.setNumber_LikesSubject(nv_nbreLike);
		}
		else if(r.getRating_value().toString().equals("DISLIKE"))
		{
			int nv_nbreDislike=s.getNumber_disLikesSubject()-1;
			s.setNumber_LikesSubject(nv_nbreDislike);
		}
		subjectforumrep.save(s);
		
		
	}

//	@Override
//	public List<String> commentairesList(int id_subject) {
//		List<String> list=subjectforumrep.commentairesList(id_subject);
//		return list;
//	}
	@Override
	public List<String> commentairesList(int id_subject) {
		SubjectForum s=subjectforumrep.findById(id_subject).orElse(null);
		List<Comment> listcom=new ArrayList<Comment>();
		listcom=s.getComment();
		List<String> lnomc=new ArrayList<String>();
		for(Comment c:listcom)
		{
			lnomc.add("NAME AND LASTNAME: "+c.getClient().getNomUser()+" "+c.getClient().getPrenomUser()+" :"+c.getText_comment());
		}
		return lnomc;
	}
	@Override
	public void affecterCommentASubject(int id, int id_subject) {
	Comment c=commentrep.findById(id).orElse(null);
	SubjectForum s=subjectforumrep.findById(id_subject).orElse(null);
	s.getComment().add(c);		
	subjectforumrep.save(s);
			
		
	}
//	public int diffdate(int id){
//		SubjectForum s=subjectforumrep.findById(id).orElse(null);
//		java.util.Date date=new java.util.Date();  
//		long diff=date.getTime()-s.getDate_subject().getTime();
//		int diffDays=(int)(diff/(24*60*60*1000));
//		return diffDays;
//	}
	
	public int diffdate(Date date_subject ){
	java.util.Date date=new java.util.Date();  
	long diff=date.getTime()-date_subject.getTime();
		int diffDays=(int)(diff/(24*60*60*1000));
		return diffDays;
	}
	@Override
	public void autodeleteSubject() {
		List<SubjectForum> listSubjectForum = (List<SubjectForum>) subjectforumrep.findAll();
		for (SubjectForum sf: listSubjectForum) {
			if((diffdate(sf.getDate_subject())>=7)&&(sf.getComment().size()==0)){
				subjectforumrep.delete(sf);
			}
		}
	}
	
	
	
}