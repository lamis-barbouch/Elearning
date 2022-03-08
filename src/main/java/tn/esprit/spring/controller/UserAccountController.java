package tn.esprit.spring.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tn.esprit.spring.dao.ConfirmationTokenRepository;
import tn.esprit.spring.dao.UserRepository;
import tn.esprit.spring.entity.ConfirmationToken;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.service.EmailSenderService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
public class UserAccountController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	 
	
	
	// https://stackabuse.com/password-encoding-with-spring-security/
	// to encode our password
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	// Registration
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, User user) {
		
		User existingUser = userRepository.findByEmailUserIgnoreCase(user.getEmailUser());
		if(existingUser != null) {
			modelAndView.addObject("message","This email already exists!");
			modelAndView.setViewName("error");
		} else {
			user.setPasswordUser(encoder.encode(user.getPasswordUser()));
			userRepository.save(user);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(user);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmailUser());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("nairobley@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
			+"http://localhost:8119/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			
			modelAndView.addObject("emailId", user.getEmailUser());
			
			modelAndView.setViewName("successfulRegisteration");
		}
		
		return modelAndView;
	}

	// Confirm registration
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null )
		{
			
			User user = userRepository.findByEmailUserIgnoreCase(token.getUser().getEmailUser());
			user.setIsEnabled(1);
			userRepository.save(user);
			modelAndView.setViewName("accountVerified");
		}
		else
		{
			modelAndView.addObject("message","The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}	

	// Login
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView displayLogin(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginUser(ModelAndView modelAndView, User user) {
		
		User existingUser = userRepository.findByEmailUserIgnoreCase(user.getEmailUser());
		/**
		 *  Check registered email or not
		 */
		if (existingUser == null) {
			modelAndView.addObject("message", "The email not registered");
			modelAndView.setViewName("login");
			
		}
		/**
		 * Check email registered ,verified and password invalid
		 */
		
		if (existingUser != null ) {
			if (existingUser.getIsEnabled()==0) {
		modelAndView.addObject("message", "The email not verified");
		modelAndView.setViewName("login");
	}
			
			else if (encoder.matches(user.getPasswordUser(), existingUser.getPasswordUser())){
				modelAndView.addObject("message", "Successfully logged in!");
				modelAndView.setViewName("successLogin");
				
			}
			else {
				modelAndView.addObject("message", "Incorrect password. Try again");
				modelAndView.setViewName("login");
			}
		}
			
		return modelAndView;
	}
	
	

}
