package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;


@Entity
public class Reclamation implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReclam;
	
	private String sujet;
	
	@Lob 
	@Column(name="text", length=512)
	private String text;
	private Date date;
	private String response;
	public int getIdReclam() {
		return idReclam;
	}
	public void setIdReclam(int idReclam) {
		this.idReclam = idReclam;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Reclamation(int idReclam, String sujet, String text, Date date, String response) {
		super();
		this.idReclam = idReclam;
		this.sujet = sujet;
		this.text = text;
		this.date = date;
		this.response = response;
	}
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reclamation(int idReclam, String sujet, String text, Date date) {
		super();
		this.idReclam = idReclam;
		this.sujet = sujet;
		this.text = text;
		this.date = date;
	}
	
	
	
}
