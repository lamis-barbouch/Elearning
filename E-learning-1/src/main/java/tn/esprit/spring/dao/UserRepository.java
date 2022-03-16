package tn.esprit.spring.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmailUserIgnoreCase(String emailUser);
	
	 @Query(value = "select * from user u where u.is_enabled = ?1", nativeQuery = true)
	    User getIsEnabled(int isEnabled);
	 

	 @Query("select u from User u join Role r where r.roleNom = :roleNom")
	 List<User> findAllByRoleNom(@Param("roleNom")String roleNom);
	 
}
