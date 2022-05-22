package tn.esprit.spring.entity;

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
public class ExamQuiz {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;
    private String title;
    private int numberOfQuestion;
    private double maxMarks;
    private long quizTime;
    private long remainingTime;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questionDtoList;

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

	public long getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}

	public long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(long remainingTime) {
		this.remainingTime = remainingTime;
	}

	public List<Question> getQuestionDtoList() {
		return questionDtoList;
	}

	public void setQuestionDtoList(List<Question> questionDtoList) {
		this.questionDtoList = questionDtoList;
	}

	public ExamQuiz(long quizId, String title, int numberOfQuestion, double maxMarks, long quizTime, long remainingTime,
			List<Question> questionDtoList) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.numberOfQuestion = numberOfQuestion;
		this.maxMarks = maxMarks;
		this.quizTime = quizTime;
		this.remainingTime = remainingTime;
		this.questionDtoList = questionDtoList;
	}

	public ExamQuiz() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
