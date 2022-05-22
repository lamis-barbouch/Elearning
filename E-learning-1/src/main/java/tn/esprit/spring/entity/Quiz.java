package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@Entity
public class Quiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long quizId;
	
    private String title;
    
    @Column(length = 500)
    private String description;
    private int numberOfQuestion;
    private double maxMarks;
    private String createdBy;
    private String code;
    private Boolean isPublic;
    private Boolean isEnable = false;
    private String date;
    private String startTime;
    private String endTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @JoinTable(name = "category_quiz",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "quiz_question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Question> questionSet = new HashSet<>();

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
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

	public int getNumberOfQuestion() {
		return numberOfQuestion;
	}

	public void setNumberOfQuestion(int numberOfQuestion) {
		this.numberOfQuestion = numberOfQuestion;
	}

	public double getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Question> getQuestionSet() {
		
		return questionSet;
	}

	public void setQuestionSet(Set<Question> questionSet) {
		this.questionSet = questionSet;
	}

	public Quiz(long quizId, String title, String description, int numberOfQuestion, double maxMarks, String createdBy,
			String code, Boolean isPublic, Boolean isEnable, String date, String startTime, String endTime,
			tn.esprit.spring.entity.Category category, Set<tn.esprit.spring.entity.Question> questionSet) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.description = description;
		this.numberOfQuestion = numberOfQuestion;
		this.maxMarks = maxMarks;
		this.createdBy = createdBy;
		this.code = code;
		this.isPublic = isPublic;
		this.isEnable = isEnable;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.category = category;
		this.questionSet = questionSet;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
