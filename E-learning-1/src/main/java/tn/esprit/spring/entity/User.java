package tn.esprit.spring.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cinUser;
	private String nomUser;
	private String prenomUser;
	private String emailUser;
	
	@Column(name="password")
	private String passwordUser;
	private String niveauEtudeApprenant;
	private String telephoneUser;
	private String specialitFormateur;
	private boolean enabled;
	
	@Column(name="is_enabled")
	private int isEnabled;
	
	@Column(updatable=false)
	private String verificationCode;

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROLE",
	joinColumns = {
			@JoinColumn(name="User_id")
	},
	inverseJoinColumns ={
			@JoinColumn(name="Role_id")
	}
	)
	private Set<Role> role;
	
	
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	public int getCinUser() {
		return cinUser;
	}
	public void setCinUser(int cinUser) {
		this.cinUser = cinUser;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public String getNiveauEtudeApprenant() {
		return niveauEtudeApprenant;
	}
	public void setNiveauEtudeApprenant(String niveauEtudeApprenant) {
		this.niveauEtudeApprenant = niveauEtudeApprenant;
	}
	public String getTelephoneUser() {
		return telephoneUser;
	}
	public void setTelephoneUser(String telephoneUser) {
		this.telephoneUser = telephoneUser;
	}
	public String getSpecialitFormateur() {
		return specialitFormateur;
	}
	public void setSpecialitFormateur(String specialitFormateur) {
		this.specialitFormateur = specialitFormateur;
	}
	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}


	
}
