package tn.esprit.spring.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tn.esprit.spring.dao.ConfirmationTokenRepository;
import tn.esprit.spring.dao.RoleDao;
import tn.esprit.spring.dao.UserRepository;
import tn.esprit.spring.entity.ConfirmationToken;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.EmailSenderService;
import tn.esprit.spring.service.UserService;



@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleDao roleDao;
	
	@PostConstruct
	public void initRolesAndUser(){
		
		userService.initRolesAndUser();
	}
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user){
		return userService.registerNewUser(user);
		
		
	}

	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin(){
		return "this URL is only accessible to admin";
	}
	
	@GetMapping({"/forFormateur"})
	@PreAuthorize("hasRole('Formateur')")
	public String forFormateur(){
		return "this URL is only accessible to formateur";
	}
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


	@PostMapping({"/register1"})
	public User registerUser(@RequestBody User user) {
		
		Set<Role>apprenantRoles=new HashSet<>();
		user.setRole(apprenantRoles);
		
		Set<Role> role=roleDao.findByRoleId(3);
		user.setRole(role);
		User existingUser = userRepository.findByEmailUserIgnoreCase(user.getEmailUser());
		if(existingUser != null) {
			
		} else {
			user.setPasswordUser(encoder.encode(user.getPasswordUser()));
			//user.setPasswordUser(getEncodedPassword(user.getPasswordUser()));
			
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmailUser());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("nairobley@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
			+"http://localhost:8084/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			
			
		}
		
		return userService.registerNewUser(user);
	}
	public String getEncodedPassword(String password){
		return passwordEncoder.encode(password);}
}
