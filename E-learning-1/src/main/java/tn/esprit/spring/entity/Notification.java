package tn.esprit.spring.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Notification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idNotif;
	private String descriptionNotif;
	
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	private List<User>listUsers = new ArrayList<>();
	
	

	
	
	private int seen =0;
	
	
	
	public int getSeen() {
		return seen;
	}


	public void setSeen(int seen) {
		this.seen = seen;
	}


	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime dateNotif;
	
	
	


	public int getIdNotif() {
		return idNotif;
	}


	public void setIdNotif(int idNotif) {
		this.idNotif = idNotif;
	}


	public String getDescriptionNotif() {
		return descriptionNotif;
	}


	public void setDescriptionNotif(String descriptionNotif) {
		this.descriptionNotif = descriptionNotif;
	}



	

	public LocalDateTime getDateNotif() {
		return dateNotif;
	}


	public void setDateNotif(LocalDateTime dateNotif) {
		this.dateNotif = dateNotif;
	}


	public List<User> getListUsers() {
		return listUsers;
	}


	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}


	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Notification(int idNotif, String descriptionNotif, List<User> listUsers, int seen, LocalDateTime dateNotif) {
		super();
		this.idNotif = idNotif;
		this.descriptionNotif = descriptionNotif;
		this.listUsers = listUsers;
		this.seen = seen;
		this.dateNotif = dateNotif;
	}


	public Notification(String descriptionNotif, LocalDateTime dateNotif) {
		super();
		this.descriptionNotif = descriptionNotif;
		this.dateNotif = dateNotif;
	}


	public Notification(int idNotif, String descriptionNotif,  int seen, LocalDateTime dateNotif) {
		super();
		this.idNotif = idNotif;
		this.descriptionNotif = descriptionNotif;
		this.seen = seen;
		this.dateNotif = dateNotif;
	}
	
	

}
