package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category implements Serializable{

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long categoryId;

	    @Column
	    private String title;

	    @Column
	    private String description;
	    
	    @OneToMany(fetch = FetchType.LAZY)
	    @JsonIgnore
	    @JoinTable(name = "category_quiz",
	            joinColumns = @JoinColumn(name = "category_id"),
	            inverseJoinColumns = @JoinColumn(name = "quiz_id"))
	    private Set<Quiz> quizSet = new LinkedHashSet<>();

		public long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(long categoryId) {
			this.categoryId = categoryId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Set<Quiz> getQuizSet() {
			return quizSet;
		}

		public void setQuizSet(Set<Quiz> quizSet) {
			this.quizSet = quizSet;
		}

		public Category(long categoryId, String title, String description, Set<Quiz> quizSet) {
			super();
			this.categoryId = categoryId;
			this.title = title;
			this.description = description;
			this.quizSet = quizSet;
		}

		public Category() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
}
