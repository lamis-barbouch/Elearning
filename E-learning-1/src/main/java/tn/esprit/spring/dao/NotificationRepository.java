package tn.esprit.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.User;

public interface NotificationRepository extends CrudRepository<Notification,Integer>{
	
	 @Query("select u from User u  where u.f = 1")
	 List<User> findAllFormateurs();

	 
}
