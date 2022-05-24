
package tn.esprit.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService jwtService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.headers().frameOptions().disable();
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers(
                		"/**","/addReclam","/reclamsLists","/repondreclam/{id}",
"/category/all-category","/chat/{roomId}/sendMessage","/chat/{roomId}/addUser",
"/sock","js/mychat.js",
"/chat/{roomId}/leaveUser",
"/chat/{roomId}/raiseHand","/chat","/topic/public","/app/chat.send",
"/viewAllSubject" ,"/addSubject"
,"/updateSubject","/deleteSubject/{id}",
"/autodeleteSubject","/findByIdSubject/{id}",
"/findbytitleSubject/{title}","/displayBestSubjectsByComments",
"/displayBestSubjectByrating",
"/addComment","/deleteComment/{id}","/commentLists","/filtreBadWords/{id_comment}","/chat-app/chat/${newRoomId}",

"/category/","/affecterRatingASubject/{id_rating}/{id_subject}"
,"/commentairesList/{id_subject}",

"/affecterCommentASubject/{id}/{id_subject}",

"/category/{categoryId}",
"/category/update",
"/category/delete/{categoryId}",


"/question/",
"/question",
"/question/delete/{questionId}",
"/question/add/{quizId}",

                		"/quiz/",
                		"/quiz/add/{categoryId}",
                		"/quiz/teacher-quizzes/{username}",
                		"/quiz/delete/{quizId}",
                		
"http://127.0.0.1:8084/sock",
"/chat-app/chat/${newRoomId}",
"/chat-room/${roomId}",
"${topic}/addUser",
"/chat-app/chat/${newRoomId}",
"${topic}/sendMessage",
"/?",
"/topic/public",
"/app/chat.register",
"/app/chat.send",
"/chat-room","/chat-app","/sock",
                		"/quiz/update-type",
                		"/quiz/update-visibility",
                		"/quiz/disable-quiz/{quizId}",
                		"/quiz/schedule",
                		"/quiz/questions/{quizId}",

                		"/quiz/active-quizzes",
                		"/quiz/result/{submitQuizId}",
                		"/quiz/submit/{username}",
                		"/quiz/start/{quizId}",
                		"/quiz/find-quiz/{code}",
                		"/quiz/get-quiz/{quizId}",
                		"/quiz/quiz-participant-result/{quizId}",
                		"/quiz/quiz-participant-result-pdf/{quizId}",
"/category/*","/question/*","/quiz/*","/js/mychat.js"," http://localhost:8084/sock/*","http://localhost:8084/sock/**","http://localhost:8084/sock/?","http://localhost:8084/sock**"
                		,"/file/files","/files","/upload","/download","/api/v1/sms","/getAllNotSeenNotif/{idFormateur}","/seenNotif/{idNotif}","/authenticate","/getAllNotif", "/registerNewUser","/register1","/login","/formateurs","/confirm","/confirm-account**","/formateurs**","/formateurs/{cinUser}","/addFormation","/formationLists","/updateFormation/{id}","/deleteFormation/{id}","/findbyIdFormation/{id}","/findFormationByName/{titre}","/filterformation/{prixFormation}","/upcomingFormations","/displayBestFormationByParticipations","/participerFormation/{idApprenant}/{idFormation}","/passedFormations","/affecterFormateurAFormation/{idFormateur}/{idFormation}","/","/?","/chat","/app","/topic","/chat.register","/topic/public","/chat.send","/topic/public","/css/main.css","/js/main.js","18.jpg","/chat**","/chat/**","/file/download/**","/file/download/*","/*","chat/info/*","/file/upload","/file/download","/file/download/{filename}","download/{filename}").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
    }
} 