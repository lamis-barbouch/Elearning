package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
@Entity
public class SubmitQuiz implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long submitQuizId;
	    private long quizId;
	    private String title;
	    private int numberOfQuestion;
	    private double maxMarks;
	    private int totalCorrectAnswer;
	    private String username;
	    
	    
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	    @JsonIgnore
	    @OneToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "submit_quiz_question",
	            joinColumns = @JoinColumn(name = "submitQuizId"),
	            inverseJoinColumns = @JoinColumn(name = "submitQuestionId"))
	    private List<SubmitQuestion> submitQuestionList;
		public long getSubmitQuizId() {
			return submitQuizId;
		}
		public void setSubmitQuizId(long submitQuizId) {
			this.submitQuizId = submitQuizId;
		}
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
		public int getTotalCorrectAnswer() {
			return totalCorrectAnswer;
		}
		public void setTotalCorrectAnswer(int totalCorrectAnswer) {
			this.totalCorrectAnswer = totalCorrectAnswer;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public List<SubmitQuestion> getSubmitQuestionList() {
			return submitQuestionList;
		}
		public void setSubmitQuestionList(List<SubmitQuestion> submitQuestionList) {
			this.submitQuestionList = submitQuestionList;
		}
		public SubmitQuiz(long submitQuizId, long quizId, String title, int numberOfQuestion, double maxMarks,
				int totalCorrectAnswer, String username,
				List<tn.esprit.spring.entity.SubmitQuestion> submitQuestionList) {
			super();
			this.submitQuizId = submitQuizId;
			this.quizId = quizId;
			this.title = title;
			this.numberOfQuestion = numberOfQuestion;
			this.maxMarks = maxMarks;
			this.totalCorrectAnswer = totalCorrectAnswer;
			this.username = username;
			this.submitQuestionList = submitQuestionList;
		}
		public SubmitQuiz() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    
}
