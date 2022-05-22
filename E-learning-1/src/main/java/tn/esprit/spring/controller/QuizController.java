package tn.esprit.spring.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import freemarker.template.TemplateException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import tn.esprit.spring.dao.QuizRepo;
import tn.esprit.spring.dao.formationRepository;
import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.ExamQuiz;
import tn.esprit.spring.entity.Question;
import tn.esprit.spring.entity.Quiz;
import tn.esprit.spring.entity.ResponseDto;
import tn.esprit.spring.entity.SubmitQuestion;
import tn.esprit.spring.entity.SubmitQuiz;
import tn.esprit.spring.service.CategoryService;
import tn.esprit.spring.service.QuestionService;
import tn.esprit.spring.service.QuizService;
import tn.esprit.spring.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/quiz")
public class QuizController {
	
	 @Autowired
    UserService userService;
	 @Autowired
    QuizService quizService;
	 
	 @Autowired
    CategoryService categoryService;
	 
	 @Autowired
     QuestionService questionService;
     @Autowired
  	QuizRepo quizrep;



    /*--------------------------------------------------*/
   /* @PostMapping("/")
    @ResponseBody
    public void addQuiz(@RequestBody Quiz quiz) {
        quiz.setCode(this.generateQuizCode());
        quiz.setCategory(this.categoryService.getCategory(quiz.getCategory().getCategoryId()));
        quizService.saveQuiz(quiz);
    }
*/
    
