package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;


@Repository
public interface UserDao extends CrudRepository<User,Integer> {

	public User findByNomUser(String name);
	
	public List<User> findUserByEmailUser(String emailUser);
	
}
