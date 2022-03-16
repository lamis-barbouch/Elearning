package tn.esprit.spring.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Exceptions.ResourceNotFoundException;
import tn.esprit.spring.dao.RoleDao;
import tn.esprit.spring.dao.UserDao;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

@RestController
public class FormateurController {
	
	@Autowired
	private UserDao userrepo;
	@Autowired
	private RoleDao roleDao;
	
	 @GetMapping({"/formateurs"})
	    public List<User> findAllByRoleNom(String formateur) {
	        return userrepo.findAllFormateurs();
	    }
	 
	 @GetMapping({"/formateurs/{cinUser}"})
	    public ResponseEntity<User> getUserById(@PathVariable(value = "cinUser") int cinUser)
	        throws ResourceNotFoundException {
	        User user = userrepo.findById(cinUser)
	          .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + cinUser));
	        return ResponseEntity.ok().body(user);
	    }
	 //work
	 @PostMapping({"/formateurs"})
	    public User createUser(@RequestBody User user) {
		 Set<Role>formateurRoles=new HashSet<>();
			user.setRole(formateurRoles);
			user.setF(1);
			
			Set<Role> role=roleDao.findByRoleId(2);
			user.setRole(role);
			String pwd="formateur";
	       	user.setPasswordUser(pwd);
	        return userrepo.save(user);
	    }
	 //work
	 @PutMapping({"/formateurs/{cinUser}"})
	    public ResponseEntity<User> updateUser(@PathVariable(value = "cinUser") int cinUser,
	         @RequestBody User userDetails) throws ResourceNotFoundException {
	       	User user = userrepo.findById(cinUser)
	        .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + cinUser));
	       	user.setEmailUser(userDetails.getEmailUser());
	       	user.setNomUser(userDetails.getNomUser());
	       	user.setPrenomUser(userDetails.getPrenomUser());
	       	user.setSpecialitFormateur(userDetails.getSpecialitFormateur());
	       	user.setTelephoneUser(userDetails.getTelephoneUser());
	       	
	      
	        final User updatedUser = userrepo.save(user);
	        return ResponseEntity.ok(updatedUser);
	    }
	 	//work
	    @DeleteMapping("/formateurs/{cinUser}")
	    @ResponseBody
	    public Map<String, Boolean> deleteFormateur(@PathVariable("cinUser") int cinUser)
	         throws ResourceNotFoundException {
	    	User user = userrepo.findById(cinUser)
	       .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + cinUser));

	      userrepo.delete(user);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	
}
