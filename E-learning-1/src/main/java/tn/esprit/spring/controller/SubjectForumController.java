package tn.esprit.spring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.SubjectForumRepository;
import tn.esprit.spring.entity.SubjectForum;
import tn.esprit.spring.service.SubjectForumService;


@RestController
public class SubjectForumController {

	@Autowired
	SubjectForumService isubjectForumservice;
	
	@Autowired
	SubjectForumRepository subrep;
	
	//http://localhost:8082/springMVC/servlet/viewAllSubject
	@GetMapping(value="/viewAllSubject")//success
	@ResponseBody
	public List<SubjectForum> viewAllSubject(){
		return isubjectForumservice.viewAllSubject();
		}
	//http://localhost:8082/springMVC/servlet/addSubject
	@PostMapping("/addSubject")//success
	@ResponseBody
	public int addSubject(@RequestBody SubjectForum subjectforum){
		return isubjectForumservice.addSubject(subjectforum);
		}
	
	//http://localhost:8082/springMVC/servlet/updateSubject
	@PutMapping(value="/updateSubject")//success
	@ResponseBody
	public SubjectForum updateSubject(@RequestBody SubjectForum subjectforum){
		return isubjectForumservice.updateSubject(subjectforum);
		}
	
	//http://localhost:8082/springMVC/servlet/deleteSubject/1
	@DeleteMapping("/deleteSubject/{id}")//success
	@ResponseBody
	public void deleteSubject(@PathVariable("id") int id) {
		isubjectForumservice.deleteSubject(id);
		}
	
	@DeleteMapping("/autodeleteSubject")//success
	@ResponseBody
	public void autodeleteSubject() {isubjectForumservice.autodeleteSubject();}
	
	//http://localhost:8082/springMVC/servlet/findByIdSubject/1
	@GetMapping("/findByIdSubject/{id}")//success
	@ResponseBody
	public SubjectForum findbyid(@PathVariable("id")int id){
		return isubjectForumservice.findbyid(id);
		}
	
//	@GetMapping("/diffdate/{id}")//success
//	@ResponseBody
//	public int diffdate(@PathVariable("id")int id){
//		return isubjectForumservice. diffdate(id);
//		}
	
	//http://localhost:8082/springMVC/servlet/findbytitleSubject/e-commerce
	@GetMapping("/findbytitleSubject/{title}")//success
	@ResponseBody
	public List<SubjectForum> findbytitle(@PathVariable("title")String title){
		return isubjectForumservice.findbytitle(title);
		}
	
	//http://localhost:8082/springMVC/servlet/displayBestSubjectsByComments
	@GetMapping("/displayBestSubjectsByComments")//sucess
	@ResponseBody
	public List<String> displayBestSubjectsByComments(){
		return isubjectForumservice.displayBestSubjectsByComments();
		}
	
	//http://localhost:8082/springMVC/servlet/displayBestSubjectByrating
	@GetMapping("/displayBestSubjectByrating")//success
	@ResponseBody
	public List<String> displayBestSubjectByrating(){return isubjectForumservice.displayBestSubjectByrating();}


	@PostMapping(value="/affecterRatingASubject/{id_rating}/{id_subject}") //success
	@ResponseBody
	public void affecterRatingASubject (@PathVariable("id_rating")int id_rating,@PathVariable("id_subject")int id_subject){
		isubjectForumservice.affecterRatingASubject(id_rating,id_subject);}

	@GetMapping(value="/commentairesList/{id_subject}") //success
	@ResponseBody
	public List<String> commentairesList(@PathVariable("id_subject")int id_subject) {return isubjectForumservice.commentairesList(id_subject);}
	
	@PostMapping(value="/affecterCommentASubject/{id}/{id_subject}") //success
	@ResponseBody
	public void affecterCommentASubject(@PathVariable("id")int id,@PathVariable("id_subject")int id_subject){
		isubjectForumservice.affecterCommentASubject(id,id_subject);}
}
