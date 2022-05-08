package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.RatingSubjectRepository;
import tn.esprit.spring.entity.RatingSubject;

@Service
public class RatingSubjectService implements IRatingSubjectService{

	
	@Autowired
	RatingSubjectRepository ratingsubjectrep;
	
	
	@Override
	public int addRatingSubject(RatingSubject ratingsubject) {
		ratingsubjectrep.save(ratingsubject);
		return ratingsubject.getId_rating();
	}

	@Override
	public RatingSubject updateRatingSubject(RatingSubject ratingsubject) {
		return ratingsubjectrep.save(ratingsubject);
	}

	@Override
	public void deleteRatingSubject(int id_rating) {
		ratingsubjectrep.deleteById(id_rating);		
	}

}