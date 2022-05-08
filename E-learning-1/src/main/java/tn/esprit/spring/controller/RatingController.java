package tn.esprit.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.RatingSubject;
import tn.esprit.spring.service.RatingSubjectService;

@RestController
public class RatingController {

	@Autowired 
	RatingSubjectService ratingsubservice;
	

	@PostMapping("/addRatingSubject")
	@ResponseBody
	public int addRatingSubject(@RequestBody RatingSubject ratingsubject){return ratingsubservice.addRatingSubject(ratingsubject);}
	
	
	@PutMapping("/updateRatingSubject")
	@ResponseBody
	public RatingSubject updateRatingSubject(@RequestBody RatingSubject ratingsubject){return ratingsubservice.updateRatingSubject(ratingsubject);}
	
	@DeleteMapping("/deleteRatingSubject/{id_rating}")//success
	@ResponseBody
	public void deleteRatingSubject(@PathVariable("id_rating") int id_rating){ratingsubservice.deleteRatingSubject(id_rating);}
}