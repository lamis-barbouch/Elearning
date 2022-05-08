package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Dictionary;

public interface ICommentService {
	public int addComment(Comment comment) ;
	public Comment updateComment (Comment comment);
	public void deleteComment(int id); 
	public void filtreBadWords(int id_comment); 
	public List<Comment> commentLists();

}