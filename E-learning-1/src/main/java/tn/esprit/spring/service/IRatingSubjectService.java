package tn.esprit.spring.service;

import tn.esprit.spring.entity.RatingSubject;

public interface IRatingSubjectService {
	public int addRatingSubject(RatingSubject ratingsubject) ;
	public RatingSubject updateRatingSubject (RatingSubject ratingsubject);
	public void deleteRatingSubject(int id_rating); 
	

}