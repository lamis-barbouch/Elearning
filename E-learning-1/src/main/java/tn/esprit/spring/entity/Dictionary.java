package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Dictionary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_mot;
	private String mot;
	
//	@OneToMany(mappedBy="dictionary")
//	private List<Comment> comment;
	
	public Dictionary(int id_mot, String mot) {
		super();
		this.id_mot = id_mot;
		this.mot = mot;
	}
	public Dictionary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dictionary(String mot) {
		super();
		this.mot = mot;
	}
//	public List<Comment> getComment() {
//		return comment;
//	}
//	public void setComment(List<Comment> comment) {
//		this.comment = comment;
//	}
	public int getId_mot() {
		return id_mot;
	}
	public void setId_mot(int id_mot) {
		this.id_mot = id_mot;
	}
	public String getMot() {
		return mot;
	}
	public void setMot(String mot) {
		this.mot = mot;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_mot;
		result = prime * result + ((mot == null) ? 0 : mot.hashCode());
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
		Dictionary other = (Dictionary) obj;
		if (id_mot != other.id_mot)
			return false;
		if (mot == null) {
			if (other.mot != null)
				return false;
		} else if (!mot.equals(other.mot))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Dictionary [id_mot=" + id_mot + ", mot=" + mot + "]";
	}
	

}