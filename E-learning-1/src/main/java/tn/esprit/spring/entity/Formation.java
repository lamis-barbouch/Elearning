package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Formation implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idFormation;
	private String titreFormation;
	
	@Lob 
	@Column(name="descriptionFormation", length=512)
	private String descriptionFormation;
	
	@Lob 
	@Column(name="descriptiondetailleFormation", length=512)
	private String descriptiondetailleFormation;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date dateFormation;
	public String getDescriptiondetailleFormation() {
		return descriptiondetailleFormation;
	}
	public void setDescriptiondetailleFormation(String descriptiondetailleFormation) {
		this.descriptiondetailleFormation = descriptiondetailleFormation;
	}
	private double prixFormation;
	private int nbDePlace;
	
	
	public int getNbDePlace() {
		return nbDePlace;
	}
	public void setNbDePlace(int nbDePlace) {
		this.nbDePlace = nbDePlace;
	}
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	private List<User>listUsers = new ArrayList<>();
	
	
	
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getTitreFormation() {
		return titreFormation;
	}
	public void setTitreFormation(String titreFormation) {
		this.titreFormation = titreFormation;
	}
	public String getDescriptionFormation() {
		return descriptionFormation;
	}
	public void setDescriptionFormation(String descriptionFormation) {
		this.descriptionFormation = descriptionFormation;
	}
	public Date getDateFormation() {
		return dateFormation;
	}
	public void setDateFormation(Date dateFormation) {
		this.dateFormation = dateFormation;
	}
	public double getPrixFormation() {
		return prixFormation;
	}
	public void setPrixFormation(double prixFormation) {
		this.prixFormation = prixFormation;
	}
	
	
	
	public List<User> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", titreFormation=" + titreFormation
				+ ", descriptionFormation=" + descriptionFormation + ", dateFormation=" + dateFormation
				+ ", prixFormation=" + prixFormation + "]";
	}
	public Formation(int idFormation, String titreFormation, String descriptionFormation, Date dateFormation,
			double prixFormation) {
		super();
		this.idFormation = idFormation;
		this.titreFormation = titreFormation;
		this.descriptionFormation = descriptionFormation;
		this.dateFormation = dateFormation;
		this.prixFormation = prixFormation;
	}
	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
