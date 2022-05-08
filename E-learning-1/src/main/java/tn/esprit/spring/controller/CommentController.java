package tn.esprit.spring.controller;

import org.springframework.web.bind.annotation.RestController;
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

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.service.CommentService;

@RestController
public class CommentController {


	
	@Autowired
	CommentService commentservice;
	
	@PostMapping("/addComment")
	@ResponseBody
	public int addComment(@RequestBody Comment comment){return commentservice.addComment(comment);}
	
//	@GetMapping(value="/CommentLists/{id_subject}")//success
//	@ResponseBody
//	public List<Comment> CommentLists(@PathVariable("id_subject")int id_subject ){return commentservice.CommentLists(id_subject);}
//	
	@PutMapping(value="/updateComment")//success
	@ResponseBody
	public Comment updateComment(@RequestBody Comment comment){return commentservice.updateComment(comment);}
	
	@DeleteMapping("/deleteComment/{id}")//success
	@ResponseBody
	public void deleteComment(@PathVariable("id") int id){commentservice.deleteComment(id);}
	

	@GetMapping(value="/commentLists")//success
	@ResponseBody
	public List<Comment> commentLists(){
		return commentservice.commentLists();
		}
	
	@DeleteMapping("/filtreBadWords/{id_comment}")//success
	@ResponseBody
	public void filtreBadWords(@PathVariable("id_comment") int id_comment){commentservice.filtreBadWords(id_comment);}

	
	

}
