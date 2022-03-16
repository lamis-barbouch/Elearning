package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

public interface IFormateurService {

	public List<User> findAllByRoleNom(String formateur);
	
}
