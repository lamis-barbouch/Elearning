package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;


@Repository
public interface UserDao extends CrudRepository<User,Integer> {

	public User findByNomUser(String name);
	
	public List<User> findUserByEmailUser(String emailUser);
	
	User findByEmailUserIgnoreCase(String emailUser);
	
	 @Query(value = "select * from user u where u.is_enabled = ?1", nativeQuery = true)
	    User getIsEnabled(int isEnabled);
	 

	 @Query("select u from User u  where u.f = 1")
	 List<User> findAllFormateurs();
	 
}
