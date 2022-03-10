package tn.esprit.spring.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Role;
import tn.esprit.spring.entity.User;

@Repository
public interface RoleDao extends CrudRepository<Role,Integer>{

		Set<Role> findByRoleId(int roleId);
}
