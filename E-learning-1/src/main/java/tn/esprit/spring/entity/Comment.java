package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_comment;
	private String text_comment;
	@Temporal(TemporalType.DATE)
	private Date date_comment;
	public Date getDate_comment() {
		return date_comment;
	}

	public void setDate_comment(Date date_comment) {
		this.date_comment = date_comment;
	}
	
	@JsonIgnoreProperties({"first_name", "last_name","phone_number", "email","login", "password","gender","date","alerte","listevent","comment","listsubjectforum","address","city"})
	@ManyToOne
	private User client;
	
	@JsonIgnore
	@ManyToOne
	private SubjectForum subjectforum;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Dictionary> dictionary;
	

	
	

	

	public List<Dictionary> getDictionary() {
		return dictionary;
	}

	public void setDictionary(List<Dictionary> dictionary) {
		this.dictionary = dictionary;
	}

	public int getId_comment() {
		return id_comment;
	}
	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}
	public String getText_comment() {
		return text_comment;
	}
	public void setText_comment(String text_comment) {
		this.text_comment = text_comment;
	}
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
	}
	public SubjectForum getSubjectforum() {
		return subjectforum;
	}
	public void setSubjectforum(SubjectForum subjectforum) {
		this.subjectforum = subjectforum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//constructor
	public Comment(int id_comment, String text_comment) {
		super();
		this.id_comment = id_comment;
		this.text_comment = text_comment;
		
	}
	
	public Comment(int id_comment, String text_comment,SubjectForum subjectforum) {
		super();
		this.id_comment = id_comment;
		this.text_comment = text_comment;
		this.subjectforum = subjectforum;
	}
	
	public Comment(int id_comment, String text_comment, User client) {
		super();
		this.id_comment = id_comment;
		this.text_comment = text_comment;
		this.client = client;
		
	}

	
	
	public Comment(int id_comment, String text_comment, User client, SubjectForum subjectforum) {
		super();
		this.id_comment = id_comment;
		this.text_comment = text_comment;
		this.client = client;
		this.subjectforum = subjectforum;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_comment;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id_comment != other.id_comment)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Comment [id_comment=" + id_comment + ", text_comment=" + text_comment + ", date_comment=" + date_comment
				+ ", client=" + client + ", subjectforum=" + subjectforum + "]";
	}
	
	
	
	
}