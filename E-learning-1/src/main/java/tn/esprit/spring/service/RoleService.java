package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.RoleDao;
import tn.esprit.spring.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	public Role createNewRole(Role role){
	
		return roleDao.save(role);
		
	}

}