    @PostMapping("/add/{categoryId}")
    @ResponseBody
    public void addQuiz(@RequestBody Quiz quiz,@PathVariable(value="categoryId")long categoryId) {
        quiz.setCode(this.generateQuizCode());
        quiz.setCategory(this.categoryService.getCategory(categoryId));
        quizService.saveQuiz(quiz);
    }

   
    @GetMapping("/teacher-quizzes/{username}")
    public List<Quiz> getTeacherAllQuizzes(@PathVariable("username") String username) {
        List<Quiz> quizList = quizService.getQuizByUsername(username);
        return quizList;
    }
    
    
    /*@GetMapping("/teacher-quizzes/{username}")
    public ResponseEntity<?> getTeacherAllQuizzes(@PathVariable("username") String username) {
        List<Quiz> quizList = quizService.getQuizByUsername(username);
        return ResponseEntity.ok(getQuizDtoList(quizList));
    }
    */

    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("quizId") Long quizId) {
        this.quizService.deleteQuiz(quizId);
        return ResponseEntity.ok(new ResponseDto("Delete"));
    }

    @PutMapping("/update-type")
    public ResponseEntity<?> updateQuizType(@RequestBody Quiz quizDto) {
        Quiz quiz = this.quizService.getQuiz(quizDto.getQuizId());
        quiz.setIsPublic(quizDto.getIsPublic());
        this.quizService.saveQuiz(quiz);
        return ResponseEntity.ok(new ResponseDto("Quiz Type Update"));
    }

    @PutMapping("/update-visibility")
    public ResponseEntity<?> updateQuizVisibility(@RequestBody Quiz quizDto) {
        Quiz quiz = this.quizService.getQuiz(quizDto.getQuizId());
        quiz.setIsEnable(quizDto.getIsEnable());
        this.quizService.saveQuiz(quiz);
        return ResponseEntity.ok(new ResponseDto("Quiz Visibility Update"));
    }

    @GetMapping("/disable-quiz/{quizId}")
    public ResponseEntity<?> disableQuizAfterExam(@PathVariable("quizId") Long quizId) {
        this.quizService.disableQuiz(quizId);
        return ResponseEntity.ok(new ResponseDto("Disable Quiz"));
    }


    @PutMapping("/schedule")
    public ResponseEntity<?> setQuizSchedule(@RequestBody Quiz quizScheduleDto) {
        Quiz quiz = this.quizService.getQuiz(quizScheduleDto.getQuizId());
        quiz.setDate(quizScheduleDto.getDate());
        quiz.setStartTime(quizScheduleDto.getStartTime());
        quiz.setEndTime(quizScheduleDto.getEndTime());
        this.quizService.saveQuiz(quiz);
        return ResponseEntity.ok(new ResponseDto("Set Schedule"));
    }
    
    @GetMapping("/questions/{quizId}")
    public Set<Question> getAllQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        return quiz.getQuestionSet();
    }

    /*
     * @GetMapping("/questions/{quizId}")
    public ResponseEntity<?> getAllQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        return ResponseEntity.ok(getQuizQuestionDtoSet(quiz));
    }
    */

    @GetMapping("/active-quizzes")
    public List<Quiz> getAllActiveQuizzes() {
        List<Quiz> quizList = quizService.getAllActiveQuizzes();
        return  quizList;
    }

    @GetMapping("/get-quiz/{quizId}")
    public Quiz getQuizInformation(@PathVariable("quizId") Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);
        return quiz;
    }

    @GetMapping("/find-quiz/{code}")
    public ResponseEntity<?> getQuizByCode(@PathVariable("code") String code) {
        Quiz quiz = quizService.getQuizByCode(code);
        if (quiz != null) {
            return ResponseEntity.ok(getQuizDto(quiz));
        } else {
            return ResponseEntity.ok(new ResponseDto("Not Found"));
        }

    }

    @GetMapping("/start/{quizId}")
    public Quiz getQuizQuestionsForExam(@PathVariable("quizId") Long quizId) throws ParseException {
        Quiz quiz = this.quizService.getQuiz(quizId);
        ExamQuiz examQuizDto = getExamQuizDto(quiz);

        //Need Time Counting Code
        if (quiz.getStartTime() != null && quiz.getEndTime() != null) {
            examQuizDto.setQuizTime(this.timeCount(quiz.getStartTime(), quiz.getEndTime()));

            //Remaining Time For Quiz
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
            LocalDateTime now = LocalDateTime.now();
            String currentTime = timeFormat.format(now);
            examQuizDto.setRemainingTime(this.timeCount(currentTime, quiz.getEndTime()));
        } else {
            examQuizDto.setQuizTime((long) examQuizDto.getNumberOfQuestion() * 2 * 60 * 1000);
            examQuizDto.setRemainingTime((long) examQuizDto.getNumberOfQuestion() * 2 * 60 * 1000);

        }

        return quiz;
    }

    @PostMapping("/submit/{username}")
    public SubmitQuiz submitQuizExam(@PathVariable("username") String username, @RequestBody SubmitQuiz submitQuizDto) throws MessagingException, TemplateException, IOException {
        SubmitQuiz submitQuiz = this.getSubmitQuiz(submitQuizDto);
        List<SubmitQuestion> submitQuestionList = this.questionService.saveSubmitQuestionList(submitQuiz.getSubmitQuestionList());
        submitQuiz.setSubmitQuestionList(submitQuestionList);
        submitQuiz.setUsername(username);
        SubmitQuiz savedSubmitQuiz = this.quizService.submitQuiz(submitQuiz);

        return savedSubmitQuiz;
    }

   

    @GetMapping("/result/{submitQuizId}")
    public SubmitQuiz getQuizExamResult(@PathVariable("submitQuizId") Long submitQuizId) {
    	SubmitQuiz savedSubmitQuiz=this.getSubmitQuizDto(this.quizService.getSubmitQuiz(submitQuizId));
        return savedSubmitQuiz;
    }
    

    @GetMapping("/quiz-participant-result/{quizId}")
    public ResponseEntity<?> getQuizParticipantsResult(@PathVariable("quizId") Long quizId) {
        return ResponseEntity.ok(this.getSubmitQuizDtoList(this.quizService.getQuizParticipantsResult(quizId)));
    }

    @GetMapping("/quiz-participant-result-pdf/{quizId}")
    public ResponseEntity<?> getQuizParticipantsResultPdf(@PathVariable("quizId") Long quizId) throws FileNotFoundException, JRException {
        Quiz quiz = quizService.getQuiz(quizId);
        String filePath = ResourceUtils.getFile("classpath:templates/pdf/QuizParticipantsResult.jrxml").getAbsolutePath();

        List<SubmitQuiz> list = this.getSubmitQuizDtoList(this.quizService.getQuizParticipantsResult(quizId));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("quizTitle", quiz.getTitle());
        parameters.put("quizCode", quiz.getCode());
        parameters.put("createdBy", quiz.getCreatedBy());
        parameters.put("tableData", dataSource);

        JasperReport report = JasperCompileManager.compileReport(filePath);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        byte[] pdfArray = JasperExportManager.exportReportToPdf(print);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "Result.pdf");

        return new ResponseEntity(pdfArray, headers, HttpStatus.OK);
    }



    /*--------------------- HELPER METHOD ----------------------*/

    private String generateQuizCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 7);
    }

    private Quiz getQuizDto(Quiz quiz) {
        Quiz quizDto = new Quiz();
       // BeanUtils.copyProperties(quiz, quizDto);

        Category categoryDto = new Category();
      //  BeanUtils.copyProperties(quiz.getCategory(), categoryDto);
        quizDto.setCategory(categoryDto);
        return quizDto;
    }

    private List<Quiz> getQuizDtoList(List<Quiz> quizList) {
        List<Quiz> quizDtoList = new ArrayList<>();
        for (Quiz quiz : quizList) {
            Quiz quizDto = new Quiz();
           // BeanUtils.copyProperties(quiz, quizDto);

            Category categoryDto = new Category();
           // BeanUtils.copyProperties(quiz.getCategory(), categoryDto);
            quizDto.setCategory(categoryDto);

            quizDtoList.add(quizDto);
        }
        return quizDtoList;
    }
    
 
    
    
    
   

   private Set<Question> getQuizQuestionDtoSet(Quiz quiz) {
        Set<Question> questionDtoSet = new HashSet<>();
        for (Question question : quiz.getQuestionSet()) {
        	
           Question questionDto = new Question();
           questionDto.getQuestionId();
           questionDto.getContent();
           questionDto.getOption1();
           questionDto.getOption2();
           questionDto.getOption3();
           questionDto.getOption4();
           questionDto.getAnswer();
          //  BeanUtils.copyProperties(question, questionDto);
             questionDtoSet.add(questionDto);
         }
         return questionDtoSet;
     }

   public ExamQuiz getExamQuizDto(Quiz quiz) {
        ExamQuiz examQuizDto = new ExamQuiz();
        //BeanUtils.copyProperties(quiz, examQuizDto);

        /*--Remove Question Answer & Convert it To Shuffle Questions--*/
        Set<Question> questionDtoSet = this.getQuizQuestionDtoSet(quiz);
        List<Question> questionDtoList = new ArrayList<>(this.removeAnswer(questionDtoSet));
        if (questionDtoList.size() > quiz.getNumberOfQuestion()) {
            questionDtoList = questionDtoList.subList(0, quiz.getNumberOfQuestion());
        }
        Collections.shuffle(questionDtoList);
        examQuizDto.setQuestionDtoList(questionDtoList);
        return examQuizDto;
    }


    private Set<Question> removeAnswer(Set<Question> questionDtoSet) {
        Set<Question> questionDtoSet1 = new HashSet<>();
        for (Question questionDto : questionDtoSet) {
            questionDto.setAnswer(null);
            questionDtoSet1.add(questionDto);
        }
        return questionDtoSet1;
    }

    private SubmitQuiz getSubmitQuiz(SubmitQuiz submitQuizDto) {
        SubmitQuiz submitQuiz = new SubmitQuiz();
       // BeanUtils.copyProperties(submitQuizDto, submitQuiz);

        List<SubmitQuestion> submitQuestionList = new ArrayList<>();
        for (SubmitQuestion submitQuestionDto : submitQuizDto.getSubmitQuestionList()) {
            SubmitQuestion submitQuestion = new SubmitQuestion();
          //  BeanUtils.copyProperties(submitQuestionDto, submitQuestion);
            submitQuestionList.add(submitQuestion);
        }
        submitQuiz.setSubmitQuestionList(submitQuestionList);
        return submitQuiz;
    }

    private SubmitQuiz getSubmitQuizDto(SubmitQuiz submitQuiz) {
        SubmitQuiz submitQuizDto = new SubmitQuiz();
       // BeanUtils.copyProperties(submitQuiz, submitQuizDto);
        List<SubmitQuestion> submitQuestionDtoList = new ArrayList<>();
        for (SubmitQuestion submitQuestion : submitQuiz.getSubmitQuestionList()) {
            SubmitQuestion submitQuestionDto = new SubmitQuestion();
         //   BeanUtils.copyProperties(submitQuestion, submitQuestionDto);
            submitQuestionDtoList.add(submitQuestionDto);
        }
        submitQuizDto.setSubmitQuestionList(submitQuestionDtoList);
        return submitQuizDto;
    }

    private List<SubmitQuiz> getSubmitQuizDtoList(List<SubmitQuiz> submitQuizList) {
        List<SubmitQuiz> submitQuizDtoList = new ArrayList<>();
        for (SubmitQuiz submitQuiz : submitQuizList) {
            submitQuizDtoList.add(this.getSubmitQuizDto(submitQuiz));
        }
        return submitQuizDtoList;
    }


    public long timeCount(String time1, String time2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        return date2.getTime() - date1.getTime();
    }

}
