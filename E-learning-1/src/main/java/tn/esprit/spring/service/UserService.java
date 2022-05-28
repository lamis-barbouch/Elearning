package tn.esprit.spring.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.RoleDao;
import tn.esprit.spring.dao.UserDao;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public User updateClient(int cinUser){
		User client=userDao.findById(cinUser).orElse(null);
		client.setAlerte(1);
		return userDao.save(client);
	}

	
	public User registerNewUser(User user) {
	
		return userDao.save(user);
    }
	
	
	public void initRolesAndUser(){
		Role adminRole=new Role(); 
		adminRole.setRoleId(1);
		adminRole.setRoleNom("Admin");
		adminRole.setRoleDescription("Admin role");
		roleDao.save(adminRole);
		
		Role formateurRole=new Role(); 
		formateurRole.setRoleId(2);
		formateurRole.setRoleNom("Formateur");
		formateurRole.setRoleDescription("Formateur role");
		roleDao.save(formateurRole);
		
		Role apprenantRole=new Role(); 
		apprenantRole.setRoleId(3);
		apprenantRole.setRoleNom("Apprenant");
		apprenantRole.setRoleDescription("Apprenant Role");
		roleDao.save(apprenantRole);
		
		
	
		
		User adminUser=new User();
		adminUser.setCinUser(06425646);
		adminUser.setEmailUser("admin@gmail.com");
		adminUser.setNomUser("admin");
		adminUser.setPrenomUser("admin");
		adminUser.setPasswordUser(getEncodedPassword("admin"));
		
		Set<Role> adminRoles=new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRole(adminRoles);
		userDao.save(adminUser);

		User formateur=new User();
		formateur.setCinUser(1223321);
		formateur.setEmailUser("formateur@gmail.com");
		formateur.setNomUser("formateur");
		formateur.setPrenomUser("formateur");
		formateur.setPasswordUser(getEncodedPassword("admin"));
		
		Set<Role>formateurRoles=new HashSet<>();
		formateurRoles.add(formateurRole);
		formateur.setRole(formateurRoles);
		userDao.save(formateur);
		
		
		User apprenantUser=new User();
		apprenantUser.setCinUser(02101221);
		apprenantUser.setEmailUser("lamis@gmail.com");
		apprenantUser.setNomUser("lamis");
		apprenantUser.setPrenomUser("lamis");
		apprenantUser.setPasswordUser(getEncodedPassword("admin"));
		
		Set<Role> apprenantRoles=new HashSet<>();
		apprenantRoles.add(apprenantRole);
		apprenantUser.setRole(apprenantRoles);
		userDao.save(apprenantUser);
		
	}
	
	public String getEncodedPassword(String password){
	return passwordEncoder.encode(password);}
	
}
